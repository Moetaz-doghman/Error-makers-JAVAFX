/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
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
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author skanderzouaoui
 */
public class LoginController implements Initializable {
    static Cipher cipher;

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
        
        
             try {
                KeyGenerator keyGenerator;
                keyGenerator = KeyGenerator.getInstance("AES");
                keyGenerator.init(128); // block size is 128bits
                SecretKey secretKey = keyGenerator.generateKey();
                 
                cipher = Cipher.getInstance("AES");
                
                UserService us = new UserService(); 
                
                String encryptedPassword = encrypt(password.getText(), secretKey);
                
                if(us.login(email.getText(), password.getText())){
                
               Stage primaryStage = new Stage();
        
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
     
                }else
                {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Login Failed");
                alert.show();
                    
                }
 
                } catch (NoSuchAlgorithmException ex) {
                 System.out.println(ex.getMessage());
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
        
    
    
    public static String encrypt(String plainText, SecretKey secretKey)
            throws Exception {
        byte[] plainTextByte = plainText.getBytes();
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedByte = cipher.doFinal(plainTextByte);
        Base64.Encoder encoder = Base64.getEncoder();
        String encryptedText = encoder.encodeToString(encryptedByte);
        return encryptedText;
    }
    

   

}
