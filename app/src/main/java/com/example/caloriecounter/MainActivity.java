package com.example.caloriecounter;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    EditText ageInput, weightInput, heightInput;
    Spinner goalSpinner;
    Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ageInput = findViewById(R.id.editAge);
        weightInput = findViewById(R.id.editWeight);
        heightInput = findViewById(R.id.editHeight);
        goalSpinner = findViewById(R.id.goalSpinner);
        nextButton = findViewById(R.id.nextButton);

        nextButton.setOnClickListener(v -> {
            String ageStr = ageInput.getText().toString().trim();
            String weightStr = weightInput.getText().toString().trim();
            String heightStr = heightInput.getText().toString().trim();

            if (ageStr.isEmpty() || weightStr.isEmpty() || heightStr.isEmpty()) {
                Toast.makeText(MainActivity.this, "Fill all the fields correctly", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                int age = Integer.parseInt(ageStr);
                double weight = Double.parseDouble(weightStr);
                double height = Double.parseDouble(heightStr);

                String goal = goalSpinner.getSelectedItem().toString();

                // Simple BMR calculation (Mifflin-St Jeor)
                double bmr = (10 * weight) + (6.25 * height) - (5 * age) + 5;
                double dailyCalories;

                if (goal.equalsIgnoreCase("Weight Loss")) {
                    dailyCalories = bmr - 400;
                } else if (goal.equalsIgnoreCase("Weight Gain")) {
                    dailyCalories = bmr + 400;
                } else {
                    dailyCalories = bmr;
                }

                Intent intent = new Intent(MainActivity.this, MealActivity.class);
                intent.putExtra("dailyCalories", dailyCalories);
                startActivity(intent);

            } catch (NumberFormatException e) {
                Toast.makeText(MainActivity.this, "Enter valid numeric values!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
