/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingapp;

import java.net.URL;
import java.sql.SQLException;
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
import schedulingapp.Models.Customer;
import utils.DBConnection;

/**
 * FXML Controller class
 *
 * @author daisy
 */
public class DeleteCustomerController implements Initializable {

    @FXML javafx.scene.control.TextField searchCustomerText;
    @FXML javafx.scene.control.Button goBackButton;
    
    @FXML
    private TableView<Customer> customersFound;
    
    @FXML
    private TableColumn<Customer, String> custNameCol;
    
    @FXML
    private TableColumn<Customer, String> custPhoneCol;
    
    @FXML
    private TableColumn<Customer, String> custAddCol;
    
    @FXML 
    void handleSearchCustomerButton(ActionEvent event) throws SQLException {        
        String nameToSearch = searchCustomerText.getText();        
        Iterable<Customer> fCustomers = DBConnection.searchByName(nameToSearch);
        ObservableList<Customer> foundCustomers = FXCollections.observableArrayList();
        fCustomers.forEach(foundCustomers::add);   
        customersFound.setItems(foundCustomers);
    }

    @FXML
    void handleDeleteButton(ActionEvent event) {
        try {
            //get selected customer from table view, then delete customer and associated info from db 
            Customer selectedCustomer = customersFound.getSelectionModel().getSelectedItem();
            String custId = selectedCustomer.getCustId();
            String addressId = selectedCustomer.getAddId();
            String cityId = selectedCustomer.getAddress().getCityId();
            String countryId = selectedCustomer.getAddress().getCity().getCountryId();
            DBConnection.deleteCustomer(custId, addressId, cityId, countryId);
            //after customer has been deleted, this removes them from the tableview
            Iterable<Customer> aCustomers = DBConnection.getCustomers();
            ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
            aCustomers.forEach(allCustomers::add); 
            customersFound.setItems(allCustomers);
        }
        catch(SQLException ex) { System.out.println("Error " + ex.getMessage()); }
    }
    
    @FXML
    void handleGoBackButton(ActionEvent event) {
        Stage stage = (Stage) goBackButton.getScene().getWindow();
        stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
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
