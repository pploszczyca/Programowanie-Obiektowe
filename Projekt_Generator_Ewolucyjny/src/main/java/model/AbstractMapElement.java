package model;

import gui.Drawing;
import gui.MapColors;
import javafx.scene.layout.Pane;

abstract public class AbstractMapElement{
    Vector2d position;
    Drawing drawing;

    AbstractMapElement(Vector2d position){
        this.position = position;
    }

    AbstractMapElement(Vector2d position, Pane world){
        this(position);
        drawing = new Drawing(world);
    }

    public void drawElement(){
        drawing.setColor(getPointColor());
        drawing.drawPoint(position);
    }

    public Vector2d getPosition(){
        return position;
    }

    public void removeDrawn(){
        drawing.removeObjectFromWorld();
    }

    abstract public MapColors getPointColor();

}
