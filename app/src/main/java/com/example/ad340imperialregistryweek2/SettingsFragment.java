package com.example.ad340imperialregistryweek2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import java.util.List;

public class SettingsFragment extends Fragment {

    public String email;
    public TextView dailyMatchesReminderTime;
    public TextView max_distance_view;
    public TextView genderview;
    public TextView privatePublicAccountView;
    public TextView interestedAgeRangeView;
    private Bundle bundle;

    Button btUpdate;

    private UserViewModel userViewModel;

    public SettingsFragment(Bundle bundle) {
        // Required empty public constructor
        this.bundle = bundle;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        dailyMatchesReminderTime = v.findViewById(R.id.DailyMatchesReminderTime);
        max_distance_view = v.findViewById(R.id.max_distance);
        genderview = v.findViewById(R.id.gender_view);
        privatePublicAccountView = v.findViewById(R.id.PrivatePublicAccountView);
        interestedAgeRangeView = v.findViewById(R.id.InterestedAgeRangeView);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        // Create the observer which updates the UI.
        final Observer<List<User>> getUsersObserver = newUsers -> {
            if (newUsers == null || newUsers.size() <= 0) {
                return;
            }

            User user = newUsers.get(0);
            if (user == null) {
                return;
            }

            dailyMatchesReminderTime.setText(user.getDailyMatchesReminderTime());
            max_distance_view.setText(user.getMaximumDistanceSearch());
            genderview.setText(user.getGender());
            privatePublicAccountView.setText(user.getPrivatePublicAccount());
            interestedAgeRangeView.setText(user.getInterestedAgeRange());
        };

        String email = this.bundle.getString(MainActivity.EXTRA_EMAIL);

        String[] emails = {email};
        userViewModel.loadAllByIds(getContext(), emails).observe(getViewLifecycleOwner(), getUsersObserver);

        btUpdate = v.findViewById(R.id.Upbutton);
        btUpdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                updateDatabase(v);
            }
        });
        return v;
    }
    public void updateDatabase(View view) {
        String email = this.bundle.getString(MainActivity.EXTRA_EMAIL);
        User fakeNewUser = new User();

        if(email.equals("")) {
            email = "fakeuser@google.com";
        }
        fakeNewUser.setEmail(email);
        fakeNewUser.setDailyMatchesReminderTime("dailyMatchesReminderTime");
        fakeNewUser.setMaximumDistanceSearch("Default");
        fakeNewUser.setGender("Nunna");
        fakeNewUser.setPrivatePublicAccount("Nunna");

        userViewModel.insertAll(getContext(), fakeNewUser);

            dailyMatchesReminderTime.setText(fakeNewUser.getDailyMatchesReminderTime());
            max_distance_view.setText(fakeNewUser.getMaximumDistanceSearch());
            genderview.setText(fakeNewUser.getGender());
            privatePublicAccountView.setText(fakeNewUser.getPrivatePublicAccount());
            interestedAgeRangeView.setText(fakeNewUser.getInterestedAgeRange());
    }

}
