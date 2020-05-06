package com.example.ad340imperialregistryweek2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    private Button btBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView textView = findViewById(R.id.textView2);
        TextView textView2 = findViewById(R.id.textView3);
        TextView textView3 = findViewById(R.id.textView4);
        TextView textView4 = findViewById(R.id.textView5);



        Intent intent = getIntent();
        String text = intent.getStringExtra(MainActivity.EXTRA_TEXT);
        String occupation = intent.getStringExtra(MainActivity.EXTRA_OCT);
        String description = intent.getStringExtra(MainActivity.EXTRA_DES);
        String age = intent.getStringExtra(MainActivity.EXTRA_AGEE);

        textView.setText(text);
        textView2.setText(occupation);
        textView3.setText(description);
        textView4.setText(age);


        btBack = findViewById(R.id.button);
        btBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });
    }

    public void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
