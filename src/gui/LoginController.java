/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entities.userSession;
import java.io.IOException;
import java.net.URL;
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
import services.UserService;

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
                
                Stage primaryStage = new Stage();
                 
               if(us.login(email.getText(), password.getText())){
                   if(userSession.isActive == false){
                     
                    //Alert
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Sorry your account is blocked");
                    alert.show();
                    password.clear();
   
                   }else{
                       try {
                    ((Stage) registerButton.getScene().getWindow()).close();
                    Parent root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
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
                alert.setContentText("Login Failed, Email or Password is invalid");
                alert.show();
                    
                }
 
        

    }

    @FXML
    private void register(ActionEvent event) {
        Stage primaryStage = new Stage();
        
        try {
            ((Stage) registerButton.getScene().getWindow()).close();
            Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("PROTECH");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
        

}
