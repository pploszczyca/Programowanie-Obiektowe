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
        Animal animal;
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                animal = new Animal(field, new Vector2d(i,j));
                field.place(animal);
                Assert.assertEquals(field.objectAt(new Vector2d(i,j)), animal);
            }
        }

        GrassField secondField = new GrassField(10);
        animal = new Animal(secondField, new Vector2d(1,1));
        secondField.place(animal);
        Assert.assertEquals(secondField.objectAt(new Vector2d(1,1)), animal);
        secondField.run(new MoveDirection[] {MoveDirection.FORWARD});
        Assert.assertEquals(secondField.objectAt(new Vector2d(1,2)), animal);
    }

    @Test
    public void canMoveToTest(){
        GrassField field = new GrassField(1);
        OptionsParser parser = new OptionsParser();

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
        GrassField field = new GrassField(0);

        field.place(new Animal(field, new Vector2d(1,1)));
        Assert.assertTrue(field.isOccupied(new Vector2d(1,1)));
        field.place(new Animal(field, new Vector2d(0,0)));
        Assert.assertTrue(field.isOccupied(new Vector2d(0,0)));
        Assert.assertFalse(field.isOccupied(new Vector2d(-2,-2)));

        field.place(new Animal(field, new Vector2d(-2,-2)));
        Assert.assertTrue(field.isOccupied(new Vector2d(-2,-2)));

    }

    @Test
    public void findCornersTest(){
        GrassField field = new GrassField(0);
        field.place(new Animal(field, new Vector2d(0,0)));
        field.place(new Animal(field, new Vector2d(1,1)));
        field.place(new Animal(field, new Vector2d(2,2)));

        Assert.assertArrayEquals(field.findCorners(), new Vector2d[] {new Vector2d(0,0), new Vector2d(2,2)});

        field.run(new MoveDirection[]{MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.FORWARD});
        Assert.assertArrayEquals(field.findCorners(), new Vector2d[] {new Vector2d(0,2), new Vector2d(2,2)});

        field.run(new MoveDirection[]{MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.FORWARD});
        Assert.assertArrayEquals(field.findCorners(), new Vector2d[] {new Vector2d(0,1), new Vector2d(3,3)});
    }
}
