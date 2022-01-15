package agh.cs.lab;

import java.util.Comparator;

class CompareIMapElementsByY extends CompareIMapElements implements Comparator<IMapElement> {
    public int compare(IMapElement element, IMapElement otherElement){
        return compareByPosition(element, otherElement,element.getPosition().y, otherElement.getPosition().y, element.getPosition().x, otherElement.getPosition().x);
    }
}