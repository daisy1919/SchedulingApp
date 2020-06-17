/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingapp;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Properties;
import java.util.TimeZone;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import schedulingapp.Models.Appointment;
import schedulingapp.Models.User;
import schedulingapp.UserCredentials;
import utils.DBConnection;
import utils.UserActivity;

/**
 *
 * @author daisy
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML javafx.scene.control.TextField usernameText;
    @FXML javafx.scene.control.TextField passwordText;
    @FXML javafx.scene.control.Label errorMessages;
    @FXML javafx.scene.control.Button submitButton;
    @FXML javafx.scene.control.Button exitButton;
    @FXML javafx.scene.control.Label loginLabel;
    @FXML javafx.scene.control.Label usernameLabel;
    @FXML javafx.scene.control.Label passwordLabel;
    
    //Please log in below
    //Username:
    //Password:
    //Exit
    //Log in
    

private Boolean isAuthenticated(User userToAuth) {        
        String unameText = this.usernameText.getText();
        String pwordText = this.passwordText.getText();
        Boolean isAuth = false;        
        try {
            LinkedList<User> usersPossible = (LinkedList<User>) DBConnection.getUsers();
            for(int i = 0; i < usersPossible.size(); i++) {  
                User currUser = usersPossible.get(i);                
                if (currUser.getUserName().equals(unameText) && currUser.getPassword().equals(pwordText)) {                    
                    return true;                    
                }                
                else { isAuth = false; }               
            }    
        }       
        catch (SQLException sqlE) { System.out.println("Error " + sqlE.getMessage()); }                
        return isAuth;        
    }

    //The following method is for requirement F. It shows an error message in English or German if the credentials are incorrect.
    //The following method validates the credentials. If they are incorrect, a message in either English or German is printed.
    @FXML
    void handleLoginButton (ActionEvent event) throws SQLException, IOException {        
        String usernameEntered = this.usernameText.getText();
        UserCredentials.setUsername(usernameEntered);
        LinkedList<User> allUsers = (LinkedList<User>) DBConnection.getUsers();
        User userToAuth = null;
        
        for(int i = 0; i < allUsers.size(); i++) {            
            if (usernameEntered.equals(allUsers.get(i).getUserName())) {                
                userToAuth = allUsers.get(i);                
            }            
        }
        
        try {            
            //If user credentials are in db, open options window and clear credentials
            if(isAuthenticated(userToAuth) == true) {
                
                String uID = String.valueOf(UserCredentials.getCurrentUserId());
                String uNameE = UserCredentials.getUsername();
                LocalDate today = LocalDate.now();
                LocalDateTime todayDT = LocalDateTime.now();
                String todayDTS = todayDT.toString();
                String userTZ = TimeZone.getDefault().getID().toString();
                
                UserActivity.userLoginTimes(uNameE, todayDTS, userTZ);
                System.out.println("File appended.");
                
                Boolean apptSoon = false;
                Iterable<Appointment> dayAppts = DBConnection.getConsultantDayAppt(today, uID);
                
                for(Appointment appt : dayAppts) {
                    
                    ZonedDateTime startTime = appt.getStartTime();
                    /*String stTime = appt.getStartTime();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
                    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    LocalDateTime startTime = LocalDateTime.parse(stTime, formatter);
                    ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());
                    ZonedDateTime zonedStartTime = ZonedDateTime.of(startTime, localZoneId);
                    Instant gmtStartToLocalStart = zonedStartTime.toInstant();
                    String zonedStartS = gmtStartToLocalStart.toString();
                    appt.setZonedStartTime(gmtStartToLocalStart);                    
                    //parse by converting letters to space
                    String subStart = zonedStartS.substring(0, 10);
                    String subStart2 = zonedStartS.substring(11, 19);
                    String newZoned = subStart + " " + subStart2;
                    LocalDateTime startDateTimeZ = LocalDateTime.parse(newZoned, formatter2);*/
                    
                    int startTimeInMin = (startTime.getHour() * 60) + startTime.getMinute();
                    int currTimeInMin = (todayDT.getHour() * 60) + todayDT.getMinute();
                    
                    if(Math.abs(startTimeInMin - currTimeInMin) <= 15) {
                        apptSoon = true;
                    }                
                    else {
                        apptSoon = false;
                    }
                }
                
                if(apptSoon == false) {
                    Parent root = FXMLLoader.load(getClass().getResource("OnLogin.fxml"));
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                }
                else {
                    Parent root = FXMLLoader.load(getClass().getResource("ApptWarning.fxml"));
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                }
                usernameText.clear();
                passwordText.clear();
                errorMessages.setText("");
            }
            else {
                ResourceBundle rb = ResourceBundle.getBundle("schedulingapp/Nat", Locale.getDefault());
                if (rb.getLocale().getLanguage().equals("de")) {
                    errorMessages.setText(rb.getString("Credentials") + " " + rb.getString("are") + " " + rb.getString("incorrect"));
                }
                else { errorMessages.setText("Credentials are incorrect"); }
            }   
        }
        catch(IOException ioEx) { System.out.println("Error " + ioEx.getMessage()); }        
    } 
    
    @FXML
    void handleExitButton(ActionEvent event) {
        Platform.exit();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rbb) {
        /*@FXML javafx.scene.control.Button submitButton;
    @FXML javafx.scene.control.Button exitButton;

        

    //Exit
    //Log in*/
        
        ResourceBundle rb = ResourceBundle.getBundle("schedulingapp/Nat", Locale.getDefault());
        if (rb.getLocale().getLanguage().equals("de")) {
            loginLabel.setText(rb.getString("Login") + " " + rb.getString("please"));
            usernameLabel.setText(rb.getString("Username") + ": ");
            passwordLabel.setText(rb.getString("Password") + ": ");
            submitButton.setText(rb.getString("Login"));
            exitButton.setText(rb.getString("Exit"));
            //errorMessages.setText(rb.getString("Credentials") + " " + rb.getString("are") + " " + rb.getString("incorrect"));
        }
        else { 
            loginLabel.setText("Login please");
            usernameLabel.setText("Username: ");
            passwordLabel.setText("Password: ");
            submitButton.setText("Login");
            exitButton.setText("Exit");
            //errorMessages.setText("Credentials are incorrect"); 
        }
    }    
    
}
