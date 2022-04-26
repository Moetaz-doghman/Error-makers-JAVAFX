/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author mariem
 */
public class ReclparentController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField reclamation;
    @FXML
    private Label LAffiche;
    @FXML
    private ComboBox<?> typer;
    @FXML
    private ImageView prenomCheckMark;
    @FXML
    private ImageView nomCheckMark;
    @FXML
    private TextField email;
    @FXML
    private ImageView emailCheckMark;
    @FXML
    private DatePicker date;
    @FXML
    private ImageView dateCheckMark;
    @FXML
    private Button btnRet;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void testNom(KeyEvent event) {
    }

    @FXML
    private void testNom(ActionEvent event) {
    }

    @FXML
    private void testPrenom(KeyEvent event) {
    }

    @FXML
    private void testPrenom(ActionEvent event) {
    }

    @FXML
    private void AjouterReclamation(ActionEvent event) {
    }

    @FXML
    private void recherchetype(ActionEvent event) {
    }

    @FXML
    private void testemail(KeyEvent event) {
    }

    @FXML
    private void testemail(ActionEvent event) {
    }

    @FXML
    private void testdate(KeyEvent event) {
    }

    @FXML
    private void testdate(ActionEvent event) {
    }

    @FXML
    private void Retour(ActionEvent event) {
    }
    
}
