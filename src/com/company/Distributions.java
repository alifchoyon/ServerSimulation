package com.company;

import java.util.Random;

public class Distributions {

    public static Random random;

    public Distributions() {

        random= new Random(1234);

    }

    public static int Geometric(int mean) {

        double probability = (double) 1 / mean;

        int trail = 1;
        while (!Bernoulli(probability)) trail++;
        return trail;

    }


    public static boolean Bernoulli(double probability) {


        try {

            //Thread.sleep((100);

        }catch (Exception e ){

        }


        double cumulitive_probability = random.nextDouble();
        return (cumulitive_probability < probability) ? true : false;
    }


    public static double Exponential(float mean) {

        double probability = (double) 1 / mean;

        double trail = 1;
        while (!Bernoulli(probability)) trail = trail + 0.1;
        return trail;

    }


}
