/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import entity.Boutique;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import utils.MyListener ;  
import entity.Produit;
import entity.Commande;
import entity.Cart;
import entity.Lignecommande;
import entity.Product;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import service.ProduitService;


/**
 * FXML Controller class
 *
 * @author rayan
 */
public class MarketController implements Initializable {

    @FXML
    private VBox chosenFruitCard;
    @FXML
    private Label fruitNameLable;
    @FXML
    private Label fruitPriceLabel;
    @FXML
    private ImageView fruitImg;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;   
    
    private List<Produit> prods = new ArrayList<>();
    ArrayList<Produit> listprod = new ArrayList<>() ;
    private Image image;
    private MyListener myListener;
 
    @FXML
    private TextField Total;
    @FXML
    private TableView<Produit> CartTab;
    @FXML
    private TableColumn<Produit, String> nomp;
    @FXML
    private TableColumn<Produit, String> typep;
    @FXML
    private TableColumn<Produit, String > prix;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button cart;
    @FXML
    private Button addcart;
    @FXML
    private Button cartt;

    
    
    
    
    private List<Produit> getData() throws SQLException {
        List<Produit> prod = new ArrayList<>();
        
        ProduitService prs = new ProduitService();
        List<Produit> l = prs.afficher();
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
    
    private void setChosenFruit2(Produit produit) throws FileNotFoundException {
        System.out.println("aaaa");
        fruitNameLable.setText(produit.getNom_produit());
        
        fruitPriceLabel.setText(produit.getPrix_produit()+"$");
        String url = produit.getImage();
        FileInputStream input = new FileInputStream ("C:\\Users\\doghm\\Desktop\\Error-makers-JAVAFX\\src\\images\\"+produit.getImage()) ; 
        Image image = new Image(input) ; 
        fruitImg.setImage(image);
        addcart.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
        try {
            //logger.info("OnAction {}", event);
            setchosenncart(produit);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MarketController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
});
        
        
        
    
    }
    
     private void setchosenncart(Produit produit) throws FileNotFoundException {
             System.out.println(produit.getId());
                    int test = 0;
                for(Produit pi : Cart.getInstance().getC()){
                    if(pi.getNom_produit().equals(produit.getNom_produit())){
                        test = 1;
                        String quantity = pi.getQuantite_produit()+ 1 ;
                        pi.setQuantite_produit(quantity);
                        
                    }       
                }
                
                if(test == 0){
                    Produit pp = new Produit(produit.getId(),produit.getPrix_produit(), produit.getQuantite_produit() , produit.getDesc_produit() ,produit.getImage() ,produit.getNom_produit());
                   System.out.println(pp);
                    Cart.instance.AddProduct(pp);
                    
                }
             //   Dialog.show("Success", "Item added to cart !", new Command("OK"));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Item Added");
            alert.showAndWait();                
    }
    
     
      @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            prods.addAll(getData());
        } catch (SQLException ex) {
            Logger.getLogger(MarketController.class.getName()).log(Level.SEVERE, null, ex);
        }
            if (prods.size() > 0) {
                try {
                    setChosenFruit2(prods.get(0));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(MarketController.class.getName()).log(Level.SEVERE, null, ex);
                }
                myListener = new MyListener() {
                    @Override
                    public void onClickListener(Produit prod) {
                        try {
                            setChosenFruit2(prod);
                          //  setchosenncart(prod);
                        } catch (FileNotFoundException ex) {
                        }
                        
                    }

                    @Override
                    public void onClickListener2(Boutique bout) {
                    }

                   
                };
            }
            int column = 0;
            int row = 1;
            try {
                for (int i = 0; i < prods.size(); i++) {
                    System.out.println(i);
                    FXMLLoader fxmlLoader = new FXMLLoader();
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

//    @FXML
//    private void addtocart(ActionEvent event) {
//        System.out.println(prods);
//        
//         try {
//            prods.addAll(getData());
//                 System.out.println(prods);
//        } catch (SQLException ex) {
//        }
//            if (prods.size() > 0) {
//                try {
//                    setchosenncart(prods.get(0));
//                         System.out.println(prods);
//                } catch (FileNotFoundException ex) {
//                }
//                myListener = new MyListener() {
//                    @Override
//                    public void onClickListener(Produit prod) {
//                        try {
//                            setchosenncart(prod);
//                                 System.out.println(prods);
//                        } catch (FileNotFoundException ex) {
//                        }
//                        
//                    }
//
//                    @Override
//                    public void onClickListener2(Boutique bout) {
//                    }
//
//                   
//                };
//            }
//        
//    }

//    private void addtocart(MouseEvent event) {
//        System.out.println(prods);
//         Node sourceComponent = (Node)event.getSource() ; 
//    String prodname = (String)sourceComponent.getUserData(); 
//  //  ShoppingCart ShopC = ShoppingCart.getInstance() ;
//        System.out.println(prodname);
//    //ShopC.addProduct(prodname) ;  
//        
//    }

    

    @FXML
    private void addtocart(ActionEvent event) {
         Stage primaryStage = new Stage();
        
        try {
            ((Stage) cartt.getScene().getWindow()).close();
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/cart.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("PROTECH");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

  
      
    
      
   
    
}
