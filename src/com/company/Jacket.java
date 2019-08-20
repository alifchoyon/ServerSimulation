package com.company;

public class Jacket {

    public boolean is_damaged = false;


    public int id;
    public int arrival_time;
    public int service_starting_time;
    public int service_ending_time;

    public int inter_arrival_time;
    public int service_time;

    public int qLength;

    public void showAttributes() {

        System.out.println("-----------------Jacket-------------------");
        System.out.println("                 id: " + id);
        System.out.println("       Arrival time: " + arrival_time);
        System.out.println(" Service started at: " + service_starting_time);
        System.out.println("   Service ended at: " + service_ending_time);
        System.out.println(" Inter-arrival time: " + inter_arrival_time);
        System.out.println("       Service time: " + service_time);
        System.out.println("            Damaged: " + is_damaged);
        System.out.println("-----------------------------------------");


    }


}
