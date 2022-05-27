package com.example.tamagtom;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText name1;
    TextView twAge;
    String name2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        checkFirstStart();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name1=(EditText) findViewById(R.id.editTextTextPersonName);
        name2 = name1.getText().toString();




        ImageButton startButton = findViewById(R.id.button);
        startButton.setOnClickListener(clickListener);

    }
    private void checkFirstStart() {

        SharedPreferences sp = getSharedPreferences("hasVisited",
                Context.MODE_PRIVATE);
        boolean hasVisited = sp.getBoolean("hasVisited", false);
        if (!hasVisited ) {
            SharedPreferences.Editor e = sp.edit();
            e.putBoolean("hasVisited", true);
            e.apply();

        }else {
            Intent intent = new Intent(getApplicationContext(),
                    GameActivity.class);
            startActivity(intent);
            finish();
        }
    }

    View.OnClickListener clickListener = v -> {

        Intent intent = new Intent(getApplicationContext(),
                GameActivity.class);
        intent.putExtra("name", name1.getText().toString());
        startActivity(intent);
        finish();
    };


}