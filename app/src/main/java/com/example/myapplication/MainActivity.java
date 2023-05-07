package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<ImageItem> imageItemList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get a reference to the RecyclerView
        recyclerView = findViewById(R.id.image_recycler_view);

        // Create a list of ImageItems
        imageItemList = new ArrayList<>();
        imageItemList.add(new ImageItem(R.drawable.cbum_parking, ""));
        imageItemList.add(new ImageItem(R.drawable.kiwi, ""));
        imageItemList.add(new ImageItem(R.drawable.pradells_1, ""));
        imageItemList.add(new ImageItem(R.drawable.deadlifter_woman, ""));
        imageItemList.add(new ImageItem(R.drawable.plates_woman, ""));
        imageItemList.add(new ImageItem(R.drawable.asian_pull, ""));
        imageItemList.add(new ImageItem(R.drawable.squat_woman, ""));
        imageItemList.add(new ImageItem(R.drawable.guy_gym, ""));
        imageItemList.add(new ImageItem(R.drawable.guy_curl, ""));

        // Set up the RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        // Create an ImageAdapter and set it as the RecyclerView adapter
        ImageAdapter imageAdapter = new ImageAdapter(imageItemList);
        recyclerView.setAdapter(imageAdapter);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_home);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.bottom_home:
                    return true;
                case R.id.bottom_search:
                    startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
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