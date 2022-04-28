/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Config.SMSApi;
import entities.Produit;
import java.awt.Button;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
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
import services.ProduitService;
import org.apache.commons.net.ftp.FTPClient;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AddProduitController implements Initializable {

    List<String> type;
    String imgG = "";
    Produit f = null;

    @FXML
    private javafx.scene.control.Button ajout;

    @FXML
    private javafx.scene.control.TextArea descProduit;

    @FXML
    private ImageView imageview;
    
    @FXML
    private javafx.scene.control.Button imagechose;


    @FXML
    private TextField nomProduit;

    @FXML
    private TextField prixProduit;

    @FXML
    private TextField quantiteProduit;
    @FXML
    private Label message;
    @FXML
    private javafx.scene.control.Button Liste;
    @FXML
    private javafx.scene.control.Button listaa;
    @FXML
    private javafx.scene.control.Button a;
    @FXML
    private javafx.scene.control.Button addB;

    void Front(ActionEvent event) {
    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO  
        type = new ArrayList<>();
        type.add("*.jpg");
        type.add("*.png");
    }    
    // Afficher tt les produits

 
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
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tilte = "Added Successful";
        message ="produit ajouter avec success";
        tray.setTitle(tilte);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(2000));
        
         
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/gui/ListProduit.fxml")));
        stage.setScene(scene);
        stage.show();
    
                    SMSApi sms = new SMSApi();    
//sms.sendSMS("+21655841954", "Admin.");

    /*    prixProduit.clear();
        quantiteProduit.clear();
        descProduit.setText(null);
        imageview.setImage(null);
        nomProduit.clear();
*/

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
            Path temp = Files.copy(fc.toPath(), Paths.get("C:\\Users\\HP\\Desktop\\3A451\\3A45\\src\\images\\" + imgG), StandardCopyOption.REPLACE_EXISTING);
            Image i = new Image(fc.getAbsoluteFile().toURI().toString());
            imageview.setImage(i);
        }
        fc.exists();
    }
    
    // changement de page
    @FXML
    private void PageListP(javafx.event.ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/gui/ListProduit.fxml")));
        stage.setScene(scene);
        stage.show();
    }
  @FXML
    private void PageListB (javafx.event.ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/gui/ListBoutique.fxml")));
        stage.setScene(scene);
        stage.show();
    }
     @FXML
    private void PageAddB (javafx.event.ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/gui/AddBoutique.fxml")));
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void Front (javafx.event.ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/gui/ProduitsF.fxml")));
        stage.setScene(scene);
        stage.show();
    }

}

