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
import com.example.houndlocation.util.viewmodel.WeatherViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class WeatherActivity extends DaggerAppCompatActivity {

    private static final String TAG = WeatherActivity.class.getName();

    @Inject
    DaggerViewModelFactory viewModelFactory;
    WeatherViewModel weatherViewModel;

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

         weatherViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(WeatherViewModel.class);

        ActivityDetailLocationBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_location);
        binding.setLifecycleOwner(this);
        binding.setViewmodel(weatherViewModel);

        if (locationID != -1) {
            weatherViewModel.start(locationID);
        } else {
            Log.d(TAG, "No location found");
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
