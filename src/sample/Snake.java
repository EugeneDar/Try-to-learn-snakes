package sample;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Snake implements Externalizable {

    Simulation simulation;

    List<Cell> snakeCells = new ArrayList<>();

    Neiron[][] neirons;

    boolean isAlive = true;

    public Snake(Simulation simulation) {
        this.simulation = simulation;

        // default initialization
        neirons = new Neiron[15][15];
        for (int i = 0;i < neirons.length;i++) {
            for (int j = 0;j < neirons[i].length;j++) {
                neirons[i][j] = new Neiron();
            }
        }
    }


    public void save () {
        try {
            writeExternal(new ObjectOutputStream(new FileOutputStream("fileForSerialization.txt")));
            System.out.println("Saving Success");
        } catch (Exception ignore) {
            System.out.println("Error in Saving");
        }
    }


    public void load () {
        try {
            readExternal(new ObjectInputStream(new FileInputStream("fileForSerialization.txt")));
            System.out.println("Loading Success");
        } catch (Exception ignore) {
            System.out.println("Error in Loading");
        }
    }



    public Way chooseWay () {

        int countRight = 0;
        int countLeft = 0;
        int countUp = 0;
        int countDown = 0;

        Cell snakeHead = snakeCells.get(snakeCells.size() - 1);

        int distanceFromCenter = neirons.length / 2;

        for (int y = 0;y < neirons.length;y++) {
            for (int x = 0;x < neirons[y].length;x++) {
                int fieldType = simulation.getCell(
                        snakeHead.x - distanceFromCenter + x,
                        snakeHead.y - distanceFromCenter + y);
                if (fieldType == 1) {
                    countDown += neirons[y][x].downWall;
                    countLeft += neirons[y][x].leftWall;
                    countUp += neirons[y][x].upWall;
                    countRight += neirons[y][x].rightWall;
                }
                if (fieldType == 2) {
                    countDown += neirons[y][x].downFood;
                    countLeft += neirons[y][x].leftFood;
                    countUp += neirons[y][x].upFood;
                    countRight += neirons[y][x].rightFood;
                }
            }
        }

        int maxValue = Math.max(Math.max(countRight, countUp), Math.max(countDown, countLeft));

        if (countDown == maxValue) {
            return Way.DOWN;
        }
        if (countLeft == maxValue) {
            return Way.LEFT;
        }
        if (countUp == maxValue) {
            return Way.UP;
        }

        return Way.RIGHT;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(neirons);
        out.close();
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        neirons = (Neiron[][]) in.readObject();
        in.close();
    }
}
