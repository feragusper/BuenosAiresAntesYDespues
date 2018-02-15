package com.feragusper.buenosairesantesydespues.view.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatDelegate;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.danielstone.materialaboutlibrary.ConvenienceBuilder;
import com.danielstone.materialaboutlibrary.MaterialAboutActivity;
import com.danielstone.materialaboutlibrary.items.MaterialAboutActionItem;
import com.danielstone.materialaboutlibrary.items.MaterialAboutItemOnClickAction;
import com.danielstone.materialaboutlibrary.items.MaterialAboutTitleItem;
import com.danielstone.materialaboutlibrary.model.MaterialAboutCard;
import com.danielstone.materialaboutlibrary.model.MaterialAboutList;
import com.danielstone.materialaboutlibrary.util.OpenSourceLicense;
import com.feragusper.buenosairesantesydespues.R;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.iconics.IconicsDrawable;

import butterknife.InjectView;

/**
 * @author Fernando.Perez
 * @since 0.1
 * <p>
 * Activity that shows the about of the applciation
 */
public class AboutLicensesActivity extends MaterialAboutActivity {

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, AboutLicensesActivity.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_about, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();    //Call the back button's method
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    @NonNull
    protected MaterialAboutList getMaterialAboutList(@NonNull Context context) {
        MaterialAboutCard rxJavaLicenseCard = ConvenienceBuilder.createLicenseCard(this,
                new IconicsDrawable(this)
                        .icon(CommunityMaterial.Icon.cmd_book)
                        .color(ContextCompat.getColor(this, android.R.color.white))
                        .sizeDp(18),
                "rxJava", "2016", "RxJava Contributors",
                OpenSourceLicense.APACHE_2);

        MaterialAboutCard leakCanaryLicenseCard = ConvenienceBuilder.createLicenseCard(this,
                new IconicsDrawable(this)
                        .icon(CommunityMaterial.Icon.cmd_book)
                        .color(ContextCompat.getColor(this, android.R.color.white))
                        .sizeDp(18),
                "LeakCanary", "2015", "Square",
                OpenSourceLicense.APACHE_2);

        MaterialAboutCard okHttpLicenseCard = ConvenienceBuilder.createLicenseCard(this,
                new IconicsDrawable(this)
                        .icon(CommunityMaterial.Icon.cmd_book)
                        .color(ContextCompat.getColor(this, android.R.color.white))
                        .sizeDp(18),
                "OkHttp", "2016", "Square",
                OpenSourceLicense.APACHE_2);

        MaterialAboutCard picassoLicenseCard = ConvenienceBuilder.createLicenseCard(this,
                new IconicsDrawable(this)
                        .icon(CommunityMaterial.Icon.cmd_book)
                        .color(ContextCompat.getColor(this, android.R.color.white))
                        .sizeDp(18),
                "Picasso", "2013", "Square",
                OpenSourceLicense.APACHE_2);

        MaterialAboutCard butterknifeLicenseCard = ConvenienceBuilder.createLicenseCard(this,
                new IconicsDrawable(this)
                        .icon(CommunityMaterial.Icon.cmd_book)
                        .color(ContextCompat.getColor(this, android.R.color.white))
                        .sizeDp(18),
                "ButterKnife", "2013", "Jake Wharton",
                OpenSourceLicense.APACHE_2);

        MaterialAboutCard mockitoLicenseCard = ConvenienceBuilder.createLicenseCard(this,
                new IconicsDrawable(this)
                        .icon(CommunityMaterial.Icon.cmd_book)
                        .color(ContextCompat.getColor(this, android.R.color.white))
                        .sizeDp(18),
                "Mockito", "2007", "Mockito Contributors",
                OpenSourceLicense.MIT);

        MaterialAboutCard materialAboutLIbraryLicenseCard = ConvenienceBuilder.createLicenseCard(this,
                new IconicsDrawable(this)
                        .icon(CommunityMaterial.Icon.cmd_book)
                        .color(ContextCompat.getColor(this, android.R.color.white))
                        .sizeDp(18),
                "Material About Library", "2016", "Daniel Stone",
                OpenSourceLicense.APACHE_2);

        MaterialAboutCard androidIconicsLicenseCard = ConvenienceBuilder.createLicenseCard(this,
                new IconicsDrawable(this)
                        .icon(CommunityMaterial.Icon.cmd_book)
                        .color(ContextCompat.getColor(this, android.R.color.white))
                        .sizeDp(18),
                "Android Iconics", "2016", "Mike Penz",
                OpenSourceLicense.APACHE_2);

        return new MaterialAboutList(rxJavaLicenseCard,
                leakCanaryLicenseCard,
                okHttpLicenseCard,
                picassoLicenseCard,
                butterknifeLicenseCard,
                materialAboutLIbraryLicenseCard,
                androidIconicsLicenseCard,
                mockitoLicenseCard);
    }

    @Override
    protected CharSequence getActivityTitle() {
        return getString(R.string.licenses);
    }}
