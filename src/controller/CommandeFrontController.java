/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Cart;
import entity.Commande;
import entity.Lignecommande;
import entity.Product;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import service.CommandeCrud;
import service.LigneCrud;
import service.ProduitCrud;

/**
 * FXML Controller class
 *
 * @author doghm
 */
public class CommandeFrontController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private BorderPane body;
    @FXML
    private VBox sideArea;
    @FXML
    private HBox sideControls;
    @FXML
    private Label closeButton;
    @FXML
    private VBox sideNav;
    @FXML
    private Region navHome;
    @FXML
    private Region navCart;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Pane handPaneMac;
    @FXML
    private TextField nomfld;
    @FXML
    private TextField prenomfld;
    @FXML
    private TextField adressefld;
    @FXML
    private TextField phonefld;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void closeApp(MouseEvent event) {
    }

    @FXML
    private void showHomeView(MouseEvent event) {
    }

    @FXML
    private void showCartView(MouseEvent event) {
    }

    @FXML
    private void ajouter(ActionEvent event) {
        
        long millis = System.currentTimeMillis();
        java.sql.Date DateRapport = new java.sql.Date(millis);

        String DateLyoum = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(DateRapport);//yyyyMMddHHmmss
        System.out.println("DateLyoummmmmmmmmmmmmmmmmmmmm   " + DateLyoum);
        
        String adresse=adressefld.getText();
        String prenom =prenomfld.getText();
        String nom =nomfld.getText();
        String phone = phonefld.getText();
        
        Double d  = Cart.getInstance().total() ;
        String montant=String.valueOf(d);  
        System.out.println(montant);
        
        
         
        StringBuilder errors =new StringBuilder(); 
        if(errors.length()>0){
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errors");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        }
        else
        {
            
           Commande e=new Commande(nom,prenom,phone,DateRapport,adresse,montant,0,0);
            CommandeCrud sp = new CommandeCrud();
            sp.AjouterCommande(e);
            
            int lastid = sp.LASTINSERTID();
            System.out.println(lastid);
            
        for (Product p : Cart.getInstance().getC()){
                    Lignecommande lg = new Lignecommande(lastid,p.getId(),p.getQuantity());
                    System.out.println(p.getId());
                    CommandeCrud order_ligne = new CommandeCrud();
                   LigneCrud dd = new LigneCrud();
                   dd.AjouterligneCommande(lg);

            }

            //clean();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Event Ajouté");
            alert.showAndWait();
            
            
        }
        
        
    }
    
}
