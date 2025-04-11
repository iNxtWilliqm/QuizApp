package com.inxtwilliqm.quizapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultActivity extends AppCompatActivity {
    TextView congratsText, scoreText;
    Button restart, finish;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        scoreText = findViewById(R.id.scoreText);
        congratsText = findViewById(R.id.congratsText);
        restart = findViewById(R.id.restart);
        finish = findViewById(R.id.finish);

        Intent intent = getIntent();
        String name = intent.getStringExtra("username");
        int score = intent.getIntExtra("score", 0);

        congratsText.setText(String.format("Congratulations %s!", name));
        scoreText.setText(String.format("Your score is: %d/%d", score, Quiz.TOTAL_QUESTIONS));

        restart.setOnClickListener(v -> {
            Intent restartIntent = new Intent(this, QuizActivity.class);
            restartIntent.putExtra("username", name);
            startActivity(restartIntent);
            finish();
        });

        finish.setOnClickListener(v -> finishAffinity());
    }
}