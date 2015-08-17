package com.feragusper.buenosairesantesydespues;

import com.google.gson.annotations.SerializedName;

/**
 * @author Fernando.Perez
 * @since 0.1
 */
public class SpreadsheetString {

    @SerializedName("$t")
    private String value;

    @Override
    public String toString() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
