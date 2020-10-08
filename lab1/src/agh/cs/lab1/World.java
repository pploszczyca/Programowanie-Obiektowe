package agh.cs.lab1;

public class World {
    public static void run(String[] arr){
//        System.out.println("Zwierzak idzie do przodu.");
        System.out.print(arr[0]);
        for(int i = 1; i < arr.length; i++){
            System.out.print(",");
            System.out.print(arr[i]);
        }
        System.out.println();
    }

    public static void main(String[] args){
        String[] tab = {"Raz", "Dwa", "Trzy", "Dwa"};
        System.out.println("System Start");
        run(tab);
        System.out.println("System End");
    }
}
