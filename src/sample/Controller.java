package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;

public class Controller {
    @FXML
    Canvas canvas;
//    @FXML
//    Button buttonUp;
//    @FXML
//    Button buttonRight;
//    @FXML
//    Button buttonDown;
//    @FXML
//    Button buttonLeft;

    Affine affine;

    Simulation simulation;

    public Controller() {
        canvas = new Canvas(800, 800);
        affine = new Affine();
        affine.appendScale(20, 20);

        simulation = new Simulation(40, 40);

        draw();
    }

    public void draw () {

        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setTransform(affine);

        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillRect(0, 0, 800, 800);

        // fill walls
        graphicsContext.setFill(Color.BLACK);
        for (int y = 0;y < simulation.height;y++) {
            for (int x = 0;x < simulation.width;x++) {
                int type = simulation.getCell(x, y);
                if (type == 1) {
                    graphicsContext.fillRect(x, y, 1, 1);
                }
            }
        }

        // draw snakes
        graphicsContext.setFill(Color.GREEN);
        for (Snake snake : simulation.snakes) {
            for (Cell cell : snake.snakeCells) {
                graphicsContext.fillRect(cell.x, cell.y, 1, 1);
            }
        }

        // draw food
        graphicsContext.setFill(Color.ORANGERED);
        for (Cell cell : simulation.food) {
            graphicsContext.fillRect(cell.x, cell.y, 1, 1);
        }

        // paint lines (#)
        graphicsContext.setStroke(Color.GRAY);
        graphicsContext.setLineWidth(0.05);
        for (int x = 0; x <= this.simulation.width; x++) {
            graphicsContext.strokeLine(x, 0, x, simulation.height);
        }

        for (int y = 0; y <= this.simulation.height; y++) {
            graphicsContext.strokeLine(0, y, simulation.width, y);
        }
    }



    public void start () {
        draw();
    }



    public void step () {

    }

    public void save () {
        simulation.snakes.get(0).save();
    }

    public void load () {
        simulation.snakes.get(0).load();
    }
}
