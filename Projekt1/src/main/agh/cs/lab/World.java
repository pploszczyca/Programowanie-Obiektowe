package agh.cs.lab;


public class World {
    public static void main(String[] args){
        Simulation simulation = new Simulation(10,10,4,4, 20, 1, 10, 70);

        simulation.start();
    }
}
