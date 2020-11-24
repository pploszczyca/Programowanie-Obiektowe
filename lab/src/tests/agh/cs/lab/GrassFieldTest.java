package agh.cs.lab;

import org.junit.Assert;
import org.junit.Test;

public class GrassFieldTest {
    @Test
    public void placeRandomGrassTest(){
        int n = 30;
        GrassField field = new GrassField(n);

        for(int i = 0; i < n; i++){
            Assert.assertTrue(field.getGrassItemPosition(i).precedes(new Vector2d((int) Math.round(Math.sqrt(10*n)),(int) Math.round(Math.sqrt(10*n)))));
            Assert.assertTrue(field.getGrassItemPosition(i).follows(new Vector2d(0,0)));
        }
    }

    @Test
    public void objectAtTest(){
        GrassField field = new GrassField(1);
        for(int i = 0 ; i < 8; i++)
            field.placeRandomGrass();

        Animal animal = new Animal(field, new Vector2d(1,1));
        field.place(animal);
        Assert.assertEquals(field.objectAt(new Vector2d(1,1)), animal);
        field.run(new MoveDirection[] {MoveDirection.FORWARD});
        Assert.assertEquals(field.objectAt(new Vector2d(1,2)), animal);
        Assert.assertTrue(field.objectAt(new Vector2d(1,1)) instanceof Grass);
        Assert.assertFalse(field.objectAt(new Vector2d(1,2)) instanceof Grass);
    }

    @Test
    public void canMoveToTest(){
        GrassField field = new GrassField(1);
        OptionsParser parser = new OptionsParser();
        for(int i = 0 ; i < 8; i++)
            field.placeRandomGrass();

        Animal animal = new Animal(field, new Vector2d(1,1));
        field.place(animal);
        field.run(parser.parse(new String[]{"b", "b"}));
        Assert.assertTrue(field.objectAt(new Vector2d(1,-1)) instanceof Animal);
        Assert.assertFalse(field.objectAt(new Vector2d(1,0)) instanceof Animal);
        Assert.assertFalse(field.objectAt(new Vector2d(1,3)) instanceof Animal);


        field.place(new Animal(field, new Vector2d(1,0)));
        field.run(parser.parse(new String[]{"f", "b", "b", "f"}));
        Assert.assertTrue(field.objectAt(new Vector2d(1,-2)) instanceof Animal);
        Assert.assertTrue(field.objectAt(new Vector2d(1,1)) instanceof Animal);
        Assert.assertFalse(field.objectAt(new Vector2d(1,-1)) instanceof Animal);
        Assert.assertFalse(field.objectAt(new Vector2d(1,0)) instanceof Animal);
    }

    @Test
    public void isOccupiedTest(){
        GrassField field = new GrassField(1);
        OptionsParser parser = new OptionsParser();
        for(int i = 0 ; i < 8; i++)
            field.placeRandomGrass();

        Assert.assertTrue(field.isOccupied(new Vector2d(1,1)));
        Assert.assertTrue(field.isOccupied(new Vector2d(0,0)));
        Assert.assertFalse(field.isOccupied(new Vector2d(-2,-2)));

        field.place(new Animal(field, new Vector2d(-2,-2)));
        Assert.assertTrue(field.isOccupied(new Vector2d(-2,-2)));

    }
}
