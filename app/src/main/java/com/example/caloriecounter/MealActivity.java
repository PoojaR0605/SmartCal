package com.example.caloriecounter;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import java.util.HashMap;

public class MealActivity extends AppCompatActivity {

    Spinner mealSpinner;
    CheckBox biryani, rice, idli, dosa, chapati, salad;
    Button calcMealBtn;
    TextView mealResult;

    double totalCalories = 0;
    double dailyGoal;
    HashMap<String, Integer> foodCalories = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);

        mealSpinner = findViewById(R.id.mealSpinner);
        biryani = findViewById(R.id.biryani);
        rice = findViewById(R.id.rice);
        idli = findViewById(R.id.idli);
        dosa = findViewById(R.id.dosa);
        chapati = findViewById(R.id.chapati);
        salad = findViewById(R.id.salad);
        calcMealBtn = findViewById(R.id.calcMealBtn);
        mealResult = findViewById(R.id.mealResult);

        dailyGoal = getIntent().getDoubleExtra("dailyCalories", 2000);

        foodCalories.put("Biryani", 350);
        foodCalories.put("Rice", 200);
        foodCalories.put("Idli", 80);
        foodCalories.put("Dosa", 150);
        foodCalories.put("Chapati", 120);
        foodCalories.put("Salad", 50);

        calcMealBtn.setOnClickListener(v -> calculateMealCalories());
    }

    private void calculateMealCalories() {
        totalCalories = 0;

        if (biryani.isChecked()) totalCalories += foodCalories.get("Biryani");
        if (rice.isChecked()) totalCalories += foodCalories.get("Rice");
        if (idli.isChecked()) totalCalories += foodCalories.get("Idli");
        if (dosa.isChecked()) totalCalories += foodCalories.get("Dosa");
        if (chapati.isChecked()) totalCalories += foodCalories.get("Chapati");
        if (salad.isChecked()) totalCalories += foodCalories.get("Salad");

        String mealType = mealSpinner.getSelectedItem().toString();
        double remaining = dailyGoal - totalCalories;

        mealResult.setText("You had " + Math.round(totalCalories) + " kcal for " +
                mealType + ".\nRemaining for the day: " + Math.round(remaining) + " kcal.");
    }
}
