/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Commande;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import service.CommandeCrud;

/**
 * FXML Controller class
 *
 * @author doghm
 */
public class EController implements Initializable {

    @FXML
    private TextField nomfld;
    @FXML
    private TextField adressefld;
    @FXML
    private TextField imgEfld;
    @FXML
    private TextField montantfld;
    @FXML
    private ImageView imgSV;
    @FXML
    private DatePicker datefld;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

  
     
    @FXML
    private void ajouter(ActionEvent event) throws ParseException {
          long millis=System.currentTimeMillis();  
         java.sql.Date date = new java.sql.Date(millis); 
         
       Date datee = Date.valueOf(datefld.getValue());
       String lieu=adressefld.getText();
       String montant =montantfld.getText();
        String nom =nomfld.getText();
        
         
        StringBuilder errors =new StringBuilder(); 
           
    
    

        if(nomfld.getText().trim().isEmpty()){
            errors.append("Please enter a name\n");
        }
        if(( !Pattern.matches("[a-zA-Z]*", nomfld.getText()))||(nomfld.getText().trim().isEmpty())){
            errors.append("Please enter a valid name\n");
        }
        if(datefld.getValue()==null){
            errors.append("Please enter a date\n");
        }
        if(montantfld.getText().trim().isEmpty()){
            errors.append("Please enter an place\n");
        }
        if(Pattern.matches("[a-zA-Z]*",montantfld.getText())||montantfld.getText().isEmpty()){
            errors.append("please enter a valid montant\n");
        
        }
     
        if(adressefld.getText().trim().isEmpty()){
            errors.append("please enter a valid adress\n");
        }
          
        if(errors.length()>0){
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errors");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        }
        else
        {
            
           Commande e=new Commande(nom,datee,lieu,montant);
            CommandeCrud sp = new CommandeCrud();
            sp.AjouterCommande(e);


            //clean();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Event Ajouté");
            alert.showAndWait();
            
            
    }
    }
    
    
      
     
        

    

    
}
