package gui;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.AbstractMapElement;
import model.Animal;
import model.IPositionChangeObserver;
import model.Vector2d;
import javafx.scene.shape.Rectangle;

import java.util.List;


public class Drawing implements IPositionChangeObserver {
    private final int sizeFactor;
    private Rectangle rectangle;
    private Pane world;
    private AbstractMapElement mapElement;

    public Drawing(Pane world,int sizeFactor){
        this.sizeFactor = sizeFactor;
        this.world = world;
        rectangle = new Rectangle();
    }

    public Drawing(Pane world, MapColors color,int sizeFactor){
        this(world, sizeFactor);
        setColor(color);
    }

    public Drawing(Pane world, AbstractMapElement mapElement, int sizeFactor){
        this(world, sizeFactor);
        this.mapElement = mapElement;
        if(mapElement instanceof Animal) {
            rectangle.addEventHandler(MouseEvent.MOUSE_CLICKED, new AnimalEventHandler((Animal) mapElement, world));
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

    public void highlightAnimalsWithPopularGen(List<Animal> animals, String gen){
        for(Animal animal: animals){
            if(animal.toString().equals(gen)){
                animal.setColor(MapColors.ANIMAL_WITH_POPULAR_GEN);
            }
        }

    }
}
