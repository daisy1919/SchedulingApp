/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import utils.DBConnection;

/**
 * FXML Controller class
 *
 * @author daisy
 */
public class CreateCustomerController implements Initializable {

    @FXML javafx.scene.control.Button backButton;
    @FXML javafx.scene.control.TextField addFirstNameText;
    @FXML javafx.scene.control.TextField addLastNameText;
    @FXML javafx.scene.control.TextField addAddressText;
    @FXML javafx.scene.control.TextField addAddress2Text;
    @FXML javafx.scene.control.TextField addZipText;
    @FXML javafx.scene.control.TextField addCityText;
    @FXML javafx.scene.control.TextField addCountryText;
    @FXML javafx.scene.control.TextField addPhoneText;
    
    @FXML
    void handleAddButton(ActionEvent event) {    
        String custName = this.addFirstNameText.getText() + " " + this.addLastNameText.getText();
        String custAddress1 = this.addAddressText.getText();
        String custAddress2;
        if (this.addAddress2Text.getText() == null) { custAddress2 = ""; }
        else{ custAddress2 = this.addAddress2Text.getText(); }        
        String custCity = this.addCityText.getText();
        String custZip = this.addZipText.getText();
        String custCountry = this.addCountryText.getText();
        String custPhone = this.addPhoneText.getText();
        
        DBConnection.addCustomer(custName, custAddress1, custAddress2, custCity, custZip, custCountry, custPhone);
    }
    
    @FXML
    void handleBackButton(ActionEvent event) {        
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
