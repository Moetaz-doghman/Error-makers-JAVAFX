/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entities.User;
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
import javafx.scene.control.Button;
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
public class RegisterController implements Initializable {
    
    static Cipher cipher; 

    
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
    
    
    @FXML
    private void Register(ActionEvent event) throws Exception {
        
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
        else if(!confirmpassword.getText().equals(password.getText())){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("Passwords do NOT match");
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
        KeyGenerator keyGenerator;
             try {
                keyGenerator = KeyGenerator.getInstance("AES");
                keyGenerator.init(128); // block size is 128bits
                SecretKey secretKey = keyGenerator.generateKey();
                 
                cipher = Cipher.getInstance("AES");
                UserService us = new UserService();
                String encryptedText = encrypt(password.getText(), secretKey);
                
                User u = new User(nom.getText(),prenom.getText(),"[]",email.getText(),telephone.getText(),password.getText());
                
                us.ajouter(u);
                
//                Stage primaryStage = new Stage();
//                 ((Stage) registerButton.getScene().getWindow()).close();
//                    Parent root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
//                    Scene scene = new Scene(root);
//                    primaryStage.setTitle("PROTECH");
//                    primaryStage.setScene(scene);
//                    primaryStage.show();
                    
                    
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Success");
                alert.setContentText("Account Created");
                alert.show();
                
                
                nom.clear();
                prenom.clear();
                email.clear();
                telephone.clear();
                password.clear();
                confirmpassword.clear();
                
//                String decryptedText = decrypt(encryptedText, secretKey);
//                System.out.println("Decrypted Text After Decryption: " + decryptedText);
                 
             } catch (NoSuchAlgorithmException ex) {
                 System.out.println(ex.getMessage());
             }
        
         }
    }

    @FXML
    private void login(ActionEvent event) {
        
         Stage primaryStage = new Stage();
        
        try {
            ((Stage) loginButton.getScene().getWindow()).close();
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
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
