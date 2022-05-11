/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.ReclamationService;

/**
 * FXML Controller class
 *
 * @author mariem
 */
public class ListReclamationFrontController implements Initializable {

    ObservableList<String> listRoles = FXCollections.observableArrayList("Hardware", "Software", "Reparation");
    String erreur;
    @FXML
    private VBox chosenFruitCard;
    @FXML
    private TextField nom;
    @FXML
    private TextField sujet;
    @FXML
    private TextField message;
    @FXML
    private ComboBox<String> typer;
    @FXML
    private TextField email;
    @FXML
    private VBox reclamationLayout;
    @FXML
    private ImageView nomCheckMark;
    @FXML
    private ImageView sujetCheckMark;
    @FXML
    private ImageView messageCheckMark;
    @FXML
    private ImageView emailCheckMark;
    @FXML
    private AnchorPane btnRet;
    @FXML
    private TextField getSearch;

   
    
    
    private List<Reclamation> getData() {
        List<Reclamation> prod = new ArrayList<>();
        
        ReclamationService prs = new ReclamationService();
        List<Reclamation> l = prs.recuperer();
        for (Reclamation p : l) {
            prod.add(p);//adding  product object to list
        }

        return prod ;
    }
    
    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        typer.setPromptText("Veuillez selectionner un type");
        typer.setItems(listRoles);
        
        
    load();
    }
        
    
    private void load(){
        reclamationLayout.getChildren().removeAll(reclamationLayout.getChildren());
            List<Reclamation> reclamation = new ArrayList<>(getData());
        for (int i=0; i<reclamation.size(); i++){
            
         
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../GUI/Reclamation_item.fxml"));
                    try{
                    HBox hBox = fxmlLoader.load();
                    Reclamation_itemController ric = fxmlLoader.getController();
                    ric.setData(reclamation.get(i));
                    
                    reclamationLayout.getChildren().add(hBox);
                    }catch(IOException e){
                    e.printStackTrace();
                    }
        
        }
    }
        
  

    @FXML
    private boolean testNom(KeyEvent event) {
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
    private boolean testNom() {
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
        if ( (!testNom()) || (!testSujet()) || !testemail() || !testMessage() ) {
           
            
            
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Erreur de format");
        alert.setHeaderText("Vérifier les champs");
        alert.setContentText(erreur);
        
        
        }

        return  testNom() && testSujet() && testemail();
    }

    @FXML
    private boolean testMessage() {
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
        load();
    }
    }
    @FXML
    private void testPrenom(KeyEvent event) {
    }

    @FXML
    private void retour(ActionEvent event) {
         try {
        Stage stage = (Stage) btnRet.getScene().getWindow();
        stage.close();
        
        FXMLLoader fxmlloader = new FXMLLoader (getClass().getResource("../GUI/AcceuilRec.fxml"));
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
    private void search(ActionEvent event) {
    }    
    
    
}
