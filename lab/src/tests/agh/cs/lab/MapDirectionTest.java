package agh.cs.lab;

import org.junit.Assert;
import org.junit.Test;

public class MapDirectionTest {

    @Test
    public void testNext(){
        Assert.assertEquals(MapDirection.WEST.next(), MapDirection.NORTH);
        Assert.assertEquals(MapDirection.NORTH.next(), MapDirection.EAST);
        Assert.assertEquals(MapDirection.EAST.next(), MapDirection.SOUTH);
        Assert.assertEquals(MapDirection.SOUTH.next(), MapDirection.WEST);
    }

    @Test
    public void testPrevious(){
        Assert.assertEquals(MapDirection.NORTH.previous(), MapDirection.WEST);
        Assert.assertEquals(MapDirection.EAST.previous(), MapDirection.NORTH);
        Assert.assertEquals(MapDirection.SOUTH.previous(), MapDirection.EAST);
        Assert.assertEquals(MapDirection.WEST.previous(), MapDirection.SOUTH);
    }



}
