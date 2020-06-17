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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.TimeZone;
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
        /*for(Appointment appt : foundAppointments) {
            String stTime = appt.getStartTime();
            String eTime = appt.getEndTime();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
            LocalDateTime startTime = LocalDateTime.parse(stTime, formatter);
            LocalDateTime endTime = LocalDateTime.parse(eTime, formatter);
            ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());
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
            startTimeCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
            endTimeCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));
            Iterable<Appointment> aTimes = DBConnection.getAvailableApptTimes(apptDate);
            ObservableList<Appointment> availableTimes = FXCollections.observableArrayList();
            aTimes.forEach(availableTimes::add);
            /*for(Appointment appt : availableTimes) {
                //String stTime = appt.getStartTime();
                //String eTime = appt.getEndTime();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                //LocalDateTime startTime = LocalDateTime.parse(stTime, formatter);
                //LocalDateTime endTime = LocalDateTime.parse(eTime, formatter);
                ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());
                //ZonedDateTime zonedStartTime = ZonedDateTime.of(startTime, localZoneId);
                //ZonedDateTime zonedEndTime = ZonedDateTime.of(endTime, localZoneId);
                //Instant gmtStartToLocalStart = zonedStartTime.toInstant();
                //Instant gmtEndToLocalEnd = zonedEndTime.toInstant();
                //appt.setZonedStartTime(gmtStartToLocalStart);
                //appt.setZonedEndTime(gmtEndToLocalEnd);
                //parse by converting letters to space
                /*String zonedStartS = gmtStartToLocalStart.toString();
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
        catch (SQLException e) { System.out.println("Error " + e.getMessage()); }
    }
    
    @FXML
    public void handleUpdateButton(ActionEvent event) throws SQLException, IOException {
        try {
            String appointmentId = appointmentsFound.getSelectionModel().getSelectedItem().getAppointmentId();
            String title = titleText.getText();
            String description = descriptionText.getText();
            String location = locationText.getText();
            String contact = contactText.getText();
            String apptType = typeText.getText();
            String apptUrl = urlText.getText();
            ZonedDateTime startTime = availableAppts.getSelectionModel().getSelectedItem().getStartTime();
            ZonedDateTime endTime = availableAppts.getSelectionModel().getSelectedItem().getEndTime();
            String lastUpdate = java.time.LocalDateTime.now().toString();
            String lastUpdateBy = UserCredentials.getUsername();
            
                ZoneId gmtZId = ZoneId.of("GMT");
                LocalDateTime startTimeL = startTime.toLocalDateTime();
                LocalDateTime endTimeL = endTime.toLocalDateTime();
                ZonedDateTime startTimeGMT = ZonedDateTime.of(startTimeL, gmtZId);
                ZonedDateTime endTimeGMT = ZonedDateTime.of(endTimeL, gmtZId);
                LocalDateTime gmtStartTimeS = startTimeGMT.toLocalDateTime();
                LocalDateTime gmtEndTimeS = endTimeGMT.toLocalDateTime();
                Timestamp gmtStartTime = Timestamp.valueOf(gmtStartTimeS);
                Timestamp gmtEndTime = Timestamp.valueOf(gmtEndTimeS);
            
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
            /*for(Appointment appt : availableTimes) {
                String stTime = appt.getStartTime();
                String eTime = appt.getEndTime();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime startTimee = LocalDateTime.parse(stTime, formatter);
                LocalDateTime endTimee = LocalDateTime.parse(eTime, formatter);
                ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());
                ZonedDateTime zonedStartTime = ZonedDateTime.of(startTimee, localZoneId);
                ZonedDateTime zonedEndTime = ZonedDateTime.of(endTimee, localZoneId);
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
            Stage stage = (Stage) updateButton.getScene().getWindow();
            stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("ApptEditContinueDialogue.fxml"));
            Stage stage2 = new Stage();
            stage2.setScene(new Scene(root));
            stage2.show();
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
            /*for(Appointment appt : allAppointments) {
                String stTime = appt.getStartTime();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
                LocalDateTime startTime = LocalDateTime.parse(stTime, formatter);
                ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());
                ZonedDateTime zonedStartTime = ZonedDateTime.of(startTime, localZoneId);
                Instant gmtStartToLocalStart = zonedStartTime.toInstant();
                appt.setZonedStartTime(gmtStartToLocalStart);
                //parse by converting letters to space
                String zonedStartS = gmtStartToLocalStart.toString();
                String subStart = zonedStartS.substring(0, 10);
                String subStart2 = zonedStartS.substring(11, 19);
                String newZonedStart = subStart + " " + subStart2;
                appt.setSZLocal(newZonedStart);
            }*/
            appointmentsFound.setItems(allAppointments);
        } 
        catch (SQLException ex) { System.out.println("Error " + ex.getMessage()); }
    }
}
