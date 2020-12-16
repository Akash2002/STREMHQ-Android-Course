package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;

public class MainActivity extends AppCompatActivity {

    private EditText hoursEditText;
    private EditText minutesEditText;
    private EditText secondsEditText;

    private Button startButton;
    private Button resetButton;

    private TextView timerTextView;

    private Long timerSeconds;
    private boolean hasStarted;
    private boolean hasStopped;
    private long timeLeft;

    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hoursEditText = findViewById(R.id.hoursEditText);
        minutesEditText = findViewById(R.id.minutesEditText);
        secondsEditText = findViewById(R.id.secondsEditText);

        startButton = findViewById(R.id.startButton);
        resetButton = findViewById(R.id.resetButton);

        timerTextView = findViewById(R.id.timerTextView);

        resetButton.setOnClickListener(view -> {
            if (countDownTimer != null) {
                countDownTimer.cancel();
                countDownTimer.start();
            }
            if (hasStopped) {
                startButton.setText("Stop");
                hasStopped = !hasStopped;
            }
        });

        startButton.setOnClickListener(view -> {

            String hours = hoursEditText.getText().toString();
            String minutes = minutesEditText.getText().toString();
            String seconds = secondsEditText.getText().toString();

            Long h = Long.parseLong(hours);
            Long m = Long.parseLong(minutes);
            Long s = Long.parseLong(seconds);

            if (!hasStarted) {
                if (m < 60) {
                    if (s < 60) {
                        // hours minutes and seconds to milliseconds
                        Log.i("Timer", "Started");
                        timerSeconds = (m * 60 + h * 3600 + s);
                        timerTextView.setText(timerSeconds + " s");
                        hasStarted = true;
                        hasStopped = false;
                        countDownTimer = new CountDownTimer(timerSeconds * 1000, 1000) {
                            @Override
                            public void onTick(long l) {
                                timeLeft = l;
                                timerTextView.setText(l / 1000 + " s");
                            }

                            @Override
                            public void onFinish() {
                                timerTextView.setText("Done");
                                hasStarted = false;
                                startButton.setText("Start");
                            }
                        }.start();

                        clearFields();

                        startButton.setText("Stop");

                    } else {
                        Toast.makeText(getApplicationContext(), "Ensure that seconds are less than 60.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Ensure that minutes are less than 60.", Toast.LENGTH_SHORT).show();
                }
            } else if (!hasStopped) {
                if (countDownTimer != null) {
                    countDownTimer.cancel();
                    Log.i("Timer", "Stopped");
                    hasStopped = true;
                    startButton.setText("Resume");
                }
            } else {
                if (countDownTimer != null) {
                    countDownTimer.cancel();
                    hasStopped = false;
                    countDownTimer = new CountDownTimer(timeLeft, 1000) {

                        @Override
                        public void onTick(long l) {
                            timerTextView.setText(l / 1000 + " s");
                            timeLeft = l;
                        }

                        @Override
                        public void onFinish() {
                            timerTextView.setText("Done");
                            startButton.setText("Start");
                            hasStarted = false;
                        }
                    }.start();
                    Log.i("Timer", "Resumed");
                    startButton.setText("Stop");
                }
            }

        });

    }

    /*
    if (...) {

    } else {

    }
    x()
     */

    private void clearFields () {
        hoursEditText.setText("0");
        minutesEditText.setText("0");
        secondsEditText.setText("0");
    }

    private boolean isLong (String s) {
        // try catch
        try {
            Long.parseLong(s); // 5000 or this
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }













}