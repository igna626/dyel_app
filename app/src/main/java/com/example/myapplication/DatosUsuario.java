package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import androidx.annotation.NonNull;



import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DatosUsuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_usuario);

        Button guardarButton = findViewById(R.id.buttonGuardar);
        guardarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí se ejecutará el código cuando se presione el botón "Guardar"
                guardarDatosEnFirestore();
            }
        });
    }

    private void guardarDatosEnFirestore(){
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // Obtén las referencias a los EditText para obtener los valores ingresados por el usuario
        EditText nombreEditText = findViewById(R.id.editTextNombre);
        EditText edadEditText = findViewById(R.id.editTextEdad);
        EditText alturaEditText = findViewById(R.id.editTextAltura);
        EditText pesoEditText = findViewById(R.id.editTextPeso);
        SeekBar slider = findViewById(R.id.sliderBar);

        slider.setMax(5);

        // Obtén los valores ingresados por el usuario
        String nombre = nombreEditText.getText().toString();
        int edad = Integer.parseInt(edadEditText.getText().toString());
        double altura = Double.parseDouble(alturaEditText.getText().toString());
        double peso = Double.parseDouble(pesoEditText.getText().toString());
        int sliderValue = slider.getProgress();

        // Crea un mapa con los datos a guardar
        Map<String, Object> datos = new HashMap<>();
        datos.put("nombre", nombre);
        datos.put("edad", edad);
        datos.put("altura", altura);
        datos.put("peso", peso);
        datos.put("actividad", sliderValue);

        // Guarda los datos en Firestore
        // Guarda los datos en Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("usuarios")
                .document(userID)
                .collection("metricas")
                .add(datos)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(), "Datos guardados exitosamente", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Error al guardar los datos", Toast.LENGTH_SHORT).show();
                        Log.e("Firestore", "Error al guardar los datos", e);
                    }
                });

    }
}
