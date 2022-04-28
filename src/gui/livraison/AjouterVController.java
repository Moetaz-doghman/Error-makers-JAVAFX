/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.livraison;

import entities.Vehicule;
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
import services.VehiculeServices;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author oaca
 */
public class AjouterVController implements Initializable {
    @FXML
        Button v = null;

    @FXML
    private TextField Matricule;
    @FXML
    private TextField Marque;
    @FXML
    private Button ajout;
   
    @FXML
    private ChoiceBox<String> type_vehicule;
    @FXML
    private ChoiceBox<String> etat_vehicule;
    @FXML
    private ChoiceBox<String> couleur;
    @FXML
    private DatePicker date_entretien;
    @FXML
    private Button l;
    @FXML
    private Button o;

   

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
    
    
@FXML
    private void PageListV(javafx.event.ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/gui/livraison/ListVehicule.fxml")));
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void PageListB(javafx.event.ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/gui/livraison/ListLivraison.fxml")));
        stage.setScene(scene);
        stage.show();
    }
     @FXML
    private void PageListC(javafx.event.ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/gui/livraison/AjouterL.fxml")));
        stage.setScene(scene);
        stage.show();
    }
    /* @FXML
    private void AjouterVehicule(javafx.event.ActionEvent event) throws SQLException {
     
        VehiculeServices sf = new VehiculeServices();
             ZoneId defaultZoneId = ZoneId.systemDefault();
        Date date1 =Date.from(date_entretien.getValue().atStartOfDay(defaultZoneId).toInstant());
       sf.ajouterVehicule(new Vehicule(0, Matricule.getText(),couleur.getValue().toString(),type_vehicule.getValue().toString(), 
               Marque.getText(),etat_vehicule.getValue().toString(),date1));
        JOptionPane.showMessageDialog(null, "Ajout effectué");}*/
      /*  VehiculeServices sp=new VehiculeServices();
          
        
        String matricule = Matricule.getText();
        String marque = Marque.getText();
    
        String clr = couleur.getValue().toString();
        String type = type_vehicule.getValue().toString();
               String etat = etat_vehicule.getValue().toString();
               ZoneId defaultZoneId = ZoneId.systemDefault();
        Date date1 =Date.from(date_entretien.getValue().atStartOfDay(defaultZoneId).toInstant());
        Vehicule t = new Vehicule(matricule, clr, type, marque, etat, date1);
            sp.ajouterVehicule(v);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Cours ajouté!");
            alert.show();
            Matricule.setText("");
            Marque.setText("");
        
            
           // VehiculeServices tt = new VehiculeServices();
          //   List<Vehicule> ltt = tt.afficher();
      
            
        }*/
}
