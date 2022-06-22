package com.example.loginassignment;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class UserForgotPassword {
    @FXML
    private TextField username, phone;
    @FXML
    private PasswordField newPassword;
    @FXML
    private Button enterInfo,
            updatePasswordButton;
    @FXML
    private Label alertMessage;
    @FXML
    private Hyperlink goLoginLink;

    public void updatePasswordButtonOnAction(ActionEvent event) throws IOException {
        Uconn.setpass(Uconn.getUsn(),newPassword.getText());
        System.out.println("work");
        Parent root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
        Stage stage = (Stage) updatePasswordButton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goLoginPage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
        Stage stage = (Stage)alertMessage.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goUpdatePassword()throws Exception {
        boolean sw = Uconn.Forgotpass(username.getText(), phone.getText());
        if (sw == false) {
            alertMessage.setText("Wrong. Please try again");
        }
        if (sw == true) {
            Parent root = FXMLLoader.load(getClass().getResource("updatePasswordPage.fxml"));
            Stage stage = (Stage) alertMessage.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            Uconn.setUsn(username.getText());
        }
    }
}
