package com.feragusper.buenosairesantesydespues.navigation;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

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
     * @param historicalRecordId The id of the historical record
     */
    public void navigateToHistoricalRecordDetails(Context context, String historicalRecordId) {
        if (context != null) {
            Intent intentToLaunch = HistoricalRecordDetailsActivity.getCallingIntent(context, historicalRecordId);
            context.startActivity(intentToLaunch);
        }
    }

    public void navigateToPlayStore(Context context) {
        Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
        }
    }
}
