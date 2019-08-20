package com.company;

import java.util.ArrayList;

public class AssemblerServer extends Server {


    public ArrayList<Pant> pantQueue;
    public ArrayList<Jacket> jacketQueue;
    Suit cureentJob;

    public AssemblerServer(int mean_service_time) {
        super(mean_service_time);
        pantQueue = new ArrayList<>();
        jacketQueue = new ArrayList<>();

    }

    public void showPerformance(){

        System.out.println("Average delay        :" + this.getAverageDelay());
        System.out.println("Average queue        :" + this.getAverageQueue());
        System.out.println("System utilization   :" + this.getSystemUsage());
        System.out.println("Maximum queue        :" + this.getMaxQueuelength());
        System.out.println("Maximum time damaged :" + this.getMaxTime(true));
        System.out.println("Maximum time cleaned :" + this.getMaxTime(false));
        System.out.println("Average time damaged :" + this.getAvgTime(true));
        System.out.println("Average time cleaned :" + this.getAvgTime(false));
    }
    public void printServername() {

        System.out.println("______________________________");
        System.out.println(this.getClass().getName());
        System.out.println("______________________________");
    }


    public double getAvgTime(boolean isDamaged) {

        double max = 0;
        int c =0;

        if(isDamaged) {
            for (Object s : all
                    ) {


                if (((Suit) s).isDamaged()) {

                    //  ((Suit) s).showAttributes();
                    int t = ((Suit) s).service_ending_time - ((Suit) s).service_starting_time;
                    //   if (t > max)
                    {
                        max += t;
                        c++;
                    }

                }


            }

            max=max/c;


        }

        else {


            for (Object s : all
                    ) {


                if (!((Suit) s).isDamaged()) {
                    int t = ((Suit) s).service_ending_time - ((Suit) s).service_starting_time;
                    //if (t > max)
                    {
                        max += t;
                        c++;
                    }

                }


            }


            max=max/c;
        }
        return max;
    }

    public double getMaxTime(boolean isDamaged) {

        double max = 0;

        if(isDamaged) {
            for (Object s : all
                    ) {


                if (((Suit) s).isDamaged()) {

                    //  ((Suit) s).showAttributes();
                    int t = ((Suit) s).service_ending_time - ((Suit) s).service_starting_time;
                    if (t > max) {
                        max = t;
                    }

                }


            }


        }

        else {


            for (Object s : all
                    ) {


                if (!((Suit) s).isDamaged()) {
                    int t = ((Suit) s).service_ending_time - ((Suit) s).service_starting_time;
                    if (t > max) {
                        max = t;
                    }

                }


            }

        }
        return max;
    }

    public int getMaxQueuelength() {
        int max = 0;
        double total_delay = 0;

        for (Object s : all
                ) {

            Suit suit = (Suit) s;

            if (((Suit) s).qLength > max) {

                max = ((Suit) s).qLength;
            }

        }

        return max;


    }

    public void addPant(Pant p) {

        int pairLocation = -1;

        for (int i = 0; i < jacketQueue.size(); i++) {

            if (jacketQueue.get(i).id == p.id) {


                pairLocation = i;

                break;
            }

        }


        if (pairLocation == -1) {

            pantQueue.add(p);

        } else {

            Jacket jacket = jacketQueue.get(pairLocation);
            jacketQueue.remove(pairLocation);


            Suit suit = new Suit(jacket, p);

            suit.inter_arrival_time = 1;
            suit.arrival_time = Main.simulation_current_time + suit.inter_arrival_time;
            suit.service_time = suit.getDamagedOrUndamagedServiceTime();


            queue.add(suit);


        }

    }

    public void addJacket(Jacket p) {


        int pairLocation = -1;

        for (int i = 0; i < pantQueue.size(); i++) {

            if (pantQueue.get(i).id == p.id) {


                pairLocation = i;

                break;
            }

        }


        if (pairLocation == -1) {

            jacketQueue.add(p);

        } else {

            Pant pant = pantQueue.get(pairLocation);
            pantQueue.remove(pairLocation);


            Suit suit = new Suit(p, pant);

            suit.inter_arrival_time = 1;
            suit.arrival_time = Main.simulation_current_time + suit.inter_arrival_time;
            suit.service_time = suit.getDamagedOrUndamagedServiceTime();


            queue.add(suit);


        }


    }

    public double getAverageDelay() {

        double total_delay = 0;

        for (Object s : all
                ) {

            Suit suit = (Suit) s;

            total_delay += ((Suit) s).service_starting_time - ((Suit) s).arrival_time;


        }
        return total_delay / (double) numberOfServed;
    }

    public double getAverageQueue() {

        double total_delay = 0;

        for (Object s : all
                ) {

            Suit suit = (Suit) s;

            total_delay += (double) (((Suit) s).service_ending_time - ((Suit) s).service_starting_time) * (double) ((Suit) s).qLength;


        }

        return total_delay / (double) Main.simulation_ending_time;

        //return 0;
    }

    public double getSystemUsage() {
        double total_delay = 0;

        for (Object s : all
                ) {

            Suit suit = (Suit) s;

            total_delay += (double) (((Suit) s).service_ending_time - ((Suit) s).service_starting_time);


        }

        return (total_delay / (double) Main.simulation_ending_time) * 100;
    }


//    public void serve(Suit o , int simulation_current_time){
//
//
//        if(isBusy){
//
//
//            if(cureentJob.arrival_time==simulation_current_time) {
//
//                isBusy=true;
//
//                System.out.println("Service started for");
//
//
//                cureentJob.service_starting_time = simulation_current_time;
//                cureentJob.service_ending_time = cureentJob.service_starting_time + cureentJob.service_time;
//                cureentJob.showAttributes();
//
//            }
//
//            if(cureentJob.service_ending_time==simulation_current_time && cureentJob.service_ending_time!=0
//                    && cureentJob.service_starting_time!=0) {
//
//                System.out.println("Service ended for");
//                cureentJob.showAttributes();
//                queue.remove(0);
//                isBusy=false;
//
//            }
//
//
//        }
//
//        //not busy
//        else{
//
//            //check currentjob
//            if(cureentJob==null){
//
//                queue.add(o);
//
//                if(queue.isEmpty()){
//
//                    //idle
//                    System.out.println("Idle");
//                }
//                else{
//
//
//
//                    System.out.println("Serving from queue");
//                    cureentJob= (Suit) queue.get(0);
//
//                    if(cureentJob.arrival_time==simulation_current_time) {
//
//                        isBusy=true;
//
//                        System.out.println("Service started for");
//
//
//                        cureentJob.service_starting_time = simulation_current_time;
//                        cureentJob.service_ending_time = cureentJob.service_starting_time + cureentJob.service_time;
//                        cureentJob.showAttributes();
//
//                    }
//
//                    if(cureentJob.service_ending_time==simulation_current_time && cureentJob.service_ending_time!=0
//                            && cureentJob.service_starting_time!=0) {
//
//                        System.out.println("Service ended for");
//                        cureentJob.showAttributes();
//                        queue.remove(0);
//                        isBusy=false;
//
//                    }
//
//
//                }
//
//            }
//            else{
//
//            }
//
//
//        }
//
//
//    }

    public Suit serve(int simulation_current_time) {



        if (isBusy) {


            if (cureentJob.service_ending_time <= simulation_current_time && cureentJob.service_ending_time != 0
                    && cureentJob.service_starting_time != 0) {

                printServername();
                System.out.println("Service ended for");
                cureentJob.showAttributes();

                Suit s = (Suit) queue.get(0);
                s.qLength = queue.size();

                all.add(s);

                queue.remove(0);
                numberOfServed++;

                isBusy = false;
                cureentJob = null;

                return s;

            }


        }

        //not busy
        else {

            //check currentjob
            if (cureentJob == null) {

                //queue.add(o);

                if (queue.isEmpty()) {

                    //idle
                    //System.out.println("Idle");
                } else {


                    printServername();
                    //System.out.println("Serving from queue");
                    cureentJob = (Suit) queue.get(0);

                    if (cureentJob.arrival_time <= simulation_current_time) {

                        isBusy = true;

                        printServername();
                        System.out.println("Service started for");


                        cureentJob.service_starting_time = simulation_current_time;
                        cureentJob.service_ending_time = cureentJob.service_starting_time + cureentJob.service_time;
                        cureentJob.showAttributes();

                    }

//                    if(cureentJob.service_ending_time==simulation_current_time && cureentJob.service_ending_time!=0
//                            && cureentJob.service_starting_time!=0) {
//
//                        System.out.println("Service ended for");
//                        cureentJob.showAttributes();
//
//                        Suit s = (Suit) queue.get(0);
//                        s.qLength=queue.size();
//
//                        all.add(s);
//
//                        queue.remove(0);
//                        numberOfServed++;
//                        isBusy=false;
//
//                    }


                }

            } else {


                if (cureentJob.arrival_time <= simulation_current_time) {

                    isBusy = true;

                    printServername();
                    System.out.println("Service started for");


                    cureentJob.service_starting_time = simulation_current_time;
                    cureentJob.service_ending_time = cureentJob.service_starting_time + cureentJob.service_time;
                    cureentJob.showAttributes();

                }

                if (cureentJob.service_ending_time <= simulation_current_time && cureentJob.service_ending_time != 0
                        && cureentJob.service_starting_time != 0) {

                    printServername();
                    System.out.println("Service ended for");
                    cureentJob.showAttributes();

                    if (!queue.isEmpty()) {
                        Suit s = (Suit) queue.get(0);
                        s.qLength = queue.size();

                        all.add(s);

                        queue.remove(0);
                        numberOfServed++;

                        return s;

                    }
                    isBusy = false;
                    cureentJob = null;

                }

            }


        }


        return null;
    }
}

