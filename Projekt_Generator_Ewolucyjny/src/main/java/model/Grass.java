package model;

import gui.MapColors;

public class Grass extends AbstractMapElement{
    public Grass(Vector2d position, FieldMap map){
        super(position, map.getPane(), map.getSimulation().getSizeFactor());
        drawElement();
    }

    public String toString(){
        return "*";
    }

    public MapColors getPointColor(){
        return MapColors.GRASS;
    }
}
