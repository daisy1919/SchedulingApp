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
public class CalendarOptionsController implements Initializable {

    @FXML
    public void handleWeekButton(ActionEvent event) throws IOException {
        try {
                Parent root = FXMLLoader.load(getClass().getResource("WeekAppointments.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            }
        catch(IOException e) { System.out.println("Error " + e.getMessage()); }
    }
    
    @FXML
    public void handleMonthButton(ActionEvent event) {
    
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
