/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingapp;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import schedulingapp.Models.Customer;
import utils.DBConnection;

/**
 * FXML Controller class
 *
 * @author daisy
 */
public class EditCustomer2Controller implements Initializable {
    
    @FXML javafx.scene.control.TextField firstNameText;
    @FXML javafx.scene.control.TextField middleInitText;
    @FXML javafx.scene.control.TextField lastNameText;
    @FXML javafx.scene.control.TextField addressText;
    @FXML javafx.scene.control.TextField address2Text;
    @FXML javafx.scene.control.TextField cityText;
    @FXML javafx.scene.control.TextField zipText;
    @FXML javafx.scene.control.TextField countryText;
    @FXML javafx.scene.control.TextField phoneText;
    @FXML javafx.scene.control.Button goBackButton;
    
    //@FXML javafx.scene.control.TextField searchCustomerText;
    
    private static Customer selectedCustomer;
    
    public static Customer getSelectedCustomer() { return selectedCustomer; }
    
    public static void setSelectedCustomer(Customer customer) { selectedCustomer = customer; }

    @FXML 
    void handleUpdateCustomerButton(ActionEvent event) throws SQLException {        
        try {            
            String custName = null;
            if(this.middleInitText.getText().isEmpty() && !this.firstNameText.getText().isEmpty() && !this.lastNameText.getText().isEmpty()) {                
                custName = this.firstNameText.getText() + " " + this.lastNameText.getText();
            }
            else if (!this.firstNameText.getText().isEmpty() && !this.middleInitText.getText().isEmpty() && !this.lastNameText.getText().isEmpty()) {
                custName = this.firstNameText.getText() + " " + this.middleInitText.getText() + " " + this.lastNameText.getText();
            }            
            String lastUpdate = java.time.LocalDateTime.now().toString();
            String lastUpdateBy = UserCredentials.getUsername();
            String custId = selectedCustomer.getCustId();
            String add1 = this.addressText.getText();
            String add2 = this.address2Text.getText();
            String zip = this.zipText.getText();
            String phone = this.phoneText.getText();
            String addId = selectedCustomer.getAddress().getAddressId();
            String city = this.cityText.getText();
            String cityId = selectedCustomer.getAddress().getCity().getCityId();            
            String country = this.countryText.getText();
            String countryId = selectedCustomer.getAddress().getCity().getCountry().getCountryId();
            DBConnection.updateCustomer(selectedCustomer, custName, lastUpdate, lastUpdateBy, custId, add1, add2, zip, phone, addId, city, cityId, country, countryId);
            //-->***need to refresh tableview in previous scene on button click or exit this and previous window
        }
        catch (SQLException ex) { System.out.println("Error " + ex.getMessage()); }
    }
    
    @FXML
    void handleGoBackButton(ActionEvent event) {
        Stage stage = (Stage) goBackButton.getScene().getWindow();
        stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        //when name gets updated in db, need to combine back into one string        
        String fullName = selectedCustomer.getCustomerName();
        String[] split = fullName.split(" ");        
        if(split.length == 3) {
            firstNameText.setText(split[0]);
            middleInitText.setText(split[1]);
            lastNameText.setText(split[2]);
        }
        else if (split.length == 2) {
            firstNameText.setText(split[0]);
            lastNameText.setText(split[1]);
        }
        addressText.setText(selectedCustomer.getAddress().getAddress1());
        address2Text.setText(selectedCustomer.getAddress().getAddress2());
        cityText.setText(selectedCustomer.getAddress().getCity().getCity());
        zipText.setText(selectedCustomer.getAddress().getPostalCode());
        countryText.setText(selectedCustomer.getAddress().getCity().getCountry().getCountry());
        phoneText.setText(selectedCustomer.getAddress().getPhone());
    }    
    
}
