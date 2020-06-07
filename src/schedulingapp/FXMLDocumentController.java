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
import java.time.LocalDate;
import java.time.LocalDateTime;
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

/**
 *
 * @author daisy
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML javafx.scene.control.TextField usernameText;
    @FXML javafx.scene.control.TextField passwordText;
    @FXML javafx.scene.control.Label errorMessages;
    

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
                LocalDate today = LocalDate.now();
                LocalDateTime todayDT = LocalDateTime.now();
                Boolean apptSoon = false;
                Iterable<Appointment> dayAppts = DBConnection.getConsultantDayAppt(today, uID);
                
                for(Appointment appt : dayAppts) {
                    String timeToParse = appt.getStartTime();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
                    LocalDateTime startDateTime = LocalDateTime.parse(timeToParse, formatter);
                    //the following if-else needs to be fixed
                    if(startDateTime.plusMinutes(15).equals(todayDT)) {
                    //change apptSoon in this if-else statement
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
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
