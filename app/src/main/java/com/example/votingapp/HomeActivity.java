package com.example.votingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        CardView electionScheduleCard = findViewById(R.id.electionScheduleCard);
        electionScheduleCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ElectionScheduleActivity.class);
                startActivity(intent);
            }
        });

        CardView boothInfoCard = findViewById(R.id.boothCard);
        boothInfoCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, BoothInfoActivity.class);
                startActivity(intent);
            }
        });

        CardView profileCard = findViewById(R.id.profileCard);
        profileCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        CardView candidatesCard = findViewById(R.id.candidatesCard);
        candidatesCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, CandidateActivity.class);
                startActivity(intent);
            }
        });

        // Navigate to ElectionResultsActivity when Election Results card is clicked
        CardView electionResultsCard = findViewById(R.id.updateVoterIdCard);
        electionResultsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ElectionResultsActivity.class);
                startActivity(intent);
            }
        });

        CardView newVoterIdCard = findViewById(R.id.newVoterIdCard);
        newVoterIdCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, NewVoterIdActivity.class);
                startActivity(intent);
            }
        });
    }
}
