package com.example.tamagtom;


public class Tamag {
    Tamag() {
        doSleep();

    }

    public double hunger = 115;
    public double energy = 100;
    public double happy = 100;
    public double waste = 100;
    public double age = 0;



    public double getAge() {
        return age;
    }

    public double getEnergy() {
        return energy;
    }

    public double getHunger() {
        return hunger;
    }

    public double getWaste() {
        return waste;
    }

    public double getHappy() {
        return happy;
    }

    public boolean death = false;

    public double xa=1;

    public static final int AGE_MIN = 3;
    public static final int AGE_MID = 20;
    public static final int AGE_DEATH = 50;

    public static final int HUNGER_CAN = 80;
    public static final int HUNGER_DEAD = -20;

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


