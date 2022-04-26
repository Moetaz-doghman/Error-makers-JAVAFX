/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import entities.User;
import entities.userSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.UserService;
import org.apache.commons.net.ftp.FTPClient;




/**
 * FXML Controller class
 *
 * @author skanderzouaoui
 */
public class ProfileController implements Initializable {
    
    List<String> type;
    String imgG = "";
    String path = "";

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
        
        
        Image image =new Image(getClass().getResourceAsStream("/images/user1.png"));
        img.setImage(image);
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
                
                User u = new User(nom.getText(),prenom.getText(),"["+"ROLE_USER"+"]",email.getText(),telephone.getText(),pass);
                u.setImg(path);
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
            try {
                String server = "ftpupload.net";
                int port = 21;
                String user = "unaux_31603441";
                String pass = "xj7aawjvl4";
                
                FTPClient ftpClient = new FTPClient();
                try {
                    
                    ftpClient.connect(server, port);
                    ftpClient.login(user, pass);
                    ftpClient.enterLocalPassiveMode();
                    String $mess = ftpClient.getReplyString();
                    System.out.println($mess);
                    ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                    
                    File firstLocalFile = new File(fc.getAbsoluteFile().toString());
                    
                    String firstRemoteFile = "/htdocs/" + fc.getName().toString();
                    InputStream inputStream = new FileInputStream(firstLocalFile);
                    
                    System.out.println("Start uploading image");
                    boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
                    inputStream.close();
                    if (done) {
                        message.setText("Your image was uploaded successfully.");
                    } else {
                        message.setText("Error");
                    }
                    
                } catch (IOException ex) {
                    System.out.println("Error: " + ex.getMessage());
                    ex.printStackTrace();
                } finally {
                    if (ftpClient.isConnected()) {
                        ftpClient.logout();
                        ftpClient.disconnect();
                    }
                }
                imgG = fc.getName().toString();
                path = fc.getAbsoluteFile().toURI().toString();
                
                 //il faut changer le path
                Path temp = Files.copy(fc.toPath(), Paths.get("Documents\\Error-makers-JAVAFX\\src\\images\\" + imgG), StandardCopyOption.REPLACE_EXISTING);
                Image i = new Image(fc.getAbsoluteFile().toURI().toString());
                System.out.println("********************"+fc.getAbsoluteFile().toURI().toString());
                img.setImage(i);
            } catch (IOException ex) {
                    System.out.println(ex.getMessage());
            }
        }
        fc.exists();
    }
    
    
}
