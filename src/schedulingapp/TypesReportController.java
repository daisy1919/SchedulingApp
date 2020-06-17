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
public class TypesReportController implements Initializable {
    @FXML javafx.scene.control.DatePicker datePicker;
    
    @FXML
    private TableView<AppointmentType> typesFound;
    
    @FXML
    private TableColumn<AppointmentType, String> typeCol;
    
    @FXML
    private TableColumn<AppointmentType, String> typeOccurrenceCol;
    
    //This method will show the appointment types and frequencies for the month selected by the user
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
        //Populate tableview with the month's appointments' frequencies
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        typeOccurrenceCol.setCellValueFactory(new PropertyValueFactory<>("typeOccurrence"));
        Iterable<AppointmentType> mTypes = DBConnection.getApptsByType(firstOfMonth, endOfMonth);
        ObservableList<AppointmentType> monthTypes = FXCollections.observableArrayList();
        mTypes.forEach(monthTypes::add);
        typesFound.setItems(monthTypes);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
