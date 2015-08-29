package com.feragusper.buenosairesantesydespues.navigation;

import android.content.Context;
import android.content.Intent;

import com.feragusper.buenosairesantesydespues.view.activity.HistoricalRecordDetailsActivity;
import com.feragusper.buenosairesantesydespues.view.activity.HistoricalRecordListActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Fernando.Perez
 * @since 0.1
 * <p>
 * Class used to navigate through the application.
 */
@Singleton
public class Navigator {

    @Inject
    public void Navigator() {
        //empty
    }

    /**
     * Goes to the user list screen.
     *
     * @param context A Context needed to open the destiny activity.
     */
    public void navigateToHistoricalRecordList(Context context) {
        if (context != null) {
            Intent intentToLaunch = HistoricalRecordListActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }

    /**
     * Goes to the user details screen.
     *
     * @param context            A Context needed to open the destiny activity.
     * @param historicalRecordId
     */
    public void navigateToHistoricalRecordDetails(Context context, String historicalRecordId) {
        if (context != null) {
            Intent intentToLaunch = HistoricalRecordDetailsActivity.getCallingIntent(context, historicalRecordId);
            context.startActivity(intentToLaunch);
        }
    }
}
