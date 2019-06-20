package com.example.houndlocation.util.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.houndlocation.R;
import com.example.houndlocation.util.viewmodel.DaggerViewModelFactory;
import com.example.houndlocation.util.viewmodel.LocationListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import javax.inject.Inject;
import dagger.android.AndroidInjection;
import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {
    private static final String TAG = MainActivity.class.getName();

    public static final int NEW_LOCATION_ACTIVITY_REQUEST_CODE = 1;

    @Inject
    DaggerViewModelFactory viewModelFactory;
    LocationListViewModel locationListViewModel;
    MyListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        locationListViewModel = ViewModelProviders.of(this, viewModelFactory).get(LocationListViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.locations_recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //get view model
         adapter = new MyListAdapter(this);
        locationListViewModel.getAllLocations().observe(this, adapter::submitList);
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddLocationActivity.class);
            startActivityForResult(intent, NEW_LOCATION_ACTIVITY_REQUEST_CODE);
        });
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if (item.getItemId() == 121) {
            Log.d(TAG, "Option clicked" + item.getGroupId());
            adapter.openDetails(this, item.getGroupId());
            return true;
        }
        return super.onContextItemSelected(item);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }
}
