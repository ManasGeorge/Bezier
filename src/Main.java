import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {
    final double WIDTH = 500;
    final double HEIGHT = 500;
    final double RADIUS = 5;
    final ArrayList<Double> xWeights = new ArrayList<>();
    final ArrayList<Double> yWeights = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        final Canvas c = new Canvas(WIDTH,HEIGHT);
        GraphicsContext gc = c.getGraphicsContext2D();
        final int[] selectedControlPoint = new int[1];

        c.setOnMousePressed(e -> {
            int index = controlPointIndex(e.getX(), e.getY());
            if(index == -1) {
                gc.setFill(Color.WHITE);
                gc.fillRect(0, 0, WIDTH, HEIGHT);
                addControlPoint(e.getX(), e.getY());
                drawCurve(gc, xWeights, yWeights);
                selectedControlPoint[0] = -1;
            } else {
                selectedControlPoint[0] = index;
            }
        });

        c.setOnMouseDragged(e -> {
            int i = selectedControlPoint[0];
            if (i != -1){
                xWeights.set(i,e.getX());
                yWeights.set(i,e.getY());
            }
        });

        root.getChildren().add(c);
        primaryStage.setScene(new Scene(root,WIDTH,HEIGHT,Color.WHITE));
        primaryStage.setTitle("Floating");
        primaryStage.setMinHeight(500);
        primaryStage.setMinWidth(500);
        primaryStage.show();
    }

    private void addControlPoint(double x, double y){
        xWeights.add(x);
        yWeights.add(y);
    }

    private int controlPointIndex(double x, double y){
        for (int i = 0; i < xWeights.size(); i++){
            if(distance(x,y,xWeights.get(i),yWeights.get(i)) < RADIUS)
                return i;
        }
        return -1;
    }

    private void drawCurve(GraphicsContext gc, ArrayList<Double> xWeights, ArrayList<Double> yWeights){
        gc.beginPath();
        gc.setStroke(Color.BLACK);
        gc.moveTo(xWeights.get(0), yWeights.get(0));
        for(double t = 0; t <= 1; t += 0.0001){
            gc.lineTo(Utilities.weightedBezier(xWeights.size()-1, t, xWeights),
                    Utilities.weightedBezier(yWeights.size()-1, t, yWeights));
        }

        for(int i = 0; i < xWeights.size(); i++){
            gc.setFill(Color.RED);
            gc.fillOval(xWeights.get(i)-RADIUS, yWeights.get(i)-RADIUS, 2*RADIUS, 2*RADIUS);
        }

        gc.stroke();
        gc.closePath();
    }

    private double distance(double x, double y, double a, double b){
        return Math.sqrt(Math.pow(x-a,2) + Math.pow(y-b,2));
    }
}
