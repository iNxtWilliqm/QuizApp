package com.inxtwilliqm.quizapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Quiz {
    public static final int TOTAL_QUESTIONS = 5;
    private int progress, score;
    private List<Question> questions;

    public Quiz() {
        init();
    }

    private void init() {
        progress = 1;
        score = 0;
        questions = new ArrayList<>();
        questions.add(new Question(
                "What is the capital of France?",
                "London", "Paris", "Berlin",
                "Paris"
        ));
        questions.add(new Question(
                "Which planet is known as the Red Planet?",
                "Venus", "Mars", "Jupiter",
                "Mars"
        ));
        questions.add(new Question(
                "What is the largest mammal?",
                "Elephant", "Blue Whale", "Giraffe",
                "Blue Whale"
        ));
        questions.add(new Question(
                "Which language is used for Android development?",
                "Java/Kotlin", "Python", "C++",
                "Java/Kotlin"
        ));
        questions.add(new Question(
                "What is the chemical symbol for gold?",
                "Go", "Au", "Ag",
                "Au"
        ));

        Collections.shuffle(questions);
    }

    public boolean isFinished() {
        return progress > TOTAL_QUESTIONS;
    }

    public Question getQuestion() {
        int index = progress - 1;
        return questions.get(index);
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isCorrect(String answer) {
        int index = progress - 1;
        return answer.equals(questions.get(index).getCorrectAnswer());
    }

    public int getTotalQuestions() {
        return TOTAL_QUESTIONS;
    }
}
