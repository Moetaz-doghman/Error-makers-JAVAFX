/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Config.MailerApi;
import entities.Boutique;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.BoutiqueService;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AddBoutiqueController implements Initializable {

    List<String> type;
    String imgG = "";
    Boutique f = null;

    @FXML
    private Button ajout;

    @FXML
    private TextArea descBoutique;

    @FXML
    private ImageView imageview;
    
    @FXML
    private Button imagechose;


    @FXML
    private TextField nomBoutique;

    private TextField prixBoutique;

    private TextField quantiteBoutique;
    @FXML
    private Label message;
    @FXML
    private TextField adresseBoutique;
    @FXML
    private Button liste;
    @FXML
    private Button z;
    @FXML
    private Button o;
    @FXML
    private Button p;

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
    private void ajouterBoutique(javafx.event.ActionEvent event) throws SQLException, IOException {
        BoutiqueService sf = new BoutiqueService();
        
        if ((nomBoutique.getText().equals(""))||(descBoutique.getText().equals(""))  
               ||(adresseBoutique.getText().equals("")))
  {Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("champs manquants !");
            alert.showAndWait();}
       else if (Pattern.matches("[a-zA-Z]+", nomBoutique.getText()) == false) 
       {Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Le nom de la boutique doit être composée de lettres seulement! ");
            alert.showAndWait();}
        else if (Pattern.matches("[a-zA-Z]+", descBoutique.getText()) == false) 
       {Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Le nom de la boutique doit être composée de lettres seulement! ");
            alert.showAndWait();}
        else {
        if (imgG.length() == 0) {

            sf.ajouterBoutique(
                    new Boutique(0, nomBoutique.getText(), descBoutique.getText(), adresseBoutique.getText(), ""));
        }
            sf.ajouterBoutique(new Boutique(0, nomBoutique.getText(), descBoutique.getText(), adresseBoutique.getText(), imgG));
        }
        
          MailerApi mailer = new MailerApi();
        mailer.SendMail("donia.sarsar@esprit.tn", "Admin.");
           Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/gui/ListBoutique.fxml")));
        stage.setScene(scene);
        stage.show();
    /*    prixBoutique.clear();
        quantiteBoutique.clear();
        descBoutique.setText(null);
        imageview.setImage(null);
        nomBoutique.clear();
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

            org.apache.commons.net.ftp.FTPClient ftpClient = new org.apache.commons.net.ftp.FTPClient();
            try {

                ftpClient.connect(server, port);
                ftpClient.login(user, pass);
                ftpClient.enterLocalPassiveMode();
                String $mess = ftpClient.getReplyString();
                System.out.println($mess);
                ftpClient.setFileType(org.apache.commons.net.ftp.FTPClient.BINARY_FILE_TYPE);

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
    private void PageListB (javafx.event.ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/gui/ListBoutique.fxml")));
        stage.setScene(scene);
        stage.show();
    }
@FXML
    private void PageAddP(javafx.event.ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/gui/AddProduit.fxml")));
        stage.setScene(scene);
        stage.show();
    }
  @FXML
    private void PageListP (javafx.event.ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("ListProduit.fxml")));
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

    @FXML
    private void PageAddB(javafx.event.ActionEvent event) {
    }
     
  

    
}
