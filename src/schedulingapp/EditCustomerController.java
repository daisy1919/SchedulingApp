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
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
    void handleUpdateCustomerButton() {        
        //customerToAdd.setCreateDate(LocalDateTime.parse(sqlRs.getString("createDate"), formatter));   
        //customerToAdd.setLastUpdateBy; get user from static fnctn            
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*try {*/
            custNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
            custPhoneCol.setCellValueFactory(tf -> new SimpleStringProperty(tf.getValue().getAddress().getPhone()));//new PropertyValueFactory<>("phone"));
            custAddCol.setCellValueFactory(tf -> new SimpleStringProperty(tf.getValue().getAddress().getAddress1())); //(new PropertyValueFactory<>("address1"));
            
            //Iterable<Customer> fCustomers = DBConnection.getCustomers();
            //ObservableList<Customer> foundCustomers = FXCollections.observableArrayList();
            //fCustomers.forEach(foundCustomers::add);
            //customersFound.setItems(foundCustomers);       
        //}
        //catch(SQLException sqEx) { System.out.println("Error " + sqEx.getMessage()); }
    }    
    
}
