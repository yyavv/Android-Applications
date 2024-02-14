package com.example.taskmanager;

import static java.lang.Math.pow;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;
import java.util.Calendar;

public class AgeCalculator extends AppCompatActivity {
    TextView age_int, header;
    TextInputEditText Year, Month, Day, Hour, Minute;
    int year, month, day, hour = 0, minute = 0;
    double sum = 0;
    Button calculate, stop;

    DecimalFormat df;

    Calendar date = Calendar.getInstance();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agelayout);

        age_int = findViewById(R.id.age_int);

        Year = findViewById(R.id.age_year);
        Month = findViewById(R.id.age_month);
        Day = findViewById(R.id.age_day);
        Hour = findViewById(R.id.age_hour);
        Minute = findViewById(R.id.age_minute);

        stop = findViewById(R.id.age_btn_stop);
        calculate = findViewById(R.id.age_btn_calc);
        calculate.setOnClickListener(v -> {
            year = Integer.parseInt(Year.getText().toString());
            month = Integer.parseInt(Month.getText().toString()) - 1;
            day = Integer.parseInt(Day.getText().toString());
            if (!TextUtils.isEmpty(Hour.getText().toString()))
                hour = Integer.parseInt(Hour.getText().toString());
            if (!TextUtils.isEmpty(Minute.getText().toString()))
                minute = Integer.parseInt(Minute.getText().toString());


            date.set(year, month, day, hour, minute);
            header = findViewById(R.id.age_header);
            header.setText(String.valueOf(date.getTime()));

            stop.setVisibility(View.VISIBLE);

            // Runnable'ı başlat
            handler.postDelayed(runnable, 500);
        });

        stop.setOnClickListener(v -> {
            handler.removeCallbacks(runnable);
        });

    }

    public void calculate()
    {
        df = new DecimalFormat("###.##########");
        sum = Calendar.getInstance().getTimeInMillis() - date.getTimeInMillis();
        header = findViewById(R.id.age_header);
        double sum1 = sum / 31556952000d;
        //header.setText(String.valueOf(sum1));
        sum /= (3.1556926 * pow(10, 10));
        int sumi = (int) sum;
        double x = (1 - sum - sumi) / 60000;
        //header.setText(String.valueOf(x));
        age_int.setText(String.valueOf(sum));
        //age_decimal.setText(String.valueOf(sum - sumi));
    }

    final Handler handler = new Handler();
    final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // Burada geciktirilmiş işlemlerinizi gerçekleştirin
            calculate();
            // Runnable'ı tekrar başlat
            handler.postDelayed(this, 500);
        }
    };


        /*Thread thread = new Thread() {

        @Override
        public void run() {
            try {
                while (!thread.isInterrupted()) {
                    Thread.sleep(500);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            df = new DecimalFormat("###.##########");

                            sum = Calendar.getInstance().getTimeInMillis() - date.getTimeInMillis();
                            header = findViewById(R.id.age_header);
                            double sum1 = sum / 31556952000d;
                            //header.setText(String.valueOf(sum1));
                            sum /= (3.1556926 * pow(10, 10));
                            int sumi = (int) sum;
                            double x = (1 - sum - sumi) / 60000;
                            //header.setText(String.valueOf(x));
                            age_int.setText(String.valueOf(sum));
                            //age_decimal.setText(String.valueOf(sum - sumi));

                        }
                    });
                }
            } catch (InterruptedException e) {
            }
        }
    };*/
}
