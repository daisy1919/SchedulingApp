/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import schedulingapp.Models.Customer;

/**
 * FXML Controller class
 *
 * @author daisy
 */
public class EditCustomer2Controller implements Initializable {
    
    @FXML javafx.scene.control.TextField firstNameText;
    @FXML javafx.scene.control.TextField lastNameText;
    @FXML javafx.scene.control.TextField addressText;
    @FXML javafx.scene.control.TextField address2Text;
    @FXML javafx.scene.control.TextField cityText;
    @FXML javafx.scene.control.TextField zipText;
    @FXML javafx.scene.control.TextField countryText;
    @FXML javafx.scene.control.TextField phoneText;

    private static Customer selectedCustomer;
    
    public static Customer getSelectedCustomer() { return selectedCustomer; }
    
    public static void setSelectedCustomer(Customer customer) { selectedCustomer = customer; }

    @FXML 
    void handleUpdateCustomerButton() {        
        //customerToAdd.setCreateDate(LocalDateTime.parse(sqlRs.getString("createDate"), formatter));   
        //customerToAdd.setLastUpdateBy; get user from static fnctn           
        //Customer selectedCustomer = customersFound.getSelectionModel().getSelectedItem();
        //System.out.println(selectedCustomer);
        //call function from DBConnection that updates the selected customer
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        
        //split customerName into two strings
        //set firstNameText to first
        //when name gets updated in db, need to combine back into one string
        
        addressText.setText(selectedCustomer.getAddress().getAddress1());
        address2Text.setText(selectedCustomer.getAddress().getAddress2());
        cityText.setText(selectedCustomer.getAddress().getCity().getCity());
        zipText.setText(selectedCustomer.getAddress().getPostalCode());
        countryText.setText(selectedCustomer.getAddress().getCity().getCountry().getCountry());
        phoneText.setText(selectedCustomer.getAddress().getPhone());
    }    
    
}
