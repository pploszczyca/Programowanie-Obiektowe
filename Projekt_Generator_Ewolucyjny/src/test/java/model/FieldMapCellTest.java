package model;

import javafx.scene.layout.Pane;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class FieldMapCellTest {

    @Test
    public void addTest(){
        Simulation simulation = new Simulation(10,10,1,1,10,5,0,0,new Pane());
        FieldMap map = simulation.getMap();
        FieldMapCell cell = new FieldMapCell(new Animal(map, simulation.getStartEnergy(), new Vector2d(2,2),0), simulation);

        Assertions.assertFalse(cell.isAnimalListEmpty());
        Assertions.assertEquals(cell.size(), 1);

        for(int i = 0; i < 5; i++)
            cell.add(new Animal(map, simulation.getStartEnergy(), new Vector2d(2,2),0));

        Assertions.assertEquals(cell.size(), 6);
    }

    @Test
    public void removeTest(){
        Simulation simulation = new Simulation(10,10,1,1,10,5,0,0,new Pane());
        FieldMap map = simulation.getMap();
        FieldMapCell cell = new FieldMapCell(new Animal(map, simulation.getStartEnergy(), new Vector2d(2,2),0), simulation);       // Animals have position (2,2)

        for(int i = 0; i < 5; i++)
            cell.add(new Animal(map, i, new Vector2d(2,2),0));

        Assertions.assertEquals(cell.size(), 6);
        cell.removeAnimal(new Vector2d(2,2));
        Assertions.assertEquals(cell.size(), 5);

        for(int i = 0; i < 5; i++)
            cell.removeAnimal(new Vector2d(2,2));

        Assertions.assertEquals(cell.size(), 0);
        Assertions.assertTrue(cell.isAnimalListEmpty());

        cell.removeAnimal(new Vector2d(2,2));
        Assertions.assertNotEquals(cell.size(), -1);
        Assertions.assertTrue(cell.isAnimalListEmpty());
    }

    @Test
    public void findTwoMaxTest(){
        Simulation simulation = new Simulation(10,10,1,1,10,5,0,0,new Pane());
        FieldMap map = simulation.getMap();
        FieldMapCell cell = new FieldMapCell(new Animal(map, 6, new Vector2d(2,2),0), simulation);
        for(int i: new int[]{5,3,9,4,1,10})
            cell.add(new Animal(map, i, new Vector2d(2,2),0));

        AnimalPair animalPair = cell.findTwoMax();
        Assertions.assertEquals(animalPair.firstAnimal.getEnergy(), 10,0);
        Assertions.assertEquals(animalPair.secondAnimal.getEnergy(), 9,0);

        for(int i: new int[]{20,6,30})
            cell.add(new Animal(map, i, new Vector2d(2,2),0));

        animalPair = cell.findTwoMax();
        Assertions.assertEquals(animalPair.firstAnimal.getEnergy(), 30,0);
        Assertions.assertEquals(animalPair.secondAnimal.getEnergy(), 20,0);

    }

    @Test
    public void animalsWithMaxEnergyTest(){
        Simulation simulation = new Simulation(10,10,1,1,10,5,0,0,new Pane());
        FieldMap map = simulation.getMap();
        FieldMapCell cell = new FieldMapCell(new Animal(map, 10, new Vector2d(2,2),0), simulation);
        cell.removeAnimal(new Vector2d(2,2));
        Assertions.assertEquals(cell.animalsWithMaxEnergy(), 0);

        for(int i: new int[]{5,0,10,3,1,5,10,10})
            cell.add(new Animal(map, i, new Vector2d(2,2),0));

        Assertions.assertEquals(cell.animalsWithMaxEnergy(), 3);       // 10,10,10

        cell.add(new Animal(map, 15, new Vector2d(2,2),0));
        Assertions.assertEquals(cell.animalsWithMaxEnergy(), 1);    // 15

        for(int i = 0; i <= 3; i++){
            cell.removeAnimal(new Vector2d(2,2));
        }

        Assertions.assertEquals(cell.animalsWithMaxEnergy(), 2);       // 5, 5

    }

    @Test
    public void removeWithLowEnergy(){
        Simulation simulation = new Simulation(10,10,1,1,10,5,0,0,new Pane());
        FieldMap map = simulation.getMap();
        FieldMapCell cell = new FieldMapCell(new Animal(map, -5, new Vector2d(2,2),0), simulation);

        for(int i= -4; i <= 5; i++){
            cell.add(new Animal(map, i, new Vector2d(2,2),0));
        }

        cell.removeWithLowEnergy();
        Assertions.assertEquals(cell.size(), 5);

        List<Animal> cellAnimals = cell.getAnimalsList();
        for(Animal animal: cellAnimals){
            Assertions.assertTrue(animal.getEnergy() > 0);
        }

        for(int i= -4; i <= 0; i++){
            cell.add(new Animal(map, i, new Vector2d(2,2),0));
        }
        cell.removeWithLowEnergy();

        cellAnimals = cell.getAnimalsList();
        for(Animal animal: cellAnimals){
            Assertions.assertTrue(animal.getEnergy() > 0);
        }
    }
}
