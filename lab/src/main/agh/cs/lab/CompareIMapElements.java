package agh.cs.lab;

class CompareIMapElements {
    private int compareByClass(IMapElement element, IMapElement otherElement){
        if((element instanceof Grass) && (otherElement instanceof Animal)){
            return 1;
        }
        else if ((element instanceof Animal) && (otherElement instanceof Grass)){
            return -1;
        }
        return 0;
    }

    protected int compareByPosition(IMapElement element, IMapElement otherElement, int firstPosition, int otherFirstPosition, int secondPosition, int otherSecondPosition){
        int result = Integer.compare(firstPosition, otherFirstPosition);
        if(result == 0){
            result = Integer.compare(secondPosition, otherSecondPosition);
            if(result == 0){
                result = compareByClass(element, otherElement);
            }
        }

        return result;
    }
}
