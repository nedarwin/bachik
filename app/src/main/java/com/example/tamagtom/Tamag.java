package com.example.tamagtom;



import android.app.Activity;
import android.content.Context;
import android.widget.Button;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class Tamag {
    private String name;
    Tamag(String name) {
        doSleep();
        this.name=name;

    }

    private Context context = null;
    public double hunger = 115;
    public double energy = 100;
    public double happy = 100;
    public double waste = 100;
    public double age = 0;

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public double getEnergy() {
        return energy;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public double getHunger() {
        return hunger;
    }

    public void setHunger(double hunger) {
        this.hunger = hunger;
    }

    public double getWaste() {
        return waste;
    }

    public void setWaste(double waste) {
        this.waste = waste;
    }

    public double getHappy() {
        return happy;
    }

    public void setHappy(double happy) {
        this.happy = happy;
    }

    public boolean death = false;

    public double xa=1;

    public static final int AGE_MIN = 3;
    public static final int AGE_MID = 20;
    public static final int AGE_DEATH = 50;

    public static final int HUNGER_CAN = 80;
    public static final int HUNGER_WANT = 50;
    public static final int HUNGER_SICK = 30;
    public static final int HUNGER_DEAD = -20;

    public static final int ENERGY_CANSLEEP = 80;
    public static final int ENERGY_WANTSLEEP = 40;
    public static final int ENERGY_PASSOUT = 0;

    public static final int WASTE_BAD = 50;




    public void Cycle() {
        RandEv();
        hunger-=0.07;
        waste-=0.16;
        happy-=0.03;
        energy-=0.1/xa;
        age += 0.003;
        if (waste <= WASTE_BAD) happy--;
    }
    public void doEx(){
        hunger-=10;
        energy-=10/xa;
        happy+=35;
        if (happy>100) { happy =100; }
    }
    public void doSleep() {
        hunger-=15;
        energy=100;
        if (energy>100) {
            energy=100;
        }
    }
    public void doEat() {
        if (hunger<=HUNGER_CAN) {
            hunger += 19;
            waste-=10;
            if (hunger>100) {
                hunger = 100;
            }
        }

        else{
            System.out.println("Том еще не хочет кушац");
        }
    }
    public void doToil(){
        waste=100;
    }
    public void RandEv() {
        switch ((int) (Math.random() * 20)) {
            case 1:
                hunger--;
            case 2:
                energy-=1/xa;
            case 4:
                waste--;
            case 5:
                happy--;
            case 6:
                happy--;
            default:
                break;
        }
    }


}


