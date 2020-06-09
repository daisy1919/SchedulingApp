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
public class ApptWarningController implements Initializable {

    @FXML javafx.scene.control.Button apptAckButton;
    
    @FXML
    public void handleApptAckButton(ActionEvent event) throws IOException {
        Stage stage2 = (Stage) apptAckButton.getScene().getWindow();
        stage2.close();
        Parent root = FXMLLoader.load(getClass().getResource("OnLogin.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
