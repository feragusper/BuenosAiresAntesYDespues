package com.feragusper.buenosairesantesydespues.entity.mapper;

import com.feragusper.buenosairesantesydespues.HistoricalRecordEntity;
import com.feragusper.buenosairesantesydespues.net.RestApi;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

/**
 * @author Fernando.Perez
 * @since 0.1
 * <p/>
 * Class used to transform from Strings representing json to valid objects.
 */
public class HistoricalRecordEntityJsonMapper {

    private final Gson gson;

    @Inject
    public HistoricalRecordEntityJsonMapper() {
        this.gson = new Gson();
    }

    /**
     * Transform from valid json string to {@link HistoricalRecordEntity}.
     *
     * @param historicalRecordJsonResponse A json representing a user profile.
     * @return {@link HistoricalRecordEntity}.
     * @throws com.google.gson.JsonSyntaxException if the json string is not a valid json structure.
     */
    public HistoricalRecordEntity transformHistoricalRecordEntity(String historicalRecordJsonResponse) throws JsonSyntaxException {
        try {
            Type historicalRecordEntityType = new TypeToken<HistoricalRecordEntity>() {
            }.getType();
            HistoricalRecordEntity historicalRecordEntity = this.gson.fromJson(historicalRecordJsonResponse, historicalRecordEntityType);

            return historicalRecordEntity;
        } catch (JsonSyntaxException jsonException) {
            throw jsonException;
        }
    }

    /**
     * Transform from valid json string to List of {@link HistoricalRecordEntity}.
     *
     * @param historicalRecordListJsonResponse A json representing a collection of users.
     * @return List of {@link HistoricalRecordEntity}.
     * @throws com.google.gson.JsonSyntaxException if the json string is not a valid json structure.
     */
    public List<HistoricalRecordEntity> transformUserEntityCollection(String historicalRecordListJsonResponse) throws JsonSyntaxException {

        List<HistoricalRecordEntity> historicalRecordEntityCollection;
        try {
            Type listOfHistoricalRecordEntityType = new TypeToken<List<HistoricalRecordEntity>>() {}.getType();
            historicalRecordEntityCollection = this.gson.fromJson(historicalRecordListJsonResponse, listOfHistoricalRecordEntityType);

            return historicalRecordEntityCollection;
        } catch (JsonSyntaxException jsonException) {
            throw jsonException;
        }
    }
}
