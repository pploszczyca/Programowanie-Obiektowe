package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class FieldMapTest {

    @Test
    public void randomPlaceTest(){
        FieldMap map = new FieldMap(100,100,new Vector2d(0,0), new Vector2d(0,0), 10, 5, 3);

        map.randomPlace(10000);
        Vector2d position;

        for(int i = 0; i < 100; i++){
            for(int j= 0; j < 100; j++){
                position = new Vector2d(i,j);
                Assertions.assertTrue(map.isOccupied(position));
                FieldMapCell cell = (FieldMapCell) map.objectAt(position);
                Assertions.assertEquals(cell.size(), 1);
            }
        }
    }

    @Test
    public void multiplicationTest(){
        FieldMap map = new FieldMap(1,2,new Vector2d(0,0), new Vector2d(0,0), 16, 5, 3);

        map.place(new Animal(map, new Vector2d(0,0)));
        map.place(new Animal(map, new Vector2d(0,0)));
        map.multiplication();

        FieldMapCell cell = (FieldMapCell) map.objectAt(new Vector2d(0,0));
        for(Animal animal: cell.getAnimalsList()){
            Assertions.assertEquals(animal.getEnergy(), 12, 0);
        }

        Animal animalChild = ((FieldMapCell) map.objectAt(new Vector2d(0,1))).getAnimalsList().get(0);
        Assertions.assertEquals(animalChild.getEnergy(), 8, 0);


        // Second test
        FieldMap anotherMap = new FieldMap(3,3,new Vector2d(0,0), new Vector2d(0,0), 20, 5, 3);
        for(int i = 0; i < 3; i++){
            for(int j=0; j < 3; j++){
                anotherMap.place(new Animal(anotherMap, new Vector2d(i,j)));
            }
        }
        anotherMap.place(new Animal(anotherMap, new Vector2d(1,1)));
        anotherMap.multiplication();

        FieldMapCell anotherCell = (FieldMapCell) anotherMap.objectAt(new Vector2d(1,1));
        for(Animal animal: anotherCell.getAnimalsList()){
            Assertions.assertEquals(animal.getEnergy(), 15, 0);
        }

        for(int i = 0; i < 3; i++){
            for(int j=0; j < 3; j++){
                if(i != 1 && j != 1) {
                    for (Animal animal : ((FieldMapCell) anotherMap.objectAt(new Vector2d(i, j))).getAnimalsList()) {
                        Assertions.assertTrue(animal.getEnergy() == 20 || animal.getEnergy() == 10);
                    }
                }
            }
        }
    }

    @Test
    public void runTest(){
        FieldMap map = new FieldMap(9,9,new Vector2d(0,0), new Vector2d(0,0), 20, 5, 3);

        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                map.place(new Animal(map, new Vector2d(3*i+1, 3*j+1)));
                Assertions.assertTrue(map.isOccupied(new Vector2d(3*i+1, 3*j+1)));
            }
        }

        map.run();

        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++){
                Assertions.assertFalse(map.isOccupied(new Vector2d(3*i+1, 3*j+1)));
            }
        }
    }

    private int[] countGrasses(FieldMap map, Vector2d jungleLowerLeft, Vector2d jungleUpperRight, int width, int height){
       Vector2d position;
       int grassesInJungle = 0, grassesOutsideJungle = 0;

        for(int x =0; x < width; x++){
            for(int y=0; y < height; y++){
                position = new Vector2d(x,y);
                if(map.isOccupiedByGrass(position)) {
                    if (position.follows(jungleLowerLeft) && position.precedes(jungleUpperRight)){
                        grassesInJungle++;
                    }
                    else {
                        grassesOutsideJungle++;
                    }
                }
            }
        }

        return new int[]{grassesInJungle, grassesOutsideJungle};
    }

    @Test
    public void putGrassesTest(){
        Vector2d jungleLowerLeft =  new Vector2d(1,1);
        Vector2d jungleUpperRight = new Vector2d(1,2);
        int width = 5, height = 5;
        int grassesInJungle, grassesOutsideJungle;

        FieldMap map = new FieldMap(width,height,jungleLowerLeft,jungleUpperRight, 20, 5, 3);

        map.putGrasses();
        Assertions.assertArrayEquals(countGrasses(map, jungleLowerLeft, jungleUpperRight, width, height), new int[]{1,1});

        map.putGrasses();
        Assertions.assertArrayEquals(countGrasses(map, jungleLowerLeft, jungleUpperRight, width, height), new int[]{2,2});

        for(int i =0; i < 5; i++){
            map.putGrasses();
        }

        Assertions.assertArrayEquals(countGrasses(map, jungleLowerLeft, jungleUpperRight, width, height), new int[]{2,7});


        // second test
        jungleLowerLeft =  new Vector2d(1,1);
        jungleUpperRight = new Vector2d(1,1);
        FieldMap otherMap = new FieldMap(width,height,jungleLowerLeft,jungleUpperRight, 20, 5, 3);

        otherMap.place(new Animal(map, jungleLowerLeft));

        otherMap.putGrasses();
        otherMap.run();
        Assertions.assertArrayEquals(countGrasses(otherMap, jungleLowerLeft, jungleUpperRight, width, height), new int[]{0,1});

        otherMap.putGrasses();
        Assertions.assertArrayEquals(countGrasses(otherMap, jungleLowerLeft, jungleUpperRight, width, height), new int[]{1,2});




    }

}
