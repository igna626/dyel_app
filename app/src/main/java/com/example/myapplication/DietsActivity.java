package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;

public class DietsActivity extends AppCompatActivity {
    private EditText editText1;
    private EditText editText2;
    private Button button;
    private FirebaseFirestore firestore;
    private CollectionReference userCollectionRef;
    private String userID;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> foodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diets);

        listView = findViewById(R.id.listView);
        foodList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, R.layout.list_item, R.id.foodTextView, foodList);
        listView.setAdapter(adapter);

        // Find references to EditText components and button
        editText1 = findViewById(R.id.input_nombre_comida);
        editText2 = findViewById(R.id.input_kcal);
        button = findViewById(R.id.add_food);

        // Nuevas lineas
        userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        userCollectionRef = FirebaseFirestore.getInstance().collection("usuarios").document(userID).collection("comidas");

        // Initialize Firestore database instance
        firestore = FirebaseFirestore.getInstance();

        // Obtener las comidas del usuario actual desde Firebase
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("usuarios").document(userID).collection("comidas")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            // Obtener los datos de cada comida y agregarlos a la lista
                            String food = document.getString("Food");
                            String kcals = document.getString("Kcals");
                            String time = document.getString("Time");

                            String foodInfo = "Food: " + food + "\nKcals: " + kcals + "\nTime: " + time;
                            foodList.add(foodInfo);
                        }

                        adapter.notifyDataSetChanged();
                    }
                });

        // Set a click listener on the button
        button.setOnClickListener(view -> {
            // Retrieve text from EditText components
            String input1 = editText1.getText().toString();
            String input2 = editText2.getText().toString();

            // Store data in Firestore and perform other actions
            storeDataInFirestore(input1, input2);

            // Retrieve elements:
            db.collection("usuarios").document(userID).collection("comidas")
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                // Obtener los datos de cada comida y agregarlos a la lista
                                String food = document.getString("Food");
                                String kcals = document.getString("Kcals");
                                String time = document.getString("Time");

                                String foodInfo = "Food: " + food + "\nKcals: " + kcals + "\nTime: " + time;
                                foodList.add(foodInfo);
                            }

                            adapter.notifyDataSetChanged();
                        }
                    });
        });
    }

    private void storeDataInFirestore(String input1, String input2) {
        Map<String, Object> data = new HashMap<>();
        // Convert current timestamp to date
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = dateFormat.format(currentDate);
        data.put("Time", formattedDate);
        data.put("Food", input1);
        data.put("Kcals", input2);

        // Use the document reference to set the data in Firestore
        userCollectionRef.add(data)
                .addOnSuccessListener(aVoid -> {
                    // Data successfully stored in Firestore
                    Toast.makeText(DietsActivity.this, "Add to DB Successful.", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    // Error occurred while storing data in Firestore
                    Toast.makeText(DietsActivity.this, "ERROR: Add to DB!", Toast.LENGTH_SHORT).show();
                });
    }
}
