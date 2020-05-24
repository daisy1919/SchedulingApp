/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingapp;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
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
    
    @FXML
    public void handleDatePicker(ActionEvent event) throws SQLException {       
        try {
            LocalDate apptDate = desiredApptDate.getValue();
            startTimeCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
            endTimeCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));
            Iterable<Appointment> aTimes = DBConnection.getAvailableApptTimes(apptDate);
            ObservableList<Appointment> availableTimes = FXCollections.observableArrayList();
            aTimes.forEach(availableTimes::add);
            availableAppts.setItems(availableTimes);
        }
        catch (SQLException e) { System.out.println("Error " + e.getMessage()); }
    }
    
    @FXML
    public void handleUpdateButton(ActionEvent event) throws SQLException {
        try {
            String appointmentId = appointmentsFound.getSelectionModel().getSelectedItem().getAppointmentId();
            String title = titleText.getText();
            String description = descriptionText.getText();
            String location = locationText.getText();
            String contact = contactText.getText();
            String apptType = typeText.getText();
            String apptUrl = urlText.getText();
            String startTime = availableAppts.getSelectionModel().getSelectedItem().getStartTime();
            String endTime = availableAppts.getSelectionModel().getSelectedItem().getEndTime();
            String lastUpdate = java.time.LocalDateTime.now().toString();
            String lastUpdateBy = UserCredentials.getUsername();
            DBConnection.updateAppointment(appointmentId, title, description, location, contact, apptType, apptUrl, startTime, endTime, lastUpdate, lastUpdateBy);
            
            //refresh table views
            
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
            apptDateCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
            Iterable<Appointment> aAppointments = DBConnection.getAppointments();
            ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
            aAppointments.forEach(allAppointments::add);
            appointmentsFound.setItems(allAppointments);
        } 
        catch (SQLException ex) { System.out.println("Error " + ex.getMessage()); }
    }
}
