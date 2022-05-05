/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entité.Commentaire;
import entité.Evenement;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.CommentaireService;
import service.EvenementService;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AfficherEventBackController implements Initializable {

   EvenementService evenements;
   CommentaireService commentaires;
    @FXML
    private TableView<Evenement> tvevent;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> nom;
    @FXML
    private TableColumn<?, ?> adresse;
    @FXML
    private TableColumn<?, ?> Date;
    @FXML
    private TableColumn<?, ?> datefin;
    @FXML
    private TableColumn<?, ?> description;
    @FXML
    private TableColumn<?, ?> longdesc;
    @FXML
    private TableView<Commentaire> tvcom;
    
    @FXML
    private Button btnModifierEvenement;
    @FXML
    private Button btnCreerEvenement;
    @FXML
    private Button btnSupprimerEvenement;
    @FXML
    private Button btnSupprimerCommentaire;
    @FXML
    private TableColumn<?, ?> pseudo;
    @FXML
    private TableColumn<?, ?> email;
    @FXML
    private TableColumn<?, ?> contenu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        evenements = new EvenementService();
        commentaires = new CommentaireService();
       
        afficherEvenements(); 
    }    
  
    public void afficherEvenements(){
        EvenementService es = new EvenementService();
        List<Evenement> events = es.findAll();
        ObservableList list = FXCollections.observableArrayList(events);
        tvevent.setItems(list);
        
        
       id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        Date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        datefin.setCellValueFactory(new PropertyValueFactory<>("Datefin"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        longdesc.setCellValueFactory(new PropertyValueFactory<>("longdesc"));
        
        
    }
   @FXML
  public void refreshCommentaires() {
      
         List<Commentaire> lCommentaires = commentaires.findByEvenement(
                tvevent.getSelectionModel().getSelectedItem().getId());
        ObservableList list = FXCollections.observableArrayList(lCommentaires);
        tvcom.setItems(list);
        pseudo.setCellValueFactory(new PropertyValueFactory<>("pseudo"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        contenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));
    }
 

    @FXML
    private void SupprimerEvenement(ActionEvent event) {
        evenements.delete(tvevent.getSelectionModel().getSelectedItem().getId());
        afficherEvenements();
    }

    @FXML
    private void SupprimerCommentaire(ActionEvent event) {
        commentaires.delete(tvcom.getSelectionModel().getSelectedItem().getId());
        refreshCommentaires();
    }

    @FXML
    private void ModifierEvenement(ActionEvent event) throws IOException {
        if (tvevent.getSelectionModel().getSelectedItem() != null) {
            Id.evenementId= tvevent.getSelectionModel().getSelectedItem().getId();
            Stage primaryStage = new Stage();
        
        try {
            ((Stage) btnModifierEvenement.getScene().getWindow()).close();
            Parent root = FXMLLoader.load(getClass().getResource("ModifierEvenement.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("PROTECH");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        }
    }

    @FXML
    private void AjouterEvenement(ActionEvent event) {
        Stage primaryStage = new Stage();
        
        try {
            ((Stage) btnCreerEvenement.getScene().getWindow()).close();
            Parent root = FXMLLoader.load(getClass().getResource("AjouterEvenement.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("PROTECH");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
}
