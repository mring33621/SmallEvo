package com.mattring.smallevo.examples.travelingsalesman;

import com.mattring.smallevo.examples.IntHolder;
import static com.mattring.smallevo.examples.travelingsalesman.DestinationFileCreatorMain.GRID_SIZE;
import java.awt.Point;
import java.util.List;
import java.util.stream.IntStream;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * A simple javafx app to playback highlights from the route evolution process.
 * @author mring
 */
public class VisualPlayback extends Application {

    static List<List<Point>> routes;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("The Traveling Salesman");
        Group root = new Group();
        Canvas canvas = new Canvas(GRID_SIZE, GRID_SIZE);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        plotRoutes(gc);
    }

    private void plotRoutes(final GraphicsContext gc) {
        gc.setStroke(Color.RED);
        gc.setLineWidth(3);
        gc.setFill(Color.BLUE);
        Task task = new Task<Void>() {
            @Override
            public Void call() {
                final IntHolder ih = new IntHolder();
                for (int i = 0; i < routes.size(); i++) {
                    List<Point> route = routes.get(i);
                    Platform.runLater(() -> {
                        gc.clearRect(0d, 0d, GRID_SIZE, GRID_SIZE);
                        IntStream.range(1, route.size()).forEach(j -> {
                            Point a = route.get(j - 1);
                            Point b = route.get(j);
                            gc.fillOval(a.x, a.y, 10, 10);
                            if (ih.getVal() > 0) {
                                gc.strokeLine(a.x, a.y, b.x, b.y);
                            }
                        });
                    });
                    ih.incr(1);
                    try {
                        Thread.sleep(500L);
                    } catch (InterruptedException ex) {
                        // don't care
                    }
                }
                return null;
            }
        };
        new Thread(task).start();
    }
}
