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
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.TimeZone;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import schedulingapp.Models.Appointment;
import utils.DBConnection;

/**
 * FXML Controller class
 *
 * @author daisy
 */
public class ConsultantScheduleController implements Initializable {
    @FXML javafx.scene.control.DatePicker datePicker;
    
    @FXML
    private TableView<Appointment> appointmentsFound;
    
    @FXML
    private TableColumn<Appointment, String> custNameCol;
    
    @FXML
    private TableColumn<Appointment, String> startTimeCol;
    
    @FXML
    public void handleDatePicker(ActionEvent event) throws SQLException {
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
            endOfMonth = firstOfMonth.plusDays(30);
        }
        else if (monthNumber == 4 || monthNumber == 6 || monthNumber == 9 || monthNumber == 11) {
            endOfMonth = firstOfMonth.plusDays(29);
        }
        else if (monthNumber == 2) {
            if (yearNumber % 4 == 0) {
                endOfMonth = firstOfMonth.plusDays(28);
            }
            else {
                endOfMonth = firstOfMonth.plusDays(27);
            }
        }
        int uId = UserCredentials.getCurrentUserId();
        String uIdS = String.valueOf(uId);
        //Populate tableview with that week's appointments
        custNameCol.setCellValueFactory(tf -> new SimpleStringProperty(tf.getValue().getCustomer().getCustomerName()));
        startTimeCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        Iterable<Appointment> mAppointments = DBConnection.getConsultantSchedule(firstOfMonth, endOfMonth, uIdS);
        ObservableList<Appointment> monthAppointments = FXCollections.observableArrayList();
        mAppointments.forEach(monthAppointments::add);        
        //This converts the appointment time in the database to the user's local time to populate the tableview
        /*for(Appointment appt : monthAppointments) {
            String stTime = appt.getStartTime();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
            LocalDateTime startTime = LocalDateTime.parse(stTime, formatter); //still its original time from the db
            ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());
            ZonedDateTime zonedStartTime = ZonedDateTime.of(startTime, localZoneId);
            Instant databaseTimeToUserLocalTime = zonedStartTime.toInstant();
            appt.setZonedStartTime(databaseTimeToUserLocalTime);
            
            //parse by converting letters to space
            String zonedStartS = databaseTimeToUserLocalTime.toString();
            String subStart = zonedStartS.substring(0, 10);
            String subStart2 = zonedStartS.substring(11, 19);
            String newZonedStart = subStart + " " + subStart2;
            appt.setSZLocal(newZonedStart);
            
        }*/     
        appointmentsFound.setItems(monthAppointments);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
}
