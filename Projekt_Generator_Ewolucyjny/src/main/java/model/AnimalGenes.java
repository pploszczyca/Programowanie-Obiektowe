package model;

import java.util.Arrays;
import java.util.Random;

public class AnimalGenes {
    private final int genesLength = 32;
    private final int possibleDifferentGenes = 8;
    private int genes[] = new int[genesLength];
    private final Random random = new Random();

    public AnimalGenes(){
        fillGenesArray();
    }

    public AnimalGenes(int[] genesFromFirstParent, int[] genesFromFSecondParent, int[] anotherGenesFromFirstParent){
        addElementsToGenes(0, genesFromFirstParent);
        addElementsToGenes(genesFromFirstParent.length, anotherGenesFromFirstParent);
        addElementsToGenes(genesFromFirstParent.length + anotherGenesFromFirstParent.length, genesFromFSecondParent);

        checkAndRepairArray();
        sortGenesArray();
    }

    private void checkAndRepairArray(){
        int[] rotationsAmount = new int[possibleDifferentGenes];

        for(int gen: genes){
            rotationsAmount[gen]++;
        }

        for(int i =0; i< possibleDifferentGenes; i++){
            if(rotationsAmount[i]==0){
                int newGenIndex;

                do{
                    newGenIndex = random.nextInt(genesLength);
                }while(rotationsAmount[genes[newGenIndex]] <= 1);

                rotationsAmount[genes[newGenIndex]]--;
                genes[newGenIndex] = i;
                rotationsAmount[i]++;
            }
        }

    }

    private void addElementsToGenes(int startIndex, int[] array){
        for(int number: array){
            genes[startIndex] = number;
            startIndex++;
        }
    }

    private void fillGenesArray(){
        for(int i = 0; i < 8; i++)
            genes[i] = i;

        for(int i = 8; i< genesLength; i++)
            genes[i] = random.nextInt(possibleDifferentGenes);

        sortGenesArray();
    }

    private void sortGenesArray(){
        Arrays.sort(genes);
    }

    public int drawRotation(){
        return genes[random.nextInt(genesLength)];
    }

    public int[] getGenesElements(int startIndex, int endIndex){
        if(startIndex > endIndex || startIndex < 0 || endIndex >= genesLength){
            throw new IllegalArgumentException("Indeksy: " + startIndex + ";" + endIndex + " niepoprawne w getGenesElements");
        }

        return Arrays.copyOfRange(genes, startIndex, endIndex+1);
    }

}
