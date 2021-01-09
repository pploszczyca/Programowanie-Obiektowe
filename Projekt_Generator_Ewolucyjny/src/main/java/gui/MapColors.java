package gui;

import javafx.scene.paint.Color;

public enum MapColors {
    FIELD(Color.rgb(205,255,0)),
    JUNGLE(Color.rgb(0,102,51)),
    GRASS(Color.rgb(0,255,0)),
    ANIMAL_MAX_ENERGY(Color.rgb(255,128,0)),
    ANIMAL_MIDDLE_ENERGY(Color.rgb(153,76,0)),
    ANIMAL_LOW_ENERGY(Color.rgb(51,25,0)),
    ANIMAL_WITH_POPULAR_GEN(Color.rgb(0,0,255));    // gene

    Color color;

    private MapColors(Color color){
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
