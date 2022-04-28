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
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.ProduitService;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ProduitsFController implements Initializable {

    @FXML
    private ImageView image;
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
            Logger.getLogger(ProduitsFController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProduitsFController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
     public void DisplayOne() throws SQLException, IOException {
        ProduitService ServP = new ProduitService();
        ArrayList<Produit> TabP = ServP.afficher();

        for (Produit f : TabP) {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ProduitFront.fxml"));
            Parent n = (Parent) loader.load();
            ProduitFrontController form = loader.getController();
            form.InitProduct(f);
            liste_formation.getChildren().add(n);
        }
    }
       @FXML
    private void PageAddB (javafx.event.ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/gui/BoutiqueF.fxml")));
        stage.setScene(scene);
        stage.show();
    }
}
