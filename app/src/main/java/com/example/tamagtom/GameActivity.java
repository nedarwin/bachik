package com.example.tamagtom;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;


public class GameActivity extends FragmentActivity {
    public static Timer t;
    private double energy,hunger,age,waste,happy;
    public ProgressBar pE,pK,pS,pT;
    String name,age1;
    public Tamag tom;
    private Button btnNG;
    private TextView Age, name1,twNG;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        tom = new Tamag(name);
        btnNG=findViewById(R.id.btnNG);
        twNG=findViewById(R.id.twNG);
        btnNG.setAlpha(0.0f);
        twNG.setAlpha(0.0f);
        pK= findViewById(R.id.prKitchen);
        pE= findViewById(R.id.prSleep);
        pS= findViewById(R.id.prSport);
        pT= findViewById(R.id.prToil);
        Age = findViewById(R.id.age);
        name1 = findViewById(R.id.name);
        name1.setText(name);


        startPollingTimer();
    }

    public synchronized void startPollingTimer() {
        if (t == null) {
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    tom.Cycle();

                    energy = tom.getEnergy();
                    pE.setProgress((int) energy);
                    hunger = tom.getHunger();
                    pK.setProgress((int) hunger);
                    age = tom.getAge();
                    age1 = String.valueOf(Math.round(age));
                    Age.setText(age1);
                    waste = tom.getWaste();
                    pT.setProgress((int) waste);
                    happy = tom.getHappy();
                    pS.setProgress((int) happy);
                    check();
                }
            };

            t = new Timer();
            t.scheduleAtFixedRate(task, 0, 300);
        }
    }
    @SuppressLint("NonConstantResourceId")
    public void change(View view){
        Fragment fragment = null;

        switch(view.getId()) {
            case R.id.btnhappy:
                fragment = new happy();
                break;
            case R.id.btnkitchen:
                fragment = new kitchen(tom);
                break;
            case R.id.btntoil:

                fragment = new tolet(tom);

                break;
            case R.id.btnsleep:
                fragment = new sleep(tom);
                break;
        }
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragcase, fragment);
        ft.commit();

    }
    public void onClick(View v){
        tom = new Tamag(name);
        twNG.setAlpha(0.0f);
        btnNG.setAlpha(0.0f);
        btnNG.setOnClickListener(null);

    }
    public void check() {
        if (age == tom.AGE_DEATH) {

            tom.death = true;

        }
        if (hunger <= tom.HUNGER_SICK) {

        }
        if (hunger <= tom.HUNGER_DEAD) {

            tom.death = true;
        }

        if (energy <= tom.ENERGY_CANSLEEP) {

        }
        if (energy <= tom.ENERGY_WANTSLEEP) {


        }
        if (waste<=0) {
            tom.death = true;
        }
        if (energy <= tom.ENERGY_PASSOUT) {
            tom.death = true;
        }

        if (happy <=0) {
            tom.death = true;

        }
        if (tom.death==true) {
            twNG.setAlpha(1.0f);
            btnNG.setAlpha(1.0f);
            btnNG.setOnClickListener(this::onClick);
        }
        if (age <= tom.AGE_MIN) {
            tom.xa = 1.2;
        } else if (age > tom.AGE_MIN && age <= tom.AGE_MID) {
            tom.xa = 1;
        } else if (age < tom.AGE_DEATH && age > tom.AGE_MID) {
            tom.xa = 0.8;
        }
        if(energy>100)  { energy=100; }
        if(waste>100)  { waste=100; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

    }
}

