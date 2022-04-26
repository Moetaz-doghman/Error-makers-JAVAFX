/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author mariem
 */
public class Start extends Application {
    
    @Override
    public void start(Stage primaryStage) { try {
        //Stage fenetre ou executer l app
        
                Parent root = FXMLLoader.load(getClass().getResource("ListReclamation.fxml"));  //permet de charger un fichier fxml (interface)
                Scene scene = new Scene(root); //scene : interface affichée à l'utilisateur  //root: les acteurs= composants graphiques

                primaryStage.setTitle("Hello World!");
                primaryStage.setScene(scene);
                primaryStage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}