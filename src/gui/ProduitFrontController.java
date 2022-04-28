/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Produit;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.ProduitService;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ProduitFrontController implements Initializable {
    public ProduitService serviceP = new ProduitService();

    @FXML
    private Label nom;
    @FXML
    private Label quantite;
    @FXML
    private Label prix;
    @FXML
    private Text description;
    @FXML
    private ImageView image;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
        public void InitProduct(Produit P) throws SQLException {

                Produit p = serviceP.details(P.getId());

        nom.setText("" + p.getNom_produit());
        prix.setText("" + p.getPrix_produit());
        description.setText("" + p.getDesc_produit());
        quantite.setText("" + p.getQuantite_produit());
        image.setImage(new Image(getClass().getResourceAsStream("/images/" + P.getImage())));
        }
      
    
}
