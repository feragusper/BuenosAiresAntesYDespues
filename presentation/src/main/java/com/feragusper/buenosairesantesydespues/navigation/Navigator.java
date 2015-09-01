package com.feragusper.buenosairesantesydespues.navigation;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;

import com.feragusper.buenosairesantesydespues.R;
import com.feragusper.buenosairesantesydespues.model.HistoricalRecordModel;
import com.feragusper.buenosairesantesydespues.view.activity.AboutActivity;
import com.feragusper.buenosairesantesydespues.view.activity.BaseActivity;
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
        if (context != null) {
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

    public void navigateToAbout(Context context) {
        if (context != null) {
            Intent intentToLaunch = AboutActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }

    public void navigateToSendFeedback(Context context) {
        Intent sendFeedbackIntent = new Intent(Intent.ACTION_SENDTO);
        sendFeedbackIntent.setData(Uri.parse("mailto:"));
        sendFeedbackIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"fernancho@gmail.com"});
        sendFeedbackIntent.putExtra(Intent.EXTRA_SUBJECT, R.string.send_feedback_subject);
        if (sendFeedbackIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(sendFeedbackIntent);
        }
    }

}
