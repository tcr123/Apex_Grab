package com.example.loginassignment;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;


public class UserRating implements Initializable {
    @FXML
    private ImageView star1, star2, star3, star4, star5;
    @FXML
    private TextField commentField;
    @FXML
    private Button confirmButton;
    @FXML
    private Label driverLabel;

    public Image yellowStarImage = null;
    public Image greyStarImage = null;

    private static int starNumber = 0;
    public static String comment = "";

    public void star1Clicked(MouseEvent event){
        star1.setImage(yellowStarImage);
        star2.setImage(greyStarImage);
        star3.setImage(greyStarImage);
        star4.setImage(greyStarImage);
        star5.setImage(greyStarImage);
        starNumber = 1;
    }

    public void star2Clicked(MouseEvent event){
        star1.setImage(yellowStarImage);
        star2.setImage(yellowStarImage);
        star3.setImage(greyStarImage);
        star4.setImage(greyStarImage);
        star5.setImage(greyStarImage);
        starNumber = 2;
    }

    public void star3Clicked(MouseEvent event){
        star1.setImage(yellowStarImage);
        star2.setImage(yellowStarImage);
        star3.setImage(yellowStarImage);
        star4.setImage(greyStarImage);
        star5.setImage(greyStarImage);
        starNumber = 3;
    }

    public void star4Clicked(MouseEvent event){
        star1.setImage(yellowStarImage);
        star2.setImage(yellowStarImage);
        star3.setImage(yellowStarImage);
        star4.setImage(yellowStarImage);
        star5.setImage(greyStarImage);
        starNumber = 4;
    }

    public void star5Clicked(MouseEvent event){
        star1.setImage(yellowStarImage);
        star2.setImage(yellowStarImage);
        star3.setImage(yellowStarImage);
        star4.setImage(yellowStarImage);
        star5.setImage(yellowStarImage);
        starNumber = 5;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            yellowStarImage = new Image(new FileInputStream("src/main/resources/yellowstar.png"));
            greyStarImage = new Image(new FileInputStream("src/main/resources/greystar.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        driverLabel.setText("Driver: " + HelloApplication.getDriver());
    }

    public void confirmButtonOnAction(ActionEvent event) throws Exception {
        if (commentField.getText().isBlank()){
            comment = "";
        } else {
            comment = commentField.getText();
        }
        System.out.println(UserLogin.name);
        Uconn.setRating(HelloApplication.getDriver(), starNumber, comment);
        Uconn.dropPassenger(UserLogin.name, HelloApplication.getDriver());
        Parent root = FXMLLoader.load(getClass().getResource("bookingPage.fxml"));
        Stage stage = (Stage) commentField.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
