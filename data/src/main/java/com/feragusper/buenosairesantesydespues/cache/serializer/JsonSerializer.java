package com.feragusper.buenosairesantesydespues.cache.serializer;

import com.feragusper.buenosairesantesydespues.entity.HistoricalRecordEntity;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Fernando.Perez
 * @since 0.1
 * <p>
 * Class user as Serializer/Deserializer for user entities.
 */
@Singleton
public class JsonSerializer {

    private final Gson gson = new Gson();

    @SuppressWarnings("WeakerAccess")
    @Inject
    public JsonSerializer() {
    }

    /**
     * Serialize an object to Json.
     *
     * @param historicalRecordEntity {@link HistoricalRecordEntity} to serialize.
     */
    public String serialize(HistoricalRecordEntity historicalRecordEntity) {
        return gson.toJson(historicalRecordEntity, HistoricalRecordEntity.class);
    }

    /**
     * Deserialize a json representation of an object.
     *
     * @param jsonString A json string to deserialize.
     * @return {@link HistoricalRecordEntity}
     */
    public HistoricalRecordEntity deserialize(String jsonString) {
        return gson.fromJson(jsonString, HistoricalRecordEntity.class);
    }
}
