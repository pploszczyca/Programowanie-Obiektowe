package agh.cs.lab;

public interface IPositionChangeObserver {

    /**
        Removing <oldPosition, animal> and adding <newPosition, animal> in animals map  // ten komentarz nie pasuje do interfejsu, można observera używać w innym celu
     */
    void positionChanged(Vector2d oldPosition, Vector2d newPosition);
}
