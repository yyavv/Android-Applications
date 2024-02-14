package com.example.taskmanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button b1, b2, b3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);

        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);

        b1.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,TicTacToe.class);
            startActivity(intent);
        });

        b2.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,ReflexGame.class);
            startActivity(intent);
        });

        b3.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,AgeCalculator.class);
            startActivity(intent);
        });
    }
}
