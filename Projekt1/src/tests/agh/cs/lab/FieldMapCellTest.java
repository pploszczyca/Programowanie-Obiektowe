package agh.cs.lab;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class FieldMapCellTest {

    @Test
    public void addTest(){
        FieldMap map = new FieldMap(10,10,new Vector2d(0,0),new Vector2d(0,0),10, 5, 0);
        FieldMapCell cell = new FieldMapCell(new Animal(map, 0));

        Assert.assertFalse(cell.isAnimalListEmpty());
        Assert.assertEquals(cell.size(), 1);

        for(int i = 0; i < 5; i++)
            cell.add(new Animal(map, 0));

        Assert.assertEquals(cell.size(), 6);
    }

    @Test
    public void removeTest(){
        FieldMap map = new FieldMap(10,10,new Vector2d(0,0),new Vector2d(0,0),10, 5, 0);
        FieldMapCell cell = new FieldMapCell(new Animal(map, 0));       // Animals have position (2,2)

        for(int i = 0; i < 5; i++)
            cell.add(new Animal(map, i));

        Assert.assertEquals(cell.size(), 6);
        cell.removeAnimal(new Vector2d(2,2));
        Assert.assertEquals(cell.size(), 5);

        for(int i = 0; i < 5; i++)
            cell.removeAnimal(new Vector2d(2,2));

        Assert.assertEquals(cell.size(), 0);
        Assert.assertTrue(cell.isAnimalListEmpty());

        cell.removeAnimal(new Vector2d(2,2));
        Assert.assertNotEquals(cell.size(), -1);
        Assert.assertTrue(cell.isAnimalListEmpty());
    }

    @Test
    public void findTwoMaxTest(){
        FieldMap map = new FieldMap(10,10,new Vector2d(0,0),new Vector2d(0,0),10, 5, 0);
        FieldMapCell cell = new FieldMapCell(new Animal(map, 0));
        for(int i: new int[]{5,3,9,4,1,10})
            cell.add(new Animal(map, i));

        AnimalPair animalPair = cell.findTwoMax();
        Assert.assertEquals(animalPair.firstAnimal.getEnergy(), 10,0);
        Assert.assertEquals(animalPair.secondAnimal.getEnergy(), 9,0);

        for(int i: new int[]{20,6,30})
            cell.add(new Animal(map, i));

        animalPair = cell.findTwoMax();
        Assert.assertEquals(animalPair.firstAnimal.getEnergy(), 30,0);
        Assert.assertEquals(animalPair.secondAnimal.getEnergy(), 20,0);

    }

    @Test
    public void animalsWithMaxEnergyTest(){
        FieldMap map = new FieldMap(10,10,new Vector2d(0,0),new Vector2d(0,0),10, 5, 0);
        FieldMapCell cell = new FieldMapCell(new Animal(map, 10));
        cell.removeAnimal(new Vector2d(2,2));
        Assert.assertEquals(cell.animalsWithMaxEnergy(), 0);

        for(int i: new int[]{5,0,10,3,1,5,10,10})
            cell.add(new Animal(map, i));

        Assert.assertEquals(cell.animalsWithMaxEnergy(), 3);       // 10,10,10

        cell.add(new Animal(map, 15));
        Assert.assertEquals(cell.animalsWithMaxEnergy(), 1);    // 15

        for(int i = 0; i <= 3; i++){
            cell.removeAnimal(new Vector2d(2,2));
        }

        Assert.assertEquals(cell.animalsWithMaxEnergy(), 2);       // 5, 5

    }

    @Test
    public void removeWithLowEnergy(){
        FieldMap map = new FieldMap(10,10,new Vector2d(0,0),new Vector2d(0,0),10, 5, 0);
        FieldMapCell cell = new FieldMapCell(new Animal(map, -5));

        for(int i= -4; i <= 5; i++){
            cell.add(new Animal(map, i));
        }

        cell.removeWithLowEnergy();
        Assert.assertEquals(cell.size(), 5);

        List<Animal> cellAnimals = cell.getAnimalsList();
        for(Animal animal: cellAnimals){
            Assert.assertTrue(animal.getEnergy() > 0);
        }

        for(int i= -4; i <= 0; i++){
            cell.add(new Animal(map, i));
        }
        cell.removeWithLowEnergy();

        cellAnimals = cell.getAnimalsList();
        for(Animal animal: cellAnimals){
            Assert.assertTrue(animal.getEnergy() > 0);
        }
    }
}
