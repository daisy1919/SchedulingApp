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
public class ReportOptionsController implements Initializable {
    
    @FXML
    public void handleApptTypeButton(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("TypesReport.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch(IOException e) { System.out.println("Error " + e.getMessage()); }
    }
    
    @FXML
    public void handleConsultantButton(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ConsultantSchedule.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch(IOException e) { System.out.println("Error " + e.getMessage()); }    
    }
    
    @FXML
    public void handleNumberApptsButton(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("MonthlyApptNumber.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch(IOException e) { System.out.println("Error " + e.getMessage()); }   
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
}
