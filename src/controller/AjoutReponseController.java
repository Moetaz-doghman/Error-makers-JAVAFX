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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import entity.Reclamation;
import entity.Reponse;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import service.Mailling;
import service.ReclamationService;
import service.ReponseService;

/**
 * FXML Controller class
 *
 * @author doghm
 */
public class AjoutReponseController implements Initializable {

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
    private Button mail;
    @FXML
    private ImageView sujetCheckMark;
    @FXML
    private ImageView emailCheckMark;
    @FXML
    private ImageView messageCheckMark;
    @FXML
    private ImageView nomCheckMark;
    @FXML
    private Label LAffiche;
    @FXML
    private TextField sujet;
    @FXML
    private TextArea message;
    @FXML
    private Button btnRet;
    private Reclamation rec; 
    String erreur;
    @FXML
    private Button reponse;
    @FXML
    private Button listlivrasion1;
    @FXML
    private Button addlivrasion1;
    @FXML
    private Button listvehicule1;
    @FXML
    private Button addvehicule1;
    @FXML
    private Button listlivrasion;
    @FXML
    private Button addlivrasion;
    @FXML
    private Button listvehicule;
    @FXML
    private Button addvehicule;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    private void AjouterReponse(ActionEvent event) {
         if (sujet.getText().isEmpty()
                || message.getText().isEmpty()) {

            InnerShadow in = new InnerShadow();
            in.setColor(Color.web("#f80000"));
            sujet.setEffect(in);
            message.setEffect(in);
           

            //txtnom.setStyle("-fx-border-color: red " );
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Il faut remplir les champs obligatoires ");
            alert.showAndWait();

        } else if (testSujet()& testMessage() ) {
            String sujetClient = sujet.getText();

            String messageClient = message.getText();
            ReclamationService rs = new ReclamationService();
            ReponseService ps = new ReponseService();
            
            Reclamation r = new Reclamation();
            Reponse v = new Reponse(sujetClient, messageClient);
            
            DateTimeFormatter created_at = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
            LocalDateTime now = LocalDateTime.now();
            System.out.println(created_at.format(now));  
            v.setCreated_at(created_at.format(now));
            r.setId(rec.getId());
            r.setEtat("Resolue");
            r.setName(rec.getName());
            r.setEmail(rec.getEmail());
            r.setSubject(rec.getSubject());
            r.setType(rec.getType());
            r.setMessage(rec.getMessage());
            int id = r.getId(); 
            ps.ajouterR(v,id);
            rs.modifier(r);
            
            
            //envoi du mail
            Mailling M = new Mailling() ;
            M.envoyer(r);
            
            Alert alertt = new Alert(Alert.AlertType.INFORMATION);
            alertt.setTitle("Success");
            alertt.setContentText(" un Email a été enoyer avec succes ");
            alertt.show();
    }
    }
    

     @FXML
    private void retour(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/ListReclamation.fxml"));
        btnjo.getScene().setRoot(root);
    }
    
    private Boolean testSaisie() {
        erreur = "";
        
        if (!testSujet()) {
            erreur = erreur + ("Veuillez verifier votre Sujet: seulement des caractères et de nombre >= 10");
        }
        if (!testMessage()) {
            erreur = erreur + ("Veuillez verifier votre Message: seulement des caractères et de nombre >= 10\n");
        }
        if ( (!testSujet()) || !testMessage() ) {
           
            
            
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Erreur de format");
        alert.setHeaderText("Vérifier les champs");
        alert.setContentText(erreur);
        
        
        }

        return testSujet() && testMessage();
    }
         
         
    private Boolean testSujet() {
         int nbNonChar = 0;
        for (int i = 1; i < sujet.getText().trim().length(); i++) {
            char ch = sujet.getText().charAt(i);
            nbNonChar++;
        
            }

        if (sujet.getText().trim().length() >= 10) {
            
            return true;
        } else {
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Il faut remplir les champs correctement ");
            alert.showAndWait();
            return false;

        }

    }

    private Boolean testMessage() {
        int nbNonChar = 0;
        for (int i = 1; i < message.getText().trim().length(); i++) {
            char ch = message.getText().charAt(i);
           nbNonChar++;
        }

        if (message.getText().trim().length() >= 10) {
            return true;
        } else {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Il faut remplir les champs correctement ");
            alert.showAndWait();
            return false;

        }

    }

    
     public void setReponse(Reclamation r){
        
        sujet.setText(r.getSubject());
        rec = r;
    }

    @FXML
    private void gererreponse(ActionEvent event) {
    }

    @FXML
    private void gererlistlivraison(ActionEvent event) {
    }

    @FXML
    private void gereraddlivraison(ActionEvent event) {
    }

    @FXML
    private void gererlistvehicule(ActionEvent event) {
    }

    @FXML
    private void gereraddvehicule(ActionEvent event) {
    }

    
}
