/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Notifcation.AlertType;
import Notifcation.AlertsBuilder;
import Notifcation.NotificationType;
import Notifcation.NotificationsBuilder;
import animations.Animations;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import entity.Categorie;
import entity.Commande;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
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
import service.CommandeCrud;
import utils.Constants;
import utils.JFXDialogTool;

/**
 * FXML Controller class
 *
 * @author doghm
 */
public class CommandeController implements Initializable {

    @FXML
    private Button btnAcceuil1;
    @FXML
    private Button btnut;
    @FXML
    private Button btnev1;
    @FXML
    private Button btnsp1;
    @FXML
    private Button btnres;
    @FXML
    private Button btnjo;
    @FXML
    private Button btnca;
    @FXML
    private Button btnSignout1;
    @FXML
    private AnchorPane rootCommande;
    @FXML
    private Pane ContainerUsersAdmin;
    @FXML
    private HBox Userinformations;
    @FXML
    private Group parentImage;
    @FXML
    private Pane imageProfileContainer;
    @FXML
    private MaterialDesignIconView icon;
    @FXML
    private TabPane PaneTableau;
   @FXML
    private TableView<Commande> TableViewCommande;
    @FXML
    private TableColumn<Commande, String> col_NomCommande;
    @FXML
    private TableColumn<Commande, String> col_PrenomCommande;
    @FXML
    private TableColumn<Commande, String> col_AdresseCommande;
    @FXML
    private TableColumn<Commande, String> col_MontantCommande;
    @FXML
    private TableColumn<Commande, String> col_ActionCommande;

    @FXML
    private AnchorPane ContainerDeleteCommande;
    @FXML
    private Circle imgOnline;
    @FXML
    private TextField txtSearch;
    @FXML
    private ComboBox<String> CombofiltreSearch;
    @FXML
    private Label txtStatTotal;
    @FXML
    private Label txtStatMax;
    private AnchorPane containerAjouterCategorie;
    @FXML
    private Text textTitreCategorie;
    @FXML
    private JFXButton btnSaveCategorie;
    private JFXButton btnCancelAddCategorie;
    private JFXTextField txtNom;
    private JFXComboBox<String> comboGenre;
    @FXML
    private ImageView iconRole;
    private JFXTextField txtprenom;
    private ObservableList<Commande> FiltreCommande;
    private ObservableList<Commande> ListCommande;
    private JFXDialogTool dialogDeleteCommande;
    private JFXDialogTool dialogAjouterCommande;
    private static final Stage stage = new Stage();



    Commande commande = new Commande(); 
    CommandeCrud crudCommande = new CommandeCrud();
    @FXML
    private StackPane stckCommande;
    @FXML
    private AnchorPane containerAjouterCommande;
    @FXML
    private JFXComboBox<String> comboEtat;
    @FXML
    private JFXButton btnModifierCommande;
    @FXML
    private JFXButton btnCancelAddCommande;
    @FXML
    private TableColumn<Commande, String> col_DateCommande;
    @FXML
    private TableColumn<Commande, String> col_EtatCommande;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        LoadTableCommande();

        FiltreCommande = FXCollections.observableArrayList();
        comboEtat.getItems().addAll("Non Livrée", "Livree");
        CombofiltreSearch.getItems().addAll("Action", "Adventure", "Strategy", "Sports", "Simulation", "ViewAll");
        LoadStat();
        Animations.fadeInUp(rootCommande);
    }    
    private void LoadStat() {

        // Changing random data after every 1 second.
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int Total = crudCommande.countTotalCommande();
                txtStatTotal.setText(String.valueOf(Total));
                String MaxGenre = crudCommande.MaxUsedAdresse();
                txtStatMax.setText(String.valueOf(MaxGenre));

            }
        }));
        ///Repeat indefinitely until stop() method is called.
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.setAutoReverse(true);
        timeline.play();

    }

    @FXML
    private void acceuil1(ActionEvent event) {
    }

    @FXML
    private void gereruser(ActionEvent event) {
    }


    @FXML
    private void gerersponsor1(ActionEvent event) {
    }

    @FXML
    private void gererreservation(ActionEvent event) {
    }


    @FXML
    private void gerercategorie(ActionEvent event) {
    }

    @FXML
    private void logout(ActionEvent event) {
    }

    @FXML
    private void iconAddCommandeClicked(MouseEvent event) {
    }

    
    @FXML
    private void hideDialogDeleteCategorie() {
    
    }


    @FXML
    private void SearchAnything(KeyEvent event) {
    }

    @FXML
    private void SearchParFiltre(MouseEvent event) {
    }

    @FXML
    private void GeneratePDF(MouseEvent event) {
    }


    private void closeDialogAddCategorie() {
        if (dialogAjouterCommande != null) {
            dialogAjouterCommande.close();

            btnModifierCommande.setVisible(true);
            btnCancelAddCategorie.setVisible(true);
        }
        txtNom.clear();
        comboEtat.getSelectionModel().clearSelection();
        LoadTableCommande();
    }
      private void LoadTableCommande() {

        List<Commande> listeCommande = new ArrayList<>();
        listeCommande = crudCommande.AfficherCommande(commande);
        ObservableList<Commande> Listeeee = FXCollections.observableArrayList(listeCommande);

        
        col_NomCommande.setCellValueFactory(new PropertyValueFactory<>("nom_client"));
        col_PrenomCommande.setCellValueFactory(new PropertyValueFactory<>("prenom_client"));
        col_AdresseCommande.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        col_MontantCommande.setCellValueFactory(new PropertyValueFactory<>("montant"));
        col_DateCommande.setCellValueFactory(new PropertyValueFactory<>("dateCommande"));
        col_EtatCommande.setCellValueFactory(new PropertyValueFactory<>("etat_commande"));




                

        ListCommande = FXCollections.observableArrayList(listeCommande);
        TableViewCommande.setItems(ListCommande);
        

        //add cell of button edit 
        Callback<TableColumn<Commande, String>, TableCell<Commande, String>> cellFoctory = (TableColumn<Commande, String> param) -> {
            //make cell containing buttons

            final TableCell<Commande, String> cell = new TableCell<Commande, String>() {

                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    // that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                    } else {

                        ImageView DeleteCommande, EditCommande;
                        EditCommande = new ImageView(new Image("/ressource/editcateg.png"));
                        EditCommande.setFitHeight(30);
                        EditCommande.setFitWidth(30);
                        setGraphic(EditCommande);

                        DeleteCommande = new ImageView(new Image("/ressource/deletecateg.png"));
                        DeleteCommande.setFitHeight(30);
                        DeleteCommande.setFitWidth(30);
                        setGraphic(DeleteCommande);

                        EditCommande.setOnMouseClicked((MouseEvent event) -> {
                            System.out.println("icon edit is pressed !");

                          
                            showDialogModifierCommande();
                        });

                        DeleteCommande.setOnMouseClicked((MouseEvent event) -> {
                            System.out.println("icon delete is pressed !");
                            int id = Integer.valueOf((TableViewCommande.getSelectionModel().getSelectedItem().getIdcommande()));
                            comboEtat.setValue(TableViewCommande.getSelectionModel().getSelectedItem().getNom_client());
                            //btnUpdateReclam.toFront();
                            textTitreCategorie.setText("Modifier La commande");

                            showDialogDeleteCommande();
                        });
                  //      managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(DeleteCommande, new Insets(2, 2, 0, 3));
                        HBox.setMargin(EditCommande, new Insets(2, 3, 0, 2));
                        HBox managebtn = new HBox(EditCommande, DeleteCommande);
                        setGraphic(managebtn);
                    }
                }
            };
            return cell;
        };
        col_ActionCommande.setCellFactory(cellFoctory);
    }
      
        private void showDialogDeleteCommande() {
        rootCommande.setEffect(Constants.BOX_BLUR_EFFECT);
        ContainerDeleteCommande.setVisible(true);

        dialogDeleteCommande = new JFXDialogTool(ContainerDeleteCommande, stckCommande);
        dialogDeleteCommande.show();

        dialogDeleteCommande.setOnDialogClosed(ev -> {
            TableViewCommande.setDisable(false);
            rootCommande.setEffect(null);
            ContainerDeleteCommande.setVisible(false);
            LoadTableCommande();

        });

    }

    @FXML
    private void hideDialogDeleteCommande(MouseEvent event) {
         if (dialogDeleteCommande != null) {
            dialogDeleteCommande.close();
        }
    }

    @FXML
    private void deleteCommandeClicked(MouseEvent event) {
          if (TableViewCommande.getSelectionModel().getSelectedItem() != null) {
            commande = TableViewCommande.getSelectionModel().getSelectedItem();
            Boolean result = crudCommande.SupprimerCommande(commande.getIdcommande());
            if (result) {
                hideDialogDeleteCategorie();
                AlertsBuilder.create(AlertType.SUCCES, stckCommande, rootCommande, TableViewCommande, "commande: " + commande.getNom_client()+ " " + commande.getPrenom_client()+ " \n" + "a été supprimé");
            } else {
                NotificationsBuilder.create(NotificationType.ERROR, Constants.MESSAGE_ERROR_CONNECTION_MYSQL);
            }
        }
    
    }
    
        private void showDialogModifierCommande() {

       rootCommande.setEffect(Constants.BOX_BLUR_EFFECT);
        //imageContainer.toFront();
        containerAjouterCommande.setVisible(true);
        // btnSaveReclam.setDisable(false);

        dialogAjouterCommande = new JFXDialogTool(containerAjouterCommande, stckCommande);
        dialogAjouterCommande.show();
        dialogAjouterCommande.setOnDialogOpened(ev -> {
            txtprenom.requestFocus();
        });

        dialogAjouterCommande.setOnDialogClosed(ev -> {
            closeStage();
            TableViewCommande.setDisable(false);
            rootCommande.setEffect(null);
            containerAjouterCategorie.setVisible(false);
            LoadTableCommande();
        });
    }
        
    public static void closeStage() {
        if (stage != null) {
            stage.hide();
        }
    }
  
    

    @FXML
    private void AjouterCommande(MouseEvent event) {
    }

    @FXML
    private void closeDialogAjouterCommande(MouseEvent event) {
        
        if (dialogAjouterCommande != null) {
            dialogAjouterCommande.close();

            btnModifierCommande.setVisible(true);
            btnCancelAddCommande.setVisible(true);
        }
        txtNom.clear();
        comboGenre.getSelectionModel().clearSelection();
        LoadTableCommande();
    }

    @FXML
    private void ModifierCommande(MouseEvent event) {
          int idcommande = 0;
        if (TableViewCommande.getSelectionModel().getSelectedItem() != null) {
            idcommande = Integer.valueOf((TableViewCommande.getSelectionModel().getSelectedItem().getIdcommande()));
        }
      //  String nom = txtNom.getText();
        int  etat = 1; 
               if ( comboEtat.getSelectionModel().getSelectedItem() == "Livree")
               {
                   etat = 0 ; 
               }

        
        if ((etat != 0 && etat != 1)) {
            Animations.shake(comboEtat);
            return;
        }

      //  closeDialogAddCategorie();
        Commande cat = new Commande(idcommande,etat);
        commande.setEtat_commande(idcommande);
        commande.setEtat_commande(etat);
        // System.out.println("icon delete is pressed !" +etat);
        System.out.println(idcommande);
        System.out.println(etat);

        Boolean result = crudCommande.ModifierCommande(commande);
        closeDialogAddCategorie();

        if (result) {
            txtNom.clear();
            closeDialogAddCategorie();
            AlertsBuilder.create(AlertType.SUCCES, stckCommande, rootCommande, TableViewCommande, Constants.MESSAGE_UPDATED);
        } else {
            NotificationsBuilder.create(NotificationType.ERROR, Constants.MESSAGE_ERROR_CONNECTION_MYSQL);
        }
        txtNom.clear();
        LoadTableCommande();

    }
    
    

    @FXML
    private void closeDialogAddCommande(MouseEvent event) {
    }

  
    @FXML
    private void gererligne(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("../GUI/LigneCommande.fxml"));
        btnjo.getScene().setRoot(root);
    }

    @FXML
    private void gerercommande(ActionEvent event)  throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("../GUI/Categorie.fxml"));
        btnjo.getScene().setRoot(root);
    }


    
}
