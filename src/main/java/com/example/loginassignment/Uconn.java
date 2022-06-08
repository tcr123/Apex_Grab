package com.example.loginassignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Uconn {
    private static String usn;

    public static String getUsn() {
        return usn;
    }

    public static void setUsn(String us) {
        usn = us;
    }

    public static Connection getConnection() {
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
//            String url = "jdbc:mysql://localhost:3306/apex";
            String url = "jdbc:mysql://10.210.6.168:3306/apex";
            String username = "apex";
            String password = "1118";
            Class.forName(driver);

            //System.out.println("Connected");
            return DriverManager.getConnection(url, username, password);
        } catch (Exception e) {

            System.out.println(e);
        }
        return null;
    }

    public static boolean createAcc(String username, String phone, String pass) throws Exception {
        // METHOD TO CREATE ACCOUNT, IF ACCOUNT ALREADY EXIST, RETURN FALSE
        try {
            Connection con = getConnection();

            //CHECK EXISTENCE OF USERNAME
            PreparedStatement statement = con.prepareStatement("SELECT COUNT(username) AS got FROM apex.user WHERE username = '" + username + "'  ");
            ResultSet exist = statement.executeQuery();
            int got = 0;
            while (exist.next()) {
                got = exist.getInt("got");
            }
            if (got == 1) {
                System.out.println("\nSorry, your Username is already occupied ");
                return false;
            }

            //CHECK EXISTENCE OF PHONE
            statement = con.prepareStatement("SELECT COUNT(phone) AS got FROM user WHERE phone = '" + phone + "' ");
            exist = statement.executeQuery();
            int got1 = 0;
            while (exist.next()) {
                got1 = exist.getInt("got");
            }
            if (got1 == 1) {
                System.out.println("\nSorry, your Phone is already occupied ");
                return false;
            }

            //INSERT INTO member TABLE
            statement = con.prepareStatement("INSERT INTO apex.user (username,phone,password)VALUES ( '" + username + "', '" + phone + "' , '" + pass + "' )");
            statement.executeUpdate();


        } catch (Exception e) {
            System.out.println(e);
        }
        return true;
    }

    public static int UserLogin(String Credential, String password) throws Exception {

        // A FUNCTION TO LOG IN TO USER ACCOUNT BY CHECKING CREDENTIAL ON MYSQL USER TABLE,
        // IF WRONG USERNAME, RETURN 1, IF WRONG PASSWORD, RETURN 2, IF SUCCESS, RETURN 0
        Connection con = getConnection();


        //CHECK EXISTENCE OF CREDENTIAL
        PreparedStatement statement = con.prepareStatement("SELECT COUNT(username) AS got FROM apex.user WHERE username = '" + Credential + "' OR phone = '" + Credential + "'   ");
        ResultSet exist = statement.executeQuery();
        int got = 0;
        while (exist.next()) {
            got = exist.getInt("got");
        }
        if (got == 0) {
            System.out.println("\nSorry, your account does not exists!");
            return 1;
        }

        //CHECK PASSWORD IS COLLECT ACCORDING TO ID AND IDENTITY
        statement = con.prepareStatement("SELECT COUNT(username) AS got FROM user WHERE username = '" + Credential + "'  AND password = '" + password + "' OR phone= '" + Credential + "' AND password = '" + password + "'   ");
        exist = statement.executeQuery();
        int got1 = 0;
        while (exist.next()) {
            got1 = exist.getInt("got");
        }
        if (got1 == 0) {
            System.out.println("\nWrong password!");
            return 2;
        }
        if(got1==1) {
            return 0;
        }
        return 9;
    }

    public static void SelectLocation(String Username,String Location) throws Exception {
        //invote this method after user enter his current location
        Connection con = getConnection();
        PreparedStatement statement = con.prepareStatement("UPDATE apex.user SET starting_point = '"+Location+"' WHERE username = '"+Username+"'");
        statement.executeUpdate();
    }

    public static void SelectDestination(String Username,String Destination) throws Exception {
        //invote this method after user enter his destination\

        Connection con = getConnection();
        PreparedStatement statement = con.prepareStatement("UPDATE apex.user SET destination = '"+Destination+"' WHERE username = '"+Username+"'");
        statement.executeUpdate();

        System.out.println("Helpless");
    }
    public static int UgetCapacity(String name) throws Exception {
        //fetch user capacity
        Connection con = getConnection();
        PreparedStatement statement = con.prepareStatement(" SELECT capacity FROM apex.user where name='"+name+"' ");
        ResultSet exist = statement.executeQuery();
        int re=0;
        while (exist.next()) {
            re = exist.getInt("capacity");
        }
        return re;
    }

    public static void UsetCapacity(String name, String num) throws Exception {
        //fetch user capacity
        Connection con = getConnection();
        PreparedStatement statement = con.prepareStatement("UPDATE `apex`.`user` SET `capacity` = '"+num+"' WHERE `username` = '"+name+"'");
        statement.executeUpdate();

    }

    // show driver list
    public static ResultSet ShowDriver(String name, int capacity) throws Exception {
        //fetch available driver
        Connection con = getConnection();
//        int num=UgetCapacity(name);
        PreparedStatement statement = con.prepareStatement("SELECT * FROM apex.driver where capacity='"+ capacity +"' AND status= 'Available' ");
        ResultSet exist = statement.executeQuery();
        PreparedStatement statement2 = con.prepareStatement("UPDATE `apex`.`user` SET `status` = 'Pending' WHERE (`username` = '"+name+"')");
        statement2.executeUpdate();
        return exist;
    }

    // update the driver to unavailable and user to wait
    public static void SelectDriver(String Username,String driver, String userOrigin, String userDestination) throws Exception {
        Connection con = getConnection();
        PreparedStatement statement = con.prepareStatement("UPDATE driver SET `status` = 'Unavailable', `customer` = '"+Username+"'  WHERE (`name` = '"+driver+"')");
        statement.executeUpdate();
        PreparedStatement statement2 = con.prepareStatement("UPDATE apex.user SET status = 'Waiting', starting_point = '"+ userOrigin +"' , destination = '"+ userDestination +"' WHERE username = '"+Username+"'");
        statement2.executeUpdate();
    }

    // update user to picked up
    public static void DriverReachedU(String Username) throws Exception {
        Connection con = getConnection();
        PreparedStatement statement2 = con.prepareStatement("UPDATE `apex`.`user` SET `status` = 'Picked Up' WHERE (`username` = '"+Username+"')");
        statement2.executeUpdate();
    }

    // update user to reached and driver to available
    public static void Reached(String Username,String driver) throws Exception {
        Connection con = getConnection();
        PreparedStatement statement2 = con.prepareStatement("UPDATE `apex`.`user` SET `status` = 'Reached' WHERE (`username` = '"+Username+"')");
        statement2.executeUpdate();
        PreparedStatement statement = con.prepareStatement("UPDATE driver SET `status` = 'Available', `customer` = null  WHERE (`name` = '"+driver+"')");
        statement.executeUpdate();
    }

    public static boolean Forgotpass (String username, String phone){
        try {
            Connection con = getConnection();

            //CHECK EXISTENCE OF USERNAME
            PreparedStatement statement = con.prepareStatement("SELECT COUNT(username) AS got FROM apex.user WHERE username = '" + username + "'  ");
            ResultSet exist = statement.executeQuery();
            int got = 0;
            while (exist.next()) {
                got = exist.getInt("got");
            }
            if (got == 0) {
                System.out.println("no such user ");
                return false;
            }

            //CHECK EXISTENCE OF PHONE
            statement = con.prepareStatement("SELECT COUNT(phone) AS got FROM user WHERE phone = '" + phone + "' ");
            exist = statement.executeQuery();
            int got1 = 0;
            while (exist.next()) {
                got1 = exist.getInt("got");
            }
            if (got1 == 0) {
                System.out.println("\nWrong phone number");
                return false;
            }

            //INSERT INTO member TABLE



        } catch (Exception e) {
            System.out.println(e);
        }
        return true;
    }

    public static void setpass (String username,String NewP){
        try {
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("UPDATE `apex`.`user` SET `password` = '" + NewP + "' WHERE (`username` = '" + username + "')      ");
            statement.executeUpdate();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void getrate (String Username, String name, String rate){

    }

}
