/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingapp;

import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author daisy
 */
public class WeekAppointmentsController implements Initializable {
    @FXML javafx.scene.control.DatePicker datePicker;
    
    @FXML
    public void handleDatePicker(ActionEvent event) {
        LocalDate selectedDate = datePicker.getValue();
        Locale userLocale = Locale.getDefault();
        int weekNumber = selectedDate.get(WeekFields.of(userLocale).weekOfWeekBasedYear());
        
        System.out.println(selectedDate.getDayOfWeek());
        //1 is Monday, 7 is Sunday
        //add 1 to day of week to get 1 to be sunday?
        int dayNumber = selectedDate.getDayOfWeek().getValue();
        System.out.println(dayNumber);
        
        //weekNumber works
        //now need to get all appointments in that week number
        //maybe get first day of that week and get all appts in that day to plus 6 days (dates)
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
