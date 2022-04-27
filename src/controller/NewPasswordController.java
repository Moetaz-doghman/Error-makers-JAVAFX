/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import entities.userSession;
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
import javafx.stage.Stage;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author skanderzouaoui
 */
public class NewPasswordController implements Initializable {

    @FXML
    private Button confirmButton;
    @FXML
    private Hyperlink loginButton;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField confirmPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Confirm(ActionEvent event) {
        if(!confirmPassword.getText().equals(password.getText())){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Passwords do NOT match");
        alert.show();
        }
        else if( (password.getText().length()<6 )){
            Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("password must be at least 6 characters long");
        alert.show();
        }
         
        else{
        
        UserService us = new UserService();
        Encoder encoder =Base64.getEncoder();
        String hash = encoder.encodeToString(password.getText().getBytes());          
        us.updatePassword(userSession.Emailreset, hash);
        
         Stage primaryStage = new Stage();
        
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
    }

    @FXML
    private void Login(ActionEvent event) {
    }
    
}
