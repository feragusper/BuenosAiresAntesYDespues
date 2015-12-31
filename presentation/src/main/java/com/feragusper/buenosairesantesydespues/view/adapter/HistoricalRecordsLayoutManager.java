package com.feragusper.buenosairesantesydespues.view.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;

/**
 * @author Fernando.Perez
 * @since 0.1
 * <p>
 * Layout manager to position items inside a {@link android.support.v7.widget.RecyclerView}.
 */
public class HistoricalRecordsLayoutManager extends GridLayoutManager {
    public HistoricalRecordsLayoutManager(Context context) {
        super(context, 2);
    }

}
