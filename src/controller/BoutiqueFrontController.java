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
import javafx.scene.Parent;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import service.BoutiqueService;
import service.ProduitService;
import utils.MyListener;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class BoutiqueFrontController implements Initializable {

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
    private TableView<Boutique> CartTab;
    @FXML
    private TableColumn<Boutique, String> nomp;
    @FXML
    private TableColumn<Boutique, String> typep;
    @FXML
    private TableColumn<Boutique, String> prix;
       private List<Boutique> bouts = new ArrayList<>();
    ArrayList<Boutique> listbout = new ArrayList<>() ;
    private Image image;
    private MyListener myListener;
    @FXML
    private Button boutiqua;
    @FXML
    private Label description;
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
     
    private List<Boutique> getData() throws SQLException {
        List<Boutique> bout = new ArrayList<>();
        
        BoutiqueService prs = new BoutiqueService();
        List<Boutique> l = prs.afficher();
        for (Boutique p : l) {
            bout.add(p);//adding  product object to list
        }

        return bout ;
    }
   
    private void setChosenFruit(Boutique boutique) throws FileNotFoundException {
        idBoutique.id=boutique.getIdd();
        fruitNameLable.setText(boutique.getNomBoutique());
        fruitPriceLabel.setText(boutique.getAdresseBoutique());
        description.setText(boutique.getDescBoutique());

        String url = boutique.getImg();
        FileInputStream input = new FileInputStream ("C:\\Users\\doghm\\Desktop\\Error-makers-JAVAFX\\src\\images\\"+boutique.getImg()) ; 
        Image image = new Image(input) ; 
        fruitImg.setImage(image);
    
    
    }
  
      @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            bouts.addAll(getData());
        } catch (SQLException ex) {
        }
            if (bouts.size() > 0) {
                try {
                    setChosenFruit(bouts.get(0));
                } catch (FileNotFoundException ex) {
                }
                myListener = new MyListener() {
                  

                    @Override
                   public void onClickListener2(Boutique bout) {
                        try {
                            setChosenFruit(bout);
                        } catch (FileNotFoundException ex) {
                        }
                        
                    }

                    @Override
                    public void onClickListener(Produit prod) {
                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                };
            }
            int column = 0;
            int row = 1;
            try {
                for (int i = 0; i < bouts.size(); i++) {
                    System.out.println(i);
                    javafx.fxml.FXMLLoader fxmlLoader = new javafx.fxml.FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("../GUI/Boutiqua.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();
                    
                    BoutiquaController boutiquaController = fxmlLoader.getController();
                    boutiquaController.setData(bouts.get(i),myListener);
                    
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

    @FXML
    private void boutiqua(ActionEvent event) throws IOException {
        Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("../GUI/Shop.fxml"));
        boutiqua.getScene().setRoot(root);
        
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