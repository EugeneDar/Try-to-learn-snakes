package sample;

import java.io.Serializable;

public class Neiron implements Serializable {
    int rightFood;
    int upFood;
    int leftFood;
    int downFood;
    int rightWall;
    int upWall;
    int leftWall;
    int downWall;

    public Neiron() {
        this.rightFood = 0;
        this.upFood = 0;
        this.leftFood = 0;
        this.downFood = 0;
        this.rightWall = 0;
        this.upWall = 0;
        this.leftWall = 0;
        this.downWall = 0;
    }
}
