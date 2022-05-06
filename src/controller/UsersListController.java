/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import entity.User;
import entity.userSession;
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

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import service.DemandeService;
import service.UserService;

/**
 * FXML Controller class
 *
 * @author doghm
 */
public class UsersListController implements Initializable {

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
    private TableView<User> tableview;
    @FXML
    private TableColumn<User, String> nom;
    @FXML
    private TableColumn<User, String> prenom;
    
    @FXML
    private TableColumn<User, String> email;
    @FXML
    private TableColumn<User, String> telephone;
    @FXML
    private TableColumn<User, String> role;
    @FXML
    private TableColumn<User, ?> etatCompte;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
try {
            afficher();
            addButtonToTable();
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());
         }
LoadStat();
    }   
public void afficher(){

        UserService ps = new UserService();
        List<User> users = ps.recuperer();
        ObservableList list = FXCollections.observableArrayList(users);
        tableview.setItems(list);
        
        
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        telephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        role.setCellValueFactory(new PropertyValueFactory<>("role"));
        etatCompte.setCellValueFactory(new PropertyValueFactory<>("isActive"));
         
        
    }   
  private void addButtonToTable() throws SQLException {
        TableColumn actionCol = new TableColumn("Block");
        actionCol.setCellValueFactory(new PropertyValueFactory<>("Block"));

        Callback<TableColumn<User, Void>, TableCell<User, Void>> cellFactory;
        cellFactory = new Callback<TableColumn<User, Void>, TableCell<User, Void>>() {
            @Override
            public TableCell<User, Void> call(final TableColumn<User, Void> param) {
                final TableCell<User, Void> cell = new TableCell<User, Void>() {
                    
                    private final Button lock = new Button("");
                    private final HBox pane = new HBox(lock);

                    //ajouter l'image pour button supprimer 
                    {
                        Image btn_delete = new Image(getClass().getResourceAsStream("/images/block.png"));
                        ImageView imgv = new ImageView(btn_delete);
                        imgv.setFitHeight(25);
                        imgv.setFitWidth(25);
                        lock.setGraphic(imgv);            
                        lock.setMaxSize(10, 10);
                        // ajouter message au survol sur button                       
                        final Tooltip tooltip = new Tooltip();
                                tooltip.setText("Lock ");
                                lock.setTooltip(tooltip);
                        // ajouter fonction supprimer au button avec message de confirmation 
                            lock.setOnAction((ActionEvent event) -> {                            
                            User u = getTableView().getItems().get(getIndex());
                            UserService us = new UserService();
                            
                            if(u.getIsActive().equals("Active")){                               
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("LOCK");
                            alert.setHeaderText("Are you sure you want to lock this user ?");
                            Optional<ButtonType> option = alert.showAndWait();
                            if (option.get() == ButtonType.OK) {                                 
                                    us.Bloquer(u.getId());
                                    afficher();
                                }}                             
                                else{

                                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                    alert.setTitle("UNLOCK");
                                    alert.setHeaderText("Are you sure you want to unlock this user ?");
                                    Optional<ButtonType> option = alert.showAndWait();
                                    if (option.get() == ButtonType.OK) {
                                            us.Debloquer(u.getId());
                                            afficher();
                                    }}                
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

        actionCol.setCellFactory(cellFactory);
        tableview.getColumns().add(actionCol);

    }
   private void LoadStat() {
  // Changing random data after every 1 second.
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                UserService us = new UserService();
                int Total = us.countTotalCommande();
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
