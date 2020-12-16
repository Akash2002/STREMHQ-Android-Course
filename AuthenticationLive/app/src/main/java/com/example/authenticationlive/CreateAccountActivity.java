package com.example.authenticationlive;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

/**
 * This class represents the CreateAccount page where the users will be able to create their account
 * with an email and a password. You will learn how to use buttons, edit texts, and SnackBars as a
 * part of this project section.
 * @author Akash Veerappan
 * @version 1.0.0
 */
public class CreateAccountActivity extends AppCompatActivity {

    private TextInputEditText emailEditText;
    private TextInputEditText passwordEditText;
    private TextInputEditText confirmPasswordEditText;
    private Button createAccountButton;
    private TextView loginTextView;
    private static ConstraintLayout layout;

    /**
     *
     * This method represents the overridden onCreate method that runs as soon as the app is created. We
     * initialize all objects and provide main functionality here.
     * @param savedInstanceState this variable is beyond the scope of the course, but think along the lines of
     *                           this - if you close this app and reopen it, it checks to see if there is a
     *                           saved state and if there is it loads that state up.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
        createAccountButton = findViewById(R.id.createAccountButton);
        loginTextView = findViewById(R.id.loginTextView);
        layout = findViewById(R.id.layout); // CreateAccountActivity's layout

        emailEditText.setText("akash@2002.com");
        passwordEditText.setText("test#1234");
        confirmPasswordEditText.setText("test#1234");

        /*
         * This method represents a button action for the login button. This will finish the activity, meaning
         * it will go back to the previous activity it was called from. The request code is beyond the scope of this
         * course, but if you are interested, research about startActivityForResult.
         */
        loginTextView.setOnClickListener(view -> {
            finish();
        });

        /*
         * This lambda represents a button action where the we tell the app to check for all the authentication information.
         * The shorthand conditional that sets up the three string variables is a useful tool to use to initialize variables.
         * We then show SnackBars if any entered information is invalid.
         */
        createAccountButton.setOnClickListener(view -> {
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            String confirmPassword = confirmPasswordEditText.getText().toString();

            if (email.length() > 6 && email.contains("@")) {
                if (password.length() > 7 && confirmPassword.length() > 7) {
                    if (password.equals(confirmPassword)) {
                        Toast.makeText(getApplicationContext(), "Authentication success", Toast.LENGTH_LONG).show();
                        System.out.println(storeUserData(email, password));
                        emailEditText.setText("");
                        passwordEditText.setText("");
                        confirmPasswordEditText.setText("");
                        emailEditText.requestFocus();
                        finish();
                    } else {
                        showWarningSnackBar(layout,"Password fields don't match. Ensure that confirm password is the same as password.");
                    }
                } else {
                    showWarningSnackBar(layout, "Password fields are invalid. Must be at least 7 characters long.");
                }
            } else {
                showWarningSnackBar(layout, "Email field is invalid. Must be at least 6 characters long.");
            }
        });
    }

    /* Notes for SharedPreferences
     * SharedPreference -> stores things in a key value pair
     * dict in python
     * {"book": "something you read", "cat": "a meowing animal"} -> Map in Java
     * [book, cat, ...] -> {1: book, 2: cat, ...}
     *   1    2
     * {email: password}
     * store {email: password} in SharedPreference (ID/name: 1) -> user #1
     * store {email2: password2} in SharedPreference (ID/name: 1) -> user #2
     * SharedPreference: stores the user data in the phone (the app remembers it even after you close it) -> stores a file in your phone as part of that app
    */

    /**
     * This method represents the functionality to save our data to our SharedPreferences. Remember that it stores the information in an XML key-value pair
     * file that belongs to the app. You can access this file in the Device File Explorer under the View -> Tool Windows tab.
     * @param email the email that needs to be saved
     * @param password the password that needs to be saved
     * @return true if the save was successful and false otherwise
     */
    private boolean storeUserData(String email, String password) {
        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);
        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
        sharedPreferencesEditor.putString(email, password);
        return sharedPreferencesEditor.commit();
    }

    /**
     * This method represents the helper that shows a SnackBar to warn the user of a potential authentication error
     * @param layout the layout in which to show the SnackBar. Remember that this changes with between the different pages (MainActivity and CreateAccountActivity)
     * @param text the message that needs to be showed
     */
    public static void showWarningSnackBar(View layout, String text) {
        Snackbar s = Snackbar.make(layout, text, Snackbar.LENGTH_INDEFINITE);
        s.setAction("Retry", v -> {
            s.dismiss();
        });
        s.show();
    }

}