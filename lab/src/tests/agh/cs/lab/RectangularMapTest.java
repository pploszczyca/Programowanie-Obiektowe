package agh.cs.lab;

import org.junit.Assert;
import org.junit.Test;

public class RectangularMapTest {

    @Test
    public void runTest(){
        IWorldMap map = new RectangularMap(11, 6);
        OptionsParser parser = new OptionsParser();
        map.place(new Animal(map));     // First Animal
        map.place(new Animal(map,new Vector2d(3,4)));          //Second Animal

        map.run(parser.parse(new String[]{"f", "b", "r" ,"l"}));
        // First Animal
        Assert.assertFalse(map.isOccupied(new Vector2d(3,2)));
        Assert.assertTrue(map.isOccupied(new Vector2d(2,3)));
        Assert.assertEquals(map.objectAt(new Vector2d(2,3)).toString(), "E");
        //Second Animal
        Assert.assertFalse(map.isOccupied(new Vector2d(2,4)));
        Assert.assertTrue(map.isOccupied(new Vector2d(3,3)));
        Assert.assertEquals(map.objectAt(new Vector2d(3,3)).toString(), "W");

        map.run(parser.parse(new String[]{"f", "f"}));
        // First Animal
        Assert.assertTrue(map.isOccupied(new Vector2d(2,3)));
        Assert.assertEquals(map.objectAt(new Vector2d(2,3)).toString(), "E");
        //Second Animal
        Assert.assertTrue(map.isOccupied(new Vector2d(3,3)));
        Assert.assertEquals(map.objectAt(new Vector2d(3,3)).toString(), "W");

        map.run(parser.parse(new String[]{"r", "r","f", "f", "f","f", "f","f", "f", "f"}));
        // First Animal
        Assert.assertTrue(map.isOccupied(new Vector2d(2,0)));
        Assert.assertEquals(map.objectAt(new Vector2d(2,0)).toString(), "S");
        //Second Animal
        Assert.assertTrue(map.isOccupied(new Vector2d(3,5)));
        Assert.assertEquals(map.objectAt(new Vector2d(3,5)).toString(), "N");
    }

    @Test
    public void canMoveToTest(){
        RectangularMap map = new RectangularMap(6,6);
        Assert.assertFalse(map.canMoveTo(new Vector2d(-1,-1)));
        Assert.assertFalse(map.canMoveTo(new Vector2d(5,6)));
        Assert.assertTrue(map.canMoveTo(new Vector2d(0,0)));
        Assert.assertTrue(map.canMoveTo(new Vector2d(5,5)));

        Assert.assertTrue(map.canMoveTo(new Vector2d(2,2)));
        map.place(new Animal(map));
        Assert.assertFalse(map.canMoveTo(new Vector2d(2,2)));

        Assert.assertTrue(map.canMoveTo(new Vector2d(3,3)));
        map.place(new Animal(map, new Vector2d(3,3)));
        Assert.assertFalse(map.canMoveTo(new Vector2d(3,3)));
    }

    @Test
    public void isOccupiedTest(){
        RectangularMap map = new RectangularMap(5,5);
        map.place(new Animal(map, new Vector2d(3,3)));
        Assert.assertTrue(map.isOccupied(new Vector2d(3,3)));
        Assert.assertFalse(map.isOccupied(new Vector2d(2,2)));
        Assert.assertFalse(map.isOccupied(new Vector2d(0,0)));
        map.place(new Animal(map));
        Assert.assertTrue(map.isOccupied(new Vector2d(2,2)));
    }

    @Test
    public void placeTest(){
        RectangularMap map = new RectangularMap(6,6);
        Assert.assertTrue(map.place(new Animal(map, new Vector2d(3,3))));
        Assert.assertThrows("Position (3,3) is incorrect or currently occupied", IllegalArgumentException.class, ()->{
            map.place(new Animal(map, new Vector2d(3,3)));
        });
        Assert.assertTrue(map.place(new Animal(map)));
        Assert.assertThrows("Position (2,2) is incorrect or currently occupied", IllegalArgumentException.class, ()->{
            map.place(new Animal(map));
        });
        Assert.assertThrows("Position (6,5) is incorrect or currently occupied", IllegalArgumentException.class, ()->{
            map.place(new Animal(map, new Vector2d(6,5)));
        });
        Assert.assertThrows("Position (-1,0) is incorrect or currently occupied", IllegalArgumentException.class, ()->{
            map.place(new Animal(map, new Vector2d(-1,0)));
        });
    }

    @Test
    public void objectAtTest(){
        RectangularMap map = new RectangularMap(5,5);
        map.place(new Animal(map, new Vector2d(1,1)));
        Assert.assertEquals(map.objectAt(new Vector2d(1,1)).toString(), "N");
        map.run(new MoveDirection[]{MoveDirection.FORWARD});
        Assert.assertEquals(map.objectAt(new Vector2d(1,1)) ,null);

        Assert.assertEquals(map.objectAt(new Vector2d(3,3)) ,null);
        Assert.assertEquals(map.objectAt(new Vector2d(0,0)) ,null);
    }

}
