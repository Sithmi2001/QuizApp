package com.example.a046_assignment_quiz_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ThankYouActivity extends AppCompatActivity {

    private SeekBar seekBarSatisfaction;
    private TextView textRatingValue;
    private Button buttonSubmit;
    private LinearLayout ratingLayout, thankYouLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);

        seekBarSatisfaction = findViewById(R.id.seekBarSatisfaction);
        textRatingValue = findViewById(R.id.textRatingValue);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        ratingLayout = findViewById(R.id.ratingLayout);
        thankYouLayout = findViewById(R.id.thankYouLayout);

        seekBarSatisfaction.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textRatingValue.setText("Selected: " + progress + " / 10");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ratingLayout.setVisibility(View.GONE);   // hide rating UI
                thankYouLayout.setVisibility(View.VISIBLE); // show Thank You card
            }
        });

    }
}