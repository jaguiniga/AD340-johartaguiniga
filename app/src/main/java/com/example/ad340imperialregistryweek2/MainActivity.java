package com.example.ad340imperialregistryweek2;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    Calendar cal = Calendar.getInstance();
    int year = cal.get(Calendar.YEAR);
    int month = cal.get(Calendar.MONTH);
    int day = cal.get(Calendar.DAY_OF_MONTH);
    Calendar today = Calendar.getInstance();

    public static final String EXTRA_TEXT = "com.example.ad340imperialregistryweek2.EXTRA_TEXT";
    public static final String EXTRA_OCT = "com.example.ad340imperialregistryweek2.EXTRA_OCT";
    public static final String EXTRA_DES = "com.example.ad340imperialregistryweek2.EXTRA_DES";
    public static final String EXTRA_AGEE = "com.example.ad340imperialregistryweek2.EXTRA_AGEE";


    // Initialize Variables
    EditText etName;
    EditText etOccupation;
    EditText etDescription;
    EditText etAge;
    Button btSubmit;


    String stringName;
    String stringOccupation;
    String stringDescription;
    String stringAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        mDisplayDate = (TextView) findViewById(R.id.editText5);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                DatePickerDialog dialog = new DatePickerDialog(
                        MainActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(android.widget.DatePicker view, int year, int month, int day) {
                month = month + 1;

                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);


                String date = month + "/" + day + "/" + year;

                mDisplayDate.setText(date);

            }


        };

        // Assign Variables
        etName = findViewById(R.id.editText2);
        etOccupation = findViewById(R.id.editText3);
        etDescription = findViewById(R.id.editText4);
        etAge = findViewById(R.id.editText5);

        btSubmit = findViewById(R.id.button);
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMain2Activity();
            }
        });

        if (savedInstanceState != null){
            stringName = savedInstanceState.getString("NameSav");
            stringOccupation = savedInstanceState.getString("OccupationSav");
            stringAge = savedInstanceState.getString("AgeSav");
            stringDescription = savedInstanceState.getString("DescriptionSav");
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        EditText Name =  (EditText) findViewById(R.id.editText2);
        EditText Age = findViewById(R.id.editText5);
        EditText Description = findViewById(R.id.editText4);
        EditText Occupation = findViewById(R.id.editText3);

        Name.setText("");
        Age.setText("");
        Description.setText("");
        Occupation.setText("");


    }



    public void openMain2Activity(){

            EditText Name =  (EditText) findViewById(R.id.editText2);
            EditText Occupation = findViewById(R.id.editText3);
            EditText Description = findViewById(R.id.editText4);
            EditText Age = findViewById(R.id.editText5);


            String text = Name.getText().toString();
            String Oct = Occupation.getText().toString();
            String Des = Description.getText().toString();
            String Agee = Age.getText().toString();


            Intent intent = new Intent(this, Main2Activity.class);
                intent.putExtra(EXTRA_OCT, Oct);
                intent.putExtra(EXTRA_DES, Des);
                intent.putExtra(EXTRA_AGEE, Agee);
                intent.putExtra(EXTRA_TEXT, text);

        while (text.isEmpty()) {
            Name.setError("Name cannot be empty");
            return;
        }


        while ((Agee.isEmpty())) {
            Age.setError("age cannot be empty");
            return;
        }

        while ((Des.isEmpty())) {
            Description.setError("description cannot be empty");
            return;
        }

        while ((Oct.isEmpty())) {
            Occupation.setError("occupation cannot be empty");
            return;
        }


            startActivity(intent);
        }

        @Override
        protected void onSaveInstanceState(Bundle outState){
            super.onSaveInstanceState(outState);

            stringName = etName.getText().toString();
            stringOccupation = etOccupation.getText().toString();
            stringDescription = etDescription.getText().toString();
            stringAge = etAge.getText().toString();

            outState.putString("NameSav", String.valueOf(stringName));
            outState.putString("OccupationSav", String.valueOf(stringOccupation));
            outState.putString("DesSav", String.valueOf(stringDescription));
            outState.putString("AgeSav", String.valueOf(stringAge));
    }
    }
