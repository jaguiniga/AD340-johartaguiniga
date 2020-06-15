package com.example.ad340imperialregistryweek2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

public class SettingsFragment extends Fragment {

    public EditText dailyMatchesReminderTime;
    public EditText maximumDistanceSearch;
    public EditText gender;
    public EditText accountType;
    public EditText interestedAgeRange;

    public String email ="";

    public TextView dailyMatchesReminderTimeView;
    public TextView max_distance_view;
    public TextView genderview;
    public TextView privatePublicAccountView;
    public TextView interestedAgeRangeView;


    Button btUpdate;

    private UserViewModel userViewModel;

    public SettingsFragment(Bundle bundleIntent) {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_settings, container, false);

        dailyMatchesReminderTimeView = v.findViewById(R.id.DailyMatchesReminderTime);
        max_distance_view = v.findViewById(R.id.max_distance);
        genderview = v.findViewById(R.id.gender_view);
        privatePublicAccountView = v.findViewById(R.id.PrivatePublicAccountView);
        interestedAgeRangeView = v.findViewById(R.id.InterestedAgeRangeView);

        dailyMatchesReminderTime = v.findViewById(R.id.dailyMatchesReminderTimeE);
        maximumDistanceSearch = v.findViewById(R.id.maximumDistanceSearchE);
        gender = v.findViewById(R.id.genderE);
        accountType = v.findViewById(R.id.accountTypeE);
         interestedAgeRange = v.findViewById(R.id.interestedAgeRangeE);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        // Create the observer which updates the UI.
        final Observer<List<User>> getUsersObserver = newUser -> {
            if (newUser == null || newUser.size() <= 0) {
                return;
            }

            User user = newUser.get(0);
            if (user == null) {
                return;
            }
            dailyMatchesReminderTimeView.setText(user.getDailyMatchesReminderTime());
            max_distance_view.setText(user.getMaximumDistanceSearch());
            genderview.setText(user.getGender());
            privatePublicAccountView.setText(user.getPrivatePublicAccount());
            interestedAgeRangeView.setText(user.getInterestedAgeRange());
        };

        Bundle bundleIntent = getArguments();

        if (bundleIntent != null) {

            if (bundleIntent.containsKey("email")) {
                email = bundleIntent.getString("email");
            }
        }

        String[] emailID = {email};
        userViewModel.loadAllByIds(getContext(), emailID).observe(getViewLifecycleOwner(), getUsersObserver);

        btUpdate = v.findViewById(R.id.Upbutton);
        btUpdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                User newUser = new User();
                //String email = this.email.getText().toString();

                if(email.equals("")) {
                    email = "fakeuser@google.com";
                }
                newUser.setEmail(email);


                newUser.setDailyMatchesReminderTime(dailyMatchesReminderTime.getText().toString());
                newUser.setMaximumDistanceSearch(maximumDistanceSearch.getText().toString());
                newUser.setGender(gender.getText().toString());
                newUser.setPrivatePublicAccount(accountType.getText().toString());
                newUser.setInterestedAgeRange(interestedAgeRange.getText().toString());


                userViewModel.insertAll(getContext(), newUser);

                dailyMatchesReminderTimeView.setText(newUser.getDailyMatchesReminderTime());
                max_distance_view.setText(newUser.getMaximumDistanceSearch());
                genderview.setText(newUser.getGender());
                privatePublicAccountView.setText(newUser.getPrivatePublicAccount());
                interestedAgeRangeView.setText(newUser.getInterestedAgeRange());
            }
        });
        return v;
    }

    public void updateDatabase(View v) {

    }

}
