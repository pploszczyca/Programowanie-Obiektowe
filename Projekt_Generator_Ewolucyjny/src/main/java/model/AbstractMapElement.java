package model;

import gui.Drawing;
import gui.MapColors;
import javafx.scene.layout.Pane;

abstract public class AbstractMapElement{
    protected Vector2d position;
    protected Drawing drawing;  // pomieszanie logiki aplikacji z wizualizacją

    AbstractMapElement(Vector2d position){
        this.position = position;
    }

    AbstractMapElement(Vector2d position, Pane world, int sizeFactor){
        this(position);
        drawing = new Drawing(world, this, sizeFactor);
    }

    public void drawElement(){
        drawElement(getPointColor());
    }

    public void drawElement(MapColors color){
        setColor(color);
        drawing.drawPoint(position);
    }

    public void setColor(MapColors color){
        drawing.setColor(color);
    }

    public Vector2d getPosition(){
        return position;
    }

    public void removeDrawn(){
        drawing.removeObjectFromWorld();
    }

    abstract public MapColors getPointColor();

}
