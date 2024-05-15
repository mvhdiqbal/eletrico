package com.ibe.assignment;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
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

    private ElectricityBillCalculator billCalculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUnits = findViewById(R.id.editTextUnits);
        editTextRebate = findViewById(R.id.editTextRebate);
        Button buttonCalculate = findViewById(R.id.buttonCalculate);
        textViewResult = findViewById(R.id.textViewResult);

        billCalculator = new ElectricityBillCalculator();

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
        String unitsStr = editTextUnits.getText().toString();
        String rebateStr = editTextRebate.getText().toString();

        if (!unitsStr.isEmpty() && !rebateStr.isEmpty()) {
            try {
                double units = Double.parseDouble(unitsStr);
                double rebate = Double.parseDouble(rebateStr);

                if (units > 0 && rebate >= 0 && rebate <= 5) {
                    double billAmount = billCalculator.calculateElectricityBill(units, rebate);
                    textViewResult.setText(String.format("Estimated Bill: %.2f sen", billAmount));
                } else {
                    showToast("Please enter valid values (units > 0, rebate between 0 - 5%).");
                }
            } catch (NumberFormatException e) {
                showToast("Please enter valid numeric values for units and rebate.");
            }
        } else {
            showToast("Please enter values for units and rebate.");
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}





