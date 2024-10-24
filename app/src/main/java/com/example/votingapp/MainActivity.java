package com.example.votingapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check if the user is logged in
        boolean userLoggedIn = checkIfUserLoggedIn(); // Implement this method to check if user is logged in

        // If user is logged in, go to HomeActivity; otherwise, go to LoginActivity
        if (userLoggedIn) {
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
        } else {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }

        // Finish MainActivity so that it's not in the back stack
        finish();
    }

    // Dummy method to check if user is logged in (replace with your actual logic)
    private boolean checkIfUserLoggedIn() {
        // For demonstration purpose, always return false (user not logged in)
        return false;
    }
}
