package agh.cs.lab;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap extends AbstractWorldMap{
    public RectangularMap(int width, int height){
        super();
        lowerLeft = new Vector2d(0,0);
        upperRight = new Vector2d(width-1, height-1);
    }
}
