/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.livraison;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author oaca
 */
public class listarvehiculeController {

    @FXML
    private Button bsave;
    @FXML
    private Button delete;
    @FXML
    private Button bedit;
    @FXML
    private TableView<?> tab_voiture;
    @FXML
    private TableColumn<?, ?> id_tab;
    @FXML
    private TableColumn<?, ?> matricule_tab;
    @FXML
    private TableColumn<?, ?> couleur_tab;
    @FXML
    private TableColumn<?, ?> type_vehicule_tab;
    @FXML
    private TableColumn<?, ?> marque_tab;
    @FXML
    private TableColumn<?, ?> etat_vehicule_tab;
    @FXML
    private TableColumn<?, ?> date_entretien_tab;
    @FXML
    private Button retour;

 

    @FXML
    private void Ajouter(ActionEvent event) {
    }

    @FXML
    private void Retour(ActionEvent event) {
    }

    @FXML
    private void tablehandleButtonAction(MouseEvent event) {
    }
    
}
