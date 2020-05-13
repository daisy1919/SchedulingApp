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
public class AppointmentOptionsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    void handleAddAppointmentButton() {}
    
    @FXML
    void handleEditAppointmentButton() {}
    
    @FXML
    void handleDeleteAppointmentButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("DeleteAppointment.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    @FXML
    void handleViewCalendarButton() {}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
