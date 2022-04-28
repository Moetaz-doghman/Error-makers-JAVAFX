/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.livraison;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author oaca
 */
public class AjouterLController implements Initializable {

    @FXML
    private Button btnOverview;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnstock;
    @FXML
    private Button btnOrder;
    @FXML
    private Button logout;
    @FXML
    private ChoiceBox<?> livreur;
    @FXML
    private ChoiceBox<?> commande;
    @FXML
    private ChoiceBox<?> vehicule;
    @FXML
    private DatePicker datelivraison;
    @FXML
    private TextField prix;
    @FXML
    private DatePicker finlivraison;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Front(ActionEvent event) {
    }
    
}
