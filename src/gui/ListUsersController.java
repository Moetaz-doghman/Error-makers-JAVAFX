/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entities.User;
import entities.userSession;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author skanderzouaoui
 */
public class ListUsersController implements Initializable {

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
    private Button logoutButton;
    @FXML
    private TableColumn<User, ?> etatCompte;

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

    @FXML
    private void rechercher(KeyEvent event) {
    }

    @FXML
    private void Logout(ActionEvent event) {
        Stage primaryStage = new Stage();
        
        try {
            ((Stage) logoutButton.getScene().getWindow()).close();
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
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
    
      private void addButtonToTable() throws SQLException {
        TableColumn actionCol = new TableColumn("Action");
        actionCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

        Callback<TableColumn<User, Void>, TableCell<User, Void>> cellFactory;
        cellFactory = new Callback<TableColumn<User, Void>, TableCell<User, Void>>() {
            @Override
            public TableCell<User, Void> call(final TableColumn<User, Void> param) {
                final TableCell<User, Void> cell = new TableCell<User, Void>() {
                    
                    private final Button lock = new Button("");
                    private final HBox pane = new HBox(lock);
                    
                    
                    //ajouter l'image pour button supprimer 

                    {
                        Image btn_delete = new Image(getClass().getResourceAsStream("block.jpg"));
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
                    
                     
                    
                    // pour afficher button supprimer
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
    
    
    
}
