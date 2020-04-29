package com.example.ad340imperialregistryweek2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.lang.invoke.ConstantCallSite;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_TEXT = "com.example.ad340imperialregistryweek2.EXTRA_TEXT";


    // Initialize Variables
    EditText etName, etUser, etEmail, etBirthday;
    Button btSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Assign Variables
        etName = findViewById(R.id.editText2);
        etUser = findViewById(R.id.editText3);
        etEmail = findViewById(R.id.editText4);
        etBirthday = findViewById(R.id.editText);
        btSubmit = findViewById(R.id.button);


        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMain2Activity();
            }
        });
    }

    public void openMain2Activity(){
            EditText UserName = (EditText) findViewById(R.id.editText3);
            String text = UserName.getText().toString();

            Intent intent = new Intent(this, Main2Activity.class);
            intent.putExtra(EXTRA_TEXT, text);
            startActivity(intent);
        }
    }
