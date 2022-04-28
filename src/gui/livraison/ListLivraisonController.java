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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author oaca
 */
public class ListLivraisonController implements Initializable {

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
    private TableView<?> view;
    @FXML
    private TableColumn<?, ?> cl_matricule;
    @FXML
    private TableColumn<?, ?> cl_couleur;
    @FXML
    private TableColumn<?, ?> cl_typevehicule;
    @FXML
    private TableColumn<?, ?> cl_marque;
    @FXML
    private TableColumn<?, ?> cl_etatvehicule;
    @FXML
    private TableColumn<?, ?> cl_etatvehicule1;
    @FXML
    private TableColumn<?, ?> cl_etatvehicule11;
    @FXML
    private Button btndel;
    @FXML
    private Button addbtn;

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


    @FXML
    private void delete(ActionEvent event) {
    }

    @FXML
    private void ajoutFour(ActionEvent event) {
    }
/*
    @FXML
    private void Updatelivreur_id(TableColumn.CellEditEvent<S, T> event) {
    }

    @FXML
    private void Updatecommande_id(TableColumn.CellEditEvent<S, T> event) {
    }

    @FXML
    private void updatevehicule_id(TableColumn.CellEditEvent<S, T> event) {
    }

    @FXML
    private void Updateetat_livraison(TableColumn.CellEditEvent<S, T> event) {
    }

    @FXML
    private void Updatedate_livraison(TableColumn.CellEditEvent<S, T> event) {
    }

    @FXML
    private void Updateprix_livraison(TableColumn.CellEditEvent<S, T> event) {
    }

    @FXML
    private void Updatefin_livraison(TableColumn.CellEditEvent<S, T> event) {
    }*/
    
}
