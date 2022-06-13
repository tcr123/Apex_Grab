package com.example.loginassignment;

import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.IOException;
import java.util.HashMap;

public class HelloApplication {
    private static double SCENE_WIDTH = 1200;
    private static double SCENE_HEIGHT = 800;
    private static final double offSetX = 5;
    private static final double offSetY = 15;

    Canvas c = new Canvas();
    private static ArrayList<Location> location = new ArrayList<>();
    static String driverLocation;
    static String userLocation;
    static String finalLocation;

    static Canvas canvas;
    static GraphicsContext gc;
    static Path path;
    static Pane root;

    private ImageView imageView;

    HashMap<String, ImageView> car = new HashMap();

    private static String driver;

    public static String getDriver() {
        return driver;
    }

    protected static void initializeGroup(Pane rt) {
        root = rt;
    }

    // return imageview from driver name by hashmap
    protected void getImageFromDriverName(String driverName) {
        if (car.isEmpty()) return;
        this.driver = driverName;
        imageView = car.get(driverName);
    }

    protected void insertImage(ArrayList<Driver> driverList) throws FileNotFoundException {
        for (Driver e : driverList) {
            Image image = new Image(new FileInputStream("src/main/resources/com/example/loginassignment/car.png"));
            ImageView imageView = new ImageView(image);

            double oldLocationX = LocationKey.Coordinate(e.getLocation()).getX();
            double oldLocationY = LocationKey.Coordinate(e.getLocation()).getY();
            imageView.setX(oldLocationX - offSetX);
            imageView.setY(oldLocationY - offSetY);

            imageView.setFitHeight(50);
            imageView.setFitWidth(50);

            imageView.setPreserveRatio(true);

            car.put(e.getName(), imageView);

            root.getChildren().add(imageView);
        }
    }

    protected void clearImage() {
        if (car.isEmpty()) return;

        car.forEach( (key, value) -> {
            root.getChildren().remove(value);
        });
    }

    protected void setDriverLocation(String driverLocation1) {
        driverLocation = driverLocation1;
    }

    protected void setUserLocation(String userLocation1) {
        userLocation = userLocation1;
    }

    protected void setFinalLocation(String finalLocation1) {
        finalLocation = finalLocation1;
    }

    protected static double findPath(String driverLocation, String userLocation) {
        // Create a map for finding the shortest path from initialLocation to finalLocation
        DijkstraMap mapDriverToUser = new DijkstraMap(LocationKey.LocationNum(driverLocation));
        mapDriverToUser.dijkstra();

        // Return the time of travelling from initialLocation to finalLocation
        double time = mapDriverToUser.getDistance(LocationKey.LocationNum(userLocation));

        // Find the path that pass through when travelling from initialLocation to finalLocation
        mapDriverToUser.getPath(location, LocationKey.LocationNum(userLocation));
        return time;
    }

    protected void startMoveDriverToUser(double time) {
        Path path = createPath();
        canvas = new Canvas(SCENE_WIDTH-400,SCENE_HEIGHT);
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.TRANSPARENT);

        double x = location.get(location.size() - 1).getX();
        double y = location.get(location.size() - 1).getY();
        Circle destination_point = new Circle(x, y, 10);
        destination_point.setFill(Color.color(0.2, 0.2, 0.8, 0.7));

        root.getChildren().addAll(path, canvas);
        root.getChildren().add(destination_point);

        clearImage();
        root.getChildren().add(imageView);

        if (location.size() != 1) {
            rotateCar(location.get(0), location.get(1));

            Animation animation2 = createPathAnimation(path, Duration.seconds(time), Color.YELLOW);
            animation2.setDelay(Duration.millis(100));
            animation2.play();
            animation2.setOnFinished(e -> {
                try {
                    clear(path, destination_point, true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
        }
        else {
            try {
                clear(path, destination_point, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private void rotateCar(Location initial, Location second) {
        if (initial.getX() == second.getX() && initial.getY() < second.getY()) {
            imageView.setRotate(0);
        }
        else if (initial.getX() < second.getX() && initial.getY() == second.getY()) {
            imageView.setRotate(90);
        }
        else if (initial.getX() == second.getX() && initial.getY() > second.getY()) {
            imageView.setRotate(180);
        }
        else if (initial.getX() > second.getX() && initial.getY() == second.getY()) {
            imageView.setRotate(270);
        }
        // x2 > x1 and y2 > y1
        else if (initial.getX() < second.getX() && initial.getY() < second.getY()) {
            System.out.println(1);
            imageView.setRotate(135);
        }
        // x2 > x1 and y2 < y1
        else if (initial.getX() < second.getX() && initial.getY() > second.getY()) {
            System.out.println(2);
            imageView.setRotate(45);
        }
        // x2 < x1 and y2 > y1
        else if (initial.getX() > second.getX() && initial.getY() > second.getY()) {
            System.out.println(3);
            imageView.setRotate(315);
        }
        // x2 < x1 and y2 > y1
        else if (initial.getX() > second.getX() && initial.getY() < second.getY()) {
            System.out.println(4);
            imageView.setRotate(225);
        }
    }

    private void startMoveUserToLocation(double time) throws Exception {
        Uconn.DriverReachedU(UserLogin.name);
        Path path = createPath();
        canvas = new Canvas(SCENE_WIDTH-400,SCENE_HEIGHT);
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.TRANSPARENT);

        double x = location.get(location.size() - 1).getX();
        double y = location.get(location.size() - 1).getY();
        Circle destination_point = new Circle(x, y, 10);
        destination_point.setFill(Color.color(0.6, 0.8, 0.1, 0.7));

        root.getChildren().addAll(path, canvas);
        root.getChildren().add(destination_point);
        root.getChildren().add(imageView);

        if (location.size() != 1)
            rotateCar(location.get(0), location.get(1));

        Animation animation2 = createPathAnimation(path, Duration.seconds(time), Color.YELLOW);
        animation2.setDelay(Duration.millis(200));
        animation2.play();
        animation2.setOnFinished(e -> {
            try {
                clear(path, destination_point, false);
                Uconn.Reached(UserLogin.name, driver);
                Thread.sleep(3000);
                Parent root = FXMLLoader.load(getClass().getResource("rating.fxml"));
                Stage stage = (Stage) path.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    // utd means user to driver
    private void clear(Path p, Circle destination_point, boolean utd) throws Exception {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        p.getElements().clear();
        // remove the destination_point
        root.getChildren().remove(destination_point);
        location.clear();
        if (utd) {
            root.getChildren().remove(imageView);
            double time = findPath(userLocation, finalLocation) * 3;
            startMoveUserToLocation(time);
        }
    }

    private static Path createPath() {
        Path path = new Path();

        path.setStroke(Color.GRAY);
        path.setStrokeWidth(10);

        addPath(path, location);
        return path;
    }

    private static void addPath(Path path, ArrayList<Location> location) {
        for (int i = 0; i < location.size(); i++) {
            double x = location.get(i).getX();
            double y = location.get(i).getY();

            if (i == 0) {
                path.getElements().add(new MoveTo(x, y));
            } else {
                path.getElements().addAll(new LineTo(x, y));
            }
        }
    }

    private Animation createPathAnimation(Path path, Duration duration, Color go) {

        // move a node along a path. we want its position
        Circle pen = new Circle(0, 0, 4);

        // create path transition
        PathTransition pathTransition = new PathTransition(duration, path, pen);
        pathTransition.currentTimeProperty().addListener( new ChangeListener<Duration>() {

            Location oldLocation = null;
            double oldDegree = 0;
            int i = 0;

            /**
             * Draw a line from the old location to the new location
             */
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                // skip starting at 0/0
                if(oldValue == Duration.ZERO)
                    return;

                // get current location
                double x = pen.getTranslateX();
                double y = pen.getTranslateY();

                imageView.setX(x - 10);
                imageView.setY(y - 10);

                // initialize the location
                if( oldLocation == null) {
                    oldLocation = location.get(0);
                    return;
                }

                double degree = Math.abs(Math.tan((oldLocation.getY() - y) / (oldLocation.getX() - x)));

                if (Math.abs(oldDegree - degree) > 0.00000001) {
                    System.out.println(degree);
                    if (oldLocation.getX() == x && oldLocation.getY() < y) {
                        imageView.setRotate(0);
                    }
                    else if (oldLocation.getX() < x && oldLocation.getY() == y) {
                        imageView.setRotate(90);
                    }
                    else if (oldLocation.getX() == x && oldLocation.getY() > y) {
                        imageView.setRotate(180);
                    }
                    else if (oldLocation.getX() > x && oldLocation.getY() == y) {
                        imageView.setRotate(270);
                    }
                    else if (oldLocation.getX() < x && oldLocation.getY() < y) {
                        System.out.println("pos" + 1);
                        System.out.println(90 + Math.atan(degree) * 180 / 3.142);
                        imageView.setRotate(90 + Math.atan(degree) * 180 / 3.142);
                        /* if (degree < 0.20) {
                            imageView.setRotate(101.25);
                        }
                        else if (degree > 0.20 && degree < 0.41) {
                            imageView.setRotate(112.5);
                        }
                        else if (degree > 0.41 && degree < 2.414) {
                            imageView.setRotate(135);
                        }
                        else if (degree > 2.414 && degree < 5.027) {
                            imageView.setRotate(157.5);
                        }
                        else {
                            imageView.setRotate(168.75);
                        } */
                    }
                    else if (oldLocation.getX() < x && oldLocation.getY() > y) {
                        System.out.println("pos" + 2);
                        System.out.println(Math.atan(degree) * 180 / 3.142);
                        imageView.setRotate(Math.atan(degree) * 180 / 3.142);
//                        if (degree < 0.20) {
//                            imageView.setRotate(11.25);
//
//                        }
//                        else if (degree > 0.20 && degree < 0.41) {
//                            imageView.setRotate(22.5);
//
//                        }
//                        else if (degree > 0.41 && degree < 2.414) {
//                            imageView.setRotate(45);
//                        }
//                        else if (degree > 2.414 && degree < 5.027) {
//                            imageView.setRotate(67.5);
//                        }
//                        else {
//                            imageView.setRotate(78.75);
//                        }
                    }
                    else if (oldLocation.getX() > x && oldLocation.getY() > y) {
                        System.out.println("pos" + 3);
                        System.out.println(270 + Math.atan(degree) * 180 / 3.142);
                        imageView.setRotate(270 + Math.atan(degree) * 180 / 3.142);
                        /* if (degree < 0.20) {
                            imageView.setRotate(281.25);
                        }
                        else if (degree > 0.20 && degree < 0.41) {
                            imageView.setRotate(292.5);
                        }
                        else if (degree > 0.41 && degree < 2.414) {
                            imageView.setRotate(315);
                        }
                        else if (degree > 2.414 && degree < 5.027) {
                            imageView.setRotate(337.5);
                        }
                        else {
                            imageView.setRotate(348.75);
                        } */
                    }
                    else if (oldLocation.getX() > x && oldLocation.getY() < y) {
                        System.out.println("pos" + 4);
                        System.out.println(180 + Math.atan(degree) * 180 / 3.142);
                        imageView.setRotate(180 + Math.atan(degree) * 180 / 3.142);
                        /* if (degree < 0.20) {
                            imageView.setRotate(191.25);
                        }
                        else if (degree > 0.20 && degree < 0.41) {
                            imageView.setRotate(202.5);
                        }
                        else if (degree > 0.41 && degree < 2.414) {
                            imageView.setRotate(225);
                        }
                        else if (degree > 2.414 && degree < 5.027) {
                            imageView.setRotate(247.5);
                        }
                        else {
                            imageView.setRotate(258.75);
                        } */
                    }
                    oldDegree = degree;
                }

                // draw line
                gc.setStroke(go);
                gc.setFill(Color.YELLOW);
                gc.setLineWidth(4);
                gc.strokeLine(oldLocation.getX(), oldLocation.getY(), x, y);

                oldLocation.setX(x);
                oldLocation.setY(y);
            }
        });

        return pathTransition;

    }
}
