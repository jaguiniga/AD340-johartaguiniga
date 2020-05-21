package com.example.ad340imperialregistryweek2;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class MainActivity extends AppCompatActivity {
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



    public void openMain2Activity(){

            EditText Name =  findViewById(R.id.editText2);
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
