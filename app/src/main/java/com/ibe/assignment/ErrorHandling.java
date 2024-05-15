package com.ibe.assignment;

import android.os.Bundle;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class ErrorHandling extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editTextUnits = findViewById(R.id.editTextUnits);

        // No need to declare or use unitsStr if it's not needed
    }
}


