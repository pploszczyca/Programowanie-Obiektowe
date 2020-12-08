package agh.cs.lab;

import org.junit.Assert;
import org.junit.Test;

public class AnimalGenesTest {
    @Test
    public void fillGenesArrayTest(){
        int genesFromAnimal[] = new AnimalGenes().getGenesElements(0,31);

        Assert.assertTrue(genesFromAnimal.length == 32);

        for(int gen: genesFromAnimal){
            Assert.assertTrue(gen >= 0 && gen <=7);
        }

    }

    @Test
    public void addElementsToGenes(){
        int[] firstArray = {0,1,2,3,4,5,6,7,0,1,2,3,4,5,6,7};
        int[] secondArray = {0,1,2,3,4,5,6,7};
        int[] thirdArray = {0,1,2,3,4,5,6,7};
        AnimalGenes animalGenes = new AnimalGenes(firstArray, secondArray, thirdArray);
        Assert.assertArrayEquals(animalGenes.getGenesElements(0,31), new int[]{0,0,0,0,1,1,1,1,2,2,2,2,3,3,3,3,4,4,4,4,5,5,5,5,6,6,6,6,7,7,7,7});
    }

    @Test
    public void checkAndRepairArrayTest(){
        int[] firstArray = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        int[] secondArray = {0,0,0,0,0,0,0,0};
        int[] thirdArray = {0,0,0,0,0,0,0,0};

        AnimalGenes animalGenes = new AnimalGenes(firstArray, secondArray, thirdArray);
        Assert.assertArrayEquals(animalGenes.getGenesElements(0,31), new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,3,4,5,6,7});
    }

    @Test
    public void getGenesElementsTest(){
        int[] firstArray = {0,1,2,3,4,5,6,7,0,1,2,3,4,5,6,7};
        int[] secondArray = {0,1,2,3,4,5,6,7};
        int[] thirdArray = {0,1,2,3,4,5,6,7};
        AnimalGenes animalGenes = new AnimalGenes(firstArray, secondArray, thirdArray);

        // Array: 0,0,0,0,1,1,1,1,2,2,2,2,3,3,3,3,4,4,4,4,5,5,5,5,6,6,6,6,7,7,7,7
        Assert.assertArrayEquals(animalGenes.getGenesElements(0,3), new int[]{0,0,0,0});
        Assert.assertArrayEquals(animalGenes.getGenesElements(4,10), new int[]{1,1,1,1,2,2,2});
        Assert.assertArrayEquals(animalGenes.getGenesElements(0,31), new int[]{0,0,0,0,1,1,1,1,2,2,2,2,3,3,3,3,4,4,4,4,5,5,5,5,6,6,6,6,7,7,7,7});
        Assert.assertThrows("Indeksy: 0;32 niepoprawne w getGenesElements", IllegalArgumentException.class, ()->{
            Assert.assertArrayEquals(new AnimalGenes(new int[]{0,1,2,3,4,5,6,7,0,1,2,3,4,5,6,7}, new int[]{0,1,2,3,4,5,6,7}, new int[]{0,1,2,3,4,5,6,7}).getGenesElements(0,32), new int[]{0,0,0,0,1,1,1,1,2,2,2,2,3,3,3,3,4,4,4,4,5,5,5,5,6,6,6,6,7,7,7,7});
        });
        Assert.assertThrows("Indeksy: -1;31 niepoprawne w getGenesElements", IllegalArgumentException.class, ()->{
            Assert.assertArrayEquals(new AnimalGenes(new int[]{0,1,2,3,4,5,6,7,0,1,2,3,4,5,6,7}, new int[]{0,1,2,3,4,5,6,7}, new int[]{0,1,2,3,4,5,6,7}).getGenesElements(-1,31), new int[]{0,0,0,0,1,1,1,1,2,2,2,2,3,3,3,3,4,4,4,4,5,5,5,5,6,6,6,6,7,7,7,7});
        });
    }
}
