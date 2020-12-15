package model;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.scene.layout.Pane;
import statistics.MapStatistics;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Simulation {
    private FieldMap map;

    private int width;
    private int height;
    private int jungleWidth;
    private int jungleHeight;
    private int startEnergy;
    private int moveEnergy;
    private float plantEnergy;
    private int randomAnimals;
    private Pane world;
    private MapStatistics statistics;

    public Simulation(int width, int height, int jungleWidth, int jungleHeight , int startEnergy, int moveEnergy, float plantEnergy, int randomAnimals, Pane world){
        this.width = width;
        this.height = height;
        this.jungleWidth = jungleWidth;
        this.jungleHeight = jungleHeight;
        this.startEnergy = startEnergy;
        this.moveEnergy = moveEnergy;
        this.plantEnergy = plantEnergy;
        this.randomAnimals = randomAnimals;
        this.world = world;
        initializeMap();
    }

    public Simulation(Pane world){
        this.world = world;
        loadDataFromFile();
        initializeMap();
    }

    private void initializeMap(){
        map = new FieldMap(width, height, calculateJungleLowerLeft(width,height,jungleWidth, jungleHeight), calculateJungleUpperRight(width,height,jungleWidth, jungleHeight), startEnergy, moveEnergy, plantEnergy, this);
        statistics = new MapStatistics(map);
        map.randomPlace(randomAnimals);
    }

    private void loadDataFromFile(){
        try{
            Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/appSettings.json"));
            JsonObject parser = JsonParser.parseReader(reader).getAsJsonObject();

            width = parser.get("width").getAsInt();
            height = parser.get("height").getAsInt();
            jungleWidth = parser.get("jungleWidth").getAsInt();
            jungleHeight = parser.get("jungleHeight").getAsInt();
            startEnergy = parser.get("startEnergy").getAsInt();
            moveEnergy = parser.get("moveEnergy").getAsInt();
            plantEnergy = parser.get("plantEnergy").getAsFloat();
            randomAnimals = parser.get("randomAnimals").getAsInt();

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


    private Vector2d calculateJungleLowerLeft(int width, int height, int jungleWidth, int jungleHeight){
        return new Vector2d(Math.round((width-jungleWidth)/2),  Math.round((height-jungleHeight)/2));
    }

    private Vector2d calculateJungleUpperRight(int width, int height, int jungleWidth, int jungleHeight){
        return new Vector2d(Math.round((width+jungleWidth-2)/2),  Math.round((height+jungleHeight-2)/2));
    }

    public void daySimulate(){
        map.removeAnimalsWithLowEnergy();
        map.run();
        map.eat();
        map.multiplication();
        map.putGrasses();
    }

    public MapStatistics getStatistics(){
        return statistics;
    }

    public Pane getPane(){
        return world;
    }

    public int getEra(){
        return statistics.getEraCounter();
    }



}
