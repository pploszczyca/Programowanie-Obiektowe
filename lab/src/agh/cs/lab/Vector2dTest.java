package agh.cs.lab;

import org.junit.Assert;
import org.junit.Test;

public class Vector2dTest {
    @Test
    public void testEquels(){   //Equals
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
    public void testPrecedes(){ // ani jednego przypadku negatywnego (assertFalse)
        Assert.assertTrue(new Vector2d(10, 10).precedes(new Vector2d(20, 20)));
        Assert.assertTrue(new Vector2d(20, 20).precedes(new Vector2d(20, 30)));
    }

    @Test
    public void testFollows(){
        Assert.assertTrue(new Vector2d(20, 20).follows(new Vector2d(10, 10)));
        Assert.assertTrue(new Vector2d(20, 30).follows(new Vector2d(20, 20)));
    }

    @Test
    public void isItUpperRight(){
        Vector2d tmp = new Vector2d(1,5);
        Vector2d tmp2 = tmp.upperRight(new Vector2d(2,6));

        Assert.assertEquals(tmp2.x, 2);
        Assert.assertEquals(tmp2.y, 6);


        tmp2 = tmp.upperRight(new Vector2d(0,6));

        Assert.assertEquals(tmp2.x, 1);
        Assert.assertEquals(tmp2.y, 6);


        tmp2 = tmp.upperRight(new Vector2d(0,4));

        Assert.assertEquals(tmp2.x, 1);
        Assert.assertEquals(tmp2.y, 5);
    }

    @Test
    public void isItLowerLeft(){
        Vector2d tmp = new Vector2d(1,5);
        Vector2d tmp2 = tmp.lowerLeft(new Vector2d(2,6));

        Assert.assertEquals(tmp2.x, 1);
        Assert.assertEquals(tmp2.y, 5);


        tmp2 = tmp.lowerLeft(new Vector2d(0,6));

        Assert.assertEquals(tmp2.x, 0);
        Assert.assertEquals(tmp2.y, 5);


        tmp2 = tmp.lowerLeft(new Vector2d(0,4));

        Assert.assertEquals(tmp2.x, 0);
        Assert.assertEquals(tmp2.y, 4);
    }

    @Test
    public void isAdding(){
        Vector2d tmp = new Vector2d(1, 2);
        Vector2d tmp2 = tmp.add(new Vector2d(3,5));

        Assert.assertEquals(tmp2.x, 4);
        Assert.assertEquals(tmp2.y, 7);


        tmp2 = tmp.add(new Vector2d(-3,-5));

        Assert.assertEquals(tmp2.x, -2);
        Assert.assertEquals(tmp2.y, -3);

    }

    @Test
    public void isSubtracting(){
        Vector2d tmp = new Vector2d(1, 2);  // tmp to nie szczęśliwa nazwa
        Vector2d tmp2 = tmp.subtract(new Vector2d(3,5));

        Assert.assertEquals(tmp2.x, -2);
        Assert.assertEquals(tmp2.y, -3);    // skoro Vector2d ma equals to można to zbić w jedną linijkę


        tmp2 = tmp.subtract(new Vector2d(-3,-5));

        Assert.assertEquals(tmp2.x, 4);
        Assert.assertEquals(tmp2.y, 7);

    }

    @Test
    public void testOpposite(){
        Vector2d tmp = new Vector2d(1,2).opposite();
        Assert.assertEquals(tmp.x, -1);
        Assert.assertEquals(tmp.y, -2);

        tmp = new Vector2d(-1,-2).opposite();
        Assert.assertEquals(tmp.x, 1);
        Assert.assertEquals(tmp.y, 2);
    }
}
