package com.ibe.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView textViewEmail = findViewById(R.id.textViewEmail);
        textViewEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });

        @SuppressLint("CutPasteId") TextView textViewGithub = findViewById(R.id.textViewEmail);
        textViewGithub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGithub();
            }
        });
    }

    @SuppressLint("QueryPermissionsNeeded")
    private void sendEmail() {
        String email = "your.email@example.com";
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:" + email));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback or Inquiry");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    private void openGithub() {
        String githubUrl = "https://github.com/mvhdiqbal/eletrico";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(githubUrl));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
