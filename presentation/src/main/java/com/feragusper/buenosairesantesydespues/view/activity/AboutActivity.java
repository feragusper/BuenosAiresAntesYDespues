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
public class AboutActivity extends MaterialAboutActivity {

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, AboutActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        aboutHistory.setMovementMethod(LinkMovementMethod.getInstance());
//
//        try {
//            appVersion.setText(getString(R.string.version, getPackageManager().getPackageInfo(getPackageName(), 0).versionName));
//        } catch (PackageManager.NameNotFoundException e) {
//            Log.e(this.getClass().getSimpleName(), "An error ocurred while trying to get the version name", e);
//        }
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
        MaterialAboutCard.Builder appCardBuilder = new MaterialAboutCard.Builder();

        // Add items to card

        appCardBuilder.addItem(new MaterialAboutTitleItem.Builder()
                .text(R.string.app_name)
                .icon(R.mipmap.ic_launcher_foreground)
                .build());

        appCardBuilder.addItem(ConvenienceBuilder.createVersionActionItem(this,
                new IconicsDrawable(this)
                        .icon(CommunityMaterial.Icon.cmd_information_outline)
                        .color(ContextCompat.getColor(this, android.R.color.white))
                        .sizeDp(18),
                "Version",
                false));

        appCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text(R.string.changelog)
                .icon(new IconicsDrawable(this)
                        .icon(CommunityMaterial.Icon.cmd_history)
                        .color(ContextCompat.getColor(this, android.R.color.white))
                        .sizeDp(18))
                .setOnClickAction(ConvenienceBuilder.createWebViewDialogOnClickAction(this, getString(R.string.releases), "https://github.com/feragusper/BuenosAiresAntesYDespues/releases", true, false))
                .build());

        appCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text(R.string.licenses)
                .icon(new IconicsDrawable(this)
                        .icon(CommunityMaterial.Icon.cmd_book)
                        .color(ContextCompat.getColor(this, android.R.color.white))
                        .sizeDp(18))
                .setOnClickAction(new MaterialAboutItemOnClickAction() {
                    @Override
                    public void onClick() {
                        startActivity(AboutLicensesActivity.getCallingIntent(AboutActivity.this));
                    }
                })
                .build());

        MaterialAboutCard.Builder authorCardBuilder = new MaterialAboutCard.Builder();
        authorCardBuilder.title(R.string.author);

        authorCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text("Fernando Pérez")
                .subText("Argentina")
                .icon(new IconicsDrawable(this)
                        .icon(CommunityMaterial.Icon.cmd_account)
                        .color(ContextCompat.getColor(this, android.R.color.white))
                        .sizeDp(18))
                .build());

        authorCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text(R.string.fork_on_github)
                .icon(new IconicsDrawable(this)
                        .icon(CommunityMaterial.Icon.cmd_github_circle)
                        .color(ContextCompat.getColor(this, android.R.color.white))
                        .sizeDp(18))
                .setOnClickAction(ConvenienceBuilder.createWebsiteOnClickAction(this, Uri.parse("https://github.com/feragusper")))
                .build());

        MaterialAboutCard.Builder projectCardBuilder = new MaterialAboutCard.Builder();

        projectCardBuilder.title(R.string.project);

        projectCardBuilder.addItem(ConvenienceBuilder.createWebsiteActionItem(this,
                new IconicsDrawable(this)
                        .icon(CommunityMaterial.Icon.cmd_earth)
                        .color(ContextCompat.getColor(this, android.R.color.white))
                        .sizeDp(18),
                getString(R.string.visit_website),
                true,
                Uri.parse("http://www.bsasantesydespues.com.ar")));

        projectCardBuilder.addItem(ConvenienceBuilder.createRateActionItem(this,
                new IconicsDrawable(this)
                        .icon(CommunityMaterial.Icon.cmd_star)
                        .color(ContextCompat.getColor(this, android.R.color.white))
                        .sizeDp(18),
                getString(R.string.rate_this_app),
                null
        ));

        projectCardBuilder.addItem(ConvenienceBuilder.createEmailItem(this,
                new IconicsDrawable(this)
                        .icon(CommunityMaterial.Icon.cmd_email)
                        .color(ContextCompat.getColor(this, android.R.color.white))
                        .sizeDp(18),
                getString(R.string.send_email),
                true,
                "feragusper@gmail.com",
                getString(R.string.about_buenosairesantesydespues)));

        return new MaterialAboutList(appCardBuilder.build(), authorCardBuilder.build(), projectCardBuilder.build());
    }

    @Override
    protected CharSequence getActivityTitle() {
        return getString(R.string.mal_title_about);
    }}
