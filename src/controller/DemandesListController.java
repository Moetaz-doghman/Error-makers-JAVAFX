/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import entities.Demandes;
import entities.userSession;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import services.DemandeService;

/**
 * FXML Controller class
 *
 * @author skanderzouaoui
 */
public class DemandesListController implements Initializable {

    @FXML
    private Button listUserButton;
    @FXML
    private Button listDemandeButton;
    @FXML
    private Button logoutButton;
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
        try {
            afficher();
            addButtonAccept();
            addButtonReject();
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());
         }
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

    @FXML
    private void ListUser(ActionEvent event) {
                   Stage primaryStage = new Stage();

        try {
            ((Stage) listUserButton.getScene().getWindow()).close();
            Parent root = FXMLLoader.load(getClass().getResource("/gui/UsersList.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("PROTECH");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void listDemande(ActionEvent event) {
                Stage primaryStage = new Stage();

        try {
            ((Stage) listDemandeButton.getScene().getWindow()).close();
            Parent root = FXMLLoader.load(getClass().getResource("/gui/DemandesList.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("PROTECH");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
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
    
}
