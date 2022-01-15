package agh.cs.lab;


public class World {
    public static void main(String[] args){
        try {
            MoveDirection[] directions = new OptionsParser().parse(args);

            GrassField map = new GrassField(10);
            map.place(new Animal(map));
            map.place(new Animal(map, new Vector2d(3, 4)));
            System.out.println(map.toString());
            map.run(directions);
            System.out.println(map.toString());

        } catch(Exception exception){
            System.out.println(exception);
        }
    }
}
