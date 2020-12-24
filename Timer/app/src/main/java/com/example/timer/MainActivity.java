package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText hoursEditText;
    private EditText minutesEditText;
    private EditText secondsEditText;

    private Button actionButton;
    private Button resetButton;

    private CountDownTimer countDownTimer;

    private TextView timerTextView;

    private Long timerSeconds;
    private Long timeLeft;
    private boolean hasStoppedAfterStart;
    private boolean hasStartedInitially;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hoursEditText = findViewById(R.id.hoursEditText);
        minutesEditText = findViewById(R.id.minutesEditText);
        secondsEditText = findViewById(R.id.secondsEditText);

        actionButton = findViewById(R.id.startButton);
        resetButton = findViewById(R.id.resetButton);

        timerTextView = findViewById(R.id.timerTextView);

        resetButton.setOnClickListener(view -> {
            if (countDownTimer != null) {
                countDownTimer.cancel();
                if (timerSeconds != 0) {
                    System.out.println(timerSeconds);
                    countDownTimer = new CountDownTimer(timerSeconds * 1000, 1000) {
                        @Override
                        public void onTick(long l) {
                            timerTextView.setText(l / 1000 + " s");
                            timeLeft = l;
                        }

                        @Override
                        public void onFinish() {
                            timerTextView.setText("Done");
                            hasStartedInitially = false;
                            hasStoppedAfterStart = false;
                            actionButton.setText("Start");
                        }
                    }.start();
                }
            }
        });

        /*
        Start -> when not started
        Stop -> after it has been started and when the timer finishes
        Resume -> when started but not stopped
         */

        actionButton.setOnClickListener(view -> {

            String hours = hoursEditText.getText().toString();
            String minutes = minutesEditText.getText().toString();
            String seconds = secondsEditText.getText().toString();

            Long h = Long.parseLong(hours);
            Long m = Long.parseLong(minutes);
            Long s = Long.parseLong(seconds);

            if (!hasStartedInitially) {
                if (m < 60) {
                    if (s < 60) {
                        // hours minutes and seconds to milliseconds
                        Log.i("Timer", "Started");
                        timerSeconds = (m * 60 + h * 3600 + s);
                        timerTextView.setText(timerSeconds + " s");
                        hasStartedInitially = true;
                        countDownTimer = new CountDownTimer(timerSeconds * 1000, 1000) {
                            @Override
                            public void onTick(long l) {
                                timerTextView.setText(l / 1000 + " s");
                                timeLeft = l;
                            }

                            @Override
                            public void onFinish() {
                                timerTextView.setText("Done");
                                hasStartedInitially = false;
                                hasStoppedAfterStart = false;
                                actionButton.setText("Start");
                            }
                        }.start();

                        actionButton.setText("Stop"); // next action button

                        clearFields();

                    } else {
                        Toast.makeText(getApplicationContext(), "Ensure that seconds are less than 60.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Ensure that minutes are less than 60.", Toast.LENGTH_SHORT).show();
                }
            } else if (!hasStoppedAfterStart) { // it would have been stop
                if (countDownTimer != null) {
                    countDownTimer.cancel();
                    actionButton.setText("Resume"); // next action
                    hasStoppedAfterStart = true;
                }
            } else {
                if (countDownTimer != null) {
                    countDownTimer.cancel();
                    hasStoppedAfterStart = false;
                    countDownTimer = new CountDownTimer(timeLeft, 1000) {
                        @Override
                        public void onTick(long l) {
                            timeLeft = l;
                            timerTextView.setText(l / 1000 + " s");
                        }

                        @Override
                        public void onFinish() {
                            timerTextView.setText("Done");
                            hasStartedInitially = false;
                            hasStoppedAfterStart = false;
                            actionButton.setText("Start");
                        }
                    }.start();
                    actionButton.setText("Stop");
                }
            }
        });
    }

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