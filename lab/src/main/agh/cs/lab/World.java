package agh.cs.lab;

public class World {
    public static void main(String[] args){
        Animal lion = new Animal();
        System.out.println(lion.toString());

        lion.move(MoveDirection.RIGHT);
        lion.move(MoveDirection.FORWARD);
        lion.move(MoveDirection.FORWARD);

        System.out.println(lion.toString());

        OptionsParser parser = new OptionsParser();
        String [] stringDirections = {"f", "b", "żaden kierunek","r"};
        MoveDirection [] directionArray = parser.parse(stringDirections);
        for(MoveDirection d: directionArray){
            lion.move(d);
        }

        System.out.println(lion.toString());

    }
}
