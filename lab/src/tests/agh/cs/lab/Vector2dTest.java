package agh.cs.lab;

import org.junit.Assert;
import org.junit.Test;

public class Vector2dTest {
    @Test
    public void testEquals(){
        Vector2d tmp = new Vector2d(0,0);

        Assert.assertTrue(tmp.equals(tmp));
        Assert.assertTrue(new Vector2d(1,2).equals(new Vector2d(1,2)));
        Assert.assertFalse(new Vector2d(1,2).equals(new Vector2d(1,5)));
        Assert.assertFalse(new Vector2d(1,2).equals(5));
    }

    @Test
    public void testToString(){
        Assert.assertEquals(new Vector2d(10,20).toString(), "(10,20)");
        Assert.assertEquals(new Vector2d(-3,4).toString(), "(-3,4)");
    }

    @Test
    public void testPrecedes(){
        Assert.assertTrue(new Vector2d(10, 10).precedes(new Vector2d(20, 20)));
        Assert.assertTrue(new Vector2d(20, 20).precedes(new Vector2d(20, 30)));
        Assert.assertFalse(new Vector2d(20, 20).precedes(new Vector2d(30, 10)));
        Assert.assertFalse(new Vector2d(20, 20).precedes(new Vector2d(5, 10)));
        Assert.assertFalse(new Vector2d(20, 20).precedes(new Vector2d(5, 30)));
    }

    @Test
    public void testFollows(){
        Assert.assertTrue(new Vector2d(20, 20).follows(new Vector2d(10, 10)));
        Assert.assertTrue(new Vector2d(20, 30).follows(new Vector2d(20, 20)));
        Assert.assertFalse(new Vector2d(5, 10).follows(new Vector2d(20, 20)));
        Assert.assertFalse(new Vector2d(10, 20).follows(new Vector2d(5, 30)));
        Assert.assertFalse(new Vector2d(10, 20).follows(new Vector2d(30, 5)));
    }

    @Test
    public void isItUpperRight(){
        Assert.assertTrue(new Vector2d(1,5).upperRight(new Vector2d(2,6)).equals(new Vector2d(2,6)));
        Assert.assertTrue(new Vector2d(1,5).upperRight(new Vector2d(0,6)).equals(new Vector2d(1,6)));
        Assert.assertTrue(new Vector2d(1,5).upperRight(new Vector2d(0,4)).equals(new Vector2d(1,5)));
        Assert.assertFalse(new Vector2d(1,5).upperRight(new Vector2d(3,7)).equals(new Vector2d(1,5)));

    }

    @Test
    public void isItLowerLeft(){
        Assert.assertTrue(new Vector2d(1,5).lowerLeft(new Vector2d(2,6)).equals(new Vector2d(1,5)));
        Assert.assertTrue(new Vector2d(1,5).lowerLeft(new Vector2d(0,6)).equals(new Vector2d(0,5)));
        Assert.assertTrue(new Vector2d(1,5).lowerLeft(new Vector2d(0,4)).equals(new Vector2d(0,4)));
        Assert.assertFalse(new Vector2d(1,5).lowerLeft(new Vector2d(3,2)).equals(new Vector2d(3,5)));
    }

    @Test
    public void isAdding(){
        Assert.assertTrue(new Vector2d(1, 2).add(new Vector2d(3,5)).equals(new Vector2d(4, 7)));
        Assert.assertTrue(new Vector2d(1, 2).add(new Vector2d(-3,-5)).equals(new Vector2d(-2, -3)));
    }

    @Test
    public void isSubtracting(){
        Assert.assertTrue(new Vector2d(1, 2).subtract(new Vector2d(3,5)).equals(new Vector2d(-2, -3)));
        Assert.assertTrue(new Vector2d(1, 2).subtract(new Vector2d(-3,-5)).equals(new Vector2d(4, 7)));
    }

    @Test
    public void testOpposite(){
        Assert.assertTrue(new Vector2d(1,2).opposite().equals(new Vector2d(-1,-2)));
        Assert.assertTrue(new Vector2d(-1,-2).opposite().equals(new Vector2d(1,2)));
    }
}
