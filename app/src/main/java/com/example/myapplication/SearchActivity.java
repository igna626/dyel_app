package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<PhotoItem> photoItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        recyclerView = findViewById(R.id.photo_recycler_view);

        // Create a list of PhotoItems
        photoItemList = new ArrayList<>();
        photoItemList.add(new PhotoItem(R.drawable.cbum_parking));
        photoItemList.add(new PhotoItem(R.drawable.pradells_1));
        photoItemList.add(new PhotoItem(R.drawable.kiwi));
        photoItemList.add(new PhotoItem(R.drawable.guy_gym));
        photoItemList.add(new PhotoItem(R.drawable.squat_woman));
        photoItemList.add(new PhotoItem(R.drawable.guy_curl));
        photoItemList.add(new PhotoItem(R.drawable.plates_woman));
        photoItemList.add(new PhotoItem(R.drawable.asian_pull));
        photoItemList.add(new PhotoItem(R.drawable.deadlifter_woman));

        // Set up the RecyclerView
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        // Create a PhotoAdapter and set it as the RecyclerView adapter
        PhotoAdapter photoAdapter = new PhotoAdapter(photoItemList);
        recyclerView.setAdapter(photoAdapter);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_search);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.bottom_home:
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_search:
                    return true;
                case R.id.bottom_settings:
                    startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_profile:
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
            }
            return false;
        });
    }
}