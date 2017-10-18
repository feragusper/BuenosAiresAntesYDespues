package com.feragusper.buenosairesantesydespues.entity;

import com.feragusper.buenosairesantesydespues.domain.model.HistoricalRecord;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Fernando.Perez
 * @since 1.4
 * <p/>
 * HistoricalRecordListPageEntity used in the data layer.
 */
public class HistoricalRecordListPageEntity {
    @SerializedName("posts")
    private List<HistoricalRecordEntity> historicalRecordList;
    @SerializedName("count_total")
    private int countTotal;
    @SerializedName("pages")
    private int pages;

    public HistoricalRecordListPageEntity(int pages, int countTotal, List<HistoricalRecordEntity> historicalRecordList) {
        this.pages = pages;
        this.countTotal = countTotal;
        this.historicalRecordList = historicalRecordList;
    }

    public List<HistoricalRecordEntity> getHistoricalRecordList() {
        return historicalRecordList;
    }

    public int getCountTotal() {
        return countTotal;
    }

    public int getPages() {
        return pages;
    }

}