/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entities.Personne;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.PersonneService;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author skanderzouaoui
 */
public class AffichageController implements Initializable {

    @FXML
    private TableView<User> tableview;
    @FXML
    private TableColumn<User, String> nom;
    @FXML
    private TableColumn<User, String> prenom;
    @FXML
    private TableColumn<User, Integer> id;
    @FXML
    private TableColumn<User, String> email;
    @FXML
    private TableColumn<User, String> telephone;
    @FXML
    private TableColumn<User, String> role;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UserService ps = new UserService();
        List<User> users = ps.recuperer();
        ObservableList list = FXCollections.observableArrayList(users);
        tableview.setItems(list);
        
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        telephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        role.setCellValueFactory(new PropertyValueFactory<>("role"));
    }    

    
    
}
