package gui;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import model.Simulation;


public class EvolutionSimulatorController {

    @FXML
    Pane world;

    @FXML
    Label eraText;


    private Simulation simulation;
    private int eraCounter;

    private DayTime clock;

    private class DayTime extends AnimationTimer{

        private long FRAMES_PER_SEC = 25L;
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
//        simulation = new Simulation(15,15,9,9, 20, 1, 10, 30, world);
        simulation = new Simulation(world);
        eraCounter = 1;
        eraText.setText("Epoka: " + eraCounter);

    }

    @FXML
    public void step(){
        simulation.daySimulate();
        eraCounter++;
        eraText.setText("Epoka: " + eraCounter);
    }

    @FXML
    public void start(){
        clock.start();
    }

    @FXML
    public void stop(){
        clock.stop();
    }
}
