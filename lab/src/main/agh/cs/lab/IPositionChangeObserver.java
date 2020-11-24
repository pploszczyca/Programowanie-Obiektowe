package agh.cs.lab;

public interface IPositionChangeObserver {

    /**
        Removing <oldPosition, animal> and adding <newPosition, animal> in animals map
     */
    void positionChanged(Vector2d oldPosition, Vector2d newPosition);
}
