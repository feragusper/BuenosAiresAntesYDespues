package com.feragusper.buenosairesantesydespues.cache;

import android.content.Context;

import com.feragusper.buenosairesantesydespues.cache.serializer.JsonSerializer;
import com.feragusper.buenosairesantesydespues.domain.executor.ThreadExecutor;
import com.feragusper.buenosairesantesydespues.entity.HistoricalRecordEntity;
import com.feragusper.buenosairesantesydespues.exception.HistoricalRecordNotFoundException;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.Subscriber;

/**
 * @author Fernando.Perez
 * @since 0.1
 * <p>
 * {@link HistoricalRecordCache} implementation.
 */
@Singleton
public class HistoricalRecordCacheImpl implements HistoricalRecordCache {

    private static final String SETTINGS_FILE_NAME = "com.feragusper.buenosairesantesydespues.SETTINGS";
    private static final String SETTINGS_KEY_LAST_CACHE_UPDATE = "last_cache_update";

    private static final String DEFAULT_FILE_NAME = "historicalrecord_";
    private static final long EXPIRATION_TIME = 60 * 10 * 1000;

    private final Context context;
    private final File cacheDir;
    private final JsonSerializer serializer;
    private final FileManager fileManager;
    private final ThreadExecutor threadExecutor;

    /**
     * Constructor of the class {@link HistoricalRecordCacheImpl}.
     *
     * @param context             A
     * @param userCacheSerializer {@link JsonSerializer} for object serialization.
     * @param fileManager         {@link FileManager} for saving serialized objects to the file system.
     */
    @Inject
    public HistoricalRecordCacheImpl(Context context, JsonSerializer userCacheSerializer,
                                     FileManager fileManager, ThreadExecutor executor) {
        if (context == null || userCacheSerializer == null || fileManager == null || executor == null) {
            throw new IllegalArgumentException("Invalid null parameter");
        }
        this.context = context.getApplicationContext();
        this.cacheDir = this.context.getCacheDir();
        this.serializer = userCacheSerializer;
        this.fileManager = fileManager;
        this.threadExecutor = executor;
    }

    /**
     * {@link Runnable} class for writing to disk.
     */
    private static class CacheWriter implements Runnable {
        private final FileManager fileManager;
        private final File fileToWrite;
        private final String fileContent;

        CacheWriter(FileManager fileManager, File fileToWrite, String fileContent) {
            this.fileManager = fileManager;
            this.fileToWrite = fileToWrite;
            this.fileContent = fileContent;
        }

        @Override
        public void run() {
            this.fileManager.writeToFile(fileToWrite, fileContent);
        }
    }

    /**
     * {@link Runnable} class for evicting all the cached files
     */
    private static class CacheEvictor implements Runnable {
        private final FileManager fileManager;
        private final File cacheDir;

        CacheEvictor(FileManager fileManager, File cacheDir) {
            this.fileManager = fileManager;
            this.cacheDir = cacheDir;
        }

        @Override
        public void run() {
            this.fileManager.clearDirectory(this.cacheDir);
        }
    }

    @Override
    public synchronized Observable<HistoricalRecordEntity> get(final String historicalRecordId) {
        return Observable.create(new Observable.OnSubscribe<HistoricalRecordEntity>() {
            @Override
            public void call(Subscriber<? super HistoricalRecordEntity> subscriber) {
                File historicalRecordEntityFile = HistoricalRecordCacheImpl.this.buildFile(historicalRecordId);
                String fileContent = HistoricalRecordCacheImpl.this.fileManager.readFileContent(historicalRecordEntityFile);
                HistoricalRecordEntity historicalRecordEntity = HistoricalRecordCacheImpl.this.serializer.deserialize(fileContent);

                if (historicalRecordEntity != null) {
                    subscriber.onNext(historicalRecordEntity);
                    subscriber.onCompleted();
                } else {
                    subscriber.onError(new HistoricalRecordNotFoundException());
                }
            }
        });
    }

    @Override
    public synchronized void put(HistoricalRecordEntity historicalRecordEntity) {
        if (historicalRecordEntity != null) {
            File historicalRecordEntitiyFile = this.buildFile(historicalRecordEntity.getHistoricalRecordId());
            if (!isCached(historicalRecordEntity.getHistoricalRecordId())) {
                String jsonString = this.serializer.serialize(historicalRecordEntity);
                this.executeAsynchronously(new CacheWriter(this.fileManager, historicalRecordEntitiyFile,
                        jsonString));
                setLastCacheUpdateTimeMillis();
            }
        }
    }

    @Override
    public boolean isCached(String historicalRecordId) {
        File historicalRecordEntitiyFile = this.buildFile(historicalRecordId);
        return this.fileManager.exists(historicalRecordEntitiyFile);
    }

    @Override
    public boolean isExpired() {
        long currentTime = System.currentTimeMillis();
        long lastUpdateTime = this.getLastCacheUpdateTimeMillis();

        boolean expired = ((currentTime - lastUpdateTime) > EXPIRATION_TIME);

        if (expired) {
            this.evictAll();
        }

        return expired;
    }

    @Override
    public synchronized void evictAll() {
        this.executeAsynchronously(new CacheEvictor(this.fileManager, this.cacheDir));
    }

    /**
     * Get in millis, the last time the cache was accessed.
     */
    private long getLastCacheUpdateTimeMillis() {
        return this.fileManager.getFromPreferences(this.context, SETTINGS_FILE_NAME,
                SETTINGS_KEY_LAST_CACHE_UPDATE);
    }

    /**
     * Executes a {@link Runnable} in another Thread.
     *
     * @param runnable {@link Runnable} to execute
     */
    private void executeAsynchronously(Runnable runnable) {
        this.threadExecutor.execute(runnable);
    }

    /**
     * Set in millis, the last time the cache was accessed.
     */
    private void setLastCacheUpdateTimeMillis() {
        long currentMillis = System.currentTimeMillis();
        this.fileManager.writeToPreferences(this.context, SETTINGS_FILE_NAME,
                SETTINGS_KEY_LAST_CACHE_UPDATE, currentMillis);
    }

    /**
     * Build a file, used to be inserted in the disk cache.
     *
     * @param historicalRecordId The id historical record to build the file.
     * @return A valid file.
     */
    private File buildFile(String historicalRecordId) {
        return new File(this.cacheDir.getPath() + File.separator + DEFAULT_FILE_NAME + historicalRecordId);
    }
}
