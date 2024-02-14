package com.example.taskmanager;

import android.os.Bundle;
import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;


public class ReflexGameMultiple extends AppCompatActivity {
    Color[] colors = new Color[9];
    RelativeLayout screen;
    Button startButton, stopButton;
    TextView result, navigate;

    String colorName;
    int randNum, curNum;

    int  count;

    double start, end;
    private final Handler handler = new Handler();
    private final Runnable runnable = new Runnable(){
        public void run() {
            Random random = new Random();
            curNum = random.nextInt(9);

            screen.setBackgroundColor(colors[curNum].hexValue);

            if (count++ > 5)
                randNum = curNum;

            if (curNum == randNum) {
                start = System.currentTimeMillis();
                handler.removeCallbacks(this);
                return;
            }

            handler.postDelayed(this, random.nextInt(3000 - 1000 + 1) + 1000);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reflexgamemulti);

        initializeColors();

        startButton = findViewById(R.id.reflexmulti_startbtn);
        stopButton = findViewById(R.id.reflexmulti_stopbtn);
        screen = findViewById(R.id.reflexmulti_screen);
        result = findViewById(R.id.reflexmulti_result);
        navigate = findViewById(R.id.reflexmulti_navigate);

        newColor();

        count = 0;

        startButton.setOnClickListener(view -> {changeColor(); result.setVisibility(View.INVISIBLE);});

        stopButton.setOnClickListener(v -> {

            if(curNum == randNum)
            {
                end = System.currentTimeMillis();
                result.setText("Score: " + String.valueOf((end - start) / 1000.0));
                result.setVisibility(View.VISIBLE);
                screen.setBackgroundColor(getResources().getColor(R.color.backgroundColor));
                newColor();
            }

            else
            {
                Toast.makeText(this, "You Clicked Too Early!", Toast.LENGTH_SHORT).show();
                handler.removeCallbacks(runnable);
                screen.setBackgroundColor(getResources().getColor(R.color.backgroundColor));
            }
        });
    }

    public void initializeColors()
    {
        colors[0] = new Color(0xFF008000, "0xFF008000","Green");
        colors[1] = new Color(0xFFFFFF00, "0xFFFFFF00","Yellow");
        colors[2] = new Color(0xFF0000FF, "0xFF0000FF","Blue");
        colors[3] = new Color(0xFFFF0000, "0xFFFF0000", "Red");
        colors[4] = new Color(0xFFA52A2A, "0xFFA52A2A", "Brown");
        colors[5] = new Color(0xFF800080, "0xFF800080", "Purple");
        colors[6] = new Color(0xFFFFC0CB, "0xFFFFC0CB", "Pink");
        colors[7] = new Color(0xFFFFA500, "0xFFFFA500", "Orange");
        colors[8] = new Color(0xFF40E0D0, "0xFF40E0D0", "Turquoise");
    }

    public void changeColor()
    {
        Random random = new Random();
        int delayMillis = random.nextInt(3000 - 1000 + 1) + 1000;

        handler.postDelayed(runnable, delayMillis);
    }

    public void newColor()
    {
        //Generating Randomize Stuff
        Random random = new Random();
        randNum = random.nextInt(9);
        colorName = colors[randNum].name;

        String text = "Click Start and When The Color Becomes " + colorName + " Click Stop";

        SpannableString spannableString = new SpannableString(text);
        int startIndex = text.indexOf(colorName);
        int endIndex = startIndex + colorName.length();
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(colors[randNum].hexValue);
        spannableString.setSpan(colorSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        navigate.setText(spannableString);
        //end of generating random stuff
    }
}
