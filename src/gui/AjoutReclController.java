/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import services.ReclamationService;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author maiez
 */
public class AjoutReclController implements Initializable {

    ObservableList<String> listRoles = FXCollections.observableArrayList("Hardware", "Software", "Reparation");
    String erreur;
    
    @FXML
    private TextField nom;
    @FXML
    private ComboBox<String> typer;

    @FXML
    private TextField email;
    @FXML
    private Button btnRet;
    @FXML
    private Label LAffiche;
    @FXML
    private ImageView nomCheckMark;
    @FXML
    private ImageView emailCheckMark;
    @FXML
    private TextField sujet;
    @FXML
    private TextField message;
    @FXML
    private ImageView sujetCheckMark;
    @FXML
    private ImageView messageCheckMark;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Stage window = new Stage();
       typer.setPromptText("Veuillez selectionner un type");
        typer.setItems(listRoles);
    }    

    @FXML
    private boolean testNom() {
        int nbNonChar = 0;
        for (int i = 1; i < nom.getText().trim().length(); i++) {
            char ch = nom.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && nom.getText().trim().length() >= 3) {
            nomCheckMark.setImage(new Image("image/checkMark.png"));
            return true;
        } else {
            nomCheckMark.setImage(new Image("image/erreurCheckMark.png"));
               
            return false;

        }
    }

    @FXML
    private boolean testSujet() {
        int nbNonChar = 0;
        for (int i = 1; i < sujet.getText().trim().length(); i++) {
            char ch = sujet.getText().charAt(i);
            nbNonChar++;
//            if (!Character.isLetter(ch)) {
//                
//            }
        }

        if (sujet.getText().trim().length() >= 10) {
            sujetCheckMark.setImage(new Image("image/checkMark.png"));
            return true;
        } else {
            sujetCheckMark.setImage(new Image("image/erreurCheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }
    }


     private Boolean testSaisie() {
        erreur = "";
        if (!testNom()) {
            erreur = erreur + ("Veuillez verifier votre Nom: seulement des caractères et de nombre >= 3 \n");
        }
        if (!testSujet()) {
            erreur = erreur + ("Veuillez verifier votre Sujet: seulement des caractères et de nombre >= 10");
        }
        if (!testemail()) {
            erreur = erreur + ("Veuillez verifier que votre adresse email est de la forme : ***@***.** \n");
        }
        if ( (!testNom()) || (!testSujet()) || !testemail() ) {
           
            
            
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Erreur de format");
        alert.setHeaderText("Vérifier les champs");
        alert.setContentText(erreur);
        
        
        }

        return  testNom() && testSujet() && testemail();
    }
    @FXML
    private void AjouterReclamation(ActionEvent event) {
        if (testSaisie()==true)
    {
       
        String x="Parent";
        ReclamationService sr= new ReclamationService();
        Reclamation r=new Reclamation();
      
        r.setName(nom.getText());
        r.setSubject(sujet.getText());
        r.setMessage(message.getText());
        r.setEmail(email.getText());
        
        if(typer.getValue().toString() =="Hardware"){
        r.setType(typer.getValue().toString());
        }
        else if (typer.getValue().toString() =="Software")
        {
        r.setType(typer.getValue().toString());    
        }
        else if (typer.getValue().toString() =="Reparation")
        {
        r.setType(typer.getValue().toString());      
        }
        else 
        {
            
        }
        sr.ajouter(r);
      
      
            LAffiche.setText(r.toString());
            nom.clear();
            sujet.clear();
            message.clear();
            email.clear();
            typer.setValue("");
            
            
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ajout à la base");
        alert.setHeaderText("Succées d'ajout");
        alert.setContentText("La reclamation choisi a été envoyer et va etre traité dans les plus brefs délais !");
        alert.showAndWait();  
    }
    }
        TableView<Reclamation> liste = new TableView<>();
    TextField rechercheReclamationn = new TextField();
     

    @FXML
    private boolean testemail() {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email.getText() == null) {
            return false;
        }

        if (pat.matcher(email.getText()).matches() == false) {
            emailCheckMark.setImage(new Image("image/erreurCheckMark.png"));
//                erreur = erreur + ("Veuillez verifier votre adresse email\n");
            return false;
//            

        } else {
            emailCheckMark.setImage(new Image("image/checkMark.png"));
        }
        return true;
    }


   
   

   
      
    
      

    @FXML
    private void Retour(ActionEvent event) {
        try {
        Stage stage = (Stage) btnRet.getScene().getWindow();
        stage.close();
        
        FXMLLoader fxmlloader = new FXMLLoader (getClass().getResource("Document.fxml"));
        Parent root;
        
            root = (Parent) fxmlloader.load();
        
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(root));
        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(AjoutReclController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void testPrenom(KeyEvent event) {
    }

    @FXML
    private boolean testMessage(ActionEvent event) {
        
         int nbNonChar = 0;
        for (int i = 1; i < message.getText().trim().length(); i++) {
            char ch = message.getText().charAt(i);
            nbNonChar++;
//            if (!Character.isLetter(ch)) {
//                nbNonChar++;
//            }
        }

        if (message.getText().trim().length() >= 10) {
            messageCheckMark.setImage(new Image("image/checkMark.png"));
            return true;
        } else {
            messageCheckMark.setImage(new Image("image/erreurCheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

    }
    }
   

    
}
