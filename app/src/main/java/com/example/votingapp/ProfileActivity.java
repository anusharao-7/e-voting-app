package com.example.votingapp;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class ProfileActivity extends AppCompatActivity {

    private EditText editName, editBirthday, editPhoneNumber, editEmail, editPassword;
    private Button buttonEditProfile; // Corrected button variable name
    private SharedPreferences sharedPreferences;
    private String email;
    private static final String PREFS_NAME = "UserProfilePrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        editName = findViewById(R.id.editName);
        editBirthday = findViewById(R.id.editBirthday);
        editPhoneNumber = findViewById(R.id.editPhoneNumber);
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        buttonEditProfile = findViewById(R.id.buttonEditProfile); // Corrected button initialization

        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        email = getIntent().getStringExtra("email");

        loadProfileInfo();

        editBirthday.setOnClickListener(v -> showDatePicker());

        buttonEditProfile.setOnClickListener(v -> saveProfileInfo());
    }

    private void showDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                ProfileActivity.this,
                (view, year1, monthOfYear, dayOfMonth) -> editBirthday.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1),
                year, month, day);
        datePickerDialog.show();
    }

    private void loadProfileInfo() {
        String name = sharedPreferences.getString(email + "_name", "");
        String birthday = sharedPreferences.getString(email + "_birthday", "");
        String phoneNumber = sharedPreferences.getString(email + "_phoneNumber", "");
        String storedEmail = sharedPreferences.getString(email + "_email", email); // Default to current email
        String password = sharedPreferences.getString(email + "_password", "");

        editName.setText(name);
        editBirthday.setText(birthday);
        editPhoneNumber.setText(phoneNumber);
        editEmail.setText(storedEmail);
        editPassword.setText(password);
    }

    private void saveProfileInfo() {
        String name = editName.getText().toString().trim();
        String birthday = editBirthday.getText().toString().trim();
        String phoneNumber = editPhoneNumber.getText().toString().trim();
        String emailInput = editEmail.getText().toString().trim();
        String password = editPassword.getText().toString().trim();

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(email + "_name", name);
        editor.putString(email + "_birthday", birthday);
        editor.putString(email + "_phoneNumber", phoneNumber);
        editor.putString(email + "_email", emailInput);
        editor.putString(email + "_password", password);

        editor.apply();

        Toast.makeText(ProfileActivity.this, "Profile information saved", Toast.LENGTH_SHORT).show();
    }
}
