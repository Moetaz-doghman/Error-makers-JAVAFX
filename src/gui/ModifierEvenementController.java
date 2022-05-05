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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import service.EvenementService;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class ModifierEvenementController implements Initializable {
    List<String> type;
    String imgG = "";
    String path = null;
    
    @FXML
    private AnchorPane apmodifierevenement;
    @FXML
    private Button enregistrerbtn;
    @FXML
    private Button annulerbtn;
    @FXML
    private TextField nomtf;
    @FXML
    private TextField adressetf;
    @FXML
    private TextField descriptiontf;
    @FXML
    private TextArea longdesctf;
    @FXML
    private TextField affichetf;
    @FXML
    private ImageView img;
    @FXML
    private Button BrowseBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         type = new ArrayList<>();
        type.add("*.jpg");
        type.add("*.png"); 
        
        EvenementService evenementService = new EvenementService();
        Evenement evenement = evenementService.findById(Id.evenementId);
        adressetf.setText(evenement.getAdresse());
        nomtf.setText(evenement.getNom());
        descriptiontf.setText(evenement.getDescription());
        affichetf.setText(evenement.getAffiche());
        longdesctf.setText(evenement.getLongdesc());
        if(evenement.getAffiche()!=null){
        Image image =new Image(evenement.getAffiche());
        img.setImage(image);
        }else{
            Image image =new Image("images/vide.png");
            img.setImage(image);
            
        }
    }    

    @FXML
    private void enregistrer(ActionEvent event) throws IOException {
        EvenementService evenement = new EvenementService();
        Evenement oldevenement = evenement.findById(Id.evenementId);
        
          if (nomtf.getText().isEmpty()
                || adressetf.getText().isEmpty()
                || descriptiontf.getText().isEmpty()
                || affichetf.getText().isEmpty()
                || longdesctf.getText().isEmpty()
           
              
                        ) {
        } else {
              oldevenement.setAdresse(adressetf.getText());
              oldevenement.setAffiche(path);
              oldevenement.setDescription(descriptiontf.getText());
              oldevenement.setLongdesc(longdesctf.getText());
              oldevenement.setNom(nomtf.getText());
         
            new EvenementService().modify(oldevenement);

            annulerbtn.getScene().setRoot(FXMLLoader.load(getClass().getResource("AfficherEventBack.fxml")));
        }
    }

    @FXML
    private void annuler(ActionEvent event) {
            Stage primaryStage = new Stage();

        try {
            ((Stage) annulerbtn.getScene().getWindow()).close();
            Parent root = FXMLLoader.load(getClass().getResource("AfficherEventBack.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("PROTECH");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void BrowseImg(ActionEvent event) {
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
