package gui;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import model.Animal;
import model.Simulation;
import statistics.MapStatistics;


public class EvolutionSimulatorController {

    @FXML
    Pane world;

    @FXML
    Label eraText;

    @FXML
    Label animalsOnMap;

    @FXML
    Label plantsOnMap;

    @FXML
    Label mostPopularGen;

    @FXML
    Label averageEnergy;

    @FXML
    Label averageAge;

    @FXML
    Label averageAmountOfChildren;

    @FXML
    Button saveButton;

    @FXML
    Button highlightButton;

    @FXML
    Button startButton;

    @FXML
    Button stopButton;

    private Simulation simulation;
    private MapStatistics statistics;
    private long stopEra;

    private DayTime clock;

    private class DayTime extends AnimationTimer{

        private final long FRAMES_PER_SEC = 50L;
        private final long INTERVAL = 1000000000L / FRAMES_PER_SEC;

        private long last = 0;

        @Override
        public void handle(long now) {
            if(now - last > INTERVAL){
                step();
                last = now;

            }

            if(simulation.getEra() == stopEra){
                stopAnimation();
                simulation.popUpInformation();
            }
        }
    }

    @FXML
    public void initialize(){
        stopEra = Long.MAX_VALUE;
        clock = new DayTime();
        simulation = new Simulation(world, this);
        statistics = simulation.getStatistics();
        updateStatistics();
        setStartStopButtonsNoActivity(false, true);
    }

    private void updateStatistics(){
        eraText.setText("Epoka: " + statistics.getEraCounter());
        animalsOnMap.setText(String.valueOf(statistics.getAnimalsOnMap()));
        plantsOnMap.setText(String.valueOf(statistics.getPlantsOnMap()));
        mostPopularGen.setText(statistics.getMostPopularGen());
        averageEnergy.setText(String.valueOf(statistics.getAverageEnergy()));
        averageAge.setText(String.valueOf(statistics.getAverageAge()));
        averageAmountOfChildren.setText(String.valueOf(statistics.getAverageAmountOfChildren()));
        statistics.updateAverageStatistics();
    }

    public void step(){
        simulation.daySimulate();
        statistics.increaseEraCounter();

        updateStatistics();
    }

    @FXML
    public void startAnimation(){
        world.setDisable(true);
        clock.start();
        setButtonsNoActivity(true);
        setStartStopButtonsNoActivity(true, false);
    }

    @FXML
    public void stopAnimation(){
        world.setDisable(false);
        clock.stop();
        setButtonsNoActivity(false);
        setStartStopButtonsNoActivity(false, true);
    }

    @FXML
    public void saveStats(){
        statistics.saveAverageStatistics();
        saveButton.setDisable(true);
    }

    @FXML
    public void findAnimalsWithPopularGens(){
        simulation.highlightAnimalsWithPopularGen();
        highlightButton.setDisable(true);
    }

    private void setButtonsNoActivity(boolean status){
        saveButton.setDisable(status);
        highlightButton.setDisable(status);
    }

    private void setStartStopButtonsNoActivity(boolean startStatus, boolean endStatus){
        startButton.setDisable(startStatus);
        stopButton.setDisable(endStatus);
    }

    public void setAnimalTracker(Animal animal){
        simulation.startAnimalObservation(animal);
    }

    public void setStopEra(long eraAmount){
        stopEra = simulation.getEra()+eraAmount;
        startAnimation();
        setStartStopButtonsNoActivity(true, true);
    }
}
