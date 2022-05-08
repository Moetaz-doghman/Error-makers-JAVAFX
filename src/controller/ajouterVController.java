/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import entity.Vehicule;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import service.VehiculeServices;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author doghm
 */
public class ajouterVController implements Initializable {

    @FXML
    private Button listUserButton;
    @FXML
    private Button ListDemandeButton;
    @FXML
    private Button btnev1;
    @FXML
    private Button btnjo;
    @FXML
    private Button btnevent;
    @FXML
    private Button Reclamation;
    @FXML
    private Button Boutique;
    @FXML
    private Button addboutique;
    @FXML
    private Button produit;
    @FXML
    private Button addproduit;
    @FXML
    private Button logoutButton;
    @FXML
    private StackPane stckCommande;
    @FXML
    private AnchorPane rootCommande;
    @FXML
    private Pane ContainerUsersAdmin;
    @FXML
    private TextField Matricule;
    @FXML
    private Button ajout;
    @FXML
    private ChoiceBox<String> type_vehicule;
    @FXML
    private ChoiceBox<String> etat_vehicule;
    @FXML
    private TextField Marque;
    @FXML
    private ChoiceBox<String> couleur;
    @FXML
    private DatePicker date_entretien;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          // TODO
       ObservableList<String> langs1 = FXCollections.observableArrayList("Rouge", "Verte", "Bleue", "Noir","Grise","blanche");
        couleur.setItems(langs1);
        ObservableList<String> langs = FXCollections.observableArrayList("camion", "voiture", "moto");
        type_vehicule.setItems(langs);
            ObservableList<String> langs2 = FXCollections.observableArrayList( "disponible", "non disponible");
        etat_vehicule.setItems(langs2);
    }    

    @FXML
    private void ListUser(ActionEvent event) {
    }

    @FXML
    private void ListDemande(ActionEvent event) {
    }

    @FXML
    private void gerercommande(ActionEvent event) {
    }

    @FXML
    private void gererligne(ActionEvent event) {
    }

    @FXML
    private void gererevent(ActionEvent event) {
    }

    @FXML
    private void gererreclamation(ActionEvent event) {
    }

    @FXML
    private void gererboutique(ActionEvent event) {
    }

    @FXML
    private void gereraddboutique(ActionEvent event) {
    }

    @FXML
    private void gererproduit(ActionEvent event) {
    }

    @FXML
    private void gereraddproduit(ActionEvent event) {
    }

    @FXML
    private void Logout(ActionEvent event) {
    }

    @FXML
    private void iconAddCommandeClicked(MouseEvent event) {
    }
    
    @FXML
    private void AjouterVehicule(javafx.event.ActionEvent event) throws SQLException {
     
        VehiculeServices sf = new VehiculeServices();
        
        Date d1 = new Date();
             ZoneId defaultZoneId = ZoneId.systemDefault();
        Date date1 =Date.from(date_entretien.getValue().atStartOfDay(defaultZoneId).toInstant());
                 
       if ((Matricule.getText().equals(""))||(couleur.getValue().toString().equals("")) || (type_vehicule.getValue().toString().equals("")) || (date1==null)
               ||(Marque.getText().equals(""))||(etat_vehicule.getValue().toString().equals("")))
  {Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("champs manquants !");
            alert.showAndWait();}
       else if (Pattern.matches("[a-zA-Z]+", Matricule.getText()) == true) 
       {Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("La matricule doit être composée de chiffres seulement! ");
            alert.showAndWait();}
        else if (Pattern.matches("[a-zA-Z]+", Marque.getText()) == false) 
       {Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("La marque doit être composée de lettres seulement! ");
            alert.showAndWait();}
               else if (date1.equals(d1)) 
       {Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("La marque doit être composée de lettres seulement! ");
            alert.showAndWait();}
               else{
       sf.ajouterVehicule(new Vehicule(0, Matricule.getText(),couleur.getValue().toString(),type_vehicule.getValue().toString(), 
               Marque.getText(),etat_vehicule.getValue().toString(),date1));
        JOptionPane.showMessageDialog(null, "Ajout effectué");
        
        
        
                 //Notification
        String tilte;
        String message;
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tilte = "Added Successful";
        message ="Voiture ajoutee avec success";
        tray.setTitle(tilte);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(2000));
               }}
    
}
