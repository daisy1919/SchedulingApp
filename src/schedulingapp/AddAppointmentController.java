/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingapp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import schedulingapp.Models.Appointment;
import schedulingapp.Models.Customer;

/**
 * FXML Controller class
 *
 * @author daisy
 */
public class AddAppointmentController implements Initializable {
    
    //this window allows the user to select a date and time
    //for the table, populate start and end times for example 10:00-10:20
        
    @FXML javafx.scene.control.TextField searchCustomerText;
    @FXML javafx.scene.control.Button goBackButton;
    
    @FXML
    private TableView<Appointment> availableAppts;
    
    @FXML
    private TableColumn/*<Customer, String>*/ apptDate;
    
    @FXML
    private TableColumn/*<Customer, String>*/ apptTime;
    
    //  --> available appointment times: filter in DBConnection function getAvailableAppointments
    //      i.e. select * from appointment where start time > 8am and end time < 5:45pm
    //      don't show those appts in the list
    //      fill the rest of the list with 15 minute time slots
    //      on selecting a time slot, press select this appointment time
    //      when pressing the button, close window with table view (don't simply refresh 
    //      because the customer may decide to not make the appt, so still want that slot to show as available)
    //      business hours: 8am to 6pm ??time zone??
    //      filter out appointments that already exist
    //      show available appointment times in tableview
    //      
    
    @FXML
    public void handleGoBackButton(ActionEvent event) {
        Stage stage = (Stage) goBackButton.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public void handleContinueButton(ActionEvent event) {
    //need to get date and time from selection on calendar/tableview        
    //Customer selectedCustomer = customersFound.getSelectionModel().getSelectedItem();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AddAppointment2.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch(IOException e) {
            System.out.println("Error loading window");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
