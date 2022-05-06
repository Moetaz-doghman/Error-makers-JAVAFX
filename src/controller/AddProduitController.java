/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import animations.Animations;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import entity.Produit;
import entity.userSession;
//import Config.SMSApi;
//import java.awt.Button;
//import java.awt.TextArea;
//import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.apache.commons.net.ftp.FTPClient;
import service.ProduitService;
//import org.apache.commons.net.ftp.FTPClient;
//import tray.animations.AnimationType;
//import tray.notification.NotificationType;
//import tray.notification.TrayNotification;


/**
 * FXML Controller class
 *
 * @author doghm
 */
public class AddProduitController implements Initializable {
    List<String> type;
    String imgG = "";
    Produit f = null;
    @FXML
    private Button listUserButton;
    @FXML
    private Button ListDemandeButton;
    @FXML
    private Button btnev1;
    @FXML
    private Button btnjo;
    @FXML
    private Button btnevent;
    @FXML
    private Button Reclamation;
    @FXML
    private Button logoutButton;
    @FXML
    private StackPane stckCommande;
    @FXML
    private AnchorPane rootCommande;
    @FXML
    private Pane ContainerUsersAdmin;
    @FXML
    private Button imagechose;
    @FXML
    private Button ajout;
    @FXML
    private Label message;
    @FXML
    private ImageView imageview;
    @FXML
    private TextField nomProduit;
    @FXML
    private TextField quantiteProduit;
    @FXML
    private TextArea descProduit;
    @FXML
    private TextField prixProduit;
    @FXML
    private Button Boutique;
    @FXML
    private Button addboutique;
    @FXML
    private Button produit;
    @FXML
    private Button addproduit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         type = new ArrayList<>();
        type.add("*.jpg");
        type.add("*.png");
         Animations.fadeInUp(rootCommande);
    }    

  @FXML
    private void ListUser(ActionEvent event) throws IOException {
      //  Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("../GUI/usersList.fxml"));
       Parent root = FXMLLoader.load(getClass().getResource("../GUI/UsersList.fxml"));
        btnjo.getScene().setRoot(root);
    }

    @FXML
    private void ListDemande(ActionEvent event) throws IOException {
//        Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("../GUI/DemandesList.fxml"));
//        ListDemandeButton.getScene().setRoot(root);
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/DemandesList.fxml"));
        btnjo.getScene().setRoot(root);
        
    }
        @FXML
    private void gererligne(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("../GUI/LigneBack.fxml"));
        btnjo.getScene().setRoot(root);
    }

    @FXML
    private void gerercommande(ActionEvent event)  throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("../GUI/CommandeBack.fxml"));
        btnjo.getScene().setRoot(root);
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
        userSession.isLoggedIn=false;
    }
    


    @FXML
    private void gererevent(ActionEvent event) {
    }

    @FXML
    private void gererreclamation(ActionEvent event) {
    }



    @FXML
    private void iconAddCommandeClicked(MouseEvent event) {
    }

    @FXML
    // fontion hedhii ta3ml l upload lel image en local et sur un serveur ftp ( API  mezyen )  n3aytolha fl fonction ajouter
    private void import_image(javafx.event.ActionEvent event) throws IOException {
        FileChooser f = new FileChooser();
        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("jpeg,png files", type));
        File fc = f.showOpenDialog(null);
        if (f != null) {
            String server = "ftpupload.net";
            int port = 21;
            String user = "unaux_31559503";
            String pass = "3e0kvlp";

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
                    message.setText("your image was uploaded successfully.");
                } else {
                    message.setText("error");
                }

            } catch (IOException ex) {
                System.out.println("Error: " + ex.getMessage());
                ex.printStackTrace();
            } finally {
                try {
                    if (ftpClient.isConnected()) {
                        ftpClient.logout();
                        ftpClient.disconnect();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            imgG = fc.getName().toString();

            // il faut changer le path 
            
            Path temp = Files.copy(fc.toPath(), Paths.get("C:\\Users\\doghm\\Desktop\\Error-makers-JAVAFX\\src\\images\\" + imgG), StandardCopyOption.REPLACE_EXISTING);
            Image i = new Image(fc.getAbsoluteFile().toURI().toString());
            imageview.setImage(i);
        }
        fc.exists();
    }

    @FXML
    private void ajouterProduit(javafx.event.ActionEvent event) throws SQLException, IOException {
        ProduitService sf = new ProduitService();
        
        if ((prixProduit.getText().equals(""))||(quantiteProduit.getText().equals(""))  
               ||(descProduit.getText().equals(""))||(nomProduit.getText().equals("")))
  {Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("champs manquants !");
            alert.showAndWait();}
       else if (Pattern.matches("[a-zA-Z]+", prixProduit.getText()) == true) 
       {Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Le prix doit être composée de chiffres seulement! ");
            alert.showAndWait();}
        else if (Pattern.matches("[a-zA-Z]+", quantiteProduit.getText()) == true) 
       {Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("La quantite doit être composée de chiffres seulement! ");
            alert.showAndWait();}
         else if (Pattern.matches("[a-zA-Z]+", descProduit.getText()) == false) 
      /* {Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("La description doit être composée de lettres seulement! ");
            alert.showAndWait();}
          else if (Pattern.matches("[a-zA-Z]+", nomProduit.getText()) == false) 
       {Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("La description doit être composée de lettres seulement! ");
            alert.showAndWait();}*/
        
        if (imgG.length() == 0) {

            sf.ajouterProduit(
                    new Produit(0, prixProduit.getText(), quantiteProduit.getText(), descProduit.getText(), "", nomProduit.getText()));
        }
            sf.ajouterProduit(new Produit(0, prixProduit.getText(), quantiteProduit.getText(), descProduit.getText(), imgG, nomProduit.getText()));
       
                     //Notification
        String tilte;
        String message;
//        TrayNotification tray = new TrayNotification();
//        AnimationType type = AnimationType.POPUP;
//        tray.setAnimationType(type);
//        tilte = "Added Successful";
//        message ="produit ajouter avec success";
//        tray.setTitle(tilte);
//        tray.setMessage(message);
//        tray.setNotificationType(NotificationType.SUCCESS);
//        tray.showAndDismiss(Duration.millis(2000));
        
         
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/gui/ListProduit.fxml")));
        stage.setScene(scene);
        stage.show();
    
                //    SMSApi sms = new SMSApi();    
//sms.sendSMS("+21655841954", "Admin.");

    /*    prixProduit.clear();
        quantiteProduit.clear();
        descProduit.setText(null);
        imageview.setImage(null);
        nomProduit.clear();
*/

    }

    @FXML
    private void gererboutique(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/ListBoutique.fxml"));
        btnjo.getScene().setRoot(root);
    }

    @FXML
    private void gereraddboutique(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/AddBoutique.fxml"));
        btnjo.getScene().setRoot(root);
    }

    @FXML
    private void gererproduit(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/ListProduit.fxml"));
        btnjo.getScene().setRoot(root);
    }
    

    @FXML
    private void gereraddproduit(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/AddProduit.fxml"));
        btnjo.getScene().setRoot(root);
    }
    
}
