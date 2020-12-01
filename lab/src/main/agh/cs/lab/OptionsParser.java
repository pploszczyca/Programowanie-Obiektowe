package agh.cs.lab;

import java.util.Arrays;

public class OptionsParser {
    public MoveDirection[] parse(String[] stringArr){
        MoveDirection[] movesArr = new MoveDirection[stringArr.length];
        int moveArrIndex = 0;

        for(String direction: stringArr){
            if (direction.equals("f") || direction.equals("forward")){
                movesArr[moveArrIndex] =  MoveDirection.FORWARD;
                moveArrIndex++;
            }

            else if (direction.equals("b") || direction.equals("backward")){
                movesArr[moveArrIndex] =  MoveDirection.BACKWARD;
                moveArrIndex++;
            }

            else if (direction.equals("r") || direction.equals("right")){
                movesArr[moveArrIndex] =  MoveDirection.RIGHT;
                moveArrIndex++;
            }

            else if (direction.equals("l") || direction.equals("left")){
                movesArr[moveArrIndex] =  MoveDirection.LEFT;
                moveArrIndex++;
            }

            else{
                throw new IllegalArgumentException(direction + " is not legal move specification");
            }

        }

        return Arrays.copyOf(movesArr, moveArrIndex);
    }
}
