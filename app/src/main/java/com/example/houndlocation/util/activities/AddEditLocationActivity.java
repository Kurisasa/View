package com.example.houndlocation.util.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.houndlocation.R;
import com.example.houndlocation.databinding.ActivityAddLocationBinding;
import com.example.houndlocation.util.other.AddEditLocationNavigator;
import com.example.houndlocation.util.util.LocationProviderManager;
import com.example.houndlocation.util.viewmodel.AddEditLocationViewModel;
import com.example.houndlocation.util.viewmodel.DaggerViewModelFactory;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;


public class AddEditLocationActivity extends DaggerAppCompatActivity implements AddEditLocationNavigator {

    @Inject
    DaggerViewModelFactory viewModelFactory;

    AddEditLocationViewModel addViewModel;

    LocationProviderManager locationProviderManager;
    private static final int REQUEST_CODE_PERMISSION = 2;

    public static final int ADD_EDIT_RESULT_OK = RESULT_FIRST_USER + 1;

    private static final String TAG = AddEditLocationActivity.class.getName();

    Button btnShowLocation;
    TextInputEditText latEditText;
    TextInputEditText lonEditText;

    View mLayout;
    int locationID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        if (intent.hasExtra("LocationID")) {

            try {
                locationID = intent.getExtras().getInt("LocationID");
                Log.d(TAG, "Extra in bundle " + locationID);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }


        } else {
            Log.d(TAG, "No extra in bundle");
        }

         addViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(AddEditLocationViewModel.class);

        ActivityAddLocationBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_add_location);
        binding.setLifecycleOwner(this);
        binding.setViewmodel(addViewModel);


        if (locationID != 0) {
            addViewModel.start(locationID);
        } else {
            addViewModel.start(0);
        }

        mLayout = findViewById(R.id.root);

        btnShowLocation = findViewById(R.id.btnGetlocation);
        latEditText = findViewById(R.id.latitude_textView);
        lonEditText = findViewById(R.id.longitude_textView);

        btnShowLocation.setOnClickListener(v -> startLocationUpdates());


        setupActionBar();

        addViewModel.getmLocationUpdated().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(Void aVoid) {
                AddEditLocationActivity.this.onLocationSaved();
            }
        });

        addViewModel.getmLocationCancelled().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(Void aVoid) {
                AddEditLocationActivity.this.onLocationCancelled();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onLocationSaved() {
        setResult(ADD_EDIT_RESULT_OK);
        finish();
    }

    @Override
    public void onLocationCancelled() {
        setResult(RESULT_CANCELED);
        finish();
    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) {
            return;
        } else  {
            actionBar.setDefaultDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        if (locationID != 0) {
            actionBar.setTitle(R.string.edit_location);
        } else {
            actionBar.setTitle(R.string.add_location);
        }
    }


    public void startLocationUpdates() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_CODE_PERMISSION);

            Snackbar.make(mLayout,
                    R.string.location_permission_granted,
                    Snackbar.LENGTH_SHORT).show();
            getLocation();
        } else {

            requestLocationPermission();
        }
    }

    private void requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_FINE_LOCATION)) {

            Snackbar.make(mLayout, R.string.location_access_required,
                    Snackbar.LENGTH_INDEFINITE).setAction(R.string.ok, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Request the permission
                    ActivityCompat.requestPermissions(AddEditLocationActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            REQUEST_CODE_PERMISSION);
                }
            }).show();
        } else {
            Snackbar.make(mLayout, R.string.location_unavailable, Snackbar.LENGTH_SHORT).show();
            // Request the permission. The result will be received in onRequestPermissionResult().
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_PERMISSION);
        }
    }


    public void getLocation() {
        locationProviderManager = new LocationProviderManager(AddEditLocationActivity.this);
        if (locationProviderManager.canGetLocation()) {
            double latitude = locationProviderManager.getLatitude();
            double longitude = locationProviderManager.getLongitude();


            try {
                DecimalFormat decimalFormat = new DecimalFormat("##.########");
                lonEditText.setText(decimalFormat.format(longitude));
                latEditText.setText(decimalFormat.format(latitude));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

            Toast.makeText(this, "longitude = " + longitude + "\nlatitude = " + latitude, Toast.LENGTH_LONG).show();

        } else {
            locationProviderManager.showAlert();
        }
    }

    @Override
    protected void onPause() {
        if (locationProviderManager != null) {
            locationProviderManager.stopGPS();
        }
        super.onPause();

    }
}
