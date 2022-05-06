/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import entity.userSession;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.UserService;

/**
 * FXML Controller class
 *
 * @author skanderzouaoui
 */
public class LoginController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private Hyperlink registerButton;
    @FXML
    private Hyperlink passwordForgottenButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Login(ActionEvent event) throws Exception {
         
                UserService us = new UserService();
                
                Base64.Encoder encoder = Base64.getEncoder();
                String pass = encoder.encodeToString(password.getText().getBytes());
                
                
                Stage primaryStage = new Stage();
                 
               if(us.login(email.getText(), pass)){
                   if(userSession.isActive == false){
                     
                    //Alert
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Sorry your account is blocked");
                    
                    alert.show();
                    password.clear();
   
                   }else if(userSession.role.equals("[\"ROLE_ADMIN\"]")){
                        try {
                    ((Stage) registerButton.getScene().getWindow()).close();
                    Parent root = FXMLLoader.load(getClass().getResource("/gui/UsersList.fxml"));
                    Scene scene = new Scene(root);
                    primaryStage.setTitle("PROTECH");
                    primaryStage.setScene(scene);
                    primaryStage.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                       
                   }
                   else {
                       try {
                    ((Stage) registerButton.getScene().getWindow()).close();
                    Parent root = FXMLLoader.load(getClass().getResource("/gui/Profile.fxml"));
                    Scene scene = new Scene(root);
                    primaryStage.setTitle("PROTECH");
                    primaryStage.setScene(scene);
                    primaryStage.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                       
                   }

                }else
                {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Login Failed, Email or Password is invalid");
                alert.show();
                    
                }
 
        

    }

    @FXML
    private void register(ActionEvent event) {
        Stage primaryStage = new Stage();
        
        try {
            ((Stage) registerButton.getScene().getWindow()).close();
            Parent root = FXMLLoader.load(getClass().getResource("/gui/Register.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("PROTECH");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void ForgottenPassword(ActionEvent event) {
        Stage primaryStage = new Stage();
        
         try {
                    ((Stage) registerButton.getScene().getWindow()).close();
                    Parent root = FXMLLoader.load(getClass().getResource("/gui/ForgotPassword.fxml"));
                    Scene scene = new Scene(root);
                    primaryStage.setTitle("PROTECH");
                    primaryStage.setScene(scene);
                    primaryStage.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                       
        }
    
        

}
