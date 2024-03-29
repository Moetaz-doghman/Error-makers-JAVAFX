/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Cart;
import entity.Product;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import service.ProduitCrud;

/**
 * FXML Controller class
 *
 * @author doghm
 */
public class homeController implements Initializable {

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
    private VBox box;
    
    private ImageView ima;
    private Label nom;
    private Label description;
    private ImageView ImageView;
    

    
    ProduitCrud ev = new ProduitCrud();
    List<Product> liste = new ArrayList<>();
    private Product o;
 Button[] btn = new Button[100];
    @FXML
    private Region navTest;
    @FXML
    private BorderPane contentPane;
    @FXML
    private VBox componentBox;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         HBox item = new HBox();
            box.getChildren().add(item);
           liste=ev.AfficherProduit(o);
        int taille=liste.size();
       
          for( int i=0;i<taille;i++)
            {
               
                if(i % 3 == 0){
                    item = new HBox();
                    box.getChildren().add(item);
                }
                VBox content = new VBox();
                
                File file=new File( liste.get(i).getImg());
                Image image = new Image(file.toURI().toString());
                // Image image = new Image( file);
                ImageView ImageView = new ImageView(image);
                Label title = new Label();
                o=liste.get(i);
                Label nom = new Label((liste.get(i).getProduct_name()));
                nom.setStyle("-fx-strikethrough: true");
               // nom.getStyleClass().add("barre");
               nom.setStyle("-fx-font-weight: bold");
                Label description = new Label((liste.get(i).getDesc()));
               description.setStyle("-fx-strikethrough: true");
                //prix.getStyleClass().add("barre");
                ImageView.setFitHeight(100);
                        ImageView.setFitWidth(100);
                content.getChildren().addAll(ImageView,nom,description);
                btn[i] = new Button("tesstt",content);
              
                btn[i].setPrefWidth(150);
                item.getChildren().add(btn[i]);
                box.setSpacing(80);
                item.setSpacing(80);
                btn[i].setOnAction(this::handleButtonAction);
    }
        
    }

    @FXML
    private void closeApp(MouseEvent event) {
    }

    @FXML
    private void showHomeView(MouseEvent event) throws IOException {
          Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("../GUI/home.fxml"));
         navHome.getScene().setRoot(root);
    }

    @FXML
    private void showCartView(MouseEvent event) throws IOException {
          Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("../GUI/cart-ui.fxml"));
         navCart.getScene().setRoot(root);
    }
    
     private void handleButtonAction (ActionEvent event)
   {
             //int taille=liste.size();
//         for (int i = 0; i <taille; i++) {
//            // Button a = supprimerb[i];
//            
//             if (event.getSource() == btn[i])
//             {
//                 System.out.println(liste.get(i).getId());
//                    int test = 0;
//                for(Product pi : Cart.getInstance().getC()){
//                    if(pi.getProduct_name().equals(liste.get(i).getProduct_name())){
//                        test = 1;
//                        int quantity = pi.getQuantity() + 1 ;
//                        pi.setQuantity(quantity);
//                        
//                    }
//                    
//                }
//                
//                if(test == 0){
//                    Product pp = new Product(liste.get(i).getId(),liste.get(i).getProduct_name(),liste.get(i).getImg(),liste.get(i).getStock(),liste.get(i).getPrice(),liste.get(i).getQuantity());
//                   System.out.println(pp);
//                    Cart.instance.AddProduct(pp);
//                    
//                }
//             //   Dialog.show("Success", "Item added to cart !", new Command("OK"));
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setHeaderText(null);
//            alert.setContentText("Item Added");
//            alert.showAndWait();                    
//             }
//            
//                 
//         } 
    }

    @FXML
    private void showTestview(MouseEvent event) throws IOException {
          Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("../GUI/addCommande.fxml"));
         navCart.getScene().setRoot(root);
    }
}
