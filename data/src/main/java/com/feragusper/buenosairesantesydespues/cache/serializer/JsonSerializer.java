package com.feragusper.buenosairesantesydespues.cache.serializer;

import com.feragusper.buenosairesantesydespues.entity.HistoricalRecordEntity;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Fernando.Perez
 * @since 0.1
 *
 * Class user as Serializer/Deserializer for user entities.
 */
@Singleton
public class JsonSerializer {

    private final Gson gson = new Gson();

    @Inject
    public JsonSerializer() {
    }

    /**
     * Serialize an object to Json.
     *
     * @param historicalRecordEntity {@link HistoricalRecordEntity} to serialize.
     */
    public String serialize(HistoricalRecordEntity historicalRecordEntity) {
        String jsonString = gson.toJson(historicalRecordEntity, HistoricalRecordEntity.class);
        return jsonString;
    }

    /**
     * Deserialize a json representation of an object.
     *
     * @param jsonString A json string to deserialize.
     * @return {@link HistoricalRecordEntity}
     */
    public HistoricalRecordEntity deserialize(String jsonString) {
        HistoricalRecordEntity historicalRecordEntity = gson.fromJson(jsonString, HistoricalRecordEntity.class);
        return historicalRecordEntity;
    }
}
