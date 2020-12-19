package gui;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
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


    private Simulation simulation;
    private MapStatistics statistics;

    private DayTime clock;

    private class DayTime extends AnimationTimer{

        private long FRAMES_PER_SEC = 50L;
        private long INTERVAL = 1000000000L / FRAMES_PER_SEC;

        private long last = 0;

        @Override
        public void handle(long now) {
            if(now - last > INTERVAL){
                step();
                last = now;
            }
        }
    }

    @FXML
    public void initialize(){
        clock = new DayTime();
        simulation = new Simulation(world);
        statistics = simulation.getStatistics();
        updateStatistics();
    }

    private void updateStatistics(){
        eraText.setText("Epoka: " + String.valueOf(statistics.getEraCounter()));
        animalsOnMap.setText(String.valueOf(statistics.getAnimalsOnMap()));
        plantsOnMap.setText(String.valueOf(statistics.getPlantsOnMap()));
        mostPopularGen.setText(statistics.getMostPopularGen());
        averageEnergy.setText(String.valueOf(statistics.getAverageEnergy()));
        averageAge.setText(String.valueOf(statistics.getAverageAge()));
        averageAmountOfChildren.setText(String.valueOf(statistics.getAverageAmountOfChildren()));
        statistics.updateAverageStatistics();
    }

    @FXML
    public void step(){
        simulation.daySimulate();
        statistics.increaseEraCounter();

        updateStatistics();
    }

    @FXML
    public void start(){
        world.setDisable(true);
        clock.start();
    }

    @FXML
    public void stop(){
        world.setDisable(false);
        clock.stop();
    }

    @FXML
    public void saveStats(){
        statistics.saveAverageStatistics();
    }
}
