package com.example.loginassignment;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
//    DatabaseConnection connectNow = new DatabaseConnection();
//    Connection connectDB = connectNow.getConnection();
//
//    String connectQuery = "";
//
//    try{
//
//        Statement statement = connectDB.createStatement();
//        ResultSet queryOutput - statement.executeQuery(connectQuery);
//
//        while(queryOutput.next()){
//            username.setText(queryOutput.getString(columnLabel: ""));
//        }
//    }catch (Exception e){
//        e.printStackTrace();
//    }
    ImageView myImageView;
    Button myButton;

    @FXML
    private Label welcomeText;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private TextField phone;
    @FXML
    private PasswordField recreatepass;
    @FXML
    Hyperlink login;
    @FXML
    Hyperlink register;
    @FXML
    Hyperlink bruhsrs;
    @FXML
    Button btnregister,btnlogin;
    @FXML
    private Label wrongLogin, wrongreg, wrongrecreate;
    @FXML
    Button rec;
    @FXML
    Button nnn;




    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }


    private Stage stage;
    private Scene scene;
    private Parent root;


   //Enter the Login Page
    public void gotologin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage window = (Stage)login.getScene().getWindow();
        window.setScene(new Scene(root, 750, 500));

    }
    //Button Press Login
    public void login(ActionEvent event) throws Exception {
        checklogin();
    }

    public String getUsername() {
        return username.getText();
    }

    //It check Login
    public void checklogin() throws Exception {
        int sw = Uconn.UserLogin(username.getText(), password.getText());
        System.out.println(sw);
        if(sw==0){
            wrongLogin.setText("success!");
//      change to the map
//            changeScene("afterLogin.fxml");
        }
        else if(sw==2){
            wrongLogin.setText("Wrong password.");
        }

        else if(sw==1){
            wrongLogin.setText("Wrong username or password");
        }
    }
    //From Login to Register through Hyperlink
    public void gotoregister(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
        Stage window = (Stage)register.getScene().getWindow();
        window.setScene(new Scene(root, 750, 500));
    }

    //Button to press
    public void register(ActionEvent event) throws Exception {
        goregis();
    }
    //check is it correct or not
    public void goregis() throws Exception {

            boolean sw=Uconn.createAcc(username.getText(), phone.getText(), password.getText());
            if(sw==false){
                wrongreg.setText("You have enter the existed account");
            }
            else {
                System.out.println("work");
                Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
                Stage window = (Stage) login.getScene().getWindow();
                window.setScene(new Scene(root, 750, 500));
            }

    }
    //From Login to Forgot Password through Hyperlink
    public void gotobruhsrs(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("forgotpassword.fxml"));
        Stage window = (Stage)bruhsrs.getScene().getWindow();
        window.setScene(new Scene(root, 750, 500));
    }

    //button to rec
    public void recreate(ActionEvent event) throws Exception {
    gorecreate();
    }
    //check to rec
    public void gorecreate()throws Exception {
        boolean sw = Uconn.Forgotpass(username.getText(), phone.getText());
        if (sw == false) {
            wrongrecreate.setText("Wrong. Please try again");
        }
        if (sw == true) {
            Parent root = FXMLLoader.load(getClass().getResource("rename.fxml"));
            Stage window = (Stage)rec.getScene().getWindow();
            window.setScene(new Scene(root, 750, 500));
            Uconn a=new Uconn();
            a.setUsn(username.getText());
        }

    }
    // Rename to login through button
    public void gotorecreate(ActionEvent event) throws IOException {
        Uconn a=new Uconn();
        Uconn.setpass(a.getUsn(),recreatepass.getText());
        System.out.println("work");
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage window = (Stage)nnn.getScene().getWindow();
        window.setScene(new Scene(root, 750, 500));
    }

}