package model;

import gui.MapColors;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Grass extends AbstractMapElement{
    private Vector2d position;

    public Grass(Vector2d position){
        super(position);
    }

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
