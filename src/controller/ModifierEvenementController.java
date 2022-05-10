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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import entity.Evenement;
import entity.userSession;
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
 * @author doghm
 */
public class ModifierEvenementController implements Initializable {
    
    List<String> type;
    String imgG = "";
    String path = null;

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
    private Button reponse;
    @FXML
    private Button Boutique;
    @FXML
    private Button addboutique;
    @FXML
    private Button produit;
    @FXML
    private Button addproduit;
    @FXML
    private Button listlivrasion;
    @FXML
    private Button addlivrasion;
    @FXML
    private Button listvehicule;
    @FXML
    private Button addvehicule;
    @FXML
    private Button logoutButton;
    @FXML
    private StackPane stckCommande;
    @FXML
    private AnchorPane rootCommande;
    @FXML
    private Pane ContainerUsersAdmin;
    @FXML
    private Button browseimgbtn;
    @FXML
    private Button enregistrerbtn;
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
    private ImageView img;
    @FXML
    private TextField affichetf;
    @FXML
    private Button annulerbtn;

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
                    Animations.fadeInUp(rootCommande);

        }
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

    @FXML
    private void gererreponse(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/ListReponse.fxml"));
        btnjo.getScene().setRoot(root);
    }

    @FXML
    private void gererlistlivraison(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/ListLivraison.fxml"));
        btnjo.getScene().setRoot(root);
    }

    @FXML
    private void gereraddlivraison(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/ajouterL.fxml"));
        btnjo.getScene().setRoot(root);
    }

    @FXML
    private void gererlistvehicule(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/ListVehicule.fxml"));
        btnjo.getScene().setRoot(root);
    }

    @FXML
    private void gereraddvehicule(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/ajouterV.fxml"));
        btnjo.getScene().setRoot(root);
    }
    
    
    @FXML
    private void gererevent(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/AfficherEventBack.fxml"));
        btnjo.getScene().setRoot(root);
    }

    @FXML
    private void gererreclamation(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/ListReclamation.fxml"));
        btnjo.getScene().setRoot(root);
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
    private void iconAddCommandeClicked(MouseEvent event) {
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

            annulerbtn.getScene().setRoot(FXMLLoader.load(getClass().getResource("../GUI/AfficherEventBack.fxml")));
        }
    }

    @FXML
    private void annuler(ActionEvent event) {
            Stage primaryStage = new Stage();

        try {
            ((Stage) annulerbtn.getScene().getWindow()).close();
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/AfficherEventBack.fxml"));
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
