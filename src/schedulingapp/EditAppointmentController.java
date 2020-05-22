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
    public void handleSearchCustomerButton(ActionEvent event) {
        
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
    public void handleUseCustomerButton(ActionEvent event) {
        
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
