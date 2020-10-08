package agh.cs.lab1;

public class World {
    public static void run(Direction[] arr){
        for(Direction data: arr){
            switch(data){
                case FORWARD:
                    System.out.println("Zwierzak idzie do przodu");
                    break;
                case BACKWARD:
                    System.out.println("Zwierzak idzie do tyłu");
                    break;
                case RIGHT:
                    System.out.println("Zwierzak idzie w prawo");
                    break;
                case LEFT:
                    System.out.println("Zwierzak idzie w lewo");
                    break;
            }
        }
    }


    public static Direction[] changeDirections(String[] arr){
        int n = arr.length;
        Direction[] directions = new Direction[n];
        for(int i = 0; i < n; i++){
            switch (arr[i]){
                case "f":
                    directions[i] = Direction.FORWARD;
                    break;
                case "b":
                    directions[i] = Direction.BACKWARD;
                    break;
                case "r":
                    directions[i] = Direction.RIGHT;
                    break;
                case "l":
                    directions[i] = Direction.LEFT;
                    break;
            }
        }
        return directions;
    }


    public static void main(String[] args){
        System.out.println("Start");
        Direction[] directTab = changeDirections(args);
        run(directTab);
        System.out.println("Stop");
    }
}
