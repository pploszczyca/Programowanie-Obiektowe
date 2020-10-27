package agh.cs.lab;

import org.junit.Assert;
import org.junit.Test;

public class AnimalTest {

    @Test
    public void moveTest(){
        Animal animal = new Animal();

        animal.move(MoveDirection.RIGHT);
        Assert.assertEquals(animal.toString(), "Wschód (2,2)");
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.RIGHT);
        Assert.assertEquals(animal.toString(), "Zachód (2,2)");

        animal.move(MoveDirection.LEFT);
        Assert.assertEquals(animal.toString(), "Południe (2,2)");
        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.LEFT);
        Assert.assertEquals(animal.toString(), "Północ (2,2)");

        animal.move(MoveDirection.FORWARD);
        Assert.assertEquals(animal.toString(), "Północ (2,3)");
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        Assert.assertEquals(animal.toString(), "Północ (2,4)");
        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);
        Assert.assertEquals(animal.toString(), "Zachód (4,4)");

        animal = new Animal();      // Północ (2,2)
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);
        Assert.assertEquals(animal.toString(), "Północ (2,0)");
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);
        Assert.assertEquals(animal.toString(), "Wschód (0,0)");

    }
}
