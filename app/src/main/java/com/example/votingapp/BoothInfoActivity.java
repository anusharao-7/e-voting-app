package com.example.votingapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;
import java.util.Map;

public class BoothInfoActivity extends AppCompatActivity {

    private Map<Integer, String> voterIdToPlaceMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booth_info);

        initializeVoterIdToPlaceMap();

        RadioGroup ageGroup = findViewById(R.id.ageGroup);
        EditText voterIdInput = findViewById(R.id.voterIdInput);
        Button submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(v -> {
            int selectedId = ageGroup.getCheckedRadioButtonId();
            String voterIdStr = voterIdInput.getText().toString().trim();

            if (selectedId == -1) {
                Toast.makeText(BoothInfoActivity.this, "Please select your age group", Toast.LENGTH_SHORT).show();
            } else if (voterIdStr.isEmpty()) {
                Toast.makeText(BoothInfoActivity.this, "Please enter your voter ID", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    int voterId = Integer.parseInt(voterIdStr);
                    if (selectedId == R.id.yesButton) {
                        String place = getPlaceForVoterId(voterId);
                        if (place != null) {
                            Toast.makeText(BoothInfoActivity.this, "Your booth location: " + place, Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(BoothInfoActivity.this, "Invalid voter ID", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(BoothInfoActivity.this, "You must be above 18 to vote", Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(BoothInfoActivity.this, "Invalid voter ID", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initializeVoterIdToPlaceMap() {
        voterIdToPlaceMap = new HashMap<>();
        for (int i = 1; i <= 68; i++) {
            if (i % 2 == 0) {
                voterIdToPlaceMap.put(i, "Dharwad");
            } else {
                voterIdToPlaceMap.put(i, "Hubli");
            }
        }
    }

    private String getPlaceForVoterId(int voterId) {
        return voterIdToPlaceMap.get(voterId);
    }
}
