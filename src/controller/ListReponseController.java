/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import animations.Animations;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import entity.Reponse;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ReclamationService;
import service.ReponseService;

/**
 * FXML Controller class
 *
 * @author doghm
 */
public class ListReponseController implements Initializable {

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
    private Button Boutique;
    @FXML
    private Button addboutique;
    @FXML
    private Button produit;
    @FXML
    private Button addproduit;
    @FXML
    private Button logoutButton;
    @FXML
    private StackPane stckCommande;
    @FXML
    private AnchorPane rootCommande;
    @FXML
    private Pane ContainerUsersAdmin;
    @FXML
    private TabPane PaneTableau;
  
    @FXML
    private TableView<?> tableRec;
    @FXML
    private TableColumn<?, ?> sujet;
    @FXML
    private TableColumn<String, String> NomClient;
    @FXML
    private TableColumn<?, ?> message;
    @FXML
    private TableColumn<?, ?> createdat;
 
    @FXML
    private TableView<String> nomtab;
    @FXML
    private AnchorPane ContainerDeleteCommande;
    @FXML
    private Circle imgOnline;
    @FXML
    private TextField txtSearch;
    @FXML
    private AnchorPane containerAjouterCommande;
    @FXML
    private Text textTitreCategorie;
    @FXML
    private JFXButton btnSaveCategorie;
    @FXML
    private JFXButton btnCancelAddCommande;
    @FXML
    private JFXButton btnModifierCommande;
    @FXML
    private JFXComboBox<?> comboEtat;
    @FXML
    private ImageView iconRole;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Animations.fadeInUp(rootCommande);
        ArrayList<Reponse> array = new ArrayList<Reponse>();
           ArrayList<String> sar = new ArrayList<>();
         
           ReponseService rs = new ReponseService();
           ReclamationService rss = new ReclamationService();
          
           array = (ArrayList) rs.recuperer();
          for (Reponse r : array  ){
              System.out.println((rs.recupererIdRec(r.getId())) );
              sar.add((rss.recupererNom(rs.recupererIdRec(r.getId()))));
               
          } 
         System.out.println(sar );
         System.out.println(sar.size());
         
          
        
        ObservableList list = FXCollections.observableArrayList(array); //objet list de type observableList : permet de détecter les changes en temps réél 
        ObservableList list1 = FXCollections.observableArrayList(sar); //objet list de type observableList : permet de détecter les changes en temps réél 
        tableRec.setItems(list);
        //tableRec.setItems(list1);//alimenter tableView avec la list
        
        
       

        NomClient.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
        nomtab.setItems(list1);
        
      //  NomClient.setCellValueFactory(new PropertyValueFactory<>("name"));
        sujet.setCellValueFactory(new PropertyValueFactory<>("subject"));
        message.setCellValueFactory(new PropertyValueFactory<>("message"));
        createdat.setCellValueFactory(new PropertyValueFactory<>("created_at"));
       // etatRec.setCellValueFactory(new PropertyValueFactory<>("etat"));
   
    }    

    @FXML
    private void ListUser(ActionEvent event) {
    }

    @FXML
    private void ListDemande(ActionEvent event) {
    }

    @FXML
    private void gerercommande(ActionEvent event) {
    }

    @FXML
    private void gererligne(ActionEvent event) {
    }

    @FXML
    private void gererevent(ActionEvent event) {
    }

    @FXML
    private void gererreclamation(ActionEvent event) {
    }

    @FXML
    private void gererboutique(ActionEvent event) {
    }

    @FXML
    private void gereraddboutique(ActionEvent event) {
    }

    @FXML
    private void gererproduit(ActionEvent event) {
    }

    @FXML
    private void gereraddproduit(ActionEvent event) {
    }

    @FXML
    private void Logout(ActionEvent event) {
    }

    @FXML
    private void iconAddCommandeClicked(MouseEvent event) {
    }

    @FXML
    private void hideDialogDeleteCommande(MouseEvent event) {
    }

    @FXML
    private void deleteCommandeClicked(MouseEvent event) {
    }

    @FXML
    private void hideDialogDeleteCategorie(MouseEvent event) {
    }

    @FXML
    private void SearchAnything(KeyEvent event) {
    }

    @FXML
    private void showchart(MouseEvent event) {
    }

    @FXML
    private void AjouterCommande(MouseEvent event) {
    }

    @FXML
    private void closeDialogAjouterCommande(MouseEvent event) {
    }

    @FXML
    private void ModifierCommande(MouseEvent event) {
    }

    @FXML
    private void closeDialogAddCommande(MouseEvent event) {
    }
    
}
