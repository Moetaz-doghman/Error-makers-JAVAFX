/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import entities.userSession;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.EmailService;

/**
 * FXML Controller class
 *
 * @author skanderzouaoui
 */
public class ForgotPasswordController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private Hyperlink loginButton;
    
    Stage primaryStage = new Stage();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Valider(ActionEvent event) {
        

        EmailService es = new EmailService();
        
        if(!es.emailExist(email.getText())){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Please enter a valid Email");
        alert.show();
                }
        else{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Email Sent");
                alert.show();
                       
          try {
                   ((Stage) loginButton.getScene().getWindow()).close();
                    Parent root = FXMLLoader.load(getClass().getResource("/gui/InsertCode.fxml"));
                    Scene scene = new Scene(root);
                    primaryStage.setTitle("PROTECH");
                    primaryStage.setScene(scene);
                    primaryStage.show();
                    
                    
                        String code = codeRecuperation();
                        es.InsertCodeEmail(code, email.getText());
                        es.envoyer(code);
                        userSession.Emailreset=email.getText();
                        
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
    
            
        }
    }
    public String codeRecuperation(){
           List<Integer> numbers = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            numbers.add(i);
        }

        Collections.shuffle(numbers);

        String result = "";
        for(int i = 0; i < 5 ; i++){
            result += numbers.get(i).toString();
        }
        System.out.println(result);
        return result;

}     

    @FXML
    private void Login(ActionEvent event) {
         
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
