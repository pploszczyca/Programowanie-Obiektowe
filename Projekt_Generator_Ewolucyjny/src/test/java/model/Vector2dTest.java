package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class Vector2dTest {
    @Test
    public void testEquals(){
        Vector2d tmp = new Vector2d(0,0);

        Assertions.assertTrue(tmp.equals(tmp));
        Assertions.assertTrue(new Vector2d(1,2).equals(new Vector2d(1,2)));
        Assertions.assertFalse(new Vector2d(1,2).equals(new Vector2d(1,5)));
        Assertions.assertFalse(new Vector2d(1,2).equals(5));
    }

    @Test
    public void testToString(){
        Assertions.assertEquals(new Vector2d(10,20).toString(), "(10,20)");
        Assertions.assertEquals(new Vector2d(-3,4).toString(), "(-3,4)");
    }

    @Test
    public void testPrecedes(){
        Assertions.assertTrue(new Vector2d(10, 10).precedes(new Vector2d(20, 20)));
        Assertions.assertTrue(new Vector2d(20, 20).precedes(new Vector2d(20, 30)));
        Assertions.assertFalse(new Vector2d(20, 20).precedes(new Vector2d(30, 10)));
        Assertions.assertFalse(new Vector2d(20, 20).precedes(new Vector2d(5, 10)));
        Assertions.assertFalse(new Vector2d(20, 20).precedes(new Vector2d(5, 30)));
    }

    @Test
    public void testFollows(){
        Assertions.assertTrue(new Vector2d(20, 20).follows(new Vector2d(10, 10)));
        Assertions.assertTrue(new Vector2d(20, 30).follows(new Vector2d(20, 20)));
        Assertions.assertFalse(new Vector2d(5, 10).follows(new Vector2d(20, 20)));
        Assertions.assertFalse(new Vector2d(10, 20).follows(new Vector2d(5, 30)));
        Assertions.assertFalse(new Vector2d(10, 20).follows(new Vector2d(30, 5)));
    }

    @Test
    public void isItUpperRight(){
        Assertions.assertTrue(new Vector2d(1,5).upperRight(new Vector2d(2,6)).equals(new Vector2d(2,6)));
        Assertions.assertTrue(new Vector2d(1,5).upperRight(new Vector2d(0,6)).equals(new Vector2d(1,6)));
        Assertions.assertTrue(new Vector2d(1,5).upperRight(new Vector2d(0,4)).equals(new Vector2d(1,5)));
        Assertions.assertFalse(new Vector2d(1,5).upperRight(new Vector2d(3,7)).equals(new Vector2d(1,5)));

    }

    @Test
    public void isItLowerLeft(){
        Assertions.assertTrue(new Vector2d(1,5).lowerLeft(new Vector2d(2,6)).equals(new Vector2d(1,5)));
        Assertions.assertTrue(new Vector2d(1,5).lowerLeft(new Vector2d(0,6)).equals(new Vector2d(0,5)));
        Assertions.assertTrue(new Vector2d(1,5).lowerLeft(new Vector2d(0,4)).equals(new Vector2d(0,4)));
        Assertions.assertFalse(new Vector2d(1,5).lowerLeft(new Vector2d(3,2)).equals(new Vector2d(3,5)));
    }

    @Test
    public void isAdding(){
        Assertions.assertTrue(new Vector2d(1, 2).add(new Vector2d(3,5)).equals(new Vector2d(4, 7)));
        Assertions.assertTrue(new Vector2d(1, 2).add(new Vector2d(-3,-5)).equals(new Vector2d(-2, -3)));
    }


    @Test
    public void testWrapBy(){
        Assertions.assertEquals(new Vector2d(-1,-1).wrapBy(new Vector2d(0,0), new Vector2d(4,4)), new Vector2d(4,4));
        Assertions.assertEquals(new Vector2d(5,5).wrapBy(new Vector2d(0,0), new Vector2d(4,4)), new Vector2d(0,0));
        Assertions.assertEquals(new Vector2d(-1,5).wrapBy(new Vector2d(0,0), new Vector2d(4,4)), new Vector2d(4,0));
        Assertions.assertEquals(new Vector2d(5,-1).wrapBy(new Vector2d(0,0), new Vector2d(4,4)), new Vector2d(0,4));

    }
}
