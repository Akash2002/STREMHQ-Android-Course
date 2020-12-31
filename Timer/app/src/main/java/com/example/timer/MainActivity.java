package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

/**
 * This class represents the main activity of the Timer app. This is a one-page application and we
 * write all the code here.
 * @author Akash Veerappan
 * @version 1.0.0
 * @date 12.24.2020
 */

public class MainActivity extends AppCompatActivity {

    private EditText hoursEditText;
    private EditText minutesEditText;
    private EditText secondsEditText;

    private Button actionButton;
    private Button resetButton;
    private Button clearButton;

    private CountDownTimer countDownTimer;

    private TextView timerTextView;

    private Long timerSeconds;
    private Long timeLeft;
    private boolean hasStoppedAfterStart;
    private boolean hasStartedInitially;

    /**
     * This method represents the overridden onCreate method that runs as soon as the app is created. We
     * initialize all objects and provide main functionality here.
     * @param savedInstanceState this variable is beyond the scope of the course, but think along the lines of
     *                           this - if you close this app and reopen it, it checks to see if there is a
     *                           saved state and if there is it loads that state up.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hoursEditText = findViewById(R.id.hoursEditText);
        minutesEditText = findViewById(R.id.minutesEditText);
        secondsEditText = findViewById(R.id.secondsEditText);

        actionButton = findViewById(R.id.startButton);
        resetButton = findViewById(R.id.resetButton);
        clearButton = findViewById(R.id.clearButton);

        timerTextView = findViewById(R.id.timerTextView);

        /*
         * Here we define the reset functionality where we start from the initial timer seconds value
         * (the value from the edit texts). We also continue saving the remaining time to be able to
         * stop and resume the timer later. Both the booleans are set to false as the initial values from
         * the start of the program where they were both initialized to be false.
         */
        resetButton.setOnClickListener(view -> {
            if (countDownTimer != null) {
                countDownTimer.cancel();
                if (timerSeconds != 0) {
                    System.out.println(timerSeconds);
                    countDownTimer = new CountDownTimer(timerSeconds * 1000, 1000) {
                        @Override
                        public void onTick(long l) {
                            timerTextView.setText(getFormattedTimerText(l));
                            timeLeft = l;
                        }

                        @Override
                        public void onFinish() {
                            timerTextView.setText(getResources().getString(R.string.done));
                            hasStartedInitially = false;
                            hasStoppedAfterStart = false;
                            actionButton.setText(getResources().getString(R.string.start));
                            chime();
                        }
                    }.start();
                }
            }
        });

        /*
         * Here we define the clear functionality that basically resets the timer for the user might have
         * started the timer unintentionally or with the incorrect timer parameters, so this feature
         * allows the user to input values again. Essentially we are resetting everything.
         */
        clearButton.setOnClickListener(view -> {
            countDownTimer.cancel();
            timerSeconds = 0L;
            timeLeft = 0L;
            clearFields();
            actionButton.setText(getResources().getString(R.string.start));
            hasStartedInitially = false;
            hasStoppedAfterStart = false;
            timerTextView.setText(getResources().getString(R.string.default_text));
        });

        /*
        Start -> when not started
        Stop -> after it has been started and when the timer finishes
        Resume -> when started but not stopped
         */

        /*
         * Here we define the main action button functionality. Although there is an explanation video
         * in this section on Udemy, here is a short explanation: we check for what action we want to perform
         * and set the text of the button to be the next action's text. When we are starting the timer,
         * we want to use the edit text timer seconds, when we resume the timer we want to use the time left
         * (resume is when the timer has started but has not stopped after it has started, but this function
         * will be invoked when both booleans are true as the timer should have been started and stopped after
         * started), and when we stop the timer we want to cancel the timer
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
                                timerTextView.setText(getFormattedTimerText(l));
                                timeLeft = l;
                            }

                            @Override
                            public void onFinish() {
                                timerTextView.setText(getResources().getString(R.string.done));
                                hasStartedInitially = false;
                                hasStoppedAfterStart = false;
                                actionButton.setText(getResources().getString(R.string.start));
                                chime();
                            }
                        }.start();

                        actionButton.setText(getResources().getString(R.string.stop)); // next action button

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
                    actionButton.setText(getResources().getString(R.string.resume)); // next action
                    hasStoppedAfterStart = true;
                }
            } else {
                if (countDownTimer != null) {
                    countDownTimer.cancel();
                    hasStoppedAfterStart = false;
                    countDownTimer = new CountDownTimer(timeLeft, 1000) {
                        @Override
                        public void onTick(long l) {
                            timerTextView.setText(getFormattedTimerText(l));
                            timeLeft = l;
                        }

                        @Override
                        public void onFinish() {
                            timerTextView.setText(getResources().getString(R.string.done));
                            hasStartedInitially = false;
                            hasStoppedAfterStart = false;
                            actionButton.setText(getResources().getString(R.string.start));
                            chime();
                        }
                    }.start();
                    actionButton.setText(getResources().getString(R.string.stop));
                }
            }
        });
    }

    /**
     * This method represents the clearing of all fields once the timer has been started.
     */
    private void clearFields () {
        hoursEditText.setText("0");
        minutesEditText.setText("0");
        secondsEditText.setText("0");
    }

    /**
     * This method represents the functionality that formats the milliseconds into readable timer text.
     * @param millis milliseconds to be formatted from a long to a string
     * @return
     */
    private String getFormattedTimerText(long millis) {
        int hours = (int) (millis/1000/60/60);
        millis = millis - hours * 3600 * 1000;
        int minutes = (int) (millis/1000/60);
        millis = millis - minutes * 1000 * 60;
        int seconds = (int) millis/1000;
        return String.format(Locale.US, "%02d:%02d:%02d", hours, minutes, seconds);
    }

    /**
     * This method represents the functionality that plays an mp3 tune after the timer is done.
     */
    private void chime () {
        MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.alarm);
        mediaPlayer.start();
    }

}