package com.company;

public class Suit {

    public Jacket jacket;
    public Pant pant;
    public int id;
    public int arrival_time;
    public int service_starting_time;
    public int service_ending_time;
    public int inter_arrival_time;
    public int service_time;
    public int qLength;

    public Suit(Jacket jacket, Pant pant) {
        this.jacket = jacket;
        this.pant = pant;
    }

    public boolean isDamaged() {

        if (jacket.is_damaged || pant.is_damaged) return true;

        return false;
    }


    public int getDamagedOrUndamagedServiceTime() {
        if (isDamaged()) {

            return Distributions.Geometric(8);
        }

        return Distributions.Geometric(5);
    }

    public void showAttributes() {

        System.out.println("------------------Suit-------------------");
        System.out.println("                 id: " + id);
        System.out.println("       Arrival time: " + arrival_time);
        System.out.println(" Service started at: " + service_starting_time);
        System.out.println("   Service ended at: " + service_ending_time);
        System.out.println(" Inter-arrival time: " + inter_arrival_time);
        System.out.println("       Service time: " + service_time);
        System.out.println("            Damaged: " + isDamaged());
        System.out.println("-----------------------------------------");


    }


}
