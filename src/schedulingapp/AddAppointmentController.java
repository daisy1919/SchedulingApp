/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author daisy
 */
public class AddAppointmentController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
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
    public void handleGoBackButton(ActionEvent event) {}
    
    @FXML
    public void handleContinueButton(ActionEvent event) {}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
