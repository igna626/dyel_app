package com.example.myapplication;

public class KcalCalculator {
    private Float height;
    private Float weight;
    private Integer age;
    private String sex;
    private String activityLevel;

    // Constructor
    public KcalCalculator(Float height, Float weight, Integer age, String sex) {
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.sex = sex;
    }

    // Método para calcular las calorías a ingerir
    public float calculateCalories() {
        float basalMetabolicRate;

        // Cálculo del metabolismo basal (Basal Metabolic Rate, BMR)
        if (sex.equalsIgnoreCase("male")) {
            basalMetabolicRate = 66 + (13.75f * weight) + (5 * height) - (6.75f * age);
        } else {
            basalMetabolicRate = 655 + (9.56f * weight) + (1.85f * height) - (4.68f * age);
        }

        // Cálculo de las calorías totales a ingerir considerando el nivel de actividad física
        float activityFactor;
        // Puedes ajustar los valores de los factores según tus necesidades
        if (activityLevel.equalsIgnoreCase("sedentary")) {
            activityFactor = 1.2f;
        } else if (activityLevel.equalsIgnoreCase("lightly active")) {
            activityFactor = 1.375f;
        } else if (activityLevel.equalsIgnoreCase("moderately active")) {
            activityFactor = 1.55f;
        } else if (activityLevel.equalsIgnoreCase("very active")) {
            activityFactor = 1.725f;
        } else {
            activityFactor = 1.9f;
        }

        float totalCalories = basalMetabolicRate * activityFactor;
        return totalCalories;
    }
}

