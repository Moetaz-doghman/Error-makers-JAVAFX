/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Boutique;
import entities.Produit;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import services.BoutiqueService;
import services.ProduitService;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class BoutiqueFController implements Initializable {

    @FXML
    private Label text;
    @FXML
    private VBox liste_formation;

    /**
     * Initializes the controller class.
     */
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         try {
            DisplayOne();
        } catch (SQLException ex) {
            Logger.getLogger(BoutiqueFController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BoutiqueFController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
     public void DisplayOne() throws SQLException, IOException {
        BoutiqueService ServP = new BoutiqueService();
        ArrayList<Boutique> TabP = ServP.afficher();

        for (Boutique f : TabP) {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("BoutiqueFront.fxml"));
            Parent n = (Parent) loader.load();
            BoutiqueFrontController form = loader.getController();
            form.InitBoutique(f);
            liste_formation.getChildren().add(n);
        }
    }    
    
}
