package agh.cs.lab1;

public class World {
    public static void run(String[] arr){
        for(String data: arr){
            switch(data){
                case "f":
                    System.out.println("Zwierzak idzie do przodu");
                    break;
                case "b":
                    System.out.println("Zwierzak idzie do tyłu");
                    break;
                case "r":
                    System.out.println("Zwierzak idzie w prawo");
                    break;
                case "l":
                    System.out.println("Zwierzak idzie w lewo");
                    break;
            }
        }
    }

    public static void main(String[] args){
        String[] tab = {"f", "f", "r", "l"};
        System.out.println("Start");
        run(tab);
        System.out.println("Stop");
    }
}
