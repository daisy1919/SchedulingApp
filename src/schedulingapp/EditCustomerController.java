/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingapp;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import schedulingapp.Models.Customer;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import schedulingapp.Models.Address;
import utils.DBConnection;

/**
 * FXML Controller class
 *
 * @author daisy
 */
public class EditCustomerController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML javafx.scene.control.TextField searchCustomerText;
    
    @FXML
    private TableView<Customer> customersFound;
    
    @FXML
    private TableColumn custNameCol;
    
    @FXML
    private TableColumn custPhoneCol;
    
    @FXML
    private TableColumn custAddCol;
    
    @FXML 
    void handleSearchCustomerButton(ActionEvent event) throws SQLException {        
        String nameToSearch = searchCustomerText.getText();        
        Iterable<Customer> fCustomers = DBConnection.searchByName(nameToSearch);
        ObservableList<Customer> foundCustomers = FXCollections.observableArrayList();
        fCustomers.forEach(foundCustomers::add);   
        customersFound.setItems(foundCustomers);
    }
    
    @FXML 
    void handleUpdateCustomerButton() {        
        //customerToAdd.setCreateDate(LocalDateTime.parse(sqlRs.getString("createDate"), formatter));   
        //customerToAdd.setLastUpdateBy; get user from static fnctn            
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            //custNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
            custNameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("cName"));
            custPhoneCol.setCellValueFactory(new PropertyValueFactory<Address, String>("phone"));
            custAddCol.setCellValueFactory(new PropertyValueFactory<Address, String>("address1"));
            
            Iterable<Customer> fCustomers = DBConnection.getCustomers();
            ObservableList<Customer> foundCustomers = FXCollections.observableArrayList();
            fCustomers.forEach(foundCustomers::add);
            customersFound.setItems(foundCustomers);  
        }
        catch(SQLException sqEx) { System.out.println("Error " + sqEx.getMessage()); }
    }    
    
}
