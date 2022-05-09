/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import Notifcation.AlertType;
import Notifcation.AlertsBuilder;
import Notifcation.NotificationType;
import Notifcation.NotificationsBuilder;
import animations.Animations;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import entity.Commande;
import entity.Lignecommande;
import entity.userSession;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import service.LigneCrud;
import utils.Constants;
import utils.JFXDialogTool;

/**
 * FXML Controller class
 *
 * @author doghm
 */
public class LigneBackController implements Initializable {

    @FXML
    private Button listUserButton;
    @FXML
    private Button ListDemandeButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button btnev1;
    @FXML
    private Button btnjo;
    @FXML
    private Button btnevent;
    @FXML
    private Button Reclamation;
    @FXML
    private StackPane stckCommande;
    @FXML
    private AnchorPane rootCommande;
    @FXML
    private Pane ContainerUsersAdmin;
    @FXML
    private TabPane PaneTableau;
    @FXML
    private TableView<Lignecommande> TableViewLigne;
    @FXML
    private TableColumn<Lignecommande, String> col_ProduitLigne;
    @FXML
    private TableColumn<Lignecommande, String> col_CommandeLigne;
    @FXML
    private TableColumn<Lignecommande, String> col_QuantiteLigne;
    @FXML
    private TableColumn<Lignecommande, String> col_ActionLigne;
    @FXML
    private AnchorPane ContainerDeleteCommande;
    @FXML
    private Circle imgOnline;
    @FXML
    private TextField txtSearch;
    @FXML
    private Label txtStatTotal;
    @FXML
    private Label txtStatMax;
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

        
    private ObservableList<Lignecommande> FiltreLigne;
    
    Lignecommande ligne = new Lignecommande(); 
    LigneCrud crudLigne = new LigneCrud();
    
    private ObservableList<Lignecommande> Lignecommande;
    private JFXDialogTool dialogDeleteLigne;
    @FXML
    private Button Boutique;
    @FXML
    private Button addboutique;
    @FXML
    private Button produit;
    @FXML
    private Button addproduit;
    @FXML
    private Button reponse;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         LoadTableLigne();

        FiltreLigne = FXCollections.observableArrayList();
       // CombofiltreSearch.getItems().addAll("Action", "Adventure", "Strategy", "Sports", "Simulation", "ViewAll");
        LoadStat();
        Animations.fadeInUp(rootCommande);
    }    

    @FXML
    private void ListUser(ActionEvent event) throws IOException {
      //  Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("../GUI/usersList.fxml"));
       Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("../GUI/UsersList.fxml"));
        btnjo.getScene().setRoot(root);
    }

    @FXML
    private void ListDemande(ActionEvent event) throws IOException {
//        Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("../GUI/DemandesList.fxml"));
//        ListDemandeButton.getScene().setRoot(root);
        Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("../GUI/DemandesList.fxml"));
        btnjo.getScene().setRoot(root);
        
    }
        @FXML
    private void gererligne(ActionEvent event) throws IOException {
         Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("../GUI/LigneBack.fxml"));
        btnjo.getScene().setRoot(root);
    }

    @FXML
    private void gerercommande(ActionEvent event)  throws IOException {
         Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("../GUI/CommandeBack.fxml"));
        btnjo.getScene().setRoot(root);
    }

    @FXML
    private void Logout(ActionEvent event) {
            Stage primaryStage = new Stage();

        try {
            ((Stage) logoutButton.getScene().getWindow()).close();
            Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("/gui/Login.fxml"));
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
    
    private void LoadTableLigne() {
        List<Lignecommande> listeLigne = new ArrayList<>();
        listeLigne = crudLigne.AfficherLigne(ligne);
        ObservableList<Lignecommande> Listeeee = FXCollections.observableArrayList(listeLigne);

        
        col_ProduitLigne.setCellValueFactory(new PropertyValueFactory<>("idproduit"));
        col_CommandeLigne.setCellValueFactory(new PropertyValueFactory<>("idcommande"));
        col_QuantiteLigne.setCellValueFactory(new PropertyValueFactory<>("quantite"));
      
        Lignecommande = FXCollections.observableArrayList(listeLigne);
        TableViewLigne.setItems(Lignecommande);
        

        //add cell of button edit 
        Callback<TableColumn<Lignecommande, String>, TableCell<Lignecommande, String>> cellFoctory = (TableColumn<Lignecommande, String> param) -> {
            //make cell containing buttons

            final TableCell<Lignecommande, String> cell = new TableCell<Lignecommande, String>() {

                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    // that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                    } else {

                        ImageView DeleteCommande;
                       

                        DeleteCommande = new ImageView(new Image("/ressource/deletecateg.png"));
                        DeleteCommande.setFitHeight(30);
                        DeleteCommande.setFitWidth(30);
                        setGraphic(DeleteCommande);

                      

                      DeleteCommande.setOnMouseClicked((MouseEvent event) -> {
                            System.out.println("icon delete is pressed !");
                            int id = Integer.valueOf((TableViewLigne.getSelectionModel().getSelectedItem().getId()));
                           

                            showDialogDeleteLigne();
                        });
                       
                  //      managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(DeleteCommande, new Insets(2, 2, 0, 3));
                        HBox managebtn = new HBox(DeleteCommande);
                        setGraphic(managebtn);
                    }
                }
            };
            return cell;
        };
        col_ActionLigne.setCellFactory(cellFoctory);
        
    }
    
       private void LoadStat() {
  // Changing random data after every 1 second.
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int Total = crudLigne.countTotalCommande();
                txtStatTotal.setText(String.valueOf(Total));
                String MaxGenre = crudLigne.Maxusedproduct();
                txtStatMax.setText(String.valueOf(MaxGenre));

            }
        }));
        ///Repeat indefinitely until stop() method is called.
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.setAutoReverse(true);
        timeline.play();   
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
    private void hideDialogDeleteCategorie(MouseEvent event) {
    }

    @FXML
    private void SearchAnything(KeyEvent event) {
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

   

   @FXML
    private void deleteLigneClicked(MouseEvent event) {
       
           if (TableViewLigne.getSelectionModel().getSelectedItem() != null) {
            ligne = TableViewLigne.getSelectionModel().getSelectedItem();
            System.out.println(ligne.getId());
            Boolean result = crudLigne.SupprimerLigne(ligne.getId());
            if (result) {
                hideDialogDeleteCategorie();
                AlertsBuilder.create(AlertType.SUCCES, stckCommande, rootCommande, TableViewLigne, "ligne: " + ligne.getId() +" " + "a été supprimé");
            } else {
                NotificationsBuilder.create(NotificationType.ERROR, Constants.MESSAGE_ERROR_CONNECTION_MYSQL);
            }
        }

          
    }
    
      @FXML
    private void hideDialogDeleteLigne(MouseEvent event) {
         if (dialogDeleteLigne != null) {
            dialogDeleteLigne.close();
        }
    }
    
         
    private void hideDialogDeleteCategorie() {
    }
    
     private void showDialogDeleteLigne() {
        rootCommande.setEffect(Constants.BOX_BLUR_EFFECT);
        ContainerDeleteCommande.setVisible(true);

        dialogDeleteLigne = new JFXDialogTool(ContainerDeleteCommande, stckCommande);
        dialogDeleteLigne.show();

        dialogDeleteLigne.setOnDialogClosed(ev -> {
            TableViewLigne.setDisable(false);
            rootCommande.setEffect(null);
            ContainerDeleteCommande.setVisible(false);
            LoadTableLigne();

        });

    }

    @FXML
    private void gererboutique(ActionEvent event) throws IOException {
        Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("../GUI/ListBoutique.fxml"));
        btnjo.getScene().setRoot(root);
    }

    @FXML
    private void gereraddboutique(ActionEvent event) throws IOException {
        Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("../GUI/AddBoutique.fxml"));
        btnjo.getScene().setRoot(root);
    }

    @FXML
    private void gererproduit(ActionEvent event) throws IOException {
        Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("../GUI/ListProduit.fxml"));
        btnjo.getScene().setRoot(root);
    }
    

    @FXML
    private void gereraddproduit(ActionEvent event) throws IOException {
        Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("../GUI/AddProduit.fxml"));
        btnjo.getScene().setRoot(root);
    }

    @FXML
    private void gererreponse(ActionEvent event) {
    }
    
}
