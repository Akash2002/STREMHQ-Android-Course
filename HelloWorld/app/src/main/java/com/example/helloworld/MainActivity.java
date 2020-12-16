package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This class represents the main entry point of our application. In this case, we are looking at
 * a simple activity that shows a TextView and an EditText and a button that enables the setting of the
 * TextView's text to the one that is entered in the EditText.
 */
public class MainActivity extends AppCompatActivity {

    TextView hellotextView;

    EditText nameEditText;

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

        hellotextView = findViewById(R.id.helloTextView);

        nameEditText = findViewById(R.id.nameEditText);

        hellotextView.setText("We changed it!");

        nameEditText.setText("Akash");

    }

    public void submitButtonClicked(View view) {
        String nameString = nameEditText.getText().toString();
        hellotextView.setText(nameString);
    }

}