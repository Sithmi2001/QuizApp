package com.example.a046_assignment_quiz_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultActivity extends AppCompatActivity {

    TextView textScore, textTime, textCorrect, textWrong;
    Button buttonRestart, buttonExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textScore = findViewById(R.id.textScore);
        textTime = findViewById(R.id.textTime);
        textCorrect = findViewById(R.id.textCorrect);
        textWrong = findViewById(R.id.textWrong);
        buttonRestart = findViewById(R.id.buttonRestart);
        buttonExit = findViewById(R.id.buttonExit);

        // Get data from quiz
        int correct = getIntent().getIntExtra("correct", 0);
        int wrong = getIntent().getIntExtra("wrong", 0);
        long timeTaken = getIntent().getLongExtra("timeTaken", 0);

        int total = correct + wrong;
        int percentage = (int) (((double) correct / total) * 100);

        // Convert timeTaken (ms) to minutes and seconds
        long totalSeconds = timeTaken / 1000;
        long minutes = totalSeconds / 60;
        long seconds = totalSeconds % 60;

        textCorrect.setText("✅ Correct Answers: " + correct);
        textWrong.setText("❌ Wrong Answers: " + wrong);
        textScore.setText("Percentage: " + percentage + "%");
        textTime.setText("⏱ Time Taken: " + minutes + "m " + seconds + "s");

        // Restart Quiz → go back to specialization page
        buttonRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, SpecializationActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Exit → go to ThankYouActivity
        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, ThankYouActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}