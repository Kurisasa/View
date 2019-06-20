package com.example.houndlocation.util.activities;

import androidx.appcompat.app.ActionBar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.houndlocation.R;
import com.example.houndlocation.databinding.ActivityDetailLocationBinding;
import com.example.houndlocation.util.viewmodel.DaggerViewModelFactory;
import com.example.houndlocation.util.viewmodel.DetailLocationViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class DetailLocationActivity extends DaggerAppCompatActivity {

    private static final String TAG = DetailLocationActivity.class.getName();

    @Inject
    DaggerViewModelFactory viewModelFactory;
    DetailLocationViewModel detailLocationViewModel;

    int locationID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        if (intent.hasExtra("LocationID")){
            try {
                locationID = intent.getExtras().getInt("LocationID");
                Log.d(TAG, "Extra in bundle " + locationID);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        } else {
            Log.d(TAG, "No extra in bundle");
        }

         detailLocationViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(DetailLocationViewModel.class);

        ActivityDetailLocationBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_location);
        binding.setLifecycleOwner(this);
        binding.setViewmodel(detailLocationViewModel);

        if (locationID != -1) {
            detailLocationViewModel.start(locationID);
        } else {
            Log.d(TAG, "No location found");
//            throw new RuntimeException("No location found");
            finish();
        }

        setupActionBar();

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) {
            return;
        } else  {
            actionBar.setDefaultDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }


            actionBar.setTitle(R.string.location_details);

    }
}
