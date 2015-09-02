package com.feragusper.buenosairesantesydespues.entity.mapper;

import com.feragusper.buenosairesantesydespues.HistoricalRecordEntity;
import com.feragusper.buenosairesantesydespues.domain.HistoricalRecord;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
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

    private static final String BASE_URL = "http://bsasantesydespues.com.ar";
    private static final String IMAGE_URL = BASE_URL + "/fotos/";
    private static final String BEFORE_IMAGE = "/antes.jpg";
    private static final String AFTER_IMAGE = "/ahora.jpg";
    private static final String THUMNAIL_IMAGE = "/th.jpg";

    @Inject
    public HistoricalRecordEntityDataMapper() {
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

            historicalRecord.setDescription(historicalRecordEntity.getFolder());
            historicalRecord.setTitle(historicalRecordEntity.getTitle());
            historicalRecord.setDescription(historicalRecordEntity.getDescription());

            final String[] latLng = historicalRecordEntity.getGeo().split(",");

            historicalRecord.setLat(Double.valueOf(latLng[0]));
            historicalRecord.setLng(Double.valueOf(latLng[1]));

            historicalRecord.setYear(historicalRecordEntity.getYear());
            historicalRecord.setNeighborhood(historicalRecordEntity.getNeighborhood());
            historicalRecord.setImageURLBefore(IMAGE_URL + historicalRecordEntity.getFolder() + BEFORE_IMAGE);
            historicalRecord.setImageURLAfter(IMAGE_URL + historicalRecordEntity.getFolder() + AFTER_IMAGE);
            historicalRecord.setThumbnail(IMAGE_URL + historicalRecordEntity.getFolder() + THUMNAIL_IMAGE);
            historicalRecord.setAddress(historicalRecordEntity.getAddress());
            historicalRecord.setShareURL(BASE_URL + "/#" + historicalRecordEntity.getId());
        }

        return historicalRecord;
    }

    /**
     * Transform a List of {@link HistoricalRecordEntity} into a Collection of {@link HistoricalRecord}.
     *
     * @param historicalRecordEntityCollection Object Collection to be transformed.
     * @return {@link HistoricalRecord} if valid {@link HistoricalRecordEntity} otherwise null.
     */
    public List<HistoricalRecord> transform(Collection<HistoricalRecordEntity> historicalRecordEntityCollection) {
        List<HistoricalRecord> historicalRecordList = new ArrayList<>();
        HistoricalRecord historicalRecord;
        for (HistoricalRecordEntity historicalRecordEntity : historicalRecordEntityCollection) {
            historicalRecord = transform(historicalRecordEntity);
            if (historicalRecord != null) {
                historicalRecordList.add(historicalRecord);
            }
        }

        Collections.reverse(historicalRecordList);

        return historicalRecordList;
    }
}
