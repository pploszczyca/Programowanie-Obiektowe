package agh.cs.lab;

import org.junit.Assert;
import org.junit.Test;

public class CompareIMapElementsByXTest {

    @Test
    public void compareTest(){
        CompareIMapElementsByX comparator = new CompareIMapElementsByX();
        IWorldMap map = new GrassField(0);

        Assert.assertEquals(comparator.compare(new Animal(map, new Vector2d(3,3)), new Animal(map, new Vector2d(5,2))), -1);
        Assert.assertEquals(comparator.compare(new Animal(map, new Vector2d(3,3)), new Animal(map, new Vector2d(3,5))), -1);
        Assert.assertEquals(comparator.compare(new Animal(map, new Vector2d(3,3)), new Animal(map, new Vector2d(3,3))), 0);
        Assert.assertEquals(comparator.compare(new Grass(new Vector2d(3,3)), new Grass(new Vector2d(3,3))), 0);
        Assert.assertNotEquals(comparator.compare(new Animal(map, new Vector2d(3,3)), new Grass(new Vector2d(3,3))), 0);
        Assert.assertEquals(comparator.compare(new Animal(map, new Vector2d(5,5)), new Animal(map, new Vector2d(3,2))), 1);
        Assert.assertEquals(comparator.compare(new Animal(map, new Vector2d(5,5)), new Animal(map, new Vector2d(5,2))), 1);
        Assert.assertEquals(comparator.compare(new Grass(new Vector2d(5,3)), new Animal(map, new Vector2d(3,3))), 1);
    }
}
