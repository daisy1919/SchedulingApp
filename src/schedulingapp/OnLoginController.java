package schedulingapp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author daisy
 */
public class OnLoginController implements Initializable {

    @FXML
    private Button customerButton;
    
    @FXML
    void handleCustomerButton(ActionEvent event) {
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("CustomerOptions.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch(IOException e) {
            System.out.println("Error loading window");
        }
        
    }
    
    @FXML
    void handleAppointmentButton(ActionEvent event) {
        
        
        
    }
    
    @FXML
    void handleCalendarButton(ActionEvent event) {
        
        
        
    }
    
    @FXML
    void handleReportsButton(ActionEvent event) {
        
        
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
