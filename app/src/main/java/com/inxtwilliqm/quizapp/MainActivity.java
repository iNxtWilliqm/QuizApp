package com.inxtwilliqm.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        name = findViewById(R.id.name);

        if (getIntent().hasExtra("username")) {
            String savedName = getIntent().getStringExtra("username");
            name.setText(savedName);
        }

        findViewById(R.id.start_quiz).setOnClickListener(v -> startQuiz());
    }

    private void startQuiz() {
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra("username", name.getText().toString());
        startActivity(intent);
        finish();
    }
}