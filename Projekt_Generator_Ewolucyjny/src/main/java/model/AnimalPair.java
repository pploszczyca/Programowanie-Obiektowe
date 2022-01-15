package model;

import java.util.Random;

public class AnimalPair {
    public Animal firstAnimal;
    public Animal secondAnimal;

    public AnimalPair(Animal firstAnimal, Animal secondAnimal){
        this.firstAnimal = firstAnimal;
        this.secondAnimal = secondAnimal;
    }

    public boolean canBeParents(){
        return firstAnimal.canReproduce() && secondAnimal.canReproduce();
    }

    public Vector2d getPairPosition(){
        return firstAnimal.getPosition();
    }

    public float getForChildEnergy(){
        float childEnergy = (firstAnimal.getEnergy()*1/4) + (secondAnimal.getEnergy()*1/4);
        firstAnimal.removeEnergy(firstAnimal.getEnergy()*1/4);
        secondAnimal.removeEnergy(secondAnimal.getEnergy()*1/4);
        return childEnergy;
    }


    private int[] createDivisionPoints(){
        Random random = new Random();
        int firstPoint,secondPoint;

        firstPoint = random.nextInt(30);

        do{
            secondPoint = random.nextInt(31);
        }while(secondPoint <= firstPoint);

        return new int[]{firstPoint, secondPoint};
    }


    public AnimalGenes mixParentsGenes(){
        int[] divisionPoints = createDivisionPoints();

        return new AnimalGenes(firstAnimal.getGenes(0, divisionPoints[0]), secondAnimal.getGenes(divisionPoints[0]+1, divisionPoints[1]), firstAnimal.getGenes(divisionPoints[1]+1, 31));
    }

    public void addChild(Animal child){
        firstAnimal.addChild(child);
        secondAnimal.addChild(child);
    }

}
