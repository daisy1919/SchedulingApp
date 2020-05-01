/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import schedulingapp.Models.Customer;
import javafx.scene.control.TableView;

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
    void handleSearchCustomerButton(ActionEvent event) {        
        String custToFind = searchCustomerText.getText();
        ObservableList<Customer> foundCustomers; /*= InventoryManager.getInventory().lookupPart(partToFind)*/;        
        //customersFound.setItems(foundCustomers);        
    }
    
    @FXML 
    void handleUpdateCustomerButton() {
        
        //customerToAdd.setCreateDate(LocalDateTime.parse(sqlRs.getString("createDate"), formatter));   
        //customerToAdd.setLastUpdateBy; get user from static fnctn    
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //the following is from a previous project for ideas
        /*
        productIDC.setCellValueFactory(new PropertyValueFactory<>("pID"));
        productNameC.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInvC.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productCostC.setCellValueFactory(new PropertyValueFactory<>("price"));

        partList.setItems(InventoryManager.getInventory().getAllParts());
        productList.setItems(InventoryManager.getInventory().getAllProducts());
        
        */        
    }    
    
}
