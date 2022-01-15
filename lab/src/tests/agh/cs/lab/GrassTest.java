package agh.cs.lab;

import org.junit.Assert;
import org.junit.Test;

public class GrassTest {
    @Test
    public void getPositionTest(){
        Assert.assertTrue(new Grass(new Vector2d(2,2)).getPosition().equals(new Vector2d(2,2)));
        Assert.assertTrue(new Grass(new Vector2d(1,1)).getPosition().equals(new Vector2d(1,1)));
        Assert.assertFalse(new Grass(new Vector2d(1,1)).getPosition().equals(new Vector2d(0,0)));
    }

    @Test
    public void toStringTest(){
        Assert.assertTrue(new Grass(new Vector2d(2,2)).toString().equals("*"));
        Assert.assertTrue(new Grass(new Vector2d(1,1)).toString().equals("*"));
        Assert.assertFalse(new Grass(new Vector2d(1,1)).toString().equals(" "));
    }
}
