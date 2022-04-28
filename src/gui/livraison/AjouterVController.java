/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.livraison;

import entities.Vehicule;
import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;
import services.VehiculeServices;

/**
 * FXML Controller class
 *
 * @author oaca
 */
public class AjouterVController implements Initializable {
        Vehicule v = null;

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
             ZoneId defaultZoneId = ZoneId.systemDefault();
        Date date1 =Date.from(date_entretien.getValue().atStartOfDay(defaultZoneId).toInstant());
       sf.ajouterVehicule(new Vehicule(0, Matricule.getText(),couleur.getValue().toString(),type_vehicule.getValue().toString(), 
               Marque.getText(),etat_vehicule.getValue().toString(),date1));
        JOptionPane.showMessageDialog(null, "Ajout effectué");}
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
