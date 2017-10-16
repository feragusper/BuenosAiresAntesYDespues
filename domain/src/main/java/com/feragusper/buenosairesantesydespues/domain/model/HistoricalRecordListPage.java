package com.feragusper.buenosairesantesydespues.domain.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Fernando.Perez
 * @since 0.1
 * <p>
 * Class that represents a HistoricalRecordListPage in the domain layer.
 */
public class HistoricalRecordListPage {
    private List<HistoricalRecord> historicalRecordList = new ArrayList<>();
    private int countTotal;
    private int pages;

    public List<HistoricalRecord> getHistoricalRecordList() {
        return historicalRecordList;
    }

    public void addHistoricalRecord(HistoricalRecord historicalRecord) {
        historicalRecordList.add(historicalRecord);
    }

    public void setCountTotal(int countTotal) {
        this.countTotal = countTotal;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getCountTotal() {
        return countTotal;
    }
}
