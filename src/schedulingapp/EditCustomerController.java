/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingapp;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import schedulingapp.Models.Customer;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import schedulingapp.Models.Address;
import utils.DBConnection;

/**
 * FXML Controller class
 *
 * @author daisy
 */
public class EditCustomerController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML javafx.scene.control.TextField searchCustomerText;
    @FXML javafx.scene.control.Button goBackButton;
    
    @FXML
    private TableView<Customer> customersFound;
    
    @FXML
    private TableColumn<Customer, String> custNameCol;
    
    @FXML
    private TableColumn<Customer, String> custPhoneCol;
    
    @FXML
    private TableColumn<Customer, String> custAddCol;
    
    @FXML 
    void handleSearchCustomerButton(ActionEvent event) throws SQLException {        
        String nameToSearch = searchCustomerText.getText();        
        Iterable<Customer> fCustomers = DBConnection.searchByName(nameToSearch);
        ObservableList<Customer> foundCustomers = FXCollections.observableArrayList();
        fCustomers.forEach(foundCustomers::add);   
        customersFound.setItems(foundCustomers);
    }
    
    @FXML 
    void handleUpdateSelectedButton(ActionEvent event) throws SQLException {        
        Customer selectedCustomer = customersFound.getSelectionModel().getSelectedItem();
        EditCustomer2Controller.setSelectedCustomer(selectedCustomer);
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("EditCustomer2.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch(IOException e) {
            System.out.println("Error " + e.getMessage());
        }
        
        //handleClearPartSearch();
        //searchPartsText.clear();
    }
    
    @FXML
    void handleGoBackButton(ActionEvent event) {
        Stage stage = (Stage) goBackButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
            custNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
            custPhoneCol.setCellValueFactory(tf -> new SimpleStringProperty(tf.getValue().getAddress().getPhone()));
            custAddCol.setCellValueFactory(tf -> new SimpleStringProperty(tf.getValue().getAddress().getAddress1()));
    }    
    
}
