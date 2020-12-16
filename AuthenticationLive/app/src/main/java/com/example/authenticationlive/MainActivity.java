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

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.Map;

/**
 * This class represents the launching point of the application - the Login screen. We provide simple login access
 * and the option for the user to go to create an account on the Create Account page.
 * @author Akash Veerappan
 * @version 1.0.0
 */
public class MainActivity extends AppCompatActivity {

    private TextInputEditText emailEditText;
    private TextInputEditText passwordEditText;
    private Button loginButton;
    private TextView signUpTextView;
    private ConstraintLayout layout;

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

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        signUpTextView = findViewById(R.id.signUpButton);
        layout = findViewById(R.id.layout); // MainActivity's layout

        /*
         * This method represents the button action for the create account button. Here we instantiate
         * an Intent (think we intend to go to another screen) and tell it to go from the MainActivity
         * to the CreateAccount class. Then we start the activity.
         */
        signUpTextView.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), CreateAccountActivity.class);
            startActivity(intent);
        });

        /*
         * This method represents the button action for login button. Ideally, we will have to check if the
         * login credentials are valid, but since we are not using an authentication service or a backend,
         * we will just resort to check if they entered something at all and making a Toast message.
         */
        loginButton.setOnClickListener(view -> {

            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            if (email.length() > 6 && email.contains("@")) {
                if (password.length() > 7) {
                    if (isCredentialValid(email, password)) {
                        Toast.makeText(getApplicationContext(), "Authentication Successful", Toast.LENGTH_LONG).show();
                    } else {
                        CreateAccountActivity.showWarningSnackBar(layout, "Authentication failed. Please check your email and password credentials.");
                    }
                } else {
                    CreateAccountActivity.showWarningSnackBar(layout, "Password fields are invalid. Must be at least 7 characters long.");
                }
            } else {
                CreateAccountActivity.showWarningSnackBar(layout, "Email field is invalid. Must be at least 6 characters long.");
            }
        });

    }

    /**
     * This method represents the functionality to retrieve the saved SharedPreferences data with Map<String, ?> that stores a key-value pair. We retrieve all
     * key-value pairs and analyze them to check if any email matches and if so if any password matches.
     * @param email the email to be checked
     * @param password the password to be checked
     * @return true if there is a match and false otherwise
     */
    private boolean isCredentialValid(String email, String password) {
        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);
        Map<String, ?> authMap = sharedPreferences.getAll();
        for (Map.Entry<String, ?> entry : authMap.entrySet()) {
            if (entry.getKey().equals(email)) {
                if (entry.getValue().toString().equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }

}