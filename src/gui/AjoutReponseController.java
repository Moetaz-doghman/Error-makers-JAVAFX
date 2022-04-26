/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Reclamation;
import entities.Reponse;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;
import services.Mailling;
import services.ReclamationService;
import services.ReponseService;

/**
 * FXML Controller class
 *
 * @author mariem
 */
public class AjoutReponseController implements Initializable {

    @FXML
    private Button mail;
    @FXML
    private TextField sujet;
    @FXML
    private TextArea message;
    private Reclamation rec; 
    String erreur;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
//    private void envoyerMail(ActionEvent event) {
        
        
//        if (sujet.getText().isEmpty()
//                || message.getText().isEmpty()) {
//
//            InnerShadow in = new InnerShadow();
//            in.setColor(Color.web("#f80000"));
//            sujet.setEffect(in);
//            message.setEffect(in);
//           
//
//            //txtnom.setStyle("-fx-border-color: red " );
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Erreur");
//            alert.setHeaderText(null);
//            alert.setContentText("Il faut remplir les champs obligatoires ");
//            alert.showAndWait();
//
//        } else if (testSujet()& testMessage() ) {
//            String sujetClient = sujet.getText();
//
//            String messageClient = message.getText();
//            
//            
//
//            
//
//            ReponseService ps = new ReponseService();
//            
//            Reponse v = new Reponse(sujetClient, messageClient);
//
//            ps.ajouter(v);
//            Mailling M = new Mailling() ;
//            M.envoyer();
//            
//            Alert alertt = new Alert(Alert.AlertType.INFORMATION);
//            alertt.setTitle("Success");
//            alertt.setContentText(" un Email a été enoyer avec succes ");
//            alertt.show();
//    }
//    }
    
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
    
}
