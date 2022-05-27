package com.example.tamagtom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Timer;
import java.util.TimerTask;


public class GameActivity extends FragmentActivity {
    public static Timer t;
    public double energy,hunger,age,waste,happy;
    public ProgressBar pE,pK,pS,pT;
    String name,age1,name2;
    public Tamag tom;
    public boolean f;
    public SharedPreferences prefs;
    private Button btnk,btnt,btnh,btns,stop,next;
    private TextView Age, name1, says;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        prefs = this.getSharedPreferences(
                "com.example.app", Context.MODE_PRIVATE);
        Intent intent = getIntent();
        startService(new Intent(this, MyService.class));
        name = intent.getStringExtra("name");
        tom=new Tamag();
        next=findViewById(R.id.button4);
        says=findViewById(R.id.textView4);
        stop=findViewById(R.id.button2);
        stop.setOnClickListener(c5);
        btnh=findViewById(R.id.btnhappy);
        btnh.setOnClickListener(c1);
        btnt=findViewById(R.id.btntoil);
        btnt.setOnClickListener(c2);
        btns=findViewById(R.id.btnsleep);
        btns.setOnClickListener(c3);
        btnk=findViewById(R.id.btnkitchen);
        btnk.setOnClickListener(c4);
        pK= findViewById(R.id.prKitchen);
        pE= findViewById(R.id.prSleep);
        pS= findViewById(R.id.prSport);
        pT= findViewById(R.id.prToil);
        Age = findViewById(R.id.age);
        name1 = findViewById(R.id.name);


        startPollingTimer();

    }
    View.OnClickListener c1= v -> {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.setReorderingAllowed(true);
        ft.replace(R.id.fragcase, new happy(tom));
        ft.commit();
    };
    View.OnClickListener c2= v -> {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.setReorderingAllowed(true);
        ft.replace(R.id.fragcase, new tolet(tom));
        ft.commit();
    };
    View.OnClickListener c3= v -> {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.setReorderingAllowed(true);
        ft.replace(R.id.fragcase, new sleep(tom));
        ft.commit();
    };
    View.OnClickListener c4= v -> {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.setReorderingAllowed(true);
        ft.replace(R.id.fragcase, new kitchen(tom));
        ft.commit();
    };
    View.OnClickListener c6= v -> {
        tom=new Tamag();
        next.setAlpha(0.0f);
        next.setOnClickListener(null);
        says.setText("");
    };
    View.OnClickListener c5= v -> {
        if(f){
            onStop();
            f=true;
        }
        else{
            onStart();
        }
    };

    @Override
    protected void onStart() {
        Age.setText(prefs.getString("tag", "0"));
        tom.age= Double.parseDouble(Age.getText().toString());
        name2=prefs.getString("name",name);
        name1.setText(name2);
        super.onStart();
    }

    @Override
    protected void onStop() {
        prefs.edit().putString("tag", Age.getText().toString()).apply();
        prefs.edit().putString("name", name1.getText().toString()).apply();
        super.onStop();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public synchronized void startPollingTimer() {
        if (t == null) {
            TimerTask task = new TimerTask() {
                @SuppressLint("SetTextI18n")
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
            t.scheduleAtFixedRate(task, 0, 350);
        }
    }
    @SuppressLint({"NonConstantResourceId", "SetTextI18n"})





    public void check() {
        if (age == Tamag.AGE_DEATH) {

            tom.death = true;
            says.setText("Старость не радость");
        }

        if (hunger <= Tamag.HUNGER_DEAD) {
            says.setText("Без еды мне не жить");
            tom.death = true;
        }
        if (waste<=0) {
            tom.death = true;
            says.setText("Гигиена важный аспект жизни");
        }
        if (energy <= Tamag.ENERGY_PASSOUT) {
            says.setText("Без сна мне не жить");
            tom.death = true;
        }
        if (happy <=0) {
            tom.death = true;
            says.setText("Жить скучно");
        }
        if (tom.death) {

            says.setText(name+" Умер... ");
            next.setAlpha(1.0f);
            next.setOnClickListener(c6);
        }
        if (age <= Tamag.AGE_MIN) {
            tom.xa = 1.2;
        } else if (age > Tamag.AGE_MIN && age <= Tamag.AGE_MID) {
            tom.xa = 1;
        } else if (age < Tamag.AGE_DEATH && age > Tamag.AGE_MID) {
            tom.xa = 0.8;
        }
        if(energy>100)  { energy=100; }
        if(waste>100)  { waste=100; }
    }

}

