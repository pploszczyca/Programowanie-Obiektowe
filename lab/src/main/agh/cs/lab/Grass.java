package agh.cs.lab;

public class Grass {
    private Vector2d position;  // to pole może być finalne

    public Grass(Vector2d position){
        this.position = position;
    }

    public Vector2d getPosition(){
        return position;
    }

    public String toString(){
        return "*";
    }
}
