package com.example.taskmanager;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class ReflexGame extends AppCompatActivity {
    RelativeLayout screen;
    Button startbtn, stopbtn, goToMultiColor;
    TextView result;
    double start, end;
    private final Handler handler = new Handler();
    private final Runnable runnable = new Runnable(){
        public void run() {

            screen.setBackgroundColor(Color.parseColor("#008000"));
            start = System.currentTimeMillis();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reflexgame);

        screen = findViewById(R.id.reflex_screen);
        result = findViewById(R.id.reflex_result);
        startbtn = findViewById(R.id.reflex_startbtn);
        stopbtn = findViewById(R.id.reflex_stopbtn);
        goToMultiColor = findViewById(R.id.multicolor_navigation);

        goToMultiColor.setOnClickListener(view -> {
            Intent intent = new Intent(ReflexGame.this, ReflexGameMultiple.class);
            startActivity(intent);
        });

        startbtn.setOnClickListener(v -> changeColor());

        stopbtn.setOnClickListener(v ->{
            int color = Color.TRANSPARENT;
            Drawable background = screen.getBackground();
            if (background instanceof ColorDrawable)
                color = ((ColorDrawable) background).getColor();
            String textColorHex = String.format("#%06X", (0xFFFFFF & color));
            if(textColorHex.equals("#008000"))
            {
                end = System.currentTimeMillis();
                result.setVisibility(View.VISIBLE);
                result.setText("Score: " + String.valueOf((end - start) / 1000.0));
                screen.setBackgroundColor(getResources().getColor(R.color.backgroundColor));
            }

            else
            {
                Toast.makeText(this, "You Clicked Too Early!", Toast.LENGTH_SHORT).show();
                handler.removeCallbacks(runnable);
            }
            /*int textColor = reflexTextView.getCurrentTextColor();
            String textColorHex = String.format("#%06X", (0xFFFFFF & textColor));
            if(timerTextView.getVisibility() == View.INVISIBLE) {
                if (textColorHex.equals("#008000")) {
                    end = System.currentTimeMillis();
                    timerTextView.setText(String.valueOf((end - start) / 1000.0));
                } else {
                    timerTextView.setText("You Clicked Too Early!!!");
                    reflexTextView.setVisibility(View.INVISIBLE);
                    startbtn.setVisibility(View.VISIBLE);
                }


            }*/
        });
    }

    public void changeColor()
    {
        result.setVisibility(View.INVISIBLE);
        Random rand = new Random();
        int interval = rand.nextInt((8000 - 3000 + 1) + 3000);

        handler.postAtTime(runnable, System.currentTimeMillis()+interval);
        handler.postDelayed(runnable, interval);

        /* reflexNavigate.setVisibility(View.VISIBLE);
        startbtn.setVisibility(View.INVISIBLE);
        reflexTextView.setVisibility(View.VISIBLE);

        Random rand = new Random();
        int interval = rand.nextInt((8000 - 3000 + 1) + 3000);

        handler.postAtTime(runnable, System.currentTimeMillis()+interval);
        handler.postDelayed(runnable, interval);*/
    }
}
