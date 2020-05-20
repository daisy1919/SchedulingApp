/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingapp;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import java.time.LocalDate;
import schedulingapp.Models.Appointment;
import schedulingapp.Models.Customer;
import utils.DBConnection;

/**
 * FXML Controller class
 *
 * @author daisy
 */
public class SelectCustomerController implements Initializable {
    
    @FXML javafx.scene.control.TextField searchCustomerText;
    @FXML javafx.scene.control.Button goBackButton;
    @FXML javafx.scene.control.Label errorLabel;
    @FXML javafx.scene.control.DatePicker desiredApptDate;
    
    @FXML
    private TableView<Customer> customersFound;
    
    @FXML
    private TableColumn<Customer, String> custNameCol;
    
    @FXML
    private TableColumn<Customer, String> custPhoneCol;
    
    @FXML
    private TableColumn<Customer, String> custAddCol;
    
    @FXML
    private TableView<Appointment> availableAppts;
    
    @FXML
    private TableColumn<Appointment, String> startTimeCol;
    
    @FXML
    private TableColumn<Appointment, String> endTimeCol;

    //this window allows the user to select a date and time
    //for the table, populate start and end times for example 10:00-10:20    

    //  --> available appointment times: filter in DBConnection function getAvailableAppointments
    //      when pressing the button, close window with table view (don't simply refresh 
    //      because the customer may decide to not make the appt, so still want that slot to show as available)
    //      business hours: 8am to 6pm ??time zone??
    
    
    @FXML
    public void handleSearchCustomerButton(ActionEvent event) throws SQLException {
        String nameToSearch = searchCustomerText.getText();        
        Iterable<Customer> fCustomers = DBConnection.searchByName(nameToSearch);
        ObservableList<Customer> foundCustomers = FXCollections.observableArrayList();
        fCustomers.forEach(foundCustomers::add);   
        customersFound.setItems(foundCustomers);
    }
    
    @FXML
    public void handleDatePicker(ActionEvent event) {       
        LocalDate apptDate = desiredApptDate.getValue();
        startTimeCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endTimeCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        //custPhoneCol.setCellValueFactory(tf -> new SimpleStringProperty(tf.getValue().getAddress().getPhone()));
        Iterable<Appointment> aTimes = DBConnection.getAvailableApptTimes(apptDate);//LocalDate MAY NOT WORK FOR TIMEZONES, CHECK PROPERTIES AND USE APPROPRIATE CONTAINER FOR DATE
        ObservableList<Appointment> availableTimes = FXCollections.observableArrayList();
        aTimes.forEach(availableTimes::add);
        availableAppts.setItems(availableTimes);
    }
    
    @FXML
    public void handleUseCustomerButton(ActionEvent event) throws IOException {
        //-->need to pass selectedCustomer to next screen/set customerId for appt to this customer's ID
        Customer selectedCustomer = customersFound.getSelectionModel().getSelectedItem();
        Appointment selectedDateTime = availableAppts.getSelectionModel().getSelectedItem();
        
        //
        ////type is NOT optional, url etc. can be **check specifics in rubric/reports        
        //
        try {
            if (!(selectedCustomer == null) && !(selectedDateTime == null)) {
                errorLabel.setText("");
            }
            else { errorLabel.setText("You must choose a customer and an appointment time to continue."); }
        }
        catch(Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }
    
    @FXML
    public void handleGoBackButton(ActionEvent event) {
        Stage stage = (Stage) goBackButton.getScene().getWindow();
        stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            custNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
            custPhoneCol.setCellValueFactory(tf -> new SimpleStringProperty(tf.getValue().getAddress().getPhone()));
            custAddCol.setCellValueFactory(tf -> new SimpleStringProperty(tf.getValue().getAddress().getAddress1()));
            Iterable<Customer> aCustomers = DBConnection.getCustomers();
            ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
            aCustomers.forEach(allCustomers::add);
            customersFound.setItems(allCustomers);
        }
        catch(SQLException ex) { System.out.println("Error " + ex.getMessage()); }
    }
    
}
