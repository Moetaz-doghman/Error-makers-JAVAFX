/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Boutique;
import entities.Produit;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import services.BoutiqueService;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class BoutiqueFrontController implements Initializable {
    public BoutiqueService serviceP = new BoutiqueService();

    @FXML
    private Label nom;
    @FXML
    private Label adresse;
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
    public void InitBoutique(Boutique P) throws SQLException {

                Boutique p = serviceP.details(P.getIdd());

        nom.setText("" + p.getNomBoutique());
        adresse.setText("" + p.getAdresseBoutique());
        description.setText("" + p.getDescBoutique());
        image.setImage(new Image(getClass().getResourceAsStream("/images/" + P.getImg())));
        }
    
}
