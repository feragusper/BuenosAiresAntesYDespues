package com.feragusper.buenosairesantesydespues.mapper;

import com.feragusper.buenosairesantesydespues.di.PerActivity;
import com.feragusper.buenosairesantesydespues.domain.HistoricalRecord;
import com.feragusper.buenosairesantesydespues.model.HistoricalRecordModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

/**
 * @author Fernando.Perez
 * @since 0.1
 * <p>
 * Mapper class used to transform {@link com.feragusper.buenosairesantesydespues.domain.HistoricalRecord} (in the domain layer) to {@link HistoricalRecordModel} in the
 * presentation layer.
 */
@PerActivity
public class HistoricalRecordModelDataMapper {

    @Inject
    public HistoricalRecordModelDataMapper() {
    }

    /**
     * Transform a {@link HistoricalRecord} into an {@link HistoricalRecordModel}.
     *
     * @param historicalRecord Object to be transformed.
     * @return {@link HistoricalRecordModel}.
     */
    public HistoricalRecordModel transform(HistoricalRecord historicalRecord) {
        if (historicalRecord == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        HistoricalRecordModel historicalRecordModel = new HistoricalRecordModel(historicalRecord.getHistoricalRecordId());
        historicalRecordModel.setTitle(historicalRecord.getTitle());
        historicalRecordModel.setDescription(historicalRecord.getDescription());
        historicalRecordModel.setCredits(historicalRecord.getCredits());
        historicalRecordModel.setLat(historicalRecord.getLat());
        historicalRecordModel.setLng(historicalRecord.getLng());
        historicalRecordModel.setYear(historicalRecord.getYear());
        historicalRecordModel.setNeighborhood(historicalRecord.getNeighborhood());
        historicalRecordModel.setAddress(historicalRecord.getAddress());
        historicalRecordModel.setImageURLBefore(historicalRecord.getImageURLBefore());
        historicalRecordModel.setImageURLAfter(historicalRecord.getImageURLAfter());
        historicalRecordModel.setThumbnail(historicalRecord.getThumbnail());
        historicalRecordModel.setShareURL(historicalRecord.getShareURL());

        return historicalRecordModel;
    }

    /**
     * Transform a Collection of {@link HistoricalRecord} into a Collection of {@link HistoricalRecordModel}.
     *
     * @param historicalRecordsCollection Objects to be transformed.
     * @return List of {@link HistoricalRecordModel}.
     */
    public Collection<HistoricalRecordModel> transform(Collection<HistoricalRecord> historicalRecordsCollection) {
        Collection<HistoricalRecordModel> historicalRecordModelsCollection;

        if (historicalRecordsCollection != null && !historicalRecordsCollection.isEmpty()) {
            historicalRecordModelsCollection = new ArrayList<>();
            for (HistoricalRecord historicalRecord : historicalRecordsCollection) {
                historicalRecordModelsCollection.add(transform(historicalRecord));
            }
        } else {
            historicalRecordModelsCollection = Collections.emptyList();
        }

        return historicalRecordModelsCollection;
    }
}
