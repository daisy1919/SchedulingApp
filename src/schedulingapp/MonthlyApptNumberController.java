/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingapp;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import schedulingapp.Models.AppointmentType;
import utils.DBConnection;

/**
 * FXML Controller class
 *
 * @author daisy
 */
public class MonthlyApptNumberController implements Initializable {

    @FXML javafx.scene.control.DatePicker datePicker;
    @FXML javafx.scene.control.Label numberApptsLabel;
    
    @FXML
    public void handleDatePicker(ActionEvent event) {
        LocalDate selectedDate = datePicker.getValue();
        int monthNumber = selectedDate.getMonth().getValue();
        int yearNumber = selectedDate.getYear();
        String dateToParse;
        if (monthNumber < 10) {
            dateToParse = String.valueOf(yearNumber) + "-0" + String.valueOf(monthNumber) + "-01";
        }
        else {
            dateToParse = String.valueOf(yearNumber) + "-" + String.valueOf(monthNumber) + "-01";
        }        
        LocalDate firstOfMonth = LocalDate.parse(dateToParse);
        LocalDate endOfMonth = LocalDate.of(2020, 01, 01);
        if (monthNumber == 1 || monthNumber == 3 || monthNumber == 5 || monthNumber == 7 || monthNumber == 8 || monthNumber == 10 || monthNumber == 12) {
            endOfMonth = firstOfMonth.plusDays(31);
        }
        else if (monthNumber == 4 || monthNumber == 6 || monthNumber == 9 || monthNumber == 11) {
            endOfMonth = firstOfMonth.plusDays(30);
        }
        else if (monthNumber == 2) {
            if (yearNumber % 4 == 0) {
                endOfMonth = firstOfMonth.plusDays(29);
            }
            else {
                endOfMonth = firstOfMonth.plusDays(28);
            }
        }
        int numberOfAppts = DBConnection.numberOfMonthAppts(firstOfMonth, endOfMonth);
        numberApptsLabel.setText(String.valueOf(numberOfAppts));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
