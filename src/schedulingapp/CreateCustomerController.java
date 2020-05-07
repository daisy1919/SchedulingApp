/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingapp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    @FXML javafx.scene.control.TextField addMidText;
    @FXML javafx.scene.control.TextField addLastNameText;
    @FXML javafx.scene.control.TextField addAddressText;
    @FXML javafx.scene.control.TextField addAddress2Text;
    @FXML javafx.scene.control.TextField addZipText;
    @FXML javafx.scene.control.TextField addCityText;
    @FXML javafx.scene.control.TextField addCountryText;
    @FXML javafx.scene.control.TextField addPhoneText;
    @FXML javafx.scene.control.Label errorMessageLabel;
    
    @FXML
    void handleAddButton(ActionEvent event) throws IOException {    
        try {
            //get data that was entered
            String custName = null;
            if(this.addMidText.getText().isEmpty() && !this.addFirstNameText.getText().isEmpty() && !this.addLastNameText.getText().isEmpty()) {                
                custName = this.addFirstNameText.getText() + " " + this.addLastNameText.getText();
            }
            else if (!this.addFirstNameText.getText().isEmpty() && !this.addMidText.getText().isEmpty() && !this.addLastNameText.getText().isEmpty()) {
                custName = this.addFirstNameText.getText() + " " + this.addMidText.getText() + " " + this.addLastNameText.getText();
            }
            String custAddress1 = this.addAddressText.getText();
            String custAddress2;
            if (this.addAddress2Text.getText() == null) { custAddress2 = ""; }
            else{ custAddress2 = this.addAddress2Text.getText(); }        
            String custCity = this.addCityText.getText();
            String custZip = this.addZipText.getText();
            String custCountry = this.addCountryText.getText();
            String custPhone = this.addPhoneText.getText();
            //add customer using entered data unless fields are empty
            if (custName == null || custAddress1.isEmpty()
                    || custCity.isEmpty() || custZip.isEmpty()
                    || custCountry.isEmpty() || custPhone.isEmpty()) {
                errorMessageLabel.setText("Enter all information to continue.");                                
            }
            else {            
                String unameE = UserCredentials.getUsername();
                DBConnection.addCustomer(custName, custAddress1, custAddress2, custCity, custZip, custCountry, custPhone, unameE);
                //clear data from form and ask what the user wants to do next
                addFirstNameText.clear();
                addMidText.clear();
                addLastNameText.clear();
                addAddressText.clear();
                addAddress2Text.clear();
                addCityText.clear();
                addZipText.clear();
                addCountryText.clear();
                addPhoneText.clear();
                errorMessageLabel.setText("");
                Parent root = FXMLLoader.load(getClass().getResource("ContinueDialogue.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();            
            }            
        }
        catch (IOException ioEx) { System.out.println("Error " + ioEx.getMessage()); }
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
