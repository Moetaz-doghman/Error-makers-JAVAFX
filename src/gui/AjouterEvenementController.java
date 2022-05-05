/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entité.Evenement;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import service.EvenementService;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AjouterEvenementController implements Initializable {
    List<String> type;
    String imgG = "";
    String path = null;

    @FXML
    private AnchorPane apCreerEvenement;
    @FXML
    private TextField nomtf;
    @FXML
    private TextField adressetf;
    @FXML
    private DatePicker datedebut;
    @FXML
    private DatePicker datefin;
    @FXML
    private TextField descriptiontf;
    @FXML
    private TextArea longdesctf;
    @FXML
    private TextField affichetf;
    @FXML
    private Button annulerbtn;
    @FXML
    private Button enregistrerbtn;
    @FXML
    private ImageView img;
    @FXML
    private Button browseimgbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        type = new ArrayList<>();
        type.add("*.jpg");
        type.add("*.png");    }    

    @FXML
    private void annuler(ActionEvent event) throws IOException {
                annulerbtn.getScene().setRoot(FXMLLoader.load(getClass().getResource("FXMLEvenement.fxml")));

    }

    @FXML
    private void enregistrer(ActionEvent event) {
        
        if (nomtf.getText().isEmpty()
                || adressetf.getText().isEmpty()
                || descriptiontf.getText().isEmpty()
                || affichetf.getText().isEmpty()
                || longdesctf.getText().isEmpty()
                || datedebut.getValue() == null
                || datefin.getValue() == null
                || new java.util.Date(java.sql.Date.valueOf(datefin.getValue()).getTime())
                        .before(
                                new java.util.Date(java.sql.Date.valueOf(datedebut.getValue()).getTime())
                        )
            || new java.util.Date(java.sql.Date.valueOf(datedebut.getValue()).getTime())
                        .before(
                                new java.util.Date()
                        )) {
        } else {
                    Evenement evenement = new Evenement(
                    nomtf.getText(),
                    0.,
                    adressetf.getText(),
                    new java.util.Date(java.sql.Date.valueOf(datedebut.getValue()).getTime()),
                    new java.util.Date(java.sql.Date.valueOf(datefin.getValue()).getTime()),
                    descriptiontf.getText(),
                    null,
                    longdesctf.getText()
            );
                evenement.setAffiche(path);
                new EvenementService().insert(evenement);

           
            

            try {
                annulerbtn.getScene().setRoot(FXMLLoader.load(getClass().getResource("AfficherEventBack.fxml")));
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @FXML
    private void browseimg(ActionEvent event) {
         FileChooser f = new FileChooser();
        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("jpeg,png files", type));
        File fc = f.showOpenDialog(null);
        if (f != null) {
//            try {
                    imgG = fc.getName().toString();
                    path = fc.getAbsoluteFile().toURI().toString();
                    
//                    Path targetPath = (Path)Paths.get("/Users", "skanderzouaoui",
//                                      "Applications", "XAMPP", "htdocs" , "uploads");
//                    System.out.println(targetPath);

//                    Path temp = Files.copy(fc.toPath(), Paths.get(imgG), StandardCopyOption.REPLACE_EXISTING);
                    Image i = new Image(fc.getAbsoluteFile().toURI().toString());
                    img.setImage(i);
                    affichetf.setText("Your image was uploaded successfully.");
//                } catch (IOException ex) {
//                     message.setText("Error");
//                    System.out.println(ex.getMessage());
//                }
        }
        fc.exists();
    }
    
    
    
}
