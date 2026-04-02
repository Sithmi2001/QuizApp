package com.example.a046_assignment_quiz_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegistrationActivity extends AppCompatActivity {

    EditText editRegNo, editName;
    Button buttonNext;
    TextView titleRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // Find views
        editRegNo = findViewById(R.id.editRegNo);
        editName = findViewById(R.id.editName);
        buttonNext = findViewById(R.id.buttonNext);
        titleRegistration = findViewById(R.id.titleRegistration);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String regNo = editRegNo.getText().toString().trim();
                String name = editName.getText().toString().trim();

                if (regNo.isEmpty() || name.isEmpty()) {
                    Toast.makeText(RegistrationActivity.this, "Please Enter both Registration Number and Name", Toast.LENGTH_SHORT).show();
                } else {
                    // Pass registration info to SpecializationActivity
                    Intent intent = new Intent(RegistrationActivity.this, SpecializationActivity.class);
                    intent.putExtra("REG_NO", regNo);
                    intent.putExtra("NAME", name);
                    startActivity(intent);
                }
            }
        });


    }
}