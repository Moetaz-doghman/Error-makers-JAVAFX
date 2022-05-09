/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import animations.Animations;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import entity.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import service.ReclamationService;

/**
 * FXML Controller class
 *
 * @author doghm
 */
public class ModifierController implements Initializable {

     ObservableList<String> listRoles = FXCollections.observableArrayList("Hardware", "Software", "Reparation");
    String erreur;
    int selectedBonplanID;
    int selectedCompteID;
        
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
    private TextField nom;
    @FXML
    private TextField email;
    @FXML
    private TextField message;
    @FXML
    private TextField sujet;
    @FXML
    private ImageView sujetCheckMark;
    @FXML
    private ImageView emailCheckMark;
    @FXML
    private ImageView messageCheckMark;
    @FXML
    private ImageView nomCheckMark;
    @FXML
    private ComboBox<String> typer;
    @FXML
    private Label LAffiche;
      private Reclamation rec; 
    @FXML
    private Button btnRet;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Stage window = new Stage();
       typer.setPromptText("Veuillez selectionner un type");
        typer.setItems(listRoles);
                Animations.fadeInUp(rootCommande);

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
        if ( (!testNom()) || (!testSujet()) || !testemail()  ) {
           
            
            
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Erreur de format");
        alert.setHeaderText("Vérifier les champs");
        alert.setContentText(erreur);
        
        
        }

        return  testNom() && testSujet() && testemail();
    }
 //   private void AjouterReclamation(ActionEvent event) {
//        if (testSaisie()==true)
//    {
//       
//        String x="Parent";
//        ReclamationService sr= new ReclamationService();
//        Reclamation r=new Reclamation();
//        r.setId(rec.getId());
//        r.setName(nom.getText());
//        r.setSubject(sujet.getText());
//        r.setMessage(message.getText());
//        r.setEmail(email.getText());
//        r.setEtat("En cours");
//        
//        
//        if(typer.getValue().toString() =="Hardware"){
//        r.setType(typer.getValue().toString());
//        }
//        else if (typer.getValue().toString() =="Software")
//        {
//        r.setType(typer.getValue().toString());    
//        }
//        else if (typer.getValue().toString() =="Reparation")
//        {
//        r.setType(typer.getValue().toString());      
//        }
//        else 
//        {
//            
//        }
//        sr.modifier(r);
//      
//      
//            LAffiche.setText(r.toString());
//            nom.clear();
//            sujet.clear();
//            message.clear();
//            email.clear();
//            typer.setValue("");
//            
//            
//           Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Modification à la base");
//        alert.setHeaderText("Succés de modification");
//        alert.setContentText("La reclamation choisie a été modofiée !");
//        alert.showAndWait();  
//    }
//    }
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
   

    
    public void setReclamation(Reclamation r){
        nom.setText(r.getName());
        sujet.setText(r.getSubject());
        typer.setValue(r.getType());
        email.setText(r.getEmail());
        message.setText(r.getMessage());
        rec = r;
    }

    @FXML
    private void ModifierReclamation(ActionEvent event) {
        if (testSaisie()==true)
    {
       
        String x="Parent";
        ReclamationService sr= new ReclamationService();
        Reclamation r=new Reclamation();
        r.setId(rec.getId());
        r.setName(nom.getText());
        r.setSubject(sujet.getText());
        r.setMessage(message.getText());
        r.setEmail(email.getText());
        r.setEtat("En cours");
        
        
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
        sr.modifier(r);
      
      
            LAffiche.setText(r.toString());
            nom.clear();
            sujet.clear();
            message.clear();
            email.clear();
            typer.setValue("");
            
            
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Modification à la base");
        alert.setHeaderText("Succés de modification");
        alert.setContentText("La reclamation choisie a été modofiée !");
        alert.showAndWait();  
    }
    
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("../GUI/ListReclamation.fxml"));
        btnjo.getScene().setRoot(root);
    }
    
}
