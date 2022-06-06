package com.example.loginassignment;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.BatchUpdateException;

public class UserRegister {
    @FXML
    private TextField username, phone;
    @FXML
    private PasswordField password;
    @FXML
    private Button registerButton;
    @FXML
    private Hyperlink goLoginLink;
    @FXML
    private Label alertMessage;

    public void goLoginPage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
        Stage stage = (Stage)alertMessage.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void registerButtonOnAction(ActionEvent event) throws Exception {
        boolean sw = Uconn.createAcc(username.getText(), phone.getText(), password.getText());
        if(!sw){
            alertMessage.setText("You have enter the existed account");
        }
        else {
            System.out.println("work");
            goLoginPage(event);
        }
    }
}
