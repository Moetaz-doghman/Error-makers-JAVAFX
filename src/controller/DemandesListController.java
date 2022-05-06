/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import animations.Animations;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import entity.Demandes;
import entity.userSession;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
import javafx.util.Callback;
import service.DemandeService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author doghm
 */
public class DemandesListController implements Initializable {
    Stage primaryStage = new Stage();

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
    private AnchorPane ContainerDeleteCommande;
    @FXML
    private Circle imgOnline;
    @FXML
    private TextField txtSearch;
    @FXML
    private Label txtStatTotal;
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
    @FXML
    private TableView<?> tableview;
    @FXML
    private TableColumn<Demandes, String> nom;
    @FXML
    private TableColumn<Demandes, String> prenom;
    @FXML
    private TableColumn<Demandes, String> email;
    @FXML
    private TableColumn<Demandes, String> telephone;
    @FXML
    private TableColumn<Demandes, String> role;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Animations.fadeInUp(rootCommande);
    
        try {
            afficher();
            addButtonAccept();
            addButtonReject();
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());
         }
        LoadStat();
    }
public void afficher(){
        DemandeService ds = new DemandeService();
        List<Demandes> dems = ds.recuperer();

        ObservableList list = FXCollections.observableArrayList(dems);
        tableview.setItems(list);
        
        
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        telephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        role.setCellValueFactory(new PropertyValueFactory<>("role"));
         
        
    }
 private void addButtonAccept() throws SQLException {
            
        TableColumn acceptCol = new TableColumn("Accept");
        acceptCol.setCellValueFactory(new PropertyValueFactory<>("Accept"));

        Callback<TableColumn<Demandes, Void>, TableCell<Demandes, Void>> cellFactory;
        cellFactory = new Callback<TableColumn<Demandes, Void>, TableCell<Demandes, Void>>() {
            @Override
            public TableCell<Demandes, Void> call(final TableColumn<Demandes, Void> param) {
                final TableCell<Demandes, Void> cell = new TableCell<Demandes, Void>() {
                    
                    private final Button accept = new Button("");
                    private final HBox pane = new HBox(accept);


                    //ajouter l'image pour button  
                    {
                        Image btn_accept = new Image(getClass().getResourceAsStream("/images/accept.png"));
                        ImageView imgv1 = new ImageView(btn_accept);
                        imgv1.setFitHeight(25);
                        imgv1.setFitWidth(25);
                        accept.setGraphic(imgv1);            
                                


                        // ajouter fonction  au button avec message de confirmation 
                            accept.setOnAction((ActionEvent event) -> {                            
                            Demandes u = getTableView().getItems().get(getIndex());
                            DemandeService ds = new DemandeService();
                            
                                                          
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Accept");
                            alert.setHeaderText("Are you sure you want to Accept this user ?");
                            Optional<ButtonType> option = alert.showAndWait();
                            if (option.get() == ButtonType.OK) {                                 
                                    ds.accepter(u.getId());
                                    afficher();
                                }                
                        });
   
                    }
                    // pour afficher button
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        setGraphic(empty ? null : pane);     
                    }
                };
                return cell;
            }
        };

        acceptCol.setCellFactory(cellFactory);
        tableview.getColumns().add(acceptCol);


    }
   private void addButtonReject() throws SQLException {
            
        TableColumn rejectCol = new TableColumn("Reject");
        rejectCol.setCellValueFactory(new PropertyValueFactory<>("Reject"));

        Callback<TableColumn<Demandes, Void>, TableCell<Demandes, Void>> cellFactory;
        cellFactory = new Callback<TableColumn<Demandes, Void>, TableCell<Demandes, Void>>() {
            @Override
            public TableCell<Demandes, Void> call(final TableColumn<Demandes, Void> param) {
                final TableCell<Demandes, Void> cell = new TableCell<Demandes, Void>() {
                    
                    private final Button reject = new Button("");
                    private final HBox pane = new HBox(reject);


                    //ajouter l'image pour button  
                    {
                        
                        Image btn_reject = new Image(getClass().getResourceAsStream("/images/cancel.png"));
                        ImageView imgv2 = new ImageView(btn_reject);
                        imgv2.setFitHeight(25);
                        imgv2.setFitWidth(25);
                        reject.setGraphic(imgv2);            


                        // ajouter fonction  au button avec message de confirmation 
                            reject.setOnAction((ActionEvent event) -> {                            
                            Demandes u = getTableView().getItems().get(getIndex());
                            DemandeService ds = new DemandeService();
                            
                                                          
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Accept");
                            alert.setHeaderText("Are you sure you want to Reject this user ?");
                            Optional<ButtonType> option = alert.showAndWait();
                            if (option.get() == ButtonType.OK) {                                 
                                    ds.Supprimer(u.getId());
                                    afficher();
                                }                
                        });
   
                    }
                    // pour afficher button
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        setGraphic(empty ? null : pane);     
                    }
                };
                return cell;
            }
        };

        rejectCol.setCellFactory(cellFactory);
        tableview.getColumns().add(rejectCol);


    }
  private void LoadStat() {
  // Changing random data after every 1 second.
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DemandeService ds = new DemandeService();
                int Total = ds.countTotalCommande();
                txtStatTotal.setText(String.valueOf(Total));
               

            }
        }));
        ///Repeat indefinitely until stop() method is called.
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.setAutoReverse(true);
        timeline.play();   
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
    private void gererevent(ActionEvent event) {
    }

    @FXML
    private void gererreclamation(ActionEvent event) {
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
    private void GeneratePDF(MouseEvent event) {
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
