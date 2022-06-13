package com.example.loginassignment;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Rating implements Initializable {
    @FXML
    private ChoiceBox<String> ratingBox;
    @FXML
    private Label star;
    @FXML
    private TextField comment;
    @FXML
    private Button btnrate;

    private String[] Rate = {"1", "2", "3", "4", "5"};

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ratingBox.getItems().addAll(Rate);
    }

    public void getRating(ActionEvent event) {
        String myRating = ratingBox.getValue();
        star.setText(myRating);
    }

    public String updateRatingChoiceOnAction(ActionEvent event) throws IOException {
        System.out.println("Rating work");
        String Rate = ratingBox.getValue();
        star.setText(Rate+" star(s)");
        switch (Rate){
            case "1" :
                return Rate;
            case "2" :
                return Rate;
            case "3" :
                return Rate;
            case "4" :
                return Rate;
            case "5" :
                return Rate;
        }
        return Rate;
    }
    public void backtoMainPage(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("bookingPage.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
