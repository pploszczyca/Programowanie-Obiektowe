package agh.cs.lab;


public class World {
    public static void main(String[] args){
        FieldMap map = new FieldMap(10,10,new Vector2d(3,3), new Vector2d(7,7), 10, 2, 5);

        map.randomPlace(70);
        System.out.println(map.toString());

        for(int i =0; i < 10; i++){
            System.out.println("DAY: " + i);
            map.removeAnimalsWithLowEnergy();
            map.run();
            map.eat();
            map.multiplication();
            map.putGrasses();
            System.out.println(map.toString());
        }



    }
}
