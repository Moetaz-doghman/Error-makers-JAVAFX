/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import entity.User;
import entity.userSession;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import service.UserService;




/**
 * FXML Controller class
 *
 * @author skanderzouaoui
 */
public class ProfileController implements Initializable {
    
    List<String> type;
    String imgG = "";
    String path = null;

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private TextField telephone;
    @FXML
    private PasswordField password;
    @FXML
    private Button logoutButton;
    @FXML
    private ImageView img;
    @FXML
    private Button addimgButton;
    @FXML
    private TextField message;
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
    @FXML
    private Button cart;
    @FXML
    private VBox chosenFruitCard;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        type = new ArrayList<>();
        type.add("*.jpg");
        type.add("*.png");
        
        Base64.Decoder decoder = Base64.getDecoder();
        byte[]  pass = decoder.decode(userSession.password);
        
        nom.setText(userSession.nom);
        prenom.setText(userSession.prenom);
        email.setText(userSession.email);
        telephone.setText(userSession.telephone);
        password.setText(new String(pass));
        
        if(userSession.img!=null){
        Image image =new Image(userSession.img);
        img.setImage(image);
        }else{
            Image image =new Image("images/user1.png");
            img.setImage(image);
            
        }
    }    

    @FXML
    private void Update(ActionEvent event) {
         if ((prenom.getText().length()==0)||(nom.getText().length()==0) 
                || (email.getText().length()==0)|| (telephone.getText().length()==0) 
                ||(password.getText().length()==0)){

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("Please fill all the fields");
        alert.show();
        }
        else if((!email.getText().contains("@")) && (!email.getText().contains("."))){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("Please enter a valid Email");
        alert.show();
                }
        else if((telephone.getText().length()!=8)  ||  ( telephone.getText().startsWith("1")) 
        ||  ( telephone.getText().startsWith("3")) ||  ( telephone.getText().startsWith("6"))
        ||  ( telephone.getText().startsWith("8"))

        ){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("Please enter a valid Phone");
        alert.show();
                }
        else if( (password.getText().length()<6 )){
            Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("password must be at least 6 characters long");
        alert.show();
        }
         
        else
        {
                
                Base64.Encoder encoder = Base64.getEncoder();
                String pass = encoder.encodeToString(password.getText().getBytes());
                
                User u = new User(nom.getText(),prenom.getText(),"["+"ROLE_USER"+"]",email.getText(),telephone.getText(),pass,userSession.img);
              if(path!=null){
                  u.setImg(path);
              }
                      
                UserService us = new UserService();
                us.modifier(u);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("Account Updated");
                alert.show();
        }
    }

    @FXML
    private void Logout(ActionEvent event) {
         Stage primaryStage = new Stage();
        
        try {
            ((Stage) logoutButton.getScene().getWindow()).close();
            Parent root = FXMLLoader.load(getClass().getResource("/gui/Login.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("PROTECH");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        userSession.id=0;
        userSession.nom=null;
        userSession.prenom=null;
        userSession.email=null;
        userSession.telephone=null;
        userSession.password=null;
        userSession.img=null;
        userSession.isLoggedIn=false;
        
        
    }


   

    @FXML
    private void addImg(ActionEvent event) {
        FileChooser f = new FileChooser();
        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("jpeg,png files", type));
        File fc = f.showOpenDialog(null);
        if (f != null) {
//            try {
                    imgG = fc.getName().toString();
                    path = fc.getAbsoluteFile().toURI().toString();
                    
//                    Path targetPath = (Path)Paths.get("/Users", "skanderzouaoui",
//                                      "Applications", "XAMPP", "htdocs" , "uploads");
//                    System.out.println(targetPath);

//                    Path temp = Files.copy(fc.toPath(), Paths.get(imgG), StandardCopyOption.REPLACE_EXISTING);
                    Image i = new Image(fc.getAbsoluteFile().toURI().toString());
                    img.setImage(i);
                    message.setText("Your image was uploaded successfully.");
//                } catch (IOException ex) {
//                     message.setText("Error");
//                    System.out.println(ex.getMessage());
//                }
        }
        fc.exists();
    }

    @FXML
    private void home(ActionEvent event) {
         Stage primaryStage = new Stage();
        
        try {
            ((Stage) logoutButton.getScene().getWindow()).close();
            Parent root = FXMLLoader.load(getClass().getResource("/gui/market.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("PROTECH");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void boutique(ActionEvent event) {
         Stage primaryStage = new Stage();
        
        try {
            ((Stage) logoutButton.getScene().getWindow()).close();
            Parent root = FXMLLoader.load(getClass().getResource("/gui/BoutiqueFront.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("PROTECH");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void event(ActionEvent event) {
         Stage primaryStage = new Stage();
        
        try {
            ((Stage) logoutButton.getScene().getWindow()).close();
            Parent root = FXMLLoader.load(getClass().getResource("/gui/Login.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("PROTECH");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void reclamation(ActionEvent event) {
         Stage primaryStage = new Stage();
        
        try {
            ((Stage) logoutButton.getScene().getWindow()).close();
            Parent root = FXMLLoader.load(getClass().getResource("/gui/Login.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("PROTECH");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void addToCart(ActionEvent event) {
         Stage primaryStage = new Stage();
        
        try {
            ((Stage) logoutButton.getScene().getWindow()).close();
            Parent root = FXMLLoader.load(getClass().getResource("/gui/cart.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("PROTECH");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
}
