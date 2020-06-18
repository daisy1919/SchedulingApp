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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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
import schedulingapp.Models.Appointment;
import schedulingapp.Models.Customer;
import utils.DBConnection;

/**
 * FXML Controller class
 *
 * @author daisy
 */
public class EditAppointmentController implements Initializable {
    @FXML javafx.scene.control.Button goBackButton;
    @FXML javafx.scene.control.Button updateButton;
    @FXML javafx.scene.control.DatePicker desiredApptDate;
    @FXML javafx.scene.control.TextField searchCustomerText;
    @FXML javafx.scene.control.TextField titleText;
    @FXML javafx.scene.control.TextField descriptionText;
    @FXML javafx.scene.control.TextField locationText;
    @FXML javafx.scene.control.TextField contactText;
    @FXML javafx.scene.control.TextField typeText;
    @FXML javafx.scene.control.TextField urlText;
    @FXML javafx.scene.control.Label errorLabel;
    
    @FXML
    private TableView<Appointment> appointmentsFound;
    
    @FXML
    private TableColumn<Appointment, String> custNameCol;
    
    @FXML
    private TableColumn<Appointment, String> apptDateCol;
    
    @FXML
    private TableView<Appointment> availableAppts;
    
    @FXML
    private TableColumn<Appointment, String> startTimeCol;
    
    @FXML
    private TableColumn<Appointment, String> endTimeCol;
    
    @FXML
    public void handleSearchCustomerButton(ActionEvent event) throws SQLException {
        String nameToSearch = searchCustomerText.getText();
        Iterable<Appointment> fAppointments = DBConnection.getAppointments();
        ObservableList<Appointment> foundAppointments = FXCollections.observableArrayList();
        fAppointments.forEach(foundAppointments::add);
        foundAppointments.removeIf(e -> !(e.getCustomer().getCustomerName().toLowerCase().contains(nameToSearch.toLowerCase())));
        for(Appointment appt : foundAppointments) {
                //parse by converting letters to space
                ZonedDateTime startZL = appt.getStartTime();
                String zonedStartS = startZL.toString();
                String subStart = zonedStartS.substring(0, 10);
                String subStart2 = zonedStartS.substring(11, 16);
                String newZonedStart = subStart + " " + subStart2;
                appt.setSZLocal(newZonedStart);
            }
        appointmentsFound.setItems(foundAppointments);
    }
    
    @FXML
    public void handleApptSelected() {
        Appointment selectedAppointment = appointmentsFound.getSelectionModel().getSelectedItem();
        titleText.setText(selectedAppointment.getTitle());
        descriptionText.setText(selectedAppointment.getDescription());
        locationText.setText(selectedAppointment.getLocation());
        contactText.setText(selectedAppointment.getContact());
        typeText.setText(selectedAppointment.getType());
        urlText.setText(selectedAppointment.getUrl());
    }
    
    //The following is for requirement F to prevent overlapping appointments.
    //The following method allows the user to choose a new appointment time, but it will not show appointment times that the currently logged in user has another appointment.
    @FXML
    public void handleDatePicker(ActionEvent event) throws SQLException {       
        try {
            LocalDate apptDate = desiredApptDate.getValue();
            startTimeCol.setCellValueFactory(new PropertyValueFactory<>("sZLocal"));
            endTimeCol.setCellValueFactory(new PropertyValueFactory<>("eZLocal"));
            Iterable<Appointment> aTimes = DBConnection.getAvailableApptTimes(apptDate);
            ObservableList<Appointment> availableTimes = FXCollections.observableArrayList();
            aTimes.forEach(availableTimes::add);
            for(Appointment appt : availableTimes) {
                //parse by converting letters to space
                ZonedDateTime startZL = appt.getStartTime();
                ZonedDateTime endZL = appt.getEndTime();
                String zonedStartS = startZL.toString();
                String zonedEndS = endZL.toString();
                String subStart = zonedStartS.substring(0, 10);
                String subEnd = zonedEndS.substring(0, 10);
                String subStart2 = zonedStartS.substring(11, 16);
                String subEnd2 = zonedEndS.substring(11, 16);
                String newZonedStart = subStart + " " + subStart2;
                String newZonedEnd = subEnd + " " + subEnd2;
                appt.setSZLocal(newZonedStart);
                appt.setEZLocal(newZonedEnd);
            }
            availableAppts.setItems(availableTimes);
        }
        catch (SQLException e) { System.out.println("Error " + e.getMessage()); }
    }
    
    @FXML
    public void handleUpdateButton(ActionEvent event) throws SQLException, IOException {
        try {
            
            Appointment apptSel = availableAppts.getSelectionModel().getSelectedItem();
            Customer customerSel = appointmentsFound.getSelectionModel().getSelectedItem().getCustomer();
            String appointmentId = new String();
            ZonedDateTime startTime = ZonedDateTime.now();
            ZonedDateTime endTime = ZonedDateTime.now();
            String title = new String();
            String description = new String();
            String location = new String();
            String contact = new String();
            String apptType = new String();
            String apptUrl = new String();
            
        if (!(customerSel == null) && !(apptSel == null)) {
            appointmentId = appointmentsFound.getSelectionModel().getSelectedItem().getAppointmentId();
            startTime = apptSel.getStartTime();
            endTime = apptSel.getEndTime();
            title = titleText.getText();
            description = descriptionText.getText();
            location = locationText.getText();
            contact = contactText.getText();
            apptType = typeText.getText();
            apptUrl = urlText.getText();
        }
                            
            if (!(customerSel == null) && !(apptSel == null) && !(apptType.isEmpty()) && !(description.isEmpty())) {
                
                String lastUpdate = java.time.LocalDateTime.now().toString();
                String lastUpdateBy = UserCredentials.getUsername();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                ZoneId gmtZId = ZoneId.of("GMT");
                ZoneId localZId = ZoneId.systemDefault();
                LocalDateTime startTimeL = startTime.toLocalDateTime();
                LocalDateTime endTimeL = endTime.toLocalDateTime();
                ZonedDateTime startTimeLocal = ZonedDateTime.of(startTimeL, localZId);
                ZonedDateTime ss = startTimeLocal.withZoneSameInstant(gmtZId);
                ZonedDateTime endTimeLocal = ZonedDateTime.of(endTimeL, localZId);
                ZonedDateTime ee = endTimeLocal.withZoneSameInstant(gmtZId);

                String startInstant = ss.toInstant().toString();
                String endInstant = ee.toInstant().toString();

                String subS = startInstant.substring(0, 10);
                String subS2 = startInstant.substring(11, 19);
                String subE = endInstant.substring(0, 10);
                String subE2 = endInstant.substring(11, 19);
                String nSI = subS + " " + subS2;
                String nEI = subE + " " + subE2;

                LocalDateTime startI = LocalDateTime.parse(nSI, formatter);
                LocalDateTime endI = LocalDateTime.parse(nEI, formatter);

                Timestamp gmtStartTime = Timestamp.valueOf(startI);;
                Timestamp gmtEndTime = Timestamp.valueOf(endI);
                
                DBConnection.updateAppointment(appointmentId, title, description, location, contact, apptType, apptUrl, gmtStartTime, gmtEndTime, lastUpdate, lastUpdateBy);
                searchCustomerText.clear();
                Iterable<Appointment> fAppointments = DBConnection.getAppointments();
                ObservableList<Appointment> foundAppointments = FXCollections.observableArrayList();
                fAppointments.forEach(foundAppointments::add);
                appointmentsFound.setItems(foundAppointments);
                LocalDate apptDate = desiredApptDate.getValue();
                startTimeCol.setCellValueFactory(new PropertyValueFactory<>("sZLocal"));
                endTimeCol.setCellValueFactory(new PropertyValueFactory<>("eZLocal"));
                Iterable<Appointment> aTimes = DBConnection.getAvailableApptTimes(apptDate);
                ObservableList<Appointment> availableTimes = FXCollections.observableArrayList();
                aTimes.forEach(availableTimes::add);
                for(Appointment appt : availableTimes) {
                    //parse by converting letters to space
                    ZonedDateTime startZL = appt.getStartTime();
                    ZonedDateTime endZL = appt.getEndTime();
                    String zonedStartS = startZL.toString();
                    String zonedEndS = endZL.toString();
                    String subStart = zonedStartS.substring(0, 10);
                    String subEnd = zonedEndS.substring(0, 10);
                    String subStart2 = zonedStartS.substring(11, 16);
                    String subEnd2 = zonedEndS.substring(11, 16);
                    String newZonedStart = subStart + " " + subStart2;
                    String newZonedEnd = subEnd + " " + subEnd2;
                    appt.setSZLocal(newZonedStart);
                    appt.setEZLocal(newZonedEnd);
                }
                availableAppts.setItems(availableTimes);
                Stage stage = (Stage) updateButton.getScene().getWindow();
                stage.close();
                Parent root = FXMLLoader.load(getClass().getResource("ApptEditContinueDialogue.fxml"));
                Stage stage2 = new Stage();
                stage2.setScene(new Scene(root));
                stage2.show();
            }
            
            else {
                errorLabel.setText("You must choose a customer, appointment time, type, and description to continue.");
            }
            
        }
        catch(SQLException e) { System.out.println("Error " + e.getMessage()); }
    }
    
    @FXML
    public void handleGoBackButton(ActionEvent event) {
        Stage stage = (Stage) goBackButton.getScene().getWindow();
        stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            custNameCol.setCellValueFactory(tf -> new SimpleStringProperty(tf.getValue().getCustomer().getCustomerName()));
            apptDateCol.setCellValueFactory(new PropertyValueFactory<>("sZLocal"));
            Iterable<Appointment> aAppointments = DBConnection.getAppointments();
            ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
            aAppointments.forEach(allAppointments::add);
            for(Appointment appt : allAppointments) {
                //parse by converting letters to space
                ZonedDateTime startZL = appt.getStartTime();
                String zonedStartS = startZL.toString();
                String subStart = zonedStartS.substring(0, 10);
                String subStart2 = zonedStartS.substring(11, 16);
                String newZonedStart = subStart + " " + subStart2;
                appt.setSZLocal(newZonedStart);
        }
            appointmentsFound.setItems(allAppointments);
        } 
        catch (SQLException ex) { System.out.println("Error " + ex.getMessage()); }
    }
}
