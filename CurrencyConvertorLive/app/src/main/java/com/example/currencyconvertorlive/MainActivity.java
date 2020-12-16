package com.example.currencyconvertorlive;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This class represents the MainActivity that the application launches into.
 * @author Akash Veerappan
 * @version 1.0.0
 */
public class MainActivity extends AppCompatActivity {

    TextView rupeeTextView;
    TextView euroTextView;
    TextView yenTextView;

    EditText dollarEditText;

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
        setContentView(R.layout.activity_main); // finalizes the design layout

        rupeeTextView = findViewById(R.id.rupeeTextView);
        euroTextView = findViewById(R.id.euroTextView);
        yenTextView = findViewById(R.id.yenTextView);

        dollarEditText = findViewById(R.id.editTextNumberDecimal);

    }

    /**
     * This method represents the button action for when the Convert button is clicked. We want to
     * convert from one value to the other. Since we convert from dollars to three other currencies, we
     * find the conversion factor and multiply them to the double values, as doubles represent the largest
     * primitive decimal values in Java. We then append the values to the text so that the user will know
     * what currency they are looking at. Java automatically casts the Double to String when we setText.
     * @param view The view that is calling the action - in this case the Convert Button.
     */
    public void convertClicked (View view) {

        Double dollars = Double.valueOf(dollarEditText.getText().toString()); // decimal

        Double euros = dollars * 0.85;
        Double rupees = dollars * 75.03;
        Double yen = dollars * 105.94;

        euroTextView.setText(euros.toString() + " euros");
        rupeeTextView.setText(rupees.toString() + " rupees");
        yenTextView.setText(yen.toString() + " yen");

    }

}