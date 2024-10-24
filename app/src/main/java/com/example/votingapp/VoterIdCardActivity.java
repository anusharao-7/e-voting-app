package com.example.votingapp;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class VoterIdCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voter_id_card);

        TextView nameTextView = findViewById(R.id.nameTextView);
        TextView fathersNameTextView = findViewById(R.id.fathersNameTextView);
        TextView dateOfBirthTextView = findViewById(R.id.dateOfBirthTextView);
        TextView sexTextView = findViewById(R.id.sexTextView);
        ImageView profileImageView = findViewById(R.id.profileImageView);

        // Retrieve data from the Intent
        String name = getIntent().getStringExtra("name");
        String fathersName = getIntent().getStringExtra("fathersName");
        String dob = getIntent().getStringExtra("dob");
        String sex = getIntent().getStringExtra("sex");
        String photoUriString = getIntent().getStringExtra("photoUri");

        // Set the retrieved data to the views
        nameTextView.setText("Name: " + name);
        fathersNameTextView.setText("Father's Name: " + fathersName);
        dateOfBirthTextView.setText("Date of Birth: " + dob);
        sexTextView.setText("Sex: " + sex);
        if (photoUriString != null) {
            Uri photoUri = Uri.parse(photoUriString);
            profileImageView.setImageURI(photoUri);
        }
    }
}
