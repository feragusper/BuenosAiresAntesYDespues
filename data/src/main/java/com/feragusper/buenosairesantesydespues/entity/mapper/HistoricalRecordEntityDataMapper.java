package com.feragusper.buenosairesantesydespues.entity.mapper;

import android.util.Log;

import com.feragusper.buenosairesantesydespues.domain.model.HistoricalRecord;
import com.feragusper.buenosairesantesydespues.domain.model.HistoricalRecordListPage;
import com.feragusper.buenosairesantesydespues.entity.HistoricalRecordEntity;
import com.feragusper.buenosairesantesydespues.entity.HistoricalRecordListPageEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Fernando.Perez
 * @since 0.1
 * <p>
 * Mapper class used to transform {@link HistoricalRecordEntity} (in the data layer) to {@link HistoricalRecord} in the
 * domain layer.
 */
@Singleton
public class HistoricalRecordEntityDataMapper {

    @Inject
    HistoricalRecordEntityDataMapper() {
    }

    /**
     * Transform a List of {@link HistoricalRecordEntity} into a Collection of {@link HistoricalRecord}.
     *
     * @param historicalRecordListPageEntity Object Collection to be transformed.
     * @return {@link HistoricalRecord} if valid {@link HistoricalRecordEntity} otherwise null.
     */
    public HistoricalRecordListPage transform(HistoricalRecordListPageEntity historicalRecordListPageEntity) {
        HistoricalRecordListPage historicalRecordListPage = new HistoricalRecordListPage();

        HistoricalRecord historicalRecord = null;
        historicalRecordListPage.setCountTotal(historicalRecordListPageEntity.getCountTotal());
        historicalRecordListPage.setPages(historicalRecordListPageEntity.getPages());
        for (HistoricalRecordEntity historicalRecordEntity : historicalRecordListPageEntity.getHistoricalRecordList()) {
            try {
                historicalRecord = transform(historicalRecordEntity);
            } catch (Exception e) {
                if (historicalRecord != null) {
                    Log.e(this.getClass().getSimpleName(), "There was an error trying to transform Historical Record with id " + (historicalRecord.getHistoricalRecordId()), e);
                } else {
                    Log.e(this.getClass().getSimpleName(), "There was an error trying to transform some Historical Record. Can't do a transform a null reference ", e);
                }
            }
            if (historicalRecord != null) {
                historicalRecordListPage.addHistoricalRecord(historicalRecord);
            }
        }

        return historicalRecordListPage;
    }

    /**
     * Transform a {@link HistoricalRecordEntity} into an {@link HistoricalRecord}.
     *
     * @param historicalRecordEntity Object to be transformed.
     * @return {@link HistoricalRecord} if valid {@link HistoricalRecordEntity} otherwise null.
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
}
