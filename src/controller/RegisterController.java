/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import entity.User;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.EmailService;
import service.UserService;

/**
 * FXML Controller class
 *
 * @author skanderzouaoui
 */
public class RegisterController implements Initializable {
    

    Stage primaryStage = new Stage();
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
    private PasswordField confirmpassword;
    @FXML
    private Hyperlink loginButton;
    @FXML
    private Button registerButton;
    @FXML
    private Hyperlink proaccountButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    private void exit(ActionEvent event) {
        System.exit(0);
    }
    
     public boolean ControleSaisie(){
        EmailService es = new EmailService();
        
         if ((prenom.getText().length()==0)||(nom.getText().length()==0) 
                 || (email.getText().length()==0)|| (telephone.getText().length()==0) 
                 ||(password.getText().length()==0)){

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Please fill all the fields");
        alert.show();
        return false;
        }
        else if((!email.getText().contains("@")) && (!email.getText().contains("."))){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Please enter a valid Email");
        alert.show();
                return false;

                }
        else if((telephone.getText().length()!=8)  ||  ( telephone.getText().startsWith("1")) 
        ||  ( telephone.getText().startsWith("3")) ||  ( telephone.getText().startsWith("6"))
        ||  ( telephone.getText().startsWith("8"))

        ){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Please enter a valid Phone");
        alert.show();
                return false;

                }
        else if(!confirmpassword.getText().equals(password.getText())){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Passwords do NOT match");
        alert.show();
                return false;

        }
        else if( (password.getText().length()<6 )){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("password must be at least 6 characters long");
        alert.show();
                return false;

        }else if(es.emailExist(email.getText())){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("This email has already an account");
        alert.show();
        return false;
            
        }
         return true;
        
    }
    @FXML
    private void Register(ActionEvent event) throws Exception {
        if(ControleSaisie()){
                UserService us = new UserService();
                Encoder encoder = Base64.getEncoder();
                String pass = encoder.encodeToString(password.getText().getBytes());
     
                User u = new User(nom.getText(),prenom.getText(),"[\"ROLE_USER\"]",email.getText(),telephone.getText(),pass);  
                us.ajouter(u);
                
                
                ((Stage) registerButton.getScene().getWindow()).close();
                    Parent root = FXMLLoader.load(getClass().getResource("/gui/Login.fxml"));
                    Scene scene = new Scene(root);
                    primaryStage.setTitle("PROTECH");
                    primaryStage.setScene(scene);
                    primaryStage.show();
                    
                    
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText("Account Created");
                    alert.show();
              
         }
    }

    @FXML
    private void login(ActionEvent event) {
        
        
        try {
            ((Stage) loginButton.getScene().getWindow()).close();
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
    private void proAccount(ActionEvent event) {
        
        try {
            ((Stage) proaccountButton.getScene().getWindow()).close();
            Parent root = FXMLLoader.load(getClass().getResource("/gui/ProRegister.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("PROTECH");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
   

   
    
}
