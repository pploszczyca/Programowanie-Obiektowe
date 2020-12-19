package model;

import gui.MapColors;
import javafx.scene.layout.Pane;

public class Grass extends AbstractMapElement{
    public Grass(Vector2d position, Pane world){
        super(position, world);
        drawElement();
    }

    public String toString(){
        return "*";
    }

    public MapColors getPointColor(){
        return MapColors.GRASS;
    }
}
