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
import entity.Reclamation;
import entity.Reponse;
import entity.userSession;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import service.ReclamationService;
import service.ReponseService;

/**
 * FXML Controller class
 *
 * @author doghm
 */
public class ListReclamationController implements Initializable {

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
    private TableView<Reclamation> tableRec;
    @FXML
    private TableColumn<Reclamation, String> nom;
    @FXML
    private TableColumn<Reclamation, String> sujet;
    @FXML
    private TableColumn<Reclamation, String> type;
    @FXML
    private TableColumn<Reclamation, String> email;
    @FXML
    private TableColumn<Reclamation, String> message;
    @FXML
    private TableColumn<?, ?> etat;
    @FXML
    private AnchorPane ContainerDeleteCommande;
    @FXML
    private Circle imgOnline;
    @FXML
    private TextField getSearch;
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
    
    ReclamationService Sp = new ReclamationService();
    List<Reclamation> lt = Sp.recuperer();
    @FXML
    private Button reponse;
    @FXML
    private Button listlivrasion;
    @FXML
    private Button addlivrasion;
    @FXML
    private Button listvehicule;
    @FXML
    private Button addvehicule;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                Animations.fadeInUp(rootCommande);

       ArrayList arrayList = null;
        ReclamationService rs = new ReclamationService();
         arrayList = (ArrayList) rs.recuperer();
        
        ObservableList list = FXCollections.observableArrayList(arrayList); //objet list de type observableList : permet de détecter les changes en temps réél 
        tableRec.setItems(list); //alimenter tableView avec la list
        
        nom.setCellValueFactory(new PropertyValueFactory<>("name"));
        sujet.setCellValueFactory(new PropertyValueFactory<>("subject"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        message.setCellValueFactory(new PropertyValueFactory<>("message"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
    
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
    private void hideDialogDeleteCommande(MouseEvent event) {
    }

    @FXML
    private void deleteCommandeClicked(MouseEvent event) {
    }

    @FXML
    private void hideDialogDeleteCategorie(MouseEvent event) {
    }

    @FXML
    private void btnUpdate(ActionEvent event) {
         Reclamation r = tableRec.getSelectionModel().getSelectedItem(); //récuperer ligne sélectionné 
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Modifier.fxml"));
        try {
            
            Parent root = loader.load();
            ReclamationService rs = new ReclamationService();
            ModifierController mc = loader.getController();
            mc.setReclamation(r);
            rs.modifier(r);
            tableRec.getScene().setRoot(root);
            
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }


   @FXML
    private void btnDelete(ActionEvent event) {
         Reclamation r = tableRec.getSelectionModel().getSelectedItem(); //récuperer ligne sélectionné 
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/ListReclamation.fxml"));
        try {
            
            Parent root = loader.load();
            ReclamationService rs = new ReclamationService();
            int id = r.getId();
            rs.supprimer(id);
              tableRec.getSelectionModel().clearSelection();
              tableRec.getItems().clear();
               ArrayList arrayList = null;
       
         arrayList = (ArrayList) rs.recuperer();
        
        ObservableList list = FXCollections.observableArrayList(arrayList);
              tableRec.getItems().addAll(list);
            tableRec.getScene().setRoot(root);
            
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
       
    }

     @FXML
    private void btnAddResp(ActionEvent event) {
         Reclamation r = tableRec.getSelectionModel().getSelectedItem(); //récuperer ligne sélectionné
         
         String messsage = message.getText();
         String subject = sujet.getText();
         
         Reponse re = new Reponse(subject, messsage);

        re.setSubject(subject);
        re.setMessage(messsage);
        
         
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/AjoutReponse.fxml"));
        try {
            
            Parent root = loader.load();
            ReponseService rs = new ReponseService();
            AjoutReponseController arc = loader.getController();
            arc.setReponse(r);
            r.setEtat("Resolue");
          //  rs.ajouter(re);
            tableRec.getScene().setRoot(root);
            
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @FXML
    private void search(KeyEvent event) {
        ReclamationService tt = new ReclamationService();
        lt = tt.RechercherReclamation(getSearch.getText());
          System.out.println("Recherche");
        System.out.println(getSearch.getText());
        tableRec.setEditable(true);
        
        
        
        
       ObservableList<Reclamation> datalist = FXCollections.observableArrayList(lt);
        
          nom.setCellValueFactory(new PropertyValueFactory<>("name"));
        sujet.setCellValueFactory(new PropertyValueFactory<>("subject"));
        message.setCellValueFactory(new PropertyValueFactory<>("message"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
       
        

        tableRec.setItems(datalist);
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
