package gui;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.AbstractMapElement;
import model.Animal;
import model.IPositionChangeObserver;
import model.Vector2d;
import javafx.scene.shape.Rectangle;


public class Drawing implements IPositionChangeObserver {
    private final int sizeFactor;
    private Rectangle rectangle;
    private Pane world;

    public Drawing(Pane world, MapColors color){
        this(world);
        setColor(color);
    }

    public Drawing(Pane world){
        sizeFactor = 50;
        this.world = world;
        rectangle = new Rectangle();
    }

    public Drawing(Pane world, AbstractMapElement mapElement){
        this(world);
        if(mapElement instanceof Animal) {
            rectangle.addEventHandler(MouseEvent.MOUSE_CLICKED, new AnimalEventHandler((Animal) mapElement));
        }
    }


    public void drawRectangle(Vector2d lowerLeft, Vector2d upperRight){
        setPosition(lowerLeft);
        rectangle.setWidth((upperRight.getX()- lowerLeft.getX()+1)*sizeFactor);
        rectangle.setHeight((upperRight.getY()- lowerLeft.getY()+1)*sizeFactor);

        world.getChildren().add(rectangle);
    }



    public void drawPoint(Vector2d position){
        drawRectangle(position, position);
    }


    public void setColor(MapColors color){
        rectangle.setFill(color.getColor());
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        setPosition(newPosition);
    }

    private void setPosition(Vector2d position){
        rectangle.setX(position.getX()*sizeFactor);
        rectangle.setY(position.getY()*sizeFactor);
    }

    public void removeObjectFromWorld(){
        world.getChildren().remove(rectangle);
    }
}
