package com.feragusper.buenosairesantesydespues.entity.mapper;

import android.util.Log;

import com.feragusper.buenosairesantesydespues.domain.HistoricalRecord;
import com.feragusper.buenosairesantesydespues.entity.HistoricalRecordEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Fernando.Perez
 * @since 0.1
 * <p>
 * Mapper class used to transform {@link SpreadsheetHistoricalRecordEntity} (in the data layer) to {@link HistoricalRecord} in the
 * domain layer.
 */
@Singleton
public class HistoricalRecordEntityDataMapper {

    @Inject
    public HistoricalRecordEntityDataMapper() {
    }

    /**
     * Transform a {@link SpreadsheetHistoricalRecordEntity} into an {@link HistoricalRecord}.
     *
     * @param historicalRecordEntity Object to be transformed.
     * @return {@link HistoricalRecord} if valid {@link SpreadsheetHistoricalRecordEntity} otherwise null.
     */
    public HistoricalRecord transform(HistoricalRecordEntity historicalRecordEntity) {
        HistoricalRecord historicalRecord = null;
        if (historicalRecordEntity != null) {
            final String[] historicalRecordEntityIdParts = historicalRecordEntity.getHistoricalRecordId().split("/");
            historicalRecord = new HistoricalRecord(historicalRecordEntityIdParts[historicalRecordEntityIdParts.length - 1]);

            historicalRecord.setTitle(historicalRecordEntity.getTitle());
            historicalRecord.setDescription(historicalRecordEntity.getDescription());
            historicalRecord.setCredits(historicalRecordEntity.getCreditsNow().replace(" - ", "\n"));

            final String[] latLng = historicalRecordEntity.getGeo().split(",");

            historicalRecord.setLat(Double.valueOf(latLng[0]));
            historicalRecord.setLng(Double.valueOf(latLng[1]));

            historicalRecord.setYear(historicalRecordEntity.getYearBefore());
            historicalRecord.setNeighborhood(historicalRecordEntity.getNeighborhood());
            historicalRecord.setImageURLBefore(historicalRecordEntity.getImageURLBefore());
            historicalRecord.setImageURLAfter(historicalRecordEntity.getImageURLAfter());
            historicalRecord.setThumbnail(historicalRecordEntity.getImageURLThumbnail());
            historicalRecord.setAddress(historicalRecordEntity.getAddress());
            historicalRecord.setShareURL(historicalRecordEntity.getShareURL());
        }

        return historicalRecord;
    }

    /**
     * Transform a List of {@link SpreadsheetHistoricalRecordEntity} into a Collection of {@link HistoricalRecord}.
     *
     * @param historicalRecordEntityCollection Object Collection to be transformed.
     * @return {@link HistoricalRecord} if valid {@link SpreadsheetHistoricalRecordEntity} otherwise null.
     */
    public List<HistoricalRecord> transform(Collection<HistoricalRecordEntity> historicalRecordEntityCollection) {
        List<HistoricalRecord> historicalRecordList = new ArrayList<>();

        HistoricalRecord historicalRecord = null;
        for (HistoricalRecordEntity historicalRecordEntity : historicalRecordEntityCollection) {
            try {
                historicalRecord = transform(historicalRecordEntity);
            } catch (Exception e) {
                Log.e(this.getClass().getSimpleName(), "There was an error trying to transform Historical Record with id " + historicalRecord.getHistoricalRecordId(), e);
            }
            if (historicalRecord != null) {
                historicalRecordList.add(historicalRecord);
            }
        }

        Collections.reverse(historicalRecordList);


        return historicalRecordList;
    }
}
