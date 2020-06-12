package com.example.ad340imperialregistryweek2;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity
public class User {
    @PrimaryKey
    @NonNull
    private String email;

    @ColumnInfo(name = "daily_matches_reminder_time")
    private String dailyMatchesReminderTime;

    @ColumnInfo(name = "maximum_distance_search")
    private String maximumDistanceSearch;

    @ColumnInfo(name = "gen_der")
    private String gender;

    @ColumnInfo(name = "interested_age_range")
    private String interestedAgeRange;

    @ColumnInfo(name = "private_public_account")
    private String PrivatePublicAccount;

    @NonNull

    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDailyMatchesReminderTime() {
        return dailyMatchesReminderTime;
    }

    public void setDailyMatchesReminderTime(String dailyMatchesReminderTime) {
        this.dailyMatchesReminderTime = dailyMatchesReminderTime;
    }

    public String getMaximumDistanceSearch() {
        return maximumDistanceSearch;
    }

    public void setMaximumDistanceSearch(String maximumDistanceSearch) {
        this.maximumDistanceSearch = maximumDistanceSearch;
    }

    public String getPrivatePublicAccount() {
        return PrivatePublicAccount;
    }

    public void setPrivatePublicAccount(String privatePublicAccount) {
        PrivatePublicAccount = privatePublicAccount;
    }

    public String getInterestedAgeRange() {
        return interestedAgeRange;
    }

    public void setInterestedAgeRange(String interestedAgeRange) {
        this.interestedAgeRange = interestedAgeRange;
    }
}