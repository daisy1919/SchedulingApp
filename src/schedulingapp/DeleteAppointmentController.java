/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingapp;

import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import schedulingapp.Models.Appointment;
import utils.DBConnection;

/**
 * FXML Controller class
 *
 * @author daisy
 */
public class DeleteAppointmentController implements Initializable {

    @FXML javafx.scene.control.TextField searchByCustomerText;
    @FXML javafx.scene.control.Button searchByCustomerButton;
    @FXML javafx.scene.control.Button goBackButton;
    
    @FXML
    private TableView<Appointment> appointmentsFound;
    
    @FXML
    private TableColumn<Appointment, String> custNameCol;
    
    @FXML
    private TableColumn<Appointment, String> apptDateCol;
    
    @FXML
    public void handleSearchByCustomerButton(ActionEvent event) {
    
    }
    
    @FXML
    public void handleGoBackButton(ActionEvent event) {
        Stage stage = (Stage) goBackButton.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public void handleDeleteButton(ActionEvent event) throws SQLException {
        try {
            //The following lines get the selected appointment from the tableview, then delete the appointment from the database
            Appointment selectedAppointment = appointmentsFound.getSelectionModel().getSelectedItem();
            String appointmentId = selectedAppointment.getAppointmentId();
            DBConnection.deleteAppointment(appointmentId);
            //The following lines remove the deleted appointment from the tableview after the appointment has been deleted
            Iterable<Appointment> aAppts = DBConnection.getAppointments();
            ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
            aAppts.forEach(allAppointments::add);
            appointmentsFound.setItems(allAppointments);
        }
        catch (SQLException e) { System.out.println("Error " + e.getMessage()); }
    }
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            custNameCol.setCellValueFactory(tf -> new SimpleStringProperty(tf.getValue().getCustomer().getCustomerName()));
            apptDateCol.setCellValueFactory(new PropertyValueFactory<>("zonedStartTime"));
            Iterable<Appointment> aAppointments = DBConnection.getAppointments();
            ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
            aAppointments.forEach(allAppointments::add);
            
            for(Appointment appt : allAppointments) {
                String stTime = appt.getStartTime();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
                LocalDateTime startTime = LocalDateTime.parse(stTime, formatter); //still its original time from the db
                ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());
                ZonedDateTime zonedStartTime = ZonedDateTime.of(startTime, localZoneId);
                Instant databaseTimeToUserLocalTime = zonedStartTime.toInstant();
                appt.setZonedStartTime(databaseTimeToUserLocalTime);
        }
            
            appointmentsFound.setItems(allAppointments);
        } 
        catch (SQLException ex) { System.out.println("Error " + ex.getMessage()); }
    }
}
