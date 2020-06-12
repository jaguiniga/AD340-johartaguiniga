package com.example.ad340imperialregistryweek2;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class ProfileFragment extends Fragment {

    private TextView textView;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView emailView;
    private Bundle bundle;


    public ProfileFragment(Bundle bundle) {
        // Required empty public constructor
        this.bundle = bundle;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        textView = v.findViewById(R.id.textView2);
        textView2 = v.findViewById(R.id.textView3);
        textView3 = v.findViewById(R.id.textView4);
        textView4 = v.findViewById(R.id.textView5);
        emailView = v.findViewById(R.id.email);


        String text = this.bundle.getString(MainActivity.EXTRA_TEXT);
        String occupation = this.bundle.getString(MainActivity.EXTRA_OCT);
        String description = this.bundle.getString(MainActivity.EXTRA_DES);
        String age = this.bundle.getString(MainActivity.EXTRA_AGEE);
        String email = this.bundle.getString(MainActivity.EXTRA_EMAIL);

        textView.setText(text);
        textView2.setText(occupation);
        textView3.setText(description);
        textView4.setText(age);
        emailView.setText(email);

        return v;
    }
}