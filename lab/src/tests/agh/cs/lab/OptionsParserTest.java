package agh.cs.lab;

import org.junit.Assert;
import org.junit.Test;

public class OptionsParserTest {
    @Test
    public void parseTest(){
        String [] inputDirectionArr = {"f", "forward", "b", "backward", "r", "right", "l", "left"};
        MoveDirection [] outputDirectionArr = {MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.LEFT};
        Assert.assertArrayEquals(new OptionsParser().parse(inputDirectionArr), outputDirectionArr);

        String [] arrWithWrongDir = {"f", "to nie jest kierunek", "r", "coś", "left"};

        Assert.assertThrows("to nie jest kierunek is not legal move specification", IllegalArgumentException.class, ()->{
            new OptionsParser().parse(arrWithWrongDir);
        });

    }

}
