package agh.cs.lab;

public class Simulation {
    final FieldMap map;
    private int simulationDays =100;

    public Simulation(int width, int height, int jungleWidth, int jungleHeight ,int startEnergy, int moveEnergy, float plantEnergy, int randomAnimals){
        map = new FieldMap(width, height, calculateJungleLowerLeft(width,height,jungleWidth, jungleHeight), calculateJungleUpperRight(width,height,jungleWidth, jungleHeight), startEnergy, moveEnergy, plantEnergy);
        map.randomPlace(randomAnimals);
    }

    private Vector2d calculateJungleLowerLeft(int width, int height, int jungleWidth, int jungleHeight){
        return new Vector2d(Math.round((width-jungleWidth-2)/2),  Math.round((height-jungleHeight-2)/2));
    }

    private Vector2d calculateJungleUpperRight(int width, int height, int jungleWidth, int jungleHeight){
        return new Vector2d(Math.round((width+jungleWidth-2)/2),  Math.round((height+jungleHeight-2)/2));
    }

    private void daySimulate(){
        map.removeAnimalsWithLowEnergy();
        map.run();
        map.eat();
        map.multiplication();
        map.putGrasses();
    }

    public void start(){
        System.out.println(map.toString());

        for(int i = 0; i < simulationDays; i++){
            System.out.println("DAY: " + (i+1));
            daySimulate();
            System.out.println(map.toString());
        }
    }


}
