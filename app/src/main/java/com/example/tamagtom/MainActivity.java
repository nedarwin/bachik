package com.example.tamagtom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Scanner;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    EditText name1;
    TextView twAge;
    String name2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name1=(EditText) findViewById(R.id.editTextTextPersonName);
        name2 = name1.getText().toString();
        final SeekBar seekBar = (SeekBar)findViewById(R.id.seekBar5);
        seekBar.setOnSeekBarChangeListener(this);
        twAge = (TextView) findViewById(R.id.twage1);
        twAge.setText("0");
        ImageButton startButton = findViewById(R.id.button);
        startButton.setOnClickListener(clickListener);

    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    View.OnClickListener clickListener = v -> {

        Intent intent = new Intent(getApplicationContext(),
                GameActivity.class);
        intent.putExtra("name", name1.getText().toString());
        startActivity(intent);
    };

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        twAge.setText(String.valueOf(seekBar.getProgress()));
    }
}