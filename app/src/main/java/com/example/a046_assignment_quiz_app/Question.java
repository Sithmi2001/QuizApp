package com.example.a046_assignment_quiz_app;

public class Question {
    String question;
    String[] options;
    String correctAnswer;

    public Question(String question, String[] options, String correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
}
