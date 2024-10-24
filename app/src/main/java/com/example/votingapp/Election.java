package com.example.votingapp;

public class Election {
    private String date;
    private String time;
    private String description;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int id; // Unique identifier for setting notifications

    public Election(String date, String time, String description, int year, int month, int day, int hour, int minute) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.id = (int) System.currentTimeMillis(); // Generate a unique ID based on the current time
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getId() {
        return id;
    }
}
