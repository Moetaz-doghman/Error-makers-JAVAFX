/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Reclamation;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author mariem
 */
public class Reclamation_itemController implements Initializable {

    
   
    @FXML
    private Label name;
    @FXML
    private Label sujet;
    @FXML
    private Label Typer;
    @FXML
    private Label email;
    @FXML
    private Label message;
    
    public void setData(Reclamation recl){
        name.setText(recl.getName());
        sujet.setText(recl.getSubject());
        Typer.setText(recl.getType());
        email.setText(recl.getEmail());
        message.setText(recl.getMessage());
    
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
