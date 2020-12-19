package statistics;

import model.Animal;
import model.FieldMap;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MapStatistics extends AbstractMapStatistics{
    final private FieldMap map;
    private int eraCounter;
    private int animalsOnMap;
    private int plantsOnMap;
    private String mostPopularGen;
    private float averageEnergy;
    private float averageAge;
    private float averageAmountOfChildren;

    private int sumAgesOfDead;
    private int deathAmount;

    private AverageMapStatistics averageMapStatistics;

    public MapStatistics(FieldMap map){
        eraCounter = 1;
        sumAgesOfDead = 0;
        deathAmount = 0;
        averageAge = 0;
        averageEnergy = 0;
        averageAmountOfChildren = 0;
        this.map = map;
        averageMapStatistics = new AverageMapStatistics();
    }

    public int getEraCounter(){
        return eraCounter;
    }

    public void increaseEraCounter(){
        eraCounter++;
    }

    public int getAnimalsOnMap(){
        animalsOnMap = map.getAmountAliveAnimals();
        return animalsOnMap;
    }

    public int getPlantsOnMap() {
        plantsOnMap = map.getGrassAmount();
        return plantsOnMap;
    }

    public float getAverageEnergy(){
        averageEnergy = map.sumAnimalsEnergy()/map.getAmountAliveAnimals();
        return averageEnergy;
    }

    public float getAverageAmountOfChildren(){
        averageAmountOfChildren = (float) map.getAnimalsChildrenAmount()/map.getAmountAliveAnimals();
        return averageAmountOfChildren;
    }

    public void addDeathAnimal(int age){
        sumAgesOfDead += age;
        deathAmount++;
        averageAge = (float) sumAgesOfDead/deathAmount;
    }

    public float getAverageAge(){
        return averageAge;
    }


    public String getMostPopularGen(){
        List<Animal> animals = map.makeListOfAllAnimals();
        Map<String, Integer> genes = new LinkedHashMap<>();
        String gen;
        int amount;

        for(Animal animal: animals){
            amount = 1;

            gen = animal.toString();

            if(genes.get(gen) != null){
                amount = genes.get(gen) + 1;
                genes.remove(gen);
            }

            genes.put(gen, amount);
        }

        mostPopularGen = findMostPopularGen(genes);
        return mostPopularGen;
    }

    public void updateAverageStatistics(){
        averageMapStatistics.updateStatistics(animalsOnMap, plantsOnMap, mostPopularGen, averageEnergy, averageAge, averageAmountOfChildren);
    }

    public void saveAverageStatistics(){
        averageMapStatistics.saveStatistics();
    }

}
