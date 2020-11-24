package agh.cs.lab;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap extends AbstractWorldMap{
    public RectangularMap(int width, int height){
        super(width, height);
        lowerLeft = new Vector2d(0,0);
    }
}
