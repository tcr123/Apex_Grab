package com.example.apex_grab;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    HelloApplication obj = new HelloApplication();
    @FXML
    private ImageView myImageView;
    Button myButton;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private double time;
    private Group rt;
    private Button start;
    private String driverLocation;
    private String userLocation;
    private String finalLocation;

    private static final double offSetX = 5;
    private static final double offSetY = 15;

    @FXML
    private Label myLabel;
    @FXML
    private Label userLabel;
    @FXML
    private ChoiceBox<String> myChoiceBox;
    @FXML
    private ChoiceBox<String> userChoiceBox;

    private String[] place={"SLUMMLAKES","CONTAINEMENT","RUNOFF","THE PIT","AIRBASE","BUNKER","THUNDERDOME","SKULL TOWN","MARKET","WATER TREATMENT","REPULSOR","THE CAGE"};

    private String[] finalplace={"SLUMMLAKES","CONTAINEMENT","RUNOFF"};

    Image myImage = new Image(getClass().getResourceAsStream("Map.jpeg"));

    public void displayImage() {
        myImageView.setImage(myImage);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        myChoiceBox.getItems().addAll(place);

        myChoiceBox.setOnAction(this::getPlace);

        userChoiceBox.getItems().addAll(finalplace);

        userChoiceBox.setOnAction(this::getfinalPlace);
    }

    public String getPlace(ActionEvent event){
        if (myChoiceBox == null)
            return null;
        String myPlace = myChoiceBox.getValue();
        myLabel.setText("Going to "+myPlace+" ?");
        switch  (myPlace)
        {
            case "SLUMMLAKES":
                return userLocation="SLUMMLAKES";
            case "CONTAINEMENT":
                return userLocation="CONTAINEMENT";
            case "RUNOFF":
                return userLocation="RUNOFF";
            case "THE PIT":
                return userLocation="THE PIT";
            case "AIRBASE":
                return userLocation="AIRBASE";
            case "BUNKER":
                return userLocation="BUNKER";
            case "THUNDERDOME":
                return userLocation="THUNDERDOME";
            case "SKULL TOWN":
                return userLocation="SKULL TOWN";
            case "MARKET":
                return userLocation="MARKET";
            case "WATER TREATMENT":
                return userLocation="WATER TREATMENT";
            case "REPULSOR":
                return userLocation="REPULSOR";
            case "THE CAGE":
                return userLocation="THE CAGE";
            case "ARTILLERY":
                return userLocation="ARTILLERY";
            case "RELAY":
                return userLocation="RELAY";
            case "WETLANDS":
                return userLocation="WETLANDS";
            case "SWAMPS":
                return userLocation="SWAMPS";
            case "HYDRO DAM":
                return userLocation="HYDRO DAM";
        }
        return myPlace;

    }

    public String getfinalPlace(ActionEvent event){
        if (userChoiceBox == null)
            return null;
        String usermyPlace = userChoiceBox.getValue();
        userLabel.setText("Wanna Go to "+usermyPlace+" ?");
        switch  (usermyPlace)
        {
            case "SLUMMLAKES":
                return finalLocation="SLUMMLAKES";
            case "CONTAINEMENT":
                return finalLocation="CONTAINEMENT";
            case "RUNOFF":
                return finalLocation="RUNOFF";
            case "THE PIT":
                return finalLocation="THE PIT";
            case "AIRBASE":
                return finalLocation="AIRBASE";
            case "BUNKER":
                return finalLocation="BUNKER";
            case "THUNDERDOME":
                return finalLocation="THUNDERDOME";
            case "SKULL TOWN":
                return finalLocation="SKULL TOWN";
            case "MARKET":
                return finalLocation="MARKET";
            case "WATER TREATMENT":
                return finalLocation="WATER TREATMENT";
            case "REPULSOR":
                return finalLocation="REPULSOR";
            case "THE CAGE":
                return finalLocation="THE CAGE";
            case "ARTILLERY":
                return finalLocation="ARTILLERY";
            case "RELAY":
                return finalLocation="RELAY";
            case "WETLANDS":
                return finalLocation="WETLANDS";
            case "SWAMPS":
                return finalLocation="SWAMPS";
            case "HYDRO DAM":
                return finalLocation="HYDRO DAM";
        }
        return usermyPlace;

    }

    //Button to start the driver toward the user (test)
    public void starttherun(ActionEvent event) throws IOException{
//        System.out.println(obj.userLocation);
        startFirst(stage);
    }

    public void startFirst(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Group root = new Group();
//        Scene scene = new Scene(root, Canvas.SCENE_WIDTH, Canvas.SCENE_HEIGHT);
//        stage.setTitle("APEX E-hailing");
//        root.getChildren().add(fxmlLoader.load());

        //from database
        driverLocation = "SLUMMLAKES";
        //put final button
//        finalLocation = "THE CAGE";

        obj.setDriverLocation(driverLocation);
        obj.setUserLocation(userLocation);
        obj.setFinalLocation(finalLocation);

        try {
            Image image = new Image(new FileInputStream("src/main/resources/com/example/apex_grab/car.png"));
            ImageView imageView = new ImageView(image);

            double oldLocationX = LocationKey.Coordinate(driverLocation).getX();
            double oldLocationY = LocationKey.Coordinate(driverLocation).getY();
            imageView.setX(oldLocationX - offSetX);
            imageView.setY(oldLocationY - offSetY);

            imageView.setFitHeight(50);
            imageView.setFitWidth(50);

            imageView.setPreserveRatio(true);

            double time = obj.findPath(driverLocation, userLocation);
            System.out.println(time);
            obj.startMoveDriverToUser(time, imageView);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

//        stage.setScene(scene);
//        stage.show();
    }

}