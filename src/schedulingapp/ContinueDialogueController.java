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

/**
 * FXML Controller class
 *
 * @author daisy
 */
public class ContinueDialogueController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML javafx.scene.control.Button addAnotherButton;
    @FXML javafx.scene.control.Button goBackButton;
    
    @FXML
    void handleAddAnotherButton(ActionEvent event) throws IOException {
        try {
            Stage stage = (Stage) addAnotherButton.getScene().getWindow();
            stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("CreateCustomer.fxml"));
            Stage stage2 = new Stage();
            stage2.setScene(new Scene(root));
            stage2.show();
        }
        catch (IOException e) { System.out.println("Error " + e.getMessage()); }
    }
    
    @FXML
    void handleGoBackButton(ActionEvent event) {
        Stage stage = (Stage) goBackButton.getScene().getWindow();
        stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
