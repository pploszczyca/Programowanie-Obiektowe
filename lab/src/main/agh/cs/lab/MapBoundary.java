package agh.cs.lab;

import java.util.Iterator;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver{
    final private TreeSet<IMapElement> sortedArrayX;
    final private TreeSet<IMapElement> sortedArrayY;

    public MapBoundary(){
        sortedArrayX = new TreeSet<IMapElement>(new CompareIMapElementsByX());
        sortedArrayY = new TreeSet<IMapElement>(new CompareIMapElementsByY());
    }

    public Vector2d[] findCorners(){
        Vector2d[] corners = new Vector2d[2];
        corners[0] = new Vector2d(sortedArrayX.first().getPosition().x, sortedArrayY.first().getPosition().y);
        corners[1] = new Vector2d(sortedArrayX.last().getPosition().x, sortedArrayY.last().getPosition().y);
        return corners;
    }

    public void addMapElement(IMapElement newElement){
        sortedArrayX.add(newElement);
        sortedArrayY.add(newElement);
    }


    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        for(Iterator<IMapElement> iterator = sortedArrayX.iterator(); iterator.hasNext();){
            IMapElement element = iterator.next();
            if(element.getPosition().equals(newPosition) && element instanceof Animal){
                iterator.remove();
                sortedArrayX.add(element);
                break;
            }
        }

        for(Iterator<IMapElement> iterator = sortedArrayY.iterator(); iterator.hasNext();){
            IMapElement element = iterator.next();
            if(element.getPosition().equals(newPosition) && element instanceof Animal){
                iterator.remove();
                sortedArrayY.add(element);
                break;
            }
        }

    }

    public String toString(){
        return sortedArrayX.toString() + "\n" + sortedArrayY.toString();
    }
}





