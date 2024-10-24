package com.example.votingapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CandidateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate);

        LinearLayout candidatesContainer = findViewById(R.id.candidatesContainer);

        // Add candidate 1
        addCandidateCard(candidatesContainer, "John Smith", "Democratic Party", "Senator", "Governor", Color.BLUE);

        // Add candidate 2
        addCandidateCard(candidatesContainer, "Jane Doe", "Republican Party", "Mayor", "Senator", Color.RED);

        // Add candidate 3
        addCandidateCard(candidatesContainer, "Alex Johnson", "Independent", "Council Member", "Mayor", Color.GREEN);
    }

    private void addCandidateCard(LinearLayout container, String name, String party, String currentPost, String runningFor, int backgroundColor) {
        View candidateCard = LayoutInflater.from(this).inflate(R.layout.candidate_card, container, false);

        ((TextView) candidateCard.findViewById(R.id.nameTextView)).setText(name);
        ((TextView) candidateCard.findViewById(R.id.partyTextView)).setText("Party: " + party);
        ((TextView) candidateCard.findViewById(R.id.currentPostTextView)).setText("Current Post: " + currentPost);
        ((TextView) candidateCard.findViewById(R.id.runningForTextView)).setText("Running For: " + runningFor);

        candidateCard.setBackgroundColor(backgroundColor);

        container.addView(candidateCard);
    }
}
