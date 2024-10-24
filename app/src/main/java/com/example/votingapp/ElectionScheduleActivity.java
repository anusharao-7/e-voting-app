package com.example.votingapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import android.Manifest;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ElectionScheduleActivity extends AppCompatActivity {

    private RecyclerView electionDetailsRecyclerView;
    private ElectionScheduleAdapter electionScheduleAdapter;
    private List<Election> electionList;
    private Button sendNotificationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_election_schedule);

        electionDetailsRecyclerView = findViewById(R.id.electionDetailsRecyclerView);
        electionDetailsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        sendNotificationButton = findViewById(R.id.sendNotificationButton);
        sendNotificationButton.setOnClickListener(view -> {
            setupNotifications();
            Toast.makeText(this, "Notifications set up", Toast.LENGTH_SHORT).show();
        });

        electionList = new ArrayList<>();
        // Add sample data
        electionList.add(new Election("01/01/2024", "10:00 AM", "Presidential Election", 2024, 0, 1, 10, 0));
        electionList.add(new Election("02/01/2024", "11:00 AM", "Senate Election", 2024, 1, 1, 11, 0));
        // Add more data as needed

        electionScheduleAdapter = new ElectionScheduleAdapter(electionList);
        electionDetailsRecyclerView.setAdapter(electionScheduleAdapter);
    }

    private void setupNotifications() {
        for (Election election : electionList) {
            Calendar electionCalendar = Calendar.getInstance();
            electionCalendar.set(election.getYear(), election.getMonth(), election.getDay(), election.getHour(), election.getMinute(), 0);

            Calendar now = Calendar.getInstance();
            Intent intent = new Intent(this, NotificationReceiver.class);

            if (electionCalendar.after(now)) {
                intent.putExtra("notificationMessage", "Reminder: Don't forget to vote in the " + election.getDescription() + " on " + election.getDate());
            } else {
                intent.putExtra("notificationMessage", "Election: " + election.getDescription() + " was on " + election.getDate());
            }

            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, election.getId(), intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            if (alarmManager != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, electionCalendar.getTimeInMillis(), pendingIntent);
                } else {
                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, electionCalendar.getTimeInMillis(), pendingIntent);
                }
            } else {
                Toast.makeText(this, "Error setting up notifications", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
