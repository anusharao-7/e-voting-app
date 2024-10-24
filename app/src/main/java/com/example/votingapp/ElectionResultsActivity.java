package com.example.votingapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ElectionResultsActivity extends AppCompatActivity {

    private TableLayout tableLayout;
    private Button btnYear2022, btnYear2023, btnYear2024;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_election_results);

        tableLayout = findViewById(R.id.tableLayout);
        btnYear2022 = findViewById(R.id.btn_year_2022);
        btnYear2023 = findViewById(R.id.btn_year_2023);
        btnYear2024 = findViewById(R.id.btn_year_2024);

        // Set click listeners for filter buttons
        btnYear2022.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Filter results for 2022
                filterResultsByYear(2022);
            }
        });

        btnYear2023.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Filter results for 2023
                filterResultsByYear(2023);
            }
        });

        btnYear2024.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Filter results for 2024
                filterResultsByYear(2024);
            }
        });

        // Generate initial data for 2022
        filterResultsByYear(2022);
    }

    private void filterResultsByYear(int year) {
        // Clear existing table data
        tableLayout.removeAllViews();

        // Add table header
        TableRow headerRow = new TableRow(this);
        headerRow.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        addHeaderCell(headerRow, "Party");
        addHeaderCell(headerRow, "Candidate");
        addHeaderCell(headerRow, "Votes");

        tableLayout.addView(headerRow);

        // Add data rows based on the year
        switch (year) {
            case 2022:
                addDataRow("Party A", "Candidate A", "5000");
                addDataRow("Party B", "Candidate B", "3000");
                addDataRow("Party C", "Candidate C", "2000");
                break;
            case 2023:
                addDataRow("Party D", "Candidate D", "4000");
                addDataRow("Party E", "Candidate E", "3500");
                addDataRow("Party F", "Candidate F", "2500");
                break;
            case 2024:
                addDataRow("Party G", "Candidate G", "6000");
                addDataRow("Party H", "Candidate H", "4500");
                addDataRow("Party I", "Candidate I", "3000");
                break;
        }
    }

    private void addHeaderCell(TableRow row, String text) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setTextSize(18);  // Set text size
        textView.setPadding(16, 16, 16, 16);
        textView.setBackgroundResource(R.drawable.yellow_border);
        row.addView(textView);
    }

    private void addDataRow(String party, String candidate, String votes) {
        TableRow row = new TableRow(this);
        row.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        addDataCell(row, party);
        addDataCell(row, candidate);
        addDataCell(row, votes);

        tableLayout.addView(row);
    }

    private void addDataCell(TableRow row, String text) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setTextSize(18);  // Set text size
        textView.setPadding(16, 16, 16, 16);
        textView.setBackgroundResource(R.drawable.yellow_border);
        row.addView(textView);
    }
}
