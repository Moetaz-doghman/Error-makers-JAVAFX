///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package controller;
//
//import entity.Produit;
//import java.net.URL;
//import java.util.ResourceBundle;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.ScrollPane;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.TextField;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.GridPane;
//import GUI.NewFXMain1;
//import entity.Cart;
//import entity.Product;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.ResourceBundle;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.geometry.Insets;
//import javafx.geometry.Orientation;
//import javafx.geometry.Pos;
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.ScrollPane;
//import javafx.scene.control.Separator;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.GridPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.Pane;
//import javafx.scene.layout.Region;
//import javafx.scene.layout.VBox;
//import service.ProduitCrud;
//import utils.JFXDialogTool;
//
///**
// * FXML Controller class
// *
// * @author doghm
// */
//public class cartController implements Initializable {
//
//    @FXML
//    private AnchorPane rootPane;
//    @FXML
//    private Button home;
//    @FXML
//    private Button boutique;
//    @FXML
//    private Button event;
//    @FXML
//    private Button reclamation;
//    @FXML
//    private Button profil;
//    @FXML
//    private Button cart;
//    @FXML
//    private ScrollPane scroll;
//    @FXML
//    private GridPane grid;
//    @FXML
//    private TextField Total;
//    
//        private ImageView ima;
//    private Label nom;
//    private Label description;
//    private ImageView ImageView;
//    private Label totalPriceLabel;
//    
//    private Produit o;
//    @FXML
//    private VBox box;
//    
//
//    /**
//     * Initializes the controller class.
//     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//         List<Produit> liste = Cart.getInstance().getCartList();
//        box.getChildren().clear();
//          
//        if ( liste.isEmpty())
//        {
//        Label label3 = new Label("Your cart is empty");
//       // label3.setPadding(new Insets(5,5,5,5));
//        box.getChildren().add(label3);
//        }
//        else 
//        {   
//             HBox item = new HBox();
//           box.getChildren().add(item);
//          Label ShoppingCartTitle = new Label("Shopping cart");
//          item.getChildren().add(ShoppingCartTitle);
//          
//          for(Produit p : liste)
//            {
//                 try {
//                     HBox content = new HBox();
//
//                    HBox image2 = cartyEntryView(p);
//                    image2.setPrefWidth(80);
//
//                    Label productName = new Label(p.getNom_produit());
//                    productName.setPrefWidth(300);
//                    productName.setStyle("-fx-font-size:15pt; -fx-padding:5px");
//
//                    Label quantityValue = new Label(p.getQuantite_produit());
//                    quantityValue.setStyle("-fx-padding:5px");
//
//                    Button additionquantity = new Button("+");
//                    additionquantity.setStyle("-fx-padding:8px");
//                    //  plusButton.setUserData(p.getProduct_name());
//
//                    Button substractquantity = new Button("-");
//                    substractquantity.setStyle("-fx-padding:8px");
//
//                    Button remove= new Button("Remove");
//                    
//
//
//                    Label price = new Label(Double.toString(p.getPrice()*p.getQuantity()));
//                    price.setPrefWidth(100);
//                    price.setStyle("-fx-padding:8px");
//
//                    content.getChildren().addAll(image2,productName,additionquantity,quantityValue,substractquantity,price,remove);
//                    box.getChildren().add(content);
//                    additionquantity.setOnAction(event -> {
//                     
//                        if(Integer.parseInt(p.getQuantite_produit())<=p.getQuantite_produit()-1){
//                            //   substractquantity.setE(true);
//                            double test = Double.parseDouble(totalPriceLabel.getText());
//
//                            int quantity = p.getQuantity()+1;
//                            p.setQuantity(quantity);
//                            quantityValue.setText(Integer.toString(p.getQuantity()));
//                            price.setText(Double.toString(p.getQuantity()*p.getPrice()));
//                            totalPriceLabel.setText(Double.toString(test+p.getPrice()));
//                        }
//                        else{
//                            // additionquantity.setEnabled(false);
//                        }
//
//                    });
//
//                    remove.setOnAction(event -> {
//                        Cart.instance.RemoveProduct(p);
//                        Parent root;
//                        try {
//                            root = javafx.fxml.FXMLLoader.load(getClass().getResource("../GUI/cart-ui.fxml"));
//                            navCart.getScene().setRoot(root);
//                        } catch (IOException ex) {
//                            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                    });
//
//                    substractquantity.setOnAction(event -> {
//                        if(Integer.parseInt(p.getQuantite_produit())>=2){
//                            //  additionquantity.setEnabled(true);
//                            double test = Double.parseDouble(totalPriceLabel.getText());
//
//                            int quantity = p.getQuantity()-1;
//                            p.setQuantity(quantity);
//                            quantityValue.setText(Integer.toString(p.getQuantity()));
//                            price.setText(Double.toString(p.getQuantity()*p.getPrice()));
//                            totalPriceLabel.setText(Double.toString(test-p.getPrice()));
//                        }
//                        else{
//                            //   substractquantity.setEnabled(false);
//                        }
//                    });
//                                     } catch (FileNotFoundException ex) {
//                                         Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
//                                     }
//
//
//
//
//
//
//
//
//
//
//
//                                }
//
//                              Separator separator = new Separator();
//                              separator.setOrientation(Orientation.HORIZONTAL);
//                              box.getChildren().add(separator);
//
//
//                            HBox totalView = totalView(Cart.getInstance().total());
//                            box.getChildren().add(totalView);
//                             Button valider= new Button("Valider la commande");
//                            box.getChildren().add(valider);
//
//
//                              box.setSpacing(80);
//                              item.setSpacing(80);
//                              
//                        valider.setOnAction(event -> {
//                 try {
//                     Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("../GUI/Addcommande.fxml"));
//                     navHome.getScene().setRoot(root);
//                 } catch (IOException ex) {
//                     Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
//                 }
//                    });
//       
//       }
//    }  
//    
//    private HBox totalView(Double totalPrice) 
//     {
//        HBox layout = new HBox();
//        layout.setAlignment(Pos.CENTER);
//        
//       Label totalLabel = new Label(" Total : ");
//       totalLabel.setStyle("-fx-font-size:15;");
//       
//       this.totalPriceLabel = new Label(String.valueOf(totalPrice));
//       layout.getChildren().addAll(totalLabel,this.totalPriceLabel);
//        return layout;
//        
//    }
//    
//     private HBox cartyEntryView(Produit o) throws FileNotFoundException 
//     {
//        HBox layout = new HBox();
//        layout.setAlignment(Pos.CENTER_LEFT);
//        
//       // FileInputStream input = new FileInputStream("Users/doghm/Desktop/Pidev---Tech-masters-main/gestionEve_Spon/src/ressource/"+cartEntry.getCartList().getImg());
//        FileInputStream input = new FileInputStream("C:/Users/doghm/Desktop/Pidev---Tech-masters-main/gestionEve_Spon/src/ressource/"+o.getImg());
//        Image image = new Image(input);
//        ImageView imageView = new ImageView(image);
//        imageView.setFitWidth(50);
//        imageView.setFitHeight(50);
//  
//        layout.getChildren().addAll(imageView);  
//
//        return layout;
//        
//    }
//
//    @FXML
//    private void home(ActionEvent event) {
//    }
//
//    @FXML
//    private void boutique(ActionEvent event) {
//    }
//
//    @FXML
//    private void event(ActionEvent event) {
//    }
//
//    @FXML
//    private void reclamation(ActionEvent event) {
//    }
//
//    @FXML
//    private void addToCart(ActionEvent event) {
//    }
//    
//}
