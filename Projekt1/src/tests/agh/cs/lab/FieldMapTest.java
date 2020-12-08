package agh.cs.lab;

import org.junit.Assert;
import org.junit.Test;

public class FieldMapTest {

    @Test
    public void randomPlaceTest(){
        FieldMap map = new FieldMap(100,100,new Vector2d(0,0), new Vector2d(0,0), 10, 5, 3);

        map.randomPlace(10000);
        Vector2d position;

        for(int i = 0; i < 100; i++){
            for(int j= 0; j < 100; j++){
                position = new Vector2d(i,j);
                Assert.assertTrue(map.isOccupied(position));
                FieldMapCell cell = (FieldMapCell) map.objectAt(position);
                Assert.assertEquals(cell.size(), 1);
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
            Assert.assertEquals(animal.getEnergy(), 12, 0);
        }

        Animal animalChild = ((FieldMapCell) map.objectAt(new Vector2d(0,1))).getAnimalsList().get(0);
        Assert.assertEquals(animalChild.getEnergy(), 8, 0);


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
            Assert.assertEquals(animal.getEnergy(), 15, 0);
        }

        for(int i = 0; i < 3; i++){
            for(int j=0; j < 3; j++){
                if(i != 1 && j != 1) {
                    for (Animal animal : ((FieldMapCell) anotherMap.objectAt(new Vector2d(i, j))).getAnimalsList()) {
                        Assert.assertTrue(animal.getEnergy() == 20 || animal.getEnergy() == 10);
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
                Assert.assertTrue(map.isOccupied(new Vector2d(3*i+1, 3*j+1)));
            }
        }

        map.run();

        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++){
                Assert.assertFalse(map.isOccupied(new Vector2d(3*i+1, 3*j+1)));
            }
        }
    }

}
