/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Boutique;
import entity.Produit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import service.ProduitService;
import utils.MyListener;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ShopController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private VBox chosenFruitCard;
    @FXML
    private Label fruitNameLable;
    @FXML
    private Label fruitPriceLabel;
    @FXML
    private ImageView fruitImg;
    @FXML
    private Button cart;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private TextField Total;
    @FXML
    private TableView<Produit> CartTab;
    @FXML
    private TableColumn<Produit, String> nomp;
    @FXML
    private TableColumn<Produit, String> typep;
    @FXML
    private TableColumn<Produit, String> prix;
    private List<Produit> prods = new ArrayList<>();
    ArrayList<Produit> listprod = new ArrayList<>() ;
    private Image image;
    private MyListener myListener;
    @FXML
    private Button home;
    @FXML
    private Button boutique;
    @FXML
    private Button event;
    @FXML
    private Button reclamation;
    @FXML
    private Button profil;
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            prods.addAll(getData());
        } catch (SQLException ex) {
            Logger.getLogger(MarketController.class.getName()).log(Level.SEVERE, null, ex);
        }
            if (prods.size() > 0) {
                try {
                    setChosenFruit(prods.get(0));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(MarketController.class.getName()).log(Level.SEVERE, null, ex);
                }
                myListener = new MyListener() {
                    @Override
                    public void onClickListener(Produit prod) {
                        try {
                            setChosenFruit(prod);
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(MarketController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                    }

                    @Override
                    public void onClickListener2(Boutique bout) {
                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                   
                };
            }
            int column = 0;
            int row = 1;
            try {
                for (int i = 0; i < prods.size(); i++) {
                    System.out.println(i);
                    javafx.fxml.FXMLLoader fxmlLoader = new javafx.fxml.FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/GUI/item.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();
                    
                    ItemController itemController = fxmlLoader.getController();
                    itemController.setData(prods.get(i),myListener);
                    
                    if (column == 3) {
                        column = 0;
                        row++;
                    }
                    
                    grid.add(anchorPane, column++, row); //(child,column,row)
                    //set grid width
                    grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                    grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    grid.setMaxWidth(Region.USE_PREF_SIZE);
                    
                    //set grid height
                    grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                    grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    grid.setMaxHeight(Region.USE_PREF_SIZE);
                    
                    GridPane.setMargin(anchorPane, new Insets(10));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
     
    
    
    }    
     private List<Produit> getData() throws SQLException {
        List<Produit> prod = new ArrayList<>();
        
        ProduitService prs = new ProduitService();
        List<Produit> l = prs.findbyboutique(idBoutique.id);
        for (Produit p : l) {
            prod.add(p);//adding  product object to list
        }

        return prod ;
    }
     private void setChosenFruit(Produit produit) throws FileNotFoundException {
        fruitNameLable.setText(produit.getNom_produit());
        fruitPriceLabel.setText(produit.getPrix_produit()+"$");
        String url = produit.getImage();
        FileInputStream input = new FileInputStream ("C:\\Users\\doghm\\Desktop\\Error-makers-JAVAFX\\src\\images\\"+produit.getImage()) ; 
        Image image = new Image(input) ; 
        fruitImg.setImage(image);
    
    
    }

    @FXML
    private void home(ActionEvent event) {
    }

    @FXML
    private void boutique(ActionEvent event) {
    }

    @FXML
    private void event(ActionEvent event) {
    }

    @FXML
    private void reclamation(ActionEvent event) {
    }

    @FXML
    private void cart(MouseEvent event) {
    }

    @FXML
    private void addToCart(ActionEvent event) {
    }
    
}
