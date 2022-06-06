package com.example.loginassignment;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class UserLogin extends Application {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    Hyperlink goForgotPassword,
            goRegister;
    @FXML
    private Label alertMessage;
    @FXML
    private Button loginButton;

    public static void accessUserLoginPage() throws IOException {
        launch();
    }

    public void checklogin() throws Exception {
        int sw = Uconn.UserLogin(username.getText(), password.getText());
        System.out.println(sw);
        if(sw==0){
            alertMessage.setText("success!");
            Animation.username = username.getText();
            Pane root = FXMLLoader.load(getClass().getResource("bookingPage.fxml"));
            Stage stage = (Stage) username.getScene().getWindow();
            HelloApplication.initializeGroup(root);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else if(sw==2){
            alertMessage.setText("Wrong password.");
        }
        else if(sw==1){
            alertMessage.setText("Wrong username or password");
        }
    }

    public void goForgotPasswordOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("forgotPasswordPage.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goRegisterOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("registerPage.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void login(ActionEvent event) throws Exception {
        checklogin();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("loginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Apex E-Hailing");
        stage.setScene(scene);
        stage.show();
        System.out.println("Work start");
    }
}
