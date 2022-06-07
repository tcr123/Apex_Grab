package com.example.loginassignment;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Animation implements Initializable {
    @FXML
    private ImageView myImageView;
    Button myButton;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private double time;
    private Group rt;
    private Button start,
            testButton;
    private String driverLocation;
    private String userLocation;
    private String finalLocation;
    private String userSelectionTime;

    private static final double offSetX = 5;
    private static final double offSetY = 15;

    @FXML
    private Label myLabel,
            userLabel,
            capacityLabel,
            alertMessage,
            SystemTime;
    @FXML
    private ChoiceBox<String> originBox;
    @FXML
    private ChoiceBox<String> destinationBox;
    @FXML
    private ChoiceBox<String> numberOfPassengersBox;
    @FXML
    private ChoiceBox<String> esitmatedTime;
    @FXML
    private TableView<Driver> driverTable;
    @FXML
    private TableColumn<Driver, String> driver_col;
    @FXML
    private TableColumn<Driver, Double> estimatedTime_col;
    @FXML
    private
    Connection conn = null;
    ResultSet driverRS = null;
    ObservableList<Driver> oblist = FXCollections.observableArrayList();
    ArrayList<Driver> driverList = new ArrayList<>();

    private volatile boolean stop = false;

    private String[] place={"SLUMMLAKES","CONTAINEMENT","RUNOFF","THE PIT","AIRBASE","BUNKER","THUNDERDOME","SKULL TOWN","MARKET","WATER TREATMENT","REPULSOR","THE CAGE","ARTILLERY","RELAY","WETLANDS","SWAMPS","HYDRO DAM"};

    private String[] finalplace={"SLUMMLAKES","CONTAINEMENT","RUNOFF","THE PIT","AIRBASE","BUNKER","THUNDERDOME","SKULL TOWN","MARKET","WATER TREATMENT","REPULSOR","THE CAGE","ARTILLERY","RELAY","WETLANDS","SWAMPS","HYDRO DAM"};

    private String[] capacity = {"4", "6"};

    private String[] listOfTime = {"nothing", "5", "10", "15", "20", "25", "30"};

    private HelloApplication obj;

    private String username = "";

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        username = UserLogin.name;

        originBox.getItems().addAll(place);

//        myChoiceBox.setOnAction(this::getPlace);

        destinationBox.getItems().addAll(finalplace);

//        userChoiceBox.setOnAction(this::getfinalPlace);

        numberOfPassengersBox.getItems().addAll(capacity);

        esitmatedTime.getItems().addAll(listOfTime);

        Timenow();

        obj = new HelloApplication();

//        capacityBox.setOnAction(this::getCapacity);
    }

    public void getEstimatedTime(ActionEvent event) {
        String estimatedTime = esitmatedTime.getValue();
    }

    public void getCapacity(ActionEvent event) {
        String myCapacity = numberOfPassengersBox.getValue();
        capacityLabel.setText(myCapacity);
    }

    private void Timenow() {
        Thread thread = new Thread(() -> {
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss aa");
            while (!stop) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    System.out.println(e);
                }
                String timeFormat[] = sdf.format(new Date()).split(":");
                timeFormat[1] = String.valueOf(Integer.parseInt(timeFormat[1]) % 12);
                final String timenow = timeFormat[1] + ":" +timeFormat[2];
                Platform.runLater( () -> {
                    SystemTime.setText(timenow);
                });
            }
        });

        thread.start();
    }

    public String getPlace(ActionEvent event){
        if (originBox == null)
            return null;
        String myPlace = originBox.getValue();
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
        if (destinationBox == null)
            return null;
        String usermyPlace = destinationBox.getValue();
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
    public void starttherun(ActionEvent event) throws Exception {
//        System.out.println(obj.userLocation);
        alertMessage.setText("");
        String driver = getDriverFromTable();
        Uconn.SelectDriver(username, driver, originBox.getValue(), destinationBox.getValue());
        System.out.println(driver);
        startFirst(stage, driver);
    }

    public void startFirst(Stage stage, String driver) throws Exception {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("bookingPage.fxml"));
//        Group root = new Group();
//        Scene scene = new Scene(root, Canvas.SCENE_WIDTH, Canvas.SCENE_HEIGHT);
//        stage.setTitle("APEX E-hailing");
//        root.getChildren().add(fxmlLoader.load());

        //from database
        conn = Uconn.getConnection();
        PreparedStatement statement = conn.prepareStatement("SELECT * FROM driver WHERE name = '"+driver+"'");
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            driverLocation = rs.getString("location");
        }
        System.out.println(driverLocation);
        //put final button
//        finalLocation = "THE CAGE";
        userLocation = originBox.getValue();
        finalLocation = destinationBox.getValue();
        System.out.println(finalLocation);

        obj.setDriverLocation(driverLocation);
        obj.setUserLocation(userLocation);
        Uconn.SelectLocation(username, userLocation);
        obj.setFinalLocation(finalLocation);
        Uconn.SelectDestination(username, finalLocation);
        Uconn.UsetCapacity(username, numberOfPassengersBox.getValue());


        double time = obj.findPath(driverLocation, userLocation) * 3;
        System.out.println(time);
        obj.getImageFromDriverName(driver);
        obj.startMoveDriverToUser(time);

//        stage.setScene(scene);
//        stage.show();
    }

    public void submitButtonOnAction(ActionEvent event){
        if (originBox.getValue() == null){
            alertMessage.setText("Please enter origin!");
        } else if (destinationBox.getValue() == null){
            alertMessage.setText("Please enter destination!");
        } else if (numberOfPassengersBox.getValue() == null){
            alertMessage.setText("Please enter number of passengers!");
        } else if (originBox.getValue().equals(destinationBox.getValue())){
            alertMessage.setText("Origin and Destination should not be same!");
        } else if (esitmatedTime.getValue() == null){
            alertMessage.setText("Please select a range of time");
        } else {
            try {
                oblist.clear();
                driverList.clear();
                conn = Uconn.getConnection();
                int capacity = Integer.parseInt(numberOfPassengersBox.getValue());
                driverRS = Uconn.ShowDriver(username, capacity);

                DijkstraMap map = new DijkstraMap(LocationKey.LocationNum(originBox.getValue()));
                map.dijkstra();

                userSelectionTime = esitmatedTime.getValue();

                // Return the time of travelling from initialLocation to finalLocation
                double fixed_time = map.getDistance(LocationKey.LocationNum(destinationBox.getValue())) * 3;

                while (driverRS.next()) {
                    Driver temp = new Driver(driverRS.getString("name"), driverRS.getInt("capacity"),
                            driverRS.getString("location"), driverRS.getString("status"),
                            driverRS.getString("customer"), driverRS.getDouble("rating"),
                            0);

                    DijkstraMap mapDriverToUser = new DijkstraMap(LocationKey.LocationNum(temp.getLocation()));
                    mapDriverToUser.dijkstra();

                    // Return the time of travelling from initialLocation to finalLocation
                    double time = mapDriverToUser.getDistance(LocationKey.LocationNum(originBox.getValue())) * 3 + fixed_time;

                    if (userSelectionTime.compareTo("nothing") != 0) {
                        if (Integer.parseInt(userSelectionTime) > time) {
                            temp.setEstimatedTime(time);
                            driverList.add(temp);
                        }
                    }
                    else {
                        temp.setEstimatedTime(time);
                        driverList.add(temp);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            // put all images of car to all driver Location
            try {
                inputAllLocationOfDriver(driverList);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            oblist.addAll(driverList);

            driver_col.setCellValueFactory(new PropertyValueFactory<>("name"));
            estimatedTime_col.setCellValueFactory(new PropertyValueFactory<>("estimatedTime"));

            driverTable.setItems(oblist);
        }
    }

    private void inputAllLocationOfDriver(ArrayList<Driver> driverList) throws FileNotFoundException {
        obj.clearImage();
        obj.insertImage(driverList);
    }

    public String getDriverFromTable(){
        if (driverTable.getSelectionModel().getSelectedItem() == null){
            alertMessage.setText("Please select a driver!");
        } else {
            String selectedDriver = driverTable.getSelectionModel().getSelectedItem().getName();
            System.out.println(selectedDriver);
            return selectedDriver;
        }
        return null;
    }
}
