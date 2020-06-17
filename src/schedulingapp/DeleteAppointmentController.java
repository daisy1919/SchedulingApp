/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingapp;

import java.net.URL;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;
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
        catch (SQLException e) { System.out.println("Error " + e.getMessage()); }
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
