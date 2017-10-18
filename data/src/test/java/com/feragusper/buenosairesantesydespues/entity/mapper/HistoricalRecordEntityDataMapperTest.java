package com.feragusper.buenosairesantesydespues.entity.mapper;

import com.feragusper.buenosairesantesydespues.domain.model.HistoricalRecord;
import com.feragusper.buenosairesantesydespues.domain.model.HistoricalRecordListPage;
import com.feragusper.buenosairesantesydespues.entity.AttachmentEntity;
import com.feragusper.buenosairesantesydespues.entity.CustomFieldsEntity;
import com.feragusper.buenosairesantesydespues.entity.HistoricalRecordEntity;
import com.feragusper.buenosairesantesydespues.entity.HistoricalRecordListPageEntity;
import com.feragusper.buenosairesantesydespues.entity.ImageEntity;
import com.feragusper.buenosairesantesydespues.entity.ImagesEntity;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Fernando.Perez
 * @since 1.4
 * <p>
 * Test Cases for {@link HistoricalRecordEntityDataMapper}
 */
public class HistoricalRecordEntityDataMapperTest {

    private static final int HISTORICAL_RECORD_LIST_PAGE_ENTITY_PAGES = 20;
    private static final int HISTORICAL_RECORD_LIST_PAGE_ENTITY_COUNT_TOTAL = 200;
    private static final String HISTORICAL_RECORD_TITLE = "title";
    private static final String CUSTOM_FIELDS_ENTITY_CREDITS_NOW = "credits_now";
    private static final String CUSTOM_FIELDS_ENTITY_CREDITS_BEFORE = "credits_before";
    private static final String CUSTOM_FIELDS_ENTITY_DESCRIPTION = "description";
    private static final String CUSTOM_FIELDS_ENTITY_YEAR_BEFORE = "year_before";
    private static final String CUSTOM_FIELDS_ENTITY_ADDRESS = "address";
    private static final String CUSTOM_FIELDS_ENTITY_NEIGHBORHOOD = "neighborhood";
    private static final Long CUSTOM_FIELDS_ENTITY_GEO_LAT = 122345235345L;
    private static final Long CUSTOM_FIELDS_ENTITY_GEO_LON = 122345235345L;
    private static final String IMAGES_ENTITY_FULL_URL = "full";
    private static final String IMAGES_ENTITY_THUMBNAIL_URL = "thumbnail";
    //region Properties
    private HistoricalRecordEntityDataMapper historicalRecordEntityDataMapper;
    //endregion

    //region Public Implementation
    @Before
    public void setUp() {
        historicalRecordEntityDataMapper = new HistoricalRecordEntityDataMapper();
    }

    @Test
    public void testTransformHistoricalRecordListPageEntity() {
        HistoricalRecordListPageEntity historicalRecordListPageEntity = makeHistoricalRecordListPageEntity();
        final HistoricalRecordListPage historicalRecordListPage = historicalRecordEntityDataMapper.transform(historicalRecordListPageEntity);
        assertThat(historicalRecordListPage, is(instanceOf(HistoricalRecordListPage.class)));
        assertThat(historicalRecordListPage.getPages(), is(HISTORICAL_RECORD_LIST_PAGE_ENTITY_PAGES));
        assertThat(historicalRecordListPage.getCountTotal(), is(HISTORICAL_RECORD_LIST_PAGE_ENTITY_COUNT_TOTAL));
    }

    @Test
    public void testTransformHistoricalRecordEntity() {
        int count = 1;
        HistoricalRecordEntity historicalRecordEntity = makeHistoricalRecordEntity(count);

        final HistoricalRecord historicalRecord = historicalRecordEntityDataMapper.transform(historicalRecordEntity);
        assertThat(historicalRecord, is(instanceOf(HistoricalRecord.class)));
        assertThat(historicalRecord.getTitle(), is(HISTORICAL_RECORD_TITLE + count));
        assertThat(historicalRecord.getAddress(), is(CUSTOM_FIELDS_ENTITY_ADDRESS + count));
        assertThat(historicalRecord.getCredits(), is(CUSTOM_FIELDS_ENTITY_CREDITS_NOW + count));
        assertThat(historicalRecord.getDescription(), is(CUSTOM_FIELDS_ENTITY_DESCRIPTION + count));
        assertThat(historicalRecord.getHistoricalRecordId(), is(String.valueOf(count)));
        assertThat(historicalRecord.getImageURLAfter(), is(IMAGES_ENTITY_FULL_URL));
        assertThat(historicalRecord.getImageURLBefore(), is(IMAGES_ENTITY_FULL_URL));
        assertThat(historicalRecord.getThumbnail(), is(IMAGES_ENTITY_THUMBNAIL_URL));
        assertThat(historicalRecord.getNeighborhood(), is(CUSTOM_FIELDS_ENTITY_NEIGHBORHOOD + count));
        assertThat(historicalRecord.getYear(), is(CUSTOM_FIELDS_ENTITY_YEAR_BEFORE + count));
    }

    private HistoricalRecordListPageEntity makeHistoricalRecordListPageEntity() {
        return new HistoricalRecordListPageEntity(
                HISTORICAL_RECORD_LIST_PAGE_ENTITY_PAGES,
                HISTORICAL_RECORD_LIST_PAGE_ENTITY_COUNT_TOTAL,
                new ArrayList<>()
        );
    }

    private HistoricalRecordEntity makeHistoricalRecordEntity(Integer count) {
        CustomFieldsEntity customFieldsEntity =
                new CustomFieldsEntity(
                        CUSTOM_FIELDS_ENTITY_CREDITS_NOW + count,
                        CUSTOM_FIELDS_ENTITY_CREDITS_BEFORE + count,
                        CUSTOM_FIELDS_ENTITY_DESCRIPTION + count,
                        CUSTOM_FIELDS_ENTITY_YEAR_BEFORE + count,
                        CUSTOM_FIELDS_ENTITY_ADDRESS + count,
                        CUSTOM_FIELDS_ENTITY_NEIGHBORHOOD + count,
                        CUSTOM_FIELDS_ENTITY_GEO_LAT + "," + CUSTOM_FIELDS_ENTITY_GEO_LON);

        List<AttachmentEntity> attachmentEntities = new ArrayList<>();

        attachmentEntities.add(makeAttachmentEntity(AttachmentEntity.KEY_AFTER));
        attachmentEntities.add(makeAttachmentEntity(AttachmentEntity.KEY_BEFORE));

        return new HistoricalRecordEntity(count.longValue(),
                HISTORICAL_RECORD_TITLE + count,
                customFieldsEntity,
                attachmentEntities);
    }

    private AttachmentEntity makeAttachmentEntity(String key) {
        return new AttachmentEntity(
                key,
                new ImagesEntity(new ImageEntity(IMAGES_ENTITY_FULL_URL), new ImageEntity(IMAGES_ENTITY_THUMBNAIL_URL)));
    }

    //endregion
}
