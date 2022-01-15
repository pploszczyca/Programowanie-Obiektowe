package statistics;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class AverageMapStatistics extends AbstractMapStatistics{
    private int eraCounter;
    private double animalsOnMap;
    private double plantsOnMap;
    private final Map<String, Integer> mostPopularGens;
    private double averageEnergy;
    private double averageAge;
    private double averageAmountOfChildren;

    public AverageMapStatistics(){
        eraCounter = 0;
        animalsOnMap = 0;
        plantsOnMap = 0;
        averageEnergy = 0;
        averageAge = 0;
        averageAmountOfChildren = 0;
        mostPopularGens = new LinkedHashMap<>();
    }

    private String getDate(){
        Date nowDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        return dateFormat.format(nowDate);
    }

    public void saveStatistics(){
        try{
            File file = new File("statistics/map_statistics " + getDate() + ".txt");
            FileWriter writer = new FileWriter(file);

            writer.write("UŚREDNIONE STATYSTYKI\n");
            writer.write("Epoka nr: " + eraCounter + "\n");
            writer.write("Ilość zwierząt na mapie: " + animalsOnMap + "\n");
            writer.write("Ilość roślin na mapie: " + plantsOnMap + "\n");
            writer.write("Średnia ilość energii: " + averageEnergy + "\n");
            writer.write("Średni wiek zwierzęcia: " + averageAge + "\n");
            writer.write("Średnia ilość dzieci: " + averageAmountOfChildren + "\n");
            writer.write("Najpopularniejszy genotyp: " + findMostPopularGen(mostPopularGens) + "\n");

            writer.close();

        } catch (IOException ex) {
            System.out.println("Błąd w zapisywaniu statystyk do pliku");
            ex.printStackTrace();
        }
    }

    private double averageTheValue(double currentValue, double newValue){
        return ((currentValue*eraCounter)+newValue)/(eraCounter+1);
    }

    private void increaseGenCounter(String gen){
        int amount = 1;

        if(mostPopularGens.get(gen) != null){
            amount = mostPopularGens.get(gen) + 1;
            mostPopularGens.remove(gen);
        }

        mostPopularGens.put(gen, amount);
    }

    public void updateStatistics(int animalsOnMap, int plantsOnMap, String popularGen, double averageEnergy, double averageAge, double averageAmountOfChildren){
        this.animalsOnMap = averageTheValue(this.animalsOnMap, animalsOnMap);
        this.plantsOnMap = averageTheValue(this.plantsOnMap, plantsOnMap);
        increaseGenCounter(popularGen);
        this.averageEnergy = averageTheValue(this.averageEnergy, averageEnergy);
        this.averageAge = averageTheValue(this.averageAge, averageAge);
        this.averageAmountOfChildren = averageTheValue(this.averageAmountOfChildren, averageAmountOfChildren);
        eraCounter++;
    }

}
