package com.ibe.assignment;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUnits;
    private EditText editTextRebate;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUnits = findViewById(R.id.editTextUnits);
        editTextRebate = findViewById(R.id.editTextRebate);
        Button buttonCalculate = findViewById(R.id.buttonCalculate);
        textViewResult = findViewById(R.id.textViewResult);

        ElectricityBillCalculator billCalculator = new ElectricityBillCalculator();

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBill();
            }
        });

        Button buttonAbout = findViewById(R.id.buttonAbout);
        buttonAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
            }
        });
    }

    private void calculateBill() {
        String unitsStr = editTextUnits.getText().toString().trim();
        String rebateStr = editTextRebate.getText().toString().trim();

        if (unitsStr.isEmpty() || rebateStr.isEmpty()) {
            showToast("Please entero values for units and rebate.");
            return;
        }

        try {
            double units = Double.parseDouble(unitsStr);
            double rebate = Double.parseDouble(rebateStr);

            if (units <= 0) {
                showToast("Units must be greater than zero.");
                return;
            }

            if (rebate < 0 || rebate > 5) {
                showToast("Rebate percentage should be between 0% and 5%.");
                return;
            }

            double billAmount = calculateElectricityBill(units, rebate);
            textViewResult.setText(String.format("Estimated Bill: %.2f sen", billAmount));

        } catch (NumberFormatException e) {
            showToast("Please enter valid numeric values for units and rebate.");
        }
    }

    private double calculateElectricityBill(double units, double rebatePercentage) {
        double totalCharge = 0;

        // Calculate bill based on electricity unit blocks and rates
        if (units > 0 && rebatePercentage >= 0 && rebatePercentage <= 5) {
            if (units <= 200) {
                totalCharge = units * 21.8;
            } else if (units <= 300) {
                totalCharge = 200 * 21.8 + (units - 200) * 33.4;
            } else if (units <= 600) {
                totalCharge = 200 * 21.8 + 100 * 33.4 + (units - 300) * 51.6;
            } else {
                totalCharge = 200 * 21.8 + 100 * 33.4 + 300 * 51.6 + (units - 600) * 54.6;
            }

            totalCharge *= (1 - (rebatePercentage / 100)); // Apply rebate percentage
        }

        return totalCharge;
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}




