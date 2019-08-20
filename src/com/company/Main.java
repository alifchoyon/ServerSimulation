package com.company;

public class Main {


    public static int simulation_ending_time = 24*60;
    public static int simulation_current_time;



    public static void main(String[] args) {

        Distributions d = new Distributions();

        int simulation_starting_time = 0;

        simulation_current_time = simulation_starting_time;
        int dt = 1;


        SuitServer suitServer = new SuitServer(6);
        PantServer pantServer = new PantServer(4);
        JacketServer jacketServer = new JacketServer(5);
        AssemblerServer assemblerServer = new AssemblerServer(4);
        CustomerRelationServer customerRelationServer = new CustomerRelationServer(12);


        while //(suitServer.numberOfServed < 10)
        (simulation_current_time<simulation_ending_time)
        {

            System.out.println("Current Time :" + simulation_current_time);

            if (Distributions.Bernoulli(1.0/5.0))
            {

                Pant p = new Pant();
                p.id = simulation_current_time;
                p.inter_arrival_time = 1;
                p.arrival_time = simulation_current_time + p.inter_arrival_time;
                p.service_time = pantServer.generateServiceTime();


                Jacket j = new Jacket();
                j.id = simulation_current_time;

                j.inter_arrival_time = 1;
                j.arrival_time = simulation_current_time + j.inter_arrival_time;
                j.service_time = jacketServer.generateServiceTime();


                Suit suit = new Suit(j, p);

                suit.id = simulation_current_time;
                suit.inter_arrival_time = Distributions.Geometric(5);
                suit.arrival_time = simulation_current_time + suit.inter_arrival_time;
                suit.service_time = suitServer.generateServiceTime();

                suitServer.queue.add(suit);


            }


            Suit tokenized_suit = suitServer.serve(simulation_current_time);

            if (tokenized_suit != null) {

                pantServer.queue.add(tokenized_suit.pant);
                jacketServer.queue.add(tokenized_suit.jacket);

            }

            Pant tpant = pantServer.serve(simulation_current_time);
            Jacket tjacket = jacketServer.serve(simulation_current_time);

            if (tpant != null) assemblerServer.addPant(tpant);
            if (tjacket != null) assemblerServer.addJacket(tjacket);

            Suit assSuit = assemblerServer.serve(simulation_current_time);

            if (assSuit != null) {

                if (assSuit.isDamaged()) {

                    customerRelationServer.queue.add(assSuit);
                } else {

                    System.out.println("returned to customer");
                    assSuit.showAttributes();

                }


            }


            Suit damagedSuit = customerRelationServer.serve(simulation_current_time);

            if (damagedSuit != null) {

                System.out.println("Sorry for our inconvenience");
                damagedSuit.showAttributes();
            }

//            System.out.println("suit server q:"+suitServer.queue.size());
//            System.out.println("pant server q:"+pantServer.queue.size());


            simulation_current_time += dt;
        }


        simulation_ending_time = simulation_current_time;

        suitServer.printServername();
        suitServer.showPerformance();


        pantServer.printServername();
        pantServer.showPerformance();



        jacketServer.printServername();
        jacketServer.showPerformance();


        assemblerServer.printServername();
        assemblerServer.showPerformance();


        customerRelationServer.printServername();
        customerRelationServer.showPerformance();













    }
}
