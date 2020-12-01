package agh.cs.lab;

import java.util.Comparator;

class CompareIMapElementsByX extends CompareIMapElements implements Comparator<IMapElement> {
    public int compare(IMapElement element, IMapElement otherElement){
        return compareByPosition(element, otherElement,element.getPosition().x, otherElement.getPosition().x, element.getPosition().y, otherElement.getPosition().y);
    }
}