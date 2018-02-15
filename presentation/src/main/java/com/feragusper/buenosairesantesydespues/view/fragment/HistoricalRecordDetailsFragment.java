package com.feragusper.buenosairesantesydespues.view.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.feragusper.buenosairesantesydespues.R;
import com.feragusper.buenosairesantesydespues.di.components.HistoricalRecordComponent;
import com.feragusper.buenosairesantesydespues.model.HistoricalRecordModel;
import com.feragusper.buenosairesantesydespues.presenter.HistoricalRecordDetailsPresenter;
import com.feragusper.buenosairesantesydespues.view.HistoricalRecordDetailsView;
import com.feragusper.buenosairesantesydespues.view.widget.ImageLoadCallback;
import com.feragusper.buenosairesantesydespues.view.widget.SlideImageView;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.Optional;

/**
 * @author Fernando.Perez
 * @since 0.1
 * <p>
 * Fragment that shows details of a certain historical record.
 */
public class HistoricalRecordDetailsFragment extends BaseFragment implements HistoricalRecordDetailsView, ImageLoadCallback {

    //region Properties
    private static final String ARGUMENT_KEY_HISTORICAL_RECORD_ID = "com.feragusper.buenosairesantesydespues.ARGUMENT_HISTORICAL_RECORD_ID";
    @Inject
    HistoricalRecordDetailsPresenter historicalRecordDetailsPresenter;
    @InjectView(R.id.siv_before_after)
    SlideImageView slideImageView;
    @InjectView(R.id.rl_progress)
    RelativeLayout rl_progress;
    @Optional
    @InjectView(R.id.tv_historical_record_address)
    TextView address;
    @Optional
    @InjectView(R.id.tv_historical_record_description)
    TextView description;
    @Optional
    @InjectView(R.id.tv_historical_record_year_neighborhood)
    TextView yearAndNeighborhood;
    @Optional
    @InjectView(R.id.iv_historical_record_share)
    View ivHistoricalRecordShare;
    @Optional
    @InjectView(R.id.tv_credits)
    TextView credits;
    private String historicalRecordId;
    private GoogleMap mMap;
    //endregion

    //region Public Static Implementation
    public static HistoricalRecordDetailsFragment newInstance(String historicalRecordId) {
        HistoricalRecordDetailsFragment historicalRecordDetailsFragment = new HistoricalRecordDetailsFragment();

        Bundle argumentsBundle = new Bundle();
        argumentsBundle.putString(ARGUMENT_KEY_HISTORICAL_RECORD_ID, historicalRecordId);
        historicalRecordDetailsFragment.setArguments(argumentsBundle);

        return historicalRecordDetailsFragment;
    }
    //endregion

    //region HistoricalRecordDetailsView Implementatino
    @SuppressLint("SetTextI18n")
    @SuppressWarnings("ConstantConditions")
    @Override
    public void renderHistoricalRecord(final HistoricalRecordModel historicalRecord) {
        if (historicalRecord != null) {
            if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
                ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(historicalRecord.getTitle());
            }

            slideImageView.setImageUrls(historicalRecord.getImageURLAfter(), historicalRecord.getImageURLBefore(), this);

            if (address != null) {
                address.setText(historicalRecord.getAddress());
            }

            if (description != null && historicalRecord.getDescription() != null && !historicalRecord.getDescription().isEmpty()) {
                description.setText(historicalRecord.getDescription());
                description.setVisibility(View.VISIBLE);
            }

            if (credits != null) {
                credits.setText(historicalRecord.getCredits());
            }

            if (yearAndNeighborhood != null && ((historicalRecord.getYear() != null && !historicalRecord.getYear().isEmpty()) || (historicalRecord.getNeighborhood() != null && !historicalRecord.getNeighborhood().isEmpty()))) {
                yearAndNeighborhood.setText(historicalRecord.getYear() + " - " + historicalRecord.getNeighborhood());
                yearAndNeighborhood.setVisibility(View.VISIBLE);
            }

            if (mMap != null) {
                final LatLng latLng = new LatLng(historicalRecord.getLat(), historicalRecord.getLng());
                mMap.addMarker(new MarkerOptions().position(latLng).title(historicalRecord.getAddress()).icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_off)));
                CameraPosition cameraPosition = new CameraPosition.Builder().target(latLng).zoom(14.0f).build();
                CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
                mMap.moveCamera(cameraUpdate);
            }

            if (ivHistoricalRecordShare != null) {
                ivHistoricalRecordShare.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        navigateToShare(historicalRecord);
                    }
                });
            }

        }
    }

    private void navigateToShare(HistoricalRecordModel historicalRecord) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, getContext().getString(R.string.share_body, historicalRecord.getAddress(), historicalRecord.getShareURL()));
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, getContext().getString(R.string.share_subject, historicalRecord.getTitle()));
        sendIntent.setType("text/plain");
        getActivity().startActivity(sendIntent);
    }

    @Override
    public Context getContext() {
        return getActivity().getApplicationContext();
    }

    //region Fragment Implementation
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_historical_record_details, container, false);
        ButterKnife.inject(this, fragmentView);

        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (supportMapFragment != null) {
            supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    mMap = googleMap;
                }
            });
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.initialize();
    }

    @Override
    public void onResume() {
        super.onResume();
        this.historicalRecordDetailsPresenter.resume();
    }
    //endregion

    @Override
    public void onPause() {
        super.onPause();
        this.historicalRecordDetailsPresenter.pause();
    }
    //endregion

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.historicalRecordDetailsPresenter.destroy();
        slideImageView.reset();
        ButterKnife.reset(this);
    }

    //region Private Implementation
    private void initialize() {
        if (historicalRecordDetailsPresenter == null) {
            this.getComponent(HistoricalRecordComponent.class).inject(this);
            historicalRecordDetailsPresenter.setView(this);
            historicalRecordId = getArguments().getString(ARGUMENT_KEY_HISTORICAL_RECORD_ID);
        }
        historicalRecordDetailsPresenter.initialize(this.historicalRecordId);
    }

    //region LoadDataView Implementation
    @Override
    public void showLoading() {
        rl_progress.setVisibility(View.VISIBLE);
        this.getActivity().setProgressBarIndeterminateVisibility(true);
    }
    //endregion

    @Override
    public void hideLoading() {
        if (rl_progress != null) {
            rl_progress.setVisibility(View.GONE);
            this.getActivity().setProgressBarIndeterminateVisibility(false);
        }
    }

    @Override
    public void showError(String message) {
        this.showToastMessage(message);
    }

    @Override
    public void onImageLoadSuccess() {
        historicalRecordDetailsPresenter.onImageLoadSuccess();
    }
    //endregion
}
