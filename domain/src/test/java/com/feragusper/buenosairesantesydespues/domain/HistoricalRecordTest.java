package com.feragusper.buenosairesantesydespues.domain;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class HistoricalRecordTest {

    private static final int FAKE_USER_ID = 8;

    private HistoricalRecord historicalRecord;

    @Before
    public void setUp() {
        historicalRecord = new HistoricalRecord(FAKE_USER_ID);
    }

    @Test
    public void testUserConstructorHappyCase() {
        int userId = historicalRecord.getHistoricalRecordId();

        assertThat(userId, is(FAKE_USER_ID));
    }
}
