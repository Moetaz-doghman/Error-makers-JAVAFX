/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.NewFXMain.main;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


  
/**
 *
 * @author doghm
 */
public class NewFXMain1 extends Application {
     public static final String CURRENCY = "$";

    Parent root;
    private static Stage Window;

    @Override
    public void start(Stage stage) {
        
          try {
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
//       Scene scene = new Scene(root, 600, 600);
//        primaryStage.setTitle("Hello World!");
//        primaryStage.setScene(scene);
//        primaryStage.show();
           stage.setTitle("Shopping Cart"); 
           //stage.initStyle(StageStyle.TRANSPARENT);
           stage.setScene(new Scene(root,Color.TRANSPARENT));
           
           stage.show();
           
        } catch (IOException ex) {
         //   Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     *
     * @return
     */
    public static Stage getWindow()
    {
        return NewFXMain1.Window;
    }

}
