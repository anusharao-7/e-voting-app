package com.example.votingapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CandidateDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_details);

        ImageView candidateImageView = findViewById(R.id.candidateImageView);
        TextView nameTextView = findViewById(R.id.nameTextView);
        TextView partyTextView = findViewById(R.id.partyTextView);
        TextView stateTextView = findViewById(R.id.stateTextView);

        // Retrieve data from intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            candidateImageView.setImageResource(extras.getInt("imageResId"));
            nameTextView.setText(extras.getString("name"));
            partyTextView.setText("Party: " + extras.getString("party"));
            stateTextView.setText("State: " + extras.getString("state"));
        }
    }
}
