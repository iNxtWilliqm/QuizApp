package com.inxtwilliqm.quizapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question {
    private final String question;
    private final String option1;
    private final String option2;
    private final String option3;
    private final String correctAnswer;

    public Question(String question, String option1, String option2, String option3, String correctAnswer) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() { return question; }

    public String getCorrectAnswer() { return correctAnswer; }

    public List<String> getOptions() {
        List<String> options = new ArrayList<>();
        options.add(option1);
        options.add(option2);
        options.add(option3);
        Collections.shuffle(options);
        return options;
    }
}
