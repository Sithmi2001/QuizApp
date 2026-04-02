package com.example.a046_assignment_quiz_app;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    TextView questionText, timerText, progressText;
    RadioGroup optionsGroup;
    RadioButton option1, option2, option3, option4;
    Button nextBtn, prevBtn;

    List<Question> questions = new ArrayList<>();
    int currentIndex = 0, score = 0;
    long timeTaken = 0;
    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionText = findViewById(R.id.questionText);
        timerText = findViewById(R.id.timerText);
        progressText = findViewById(R.id.progressText);
        optionsGroup = findViewById(R.id.optionsGroup);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        nextBtn = findViewById(R.id.nextBtn);
        prevBtn = findViewById(R.id.prevBtn);

        // Load quiz type
        String quizType = getIntent().getStringExtra("quizType");
        if ("network".equals(quizType)) {
            loadNetworkQuestions();
        } else {
            loadSoftwareQuestions();
        }

        showQuestion();


        // Start Timer (10 min for 10 questions)
        timer = new CountDownTimer(10 * 60 * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                long secondsRemaining = millisUntilFinished / 1000;
                long minutes = secondsRemaining / 60;
                long seconds = secondsRemaining % 60;

                timerText.setText("Time: " + minutes + "m " + seconds + "s");

                timeTaken = (10 * 60 * 1000 - millisUntilFinished) / 1000;
            }


            public void onFinish() {
                finishQuiz();
            }
        }.start();



        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = optionsGroup.getCheckedRadioButtonId();
                if (selectedId == -1) return;

                RadioButton selected = findViewById(selectedId);
                if (selected.getText().toString().equals(questions.get(currentIndex).correctAnswer)) {
                    score++;
                }

                currentIndex++;
                if (currentIndex < questions.size()) {
                    showQuestion();
                } else {
                    finishQuiz();
                }
            }
        });

        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndex > 0) { //  don’t go before Q2
                    currentIndex--;
                    showQuestion();
                }
            }
        });
    }

    private void showQuestion() {
        Question q = questions.get(currentIndex);
        questionText.setText(q.question);
        option1.setText(q.options[0]);
        option2.setText(q.options[1]);
        option3.setText(q.options[2]);
        option4.setText(q.options[3]);
        progressText.setText("Q " + (currentIndex + 1) + "/" + questions.size());
        optionsGroup.clearCheck();

        // Change Next button text on last question
        if (currentIndex == questions.size() - 1) {
            nextBtn.setText("Finish");
            nextBtn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#640D5F")));
        } else {
            nextBtn.setText("Next");
            nextBtn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#35155D")));
        }

        // Show/hide Previous button
        if (currentIndex == 0) {
            prevBtn.setVisibility(View.GONE);
        } else {
            prevBtn.setVisibility(View.VISIBLE);
        }
    }



    private void finishQuiz() {
        timer.cancel();

        int correct = score;
        int wrong = questions.size() - score;

        Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
        intent.putExtra("correct", correct);         //  send correct answers
        intent.putExtra("wrong", wrong);             //  send wrong answers
        intent.putExtra("timeTaken", timeTaken * 1000); //  send ms instead of seconds
        startActivity(intent);
        finish();
    }


    private void loadNetworkQuestions() {
        questions.add(new Question("Which protocol is used for secure web browsing?", new String[]{"HTTP", "HTTPS", "FTP", "SMTP"}, "HTTPS"));
        questions.add(new Question("Firewall is used for?", new String[]{"File sharing", "Network Security", "Data Entry", "Gaming"}, "Network Security"));
        questions.add(new Question("What does IP stand for in networking?",
                new String[]{"Internet Protocol", "Internal Program", "Input Process", "Internet Program"},
                "Internet Protocol"));

        questions.add(new Question("Which device connects multiple networks together?",
                new String[]{"Router", "Switch", "Hub", "Repeater"},
                "Router"));

        questions.add(new Question("Which topology has a central hub connecting all devices?",
                new String[]{"Star", "Ring", "Bus", "Mesh"},
                "Star"));

        questions.add(new Question("Which protocol is used to send emails?",
                new String[]{"SMTP", "FTP", "HTTP", "POP3"},
                "SMTP"));

        questions.add(new Question("What is the main function of DNS?",
                new String[]{"Translate domain names to IP addresses", "Encrypt data", "Store emails", "Manage network traffic"},
                "Translate domain names to IP addresses"));

        questions.add(new Question("Which layer of the OSI model handles routing?",
                new String[]{"Network", "Transport", "Session", "Physical"},
                "Network"));

        questions.add(new Question("Wi-Fi stands for?",
                new String[]{"Wireless Fidelity", "Wide Fidelity", "Wireless Function", "Web Fidelity"},
                "Wireless Fidelity"));

        questions.add(new Question("Which device amplifies signals to extend network range?",
                new String[]{"Repeater", "Hub", "Switch", "Router"},
                "Repeater"));

    }

    private void loadSoftwareQuestions() {
        questions.add(new Question("Which language is platform-independent?", new String[]{"C", "Java", "Assembly", "Python"}, "Java"));
        questions.add(new Question("What is OOP?", new String[]{"Object-Oriented Programming", "Online Operation Protocol", "Open Office Program", "Only Output Print"}, "Object-Oriented Programming"));
        questions.add(new Question("Which of these is NOT a programming paradigm?",
                new String[]{"Procedural", "Object-Oriented", "Functional", "Database"},
                "Database"));

        questions.add(new Question("Which keyword is used to create a class in Java?",
                new String[]{"class", "struct", "object", "define"},
                "class"));

        questions.add(new Question("What is the main purpose of an IDE?",
                new String[]{"To write and test code", "To compile hardware", "To manage databases", "To design websites"},
                "To write and test code"));

        questions.add(new Question("Which is a version control system?",
                new String[]{"Git", "Jenkins", "Docker", "MySQL"},
                "Git"));

        questions.add(new Question("Which software testing type checks individual units?",
                new String[]{"Unit Testing", "Integration Testing", "System Testing", "Acceptance Testing"},
                "Unit Testing"));

        questions.add(new Question("Which data structure uses LIFO order?",
                new String[]{"Stack", "Queue", "Linked List", "Tree"},
                "Stack"));

        questions.add(new Question("Which is a high-level programming language?",
                new String[]{"Python", "Assembly", "Machine Code", "Binary"},
                "Python"));

        questions.add(new Question("What does API stand for?",
                new String[]{"Application Programming Interface", "Automatic Program Integration", "Advanced Processing Input", "Application Protocol Interface"},
                "Application Programming Interface"));

    }

}