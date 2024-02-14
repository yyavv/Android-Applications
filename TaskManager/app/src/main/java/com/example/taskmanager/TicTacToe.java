package com.example.taskmanager;

import static java.lang.Character.toUpperCase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class TicTacToe extends AppCompatActivity implements View.OnClickListener {

    MyDrawingView drawingView;

    float scale;

    TextView turn, box1, box2, box3, box4, box5, box6, box7, box8, box9, winner;
    Button btnReset;
    int count = 0;
    boolean game = true;
    char[][] array = new char[3][3];
    int[][][] loc = new int[3][3][2];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tictactoe);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        scale = getResources().getDisplayMetrics().density;

        // MyDrawingView'i oluşturun ve ViewGroup'e ekleyin
        drawingView = new MyDrawingView(this, null);
        ViewGroup container = findViewById(R.id.container); // XML dosyasındaki ViewGroup
        container.addView(drawingView);

        // Çizgiyi çizmek için animasyonu başlatın
        //drawingView.drawLineWithAnimation(50, 50, 250, 250);

        winner = findViewById(R.id.textWinner);
        turn = findViewById(R.id.textview2);
        box1 = findViewById(R.id.box1);
        box2 = findViewById(R.id.box2);
        box3 = findViewById(R.id.box3);
        box4 = findViewById(R.id.box4);
        box5 = findViewById(R.id.box5);
        box6 = findViewById(R.id.box6);
        box7 = findViewById(R.id.box7);
        box8 = findViewById(R.id.box8);
        box9 = findViewById(R.id.box9);
        btnReset = findViewById(R.id.btnReset);

        //getLoc();

        box1.setOnClickListener(this);
        box2.setOnClickListener(this);
        box3.setOnClickListener(this);
        box4.setOnClickListener(this);
        box5.setOnClickListener(this);
        box6.setOnClickListener(this);
        box7.setOnClickListener(this);
        box8.setOnClickListener(this);
        box9.setOnClickListener(this);

        btnReset.setOnClickListener(v -> recreate());

    }


    @Override
    public void onClick(View view) {

    if(game){
        switch(view.getId())
        {
            case R.id.box1:
                if (array[0][0] == 'x' || array[0][0] == 'o') {
                    Toast.makeText(getApplicationContext(), "This box is already filled!", Toast.LENGTH_SHORT).show();
                } else {
                    box1.setText(String.valueOf(message()));
                    array[0][0] = message();
                    count++;
                }
                break;

            case R.id.box2:
                if (array[0][1] == 'x' || array[0][1] == 'o') {
                    Toast.makeText(getApplicationContext(), "This box is already filled!", Toast.LENGTH_SHORT).show();
                } else {
                    box2.setText(String.valueOf(message()));
                    array[0][1] = message();
                    count++;
                }
                break;

            case R.id.box3:
                if (array[0][2] == 'x' || array[0][2] == 'o') {
                    Toast.makeText(getApplicationContext(), "This box is already filled!", Toast.LENGTH_SHORT).show();
                } else {
                    box3.setText(String.valueOf(message()));
                    array[0][2] = message();
                    count++;
                }
                break;

            case R.id.box4:
                if (array[1][0] == 'x' || array[1][0] == 'o') {
                    Toast.makeText(getApplicationContext(), "This box is already filled!", Toast.LENGTH_SHORT).show();
                } else {
                    box4.setText(String.valueOf(message()));
                    array[1][0] = message();
                    count++;
                }
                break;

            case R.id.box5:
                if (array[1][1] == 'x' || array[1][1] == 'o') {
                    Toast.makeText(getApplicationContext(), "This box is already filled!", Toast.LENGTH_SHORT).show();
                } else {
                    box5.setText(String.valueOf(message()));
                    array[1][1] = message();
                    count++;
                }
                break;

            case R.id.box6:
                if (array[1][2] == 'x' || array[1][2] == 'o') {
                    Toast.makeText(getApplicationContext(), "This box is already filled!", Toast.LENGTH_SHORT).show();
                } else {
                    box6.setText(String.valueOf(message()));
                    array[1][2] = message();
                    count++;
                }
                break;

            case R.id.box7:
                if (array[2][0] == 'x' || array[2][0] == 'o') {
                    Toast.makeText(getApplicationContext(), "This box is already filled!", Toast.LENGTH_SHORT).show();
                } else {
                    box7.setText(String.valueOf(message()));
                    array[2][0] = message();
                    count++;
                }
                break;

            case R.id.box8:
                if (array[2][1] == 'x' || array[2][1] == 'o') {
                    Toast.makeText(getApplicationContext(), "This box is already filled!", Toast.LENGTH_SHORT).show();
                } else {
                    box8.setText(String.valueOf(message()));
                    array[2][1] = message();
                    count++;
                }
                break;

            case R.id.box9:
                if (array[2][2] == 'x' || array[2][2] == 'o') {
                    Toast.makeText(getApplicationContext(), "This box is already filled!", Toast.LENGTH_SHORT).show();
                } else {
                    box9.setText(String.valueOf(message()));
                    array[2][2] = message();
                    count++;
                }
                break;
        }
        turn.setText("Turn For " + toUpperCase(message()));

        if(checkWin('x'))
        {
            game = false;
            winner.setText("Player X Wins");
            btnReset.setVisibility(View.VISIBLE);
            return;
        }

        if(checkWin('o'))
        {
            game = false;
            winner.setText("Player O Wins");
            btnReset.setVisibility(View.VISIBLE);
            return;
        }

        if(count == 9)
        {
            game = false;
            winner.setText("Stalemate! No Winner");
            btnReset.setVisibility(View.VISIBLE);
        }}
        else
            Toast.makeText(getApplicationContext(), "GameOver!", Toast.LENGTH_SHORT).show();
    }

    public char message()
    {
        if(count % 2 == 0)
            return 'x';
        else
            return 'o';
    }

    public boolean checkWin(char player) {
        // Check rows, columns, and diagonals for a win
        for (int i = 0; i < 3; i++) {
            // Check rows
            if (array[i][0] == player && array[i][1] == player && array[i][2] == player) {
                //drawingView.drawLineWithAnimation(loc[i][0][0] + (int) (55 * scale + 0.5f), loc[i][0][1] - (int) (50 * scale + 0.5f), loc[i][2][0] + (int) (50 * scale + 0.5f), loc[i][2][1] - (int) (50 * scale + 0.5f));
                drawingView.drawLineWithAnimation(loc[i][0][0], loc[i][0][1], loc[i][2][0], loc[i][2][1]);
                return true;
            }
            // Check columns
            if (array[0][i] == player && array[1][i] == player && array[2][i] == player) {
                drawingView.drawLineWithAnimation(loc[0][i][0], loc[0][i][1], loc[2][i][0], loc[2][i][1]);
                return true;
            }
        }
        // Check diagonals
        if (array[0][0] == player && array[1][1] == player && array[2][2] == player) {
            drawingView.drawLineWithAnimation(loc[0][0][0], loc[0][0][1], loc[2][2][0], loc[2][2][1]);
            return true;
        }
        if (array[0][2] == player && array[1][1] == player && array[2][0] == player) {
            drawingView.drawLineWithAnimation(loc[0][2][0], loc[0][2][1], loc[2][0][0], loc[2][0][1]);
            return true;
        }
        return false;
    }

    public void getLoc()
    {
        int x = box1.getHeight()/2;
        box1.getLocationOnScreen(loc[0][0]);
        box2.getLocationOnScreen(loc[0][1]);
        box3.getLocationOnScreen(loc[0][2]);
        box4.getLocationOnScreen(loc[1][0]);
        box5.getLocationOnScreen(loc[1][1]);
        box6.getLocationOnScreen(loc[1][2]);
        box7.getLocationOnScreen(loc[2][0]);
        box8.getLocationOnScreen(loc[2][1]);
        box9.getLocationOnScreen(loc[2][2]);

        for(int i = 0; i <= 2; i++)
            for(int j = 0; j <= 2; j++)
                for(int k = 0; k < 2; k++)
                {
                    if(k%2 == 0)
                        loc[i][j][k] += x;

                    else
                        loc[i][j][k] += x - (50*scale+0.5f);
                }




    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getLoc(); // Ekran hazır olduğunda getLoc fonksiyonunu çağır.
        }
    }
}
