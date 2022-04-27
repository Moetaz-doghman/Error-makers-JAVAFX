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
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import entity.Commande;
import entity.Lignecommande;
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
public class LigneController implements Initializable {

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
    private StackPane stckLigne;
    @FXML
    private AnchorPane rootLigne;
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
    private AnchorPane ContainerDeleteLigne;
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
    private ObservableList<Lignecommande> FiltreLigne;
    
    Lignecommande ligne = new Lignecommande(); 
    LigneCrud crudLigne = new LigneCrud();
    
    private ObservableList<Lignecommande> Lignecommande;
    private JFXDialogTool dialogDeleteLigne;




    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LoadTableLigne();

        FiltreLigne = FXCollections.observableArrayList();
        CombofiltreSearch.getItems().addAll("Action", "Adventure", "Strategy", "Sports", "Simulation", "ViewAll");
        LoadStat();
        Animations.fadeInUp(rootLigne);
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


    private void hideDialogDeleteCategorie() {
    }

    @FXML
    private void SearchAnything(KeyEvent event) {
    }

    @FXML
    private void SearchParFiltre(MouseEvent event) {
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
    
     @FXML
    private void deleteLigneClicked(MouseEvent event) {
       
           if (TableViewLigne.getSelectionModel().getSelectedItem() != null) {
            ligne = TableViewLigne.getSelectionModel().getSelectedItem();
            System.out.println(ligne.getId());
            Boolean result = crudLigne.SupprimerLigne(ligne.getId());
            if (result) {
                hideDialogDeleteCategorie();
                AlertsBuilder.create(AlertType.SUCCES, stckLigne, rootLigne, TableViewLigne, "ligne: " + ligne.getId() +" " + "a été supprimé");
            } else {
                NotificationsBuilder.create(NotificationType.ERROR, Constants.MESSAGE_ERROR_CONNECTION_MYSQL);
            }
        }

          
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
    
     private void showDialogDeleteLigne() {
        rootLigne.setEffect(Constants.BOX_BLUR_EFFECT);
        ContainerDeleteLigne.setVisible(true);

        dialogDeleteLigne = new JFXDialogTool(ContainerDeleteLigne, stckLigne);
        dialogDeleteLigne.show();

        dialogDeleteLigne.setOnDialogClosed(ev -> {
            TableViewLigne.setDisable(false);
            rootLigne.setEffect(null);
            ContainerDeleteLigne.setVisible(false);
            LoadTableLigne();

        });

    }

    @FXML
    private void hideDialogDeleteLigne(MouseEvent event) {
         if (dialogDeleteLigne != null) {
            dialogDeleteLigne.close();
        }
    }

    @FXML
    private void gerercommande(ActionEvent event)  throws IOException {
         Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("../GUI/Categorie.fxml"));
        btnjo.getScene().setRoot(root);
    }

    @FXML
    private void gererligne(ActionEvent event)  throws IOException {
         Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("../GUI/LigneCommande.fxml"));
        btnjo.getScene().setRoot(root);
    }
   

   
    
}
