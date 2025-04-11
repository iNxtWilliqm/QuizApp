package com.inxtwilliqm.quizapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class QuizActivity extends AppCompatActivity {
    TextView welcomeText, progressText, questionText;
    Button submit, next, option1, option2, option3, selected;
    ProgressBar progressBar;

    boolean isAnswered = false;
    boolean isSubmitted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        welcomeText = findViewById(R.id.welcome);
        progressText = findViewById(R.id.progress);
        progressBar = findViewById(R.id.progressBar);
        questionText = findViewById(R.id.question);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        submit = findViewById(R.id.submit);
        next = findViewById(R.id.next);
        Intent intent = getIntent();
        String name = intent.getStringExtra("username");
        welcomeText.setText(String.format("Welcome %s!", name));

        Quiz quiz = new Quiz();
        startQuiz(quiz);

        progressBar.setMax(quiz.getTotalQuestions());

        option1.setOnClickListener(v -> handleAnswer(option1));
        option2.setOnClickListener(v -> handleAnswer(option2));
        option3.setOnClickListener(v -> handleAnswer(option3));
        submit.setOnClickListener(v -> handleSubmit(quiz));
        next.setOnClickListener(v -> handleNext(quiz));
    }

    private void startQuiz(Quiz quiz) {
        resetButtonState();
        updateQuestion(quiz);
    }

    private void resetButtonState() {
        isAnswered = false;
        isSubmitted = false;
        selected = null;

        for (Button option : new Button[]{option1, option2, option3}) {
            option.setEnabled(true);
            option.setBackgroundColor(Color.WHITE);
        }
    }

    @SuppressLint("DefaultLocale")
    private void updateProgress(Quiz quiz) {
        int progress = quiz.getProgress();
        int totalQuestions = quiz.getTotalQuestions();
        progressText.setText(String.format("%d/%d", progress, totalQuestions));
        progressBar.setProgress(progress);
    }

    private void updateQuestion(Quiz quiz) {
        if (quiz.isFinished()) return;

        Question q = quiz.getQuestion();
        questionText.setText(q.getQuestion());

        List<String> options = q.getOptions();
        option1.setText(options.get(0));
        option2.setText(options.get(1));
        option3.setText(options.get(2));

        updateProgress(quiz);
    }

    private void handleAnswer(Button option) {
        if (isAnswered) {
            selected.setBackgroundColor(Color.WHITE);
        }
        selected = option;
        selected.setBackgroundColor(Color.GRAY);
        isAnswered = true;
    }

    private void handleSubmit(Quiz quiz) {
        if (!isAnswered) return;

        showAnswer(quiz);
        submit.setVisibility(View.GONE);
        next.setVisibility(View.VISIBLE);
    }

    private void showAnswer(Quiz quiz) {
        for (Button option : new Button[]{option1, option2, option3}) {
            if (quiz.isCorrect(option.getText().toString())) {
                option.setBackgroundColor(Color.GREEN);
            }
            option.setEnabled(false);
        }

        if (quiz.isCorrect(selected.getText().toString())) {
            quiz.setScore(quiz.getScore() + 1);
        } else {
            selected.setBackgroundColor(Color.RED);
        }
    }

    private void handleNext(Quiz quiz) {
        quiz.setProgress(quiz.getProgress() + 1);
        if (quiz.isFinished()) {
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("score", quiz.getScore());
            intent.putExtra("username", getIntent().getStringExtra("username"));
            startActivity(intent);
            finish();
            return;
        }
        updateQuestion(quiz);
        resetButtonState();
        submit.setVisibility(View.VISIBLE);
        next.setVisibility(View.GONE);
    }
}