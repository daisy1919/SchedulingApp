/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingapp;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;
import schedulingapp.Models.Appointment;
import schedulingapp.Models.Customer;
import utils.DBConnection;

/**
 * FXML Controller class
 *
 * @author daisy
 */
public class SelectCustomerController implements Initializable {    
    @FXML javafx.scene.control.TextField searchCustomerText;
    @FXML javafx.scene.control.Button useCustomerButton;
    @FXML javafx.scene.control.Button goBackButton;
    @FXML javafx.scene.control.Label errorLabel;
    @FXML javafx.scene.control.DatePicker desiredApptDate;
    @FXML javafx.scene.control.TextField titleText;
    @FXML javafx.scene.control.TextField descriptionText;
    @FXML javafx.scene.control.TextField locationText;
    @FXML javafx.scene.control.TextField contactText;
    @FXML javafx.scene.control.TextField typeText;
    @FXML javafx.scene.control.TextField urlText;
    
    @FXML
    private TableView<Customer> customersFound;
    
    @FXML
    private TableColumn<Customer, String> custNameCol;
    
    @FXML
    private TableColumn<Customer, String> custPhoneCol;
    
    @FXML
    private TableColumn<Customer, String> custAddCol;
    
    @FXML
    private TableView<Appointment> availableAppts;
    
    @FXML
    private TableColumn<Appointment, String> startTimeCol;
    
    @FXML
    private TableColumn<Appointment, String> endTimeCol;
    
    @FXML
    public void handleSearchCustomerButton(ActionEvent event) throws SQLException {
        String nameToSearch = searchCustomerText.getText();        
        Iterable<Customer> fCustomers = DBConnection.searchByName(nameToSearch);
        ObservableList<Customer> foundCustomers = FXCollections.observableArrayList();
        fCustomers.forEach(foundCustomers::add);   
        customersFound.setItems(foundCustomers);
    }
    
    //The following is for requirement F to prevent overlapping appointments, as well as prevent users from scheduling appointments outside of business hours.
    //The way the date picker loads times prevents users from scheduling outside of business hours, which are 8-17 GMT.
    //The tableviews all print in the user's local time for their convenience, but the appointment times are stored in GMT.
    //This method (as well as the method in the edit appt controller) also only shows times that the currently logged in user is not using for another appointment.
    @FXML
    public void handleDatePicker(ActionEvent event) throws SQLException {
        LocalDate apptDate = desiredApptDate.getValue();
        startTimeCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endTimeCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        Iterable<Appointment> aTimes = DBConnection.getAvailableApptTimes(apptDate); //This line retrieves available times for the logged in user, not populating times that they're already in another appointment. This is for requirement F.
        ObservableList<Appointment> availableTimes = FXCollections.observableArrayList();
        aTimes.forEach(availableTimes::add);
        
        /*for(Appointment appt : availableTimes) {
            String stTime = appt.getStartTime();
            String eTime = appt.getEndTime();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
            LocalDateTime startTime = LocalDateTime.parse(stTime, formatter);
            LocalDateTime endTime = LocalDateTime.parse(eTime, formatter);
            /*ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());
            ZonedDateTime zonedStartTime = ZonedDateTime.of(startTime, localZoneId);
            ZonedDateTime zonedEndTime = ZonedDateTime.of(endTime, localZoneId);
            Instant gmtStartToLocalStart = zonedStartTime.toInstant();
            Instant gmtEndToLocalEnd = zonedEndTime.toInstant();
            appt.setZonedStartTime(gmtStartToLocalStart);
            appt.setZonedEndTime(gmtEndToLocalEnd);
            //parse by converting letters to space
            String zonedStartS = gmtStartToLocalStart.toString();
            String zonedEndS = gmtEndToLocalEnd.toString();
            String subStart = zonedStartS.substring(0, 10);
            String subEnd = zonedEndS.substring(0, 10);
            String subStart2 = zonedStartS.substring(11, 19);
            String subEnd2 = zonedEndS.substring(11, 19);
            String newZonedStart = subStart + " " + subStart2;
            String newZonedEnd = subEnd + " " + subEnd2;
            appt.setSZLocal(newZonedStart);
            appt.setEZLocal(newZonedEnd);
            
        }*/
        availableAppts.setItems(availableTimes);
    }
    
    @FXML
    public void handleUseCustomerButton(ActionEvent event) throws IOException, SQLException {
        String unameE = UserCredentials.getUsername();
        int uId = UserCredentials.getCurrentUserId();
        Customer selectedCustomer = customersFound.getSelectionModel().getSelectedItem();
        Appointment selectedDateTime = availableAppts.getSelectionModel().getSelectedItem();
        ZonedDateTime startTime = ZonedDateTime.now();
        ZonedDateTime endTime = ZonedDateTime.now();
        String custId = new String();
        String apptTitle = new String();
        String apptDescription = new String();
        String apptLocation = new String();
        String apptContact = new String();
        String apptType = new String();
        String apptUrl = new String();
        if (!(selectedCustomer == null) && !(selectedDateTime == null)) {
            startTime = selectedDateTime.getStartTime();
            endTime = selectedDateTime.getEndTime();
            custId = selectedCustomer.getCustId();
            apptTitle = titleText.getText();
            apptDescription = descriptionText.getText();
            apptLocation = locationText.getText();
            apptContact = contactText.getText();
            apptType = typeText.getText();
            apptUrl = urlText.getText();
        }
        try {
            if (!(selectedCustomer == null) && !(selectedDateTime == null) && !(apptType.isEmpty()) && !(apptDescription.isEmpty())) {
                
                //need to instead at gmtStartTime and gmtEndTime by convert start/end to gmt
                
                /*
                String stTime = appt.getStartTime();
                String eTime = appt.getEndTime();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime startTime = LocalDateTime.parse(stTime, formatter);
                LocalDateTime endTime = LocalDateTime.parse(eTime, formatter);
                ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());
                ZonedDateTime zonedStartTime = ZonedDateTime.of(startTime, localZoneId);
                ZonedDateTime zonedEndTime = ZonedDateTime.of(endTime, localZoneId);
                Instant gmtStartToLocalStart = zonedStartTime.toInstant();
                Instant gmtEndToLocalEnd = zonedEndTime.toInstant();
                appt.setZonedStartTime(gmtStartToLocalStart);
                appt.setZonedEndTime(gmtEndToLocalEnd);*/
                
                ZoneId gmtZId = ZoneId.of("GMT");
                LocalDateTime startTimeL = startTime.toLocalDateTime();
                LocalDateTime endTimeL = endTime.toLocalDateTime();
                ZonedDateTime startTimeGMT = ZonedDateTime.of(startTimeL, gmtZId);
                ZonedDateTime endTimeGMT = ZonedDateTime.of(endTimeL, gmtZId);
                LocalDateTime gmtStartTimeS = startTimeGMT.toLocalDateTime();
                LocalDateTime gmtEndTimeS = endTimeGMT.toLocalDateTime();
                Timestamp gmtStartTime = Timestamp.valueOf(gmtStartTimeS);
                Timestamp gmtEndTime = Timestamp.valueOf(gmtEndTimeS);
                
                DBConnection.addAppointment(custId, uId, apptTitle, apptDescription, apptLocation, apptContact, apptType, apptUrl, gmtStartTime, gmtEndTime, unameE);                
                errorLabel.setText("");
                Stage stage = (Stage) useCustomerButton.getScene().getWindow();
                stage.close();
                Parent root = FXMLLoader.load(getClass().getResource("ApptContinueDialogue.fxml"));
                Stage stage2 = new Stage();
                stage2.setScene(new Scene(root));
                stage2.show();
            }
            else { errorLabel.setText("You must choose a customer, appointment time, type, and description to continue."); }
        }
        catch(Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }
    
    @FXML
    public void handleGoBackButton(ActionEvent event) {
        Stage stage = (Stage) goBackButton.getScene().getWindow();
        stage.close();
    }
    
    //The following method contains a lambda expression for requirement G
    //It makes the program more efficient by effectively retrieving the correct property needed for the tableview
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            custNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
            custPhoneCol.setCellValueFactory(tf -> new SimpleStringProperty(tf.getValue().getAddress().getPhone()));
            custAddCol.setCellValueFactory(tf -> new SimpleStringProperty(tf.getValue().getAddress().getAddress1()));
            Iterable<Customer> aCustomers = DBConnection.getCustomers();
            ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
            aCustomers.forEach(allCustomers::add);
            customersFound.setItems(allCustomers);
        }
        catch(SQLException ex) { System.out.println("Error " + ex.getMessage()); }
    }
}
