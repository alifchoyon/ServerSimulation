package com.company;

import java.util.ArrayList;

public class Server {

    public ArrayList queue;
    public ArrayList all;
    public ArrayList<Integer> queueLength;
    public ArrayList<Integer> starting_service_time;
    public ArrayList<Integer> ending_service_time;
    public double damaging_probability;
    public boolean isBusy = false;
    public int service_time;
    public int mean_service_time;
    public int numberOfServed = 0;
    public Server() {

        queue = new ArrayList();

        queueLength = new ArrayList();
        starting_service_time = new ArrayList();
        ending_service_time = new ArrayList();
    }
    public Server(int mean_service_time) {

        queue = new ArrayList();
        all = new ArrayList();

        queueLength = new ArrayList();
        starting_service_time = new ArrayList();
        ending_service_time = new ArrayList();

        this.mean_service_time = mean_service_time;
    }

    public int generateServiceTime() {

        return Distributions.Geometric(mean_service_time);
    }


}
