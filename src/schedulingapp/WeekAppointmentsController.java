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
public class WeekAppointmentsController implements Initializable {
    @FXML javafx.scene.control.DatePicker datePicker;
    
    @FXML
    private TableView<Appointment> appointmentsFound;
    
    @FXML
    private TableColumn<Appointment, String> custNameCol;
    
    @FXML
    private TableColumn<Appointment, String> apptDateCol;
    
    @FXML
    public void handleDatePicker(ActionEvent event) {
        LocalDate selectedDate = datePicker.getValue();
        Locale userLocale = Locale.getDefault();
        System.out.println(selectedDate.getDayOfWeek());
        int dayNumber = selectedDate.getDayOfWeek().getValue();
        System.out.println(dayNumber);
        
        //change selectedDate to Sunday of the week, so get its index and then set selectedDate to selectedDate.plusDays(-2);
        
        DBConnection.getApptsByWeek(selectedDate);
        //Populate tableview with that week's appointments
        custNameCol.setCellValueFactory(tf -> new SimpleStringProperty(tf.getValue().getCustomer().getCustomerName()));
        apptDateCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        Iterable<Appointment> wAppointments = DBConnection.getApptsByWeek(selectedDate);
        ObservableList<Appointment> weekAppointments = FXCollections.observableArrayList();
        wAppointments.forEach(weekAppointments::add);
        appointmentsFound.setItems(weekAppointments);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
