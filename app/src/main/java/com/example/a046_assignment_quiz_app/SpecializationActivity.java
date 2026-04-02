package com.example.a046_assignment_quiz_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SpecializationActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton radioNetwork, radioSoftware;
    Button startQuizBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_specialization);

        radioGroup = findViewById(R.id.radioGroupSpecialization);
        radioNetwork = findViewById(R.id.radioLevel2);
        radioSoftware = findViewById(R.id.radioLevel3);
        startQuizBtn = findViewById(R.id.buttonStartQuiz);

        startQuizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();

                if (selectedId == -1) {
                    Toast.makeText(SpecializationActivity.this, "Please select a specialization", Toast.LENGTH_SHORT).show();
                } else if (selectedId == R.id.radioLevel2) {
                    // Network Quiz
                    Intent intent = new Intent(SpecializationActivity.this, QuizActivity.class);
                    intent.putExtra("quizType", "network");
                    startActivity(intent);
                } else if (selectedId == R.id.radioLevel3) {
                    // Software Quiz
                    Intent intent = new Intent(SpecializationActivity.this, QuizActivity.class);
                    intent.putExtra("quizType", "software");
                    startActivity(intent);
                }
            }
        });

    }
}