/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import Config.MailerApi;
import Config.SMSApi;
import animations.Animations;
import entity.Commande;
import entity.Livraison;
import entity.User;
import entity.Vehicule;
import entity.userSession;
import java.io.IOException;
import java.util.Date;
import java.net.URL;


import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import service.LivraisonServices;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author doghm
 */
public class ajouterLController implements Initializable {

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
    private Button reponse;
    @FXML
    private Button Boutique;
    @FXML
    private Button addboutique;
    @FXML
    private Button produit;
    @FXML
    private Button addproduit;
    @FXML
    private Button listlivrasion;
    @FXML
    private Button addlivrasion;
    @FXML
    private Button listvehicule;
    @FXML
    private Button addvehicule;
    @FXML
    private Button logoutButton;
    @FXML
    private StackPane stckCommande;
    @FXML
    private AnchorPane rootCommande;
    @FXML
    private Pane ContainerUsersAdmin;
    @FXML
    private Button ajout;
    @FXML
    private DatePicker datelivraison;
    @FXML
    private TextField prix;
    @FXML
    private DatePicker finlivraison;
    @FXML
    private ChoiceBox<String> etat;
    @FXML
    private ComboBox<User> livreur;
    @FXML
    private ComboBox<Commande> commande;
    @FXML
    private ComboBox<Vehicule> vehicule;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Animations.fadeInUp(rootCommande);
               // TODO
    
       
       
      //  ajout.setDisable(true);
       

       
        // remplir liste des categories
        Callback<ListView<Vehicule>, ListCell<Vehicule>> cellFactoryy = new Callback<ListView<Vehicule>, ListCell<Vehicule>>() {

            @Override
            public ListCell<Vehicule> call(ListView<Vehicule> l) {
                return new ListCell<Vehicule>() {

                    protected void updateItem(Vehicule item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            setText(item.getMatricule());
                        }
                    }
                };
            }
        };

        vehicule.setButtonCell(cellFactoryy.call(null));
        vehicule.setCellFactory(cellFactoryy);

        LivraisonServices us = new LivraisonServices();
        List<Vehicule> arr = new ArrayList<>();
        try {
            arr = us.findCatrgory();
        } catch (SQLException ex) {
           // Logger.getLogger(AjouterLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Vehicule u : arr) {
            String Matricule = u.getMatricule();

            vehicule.getItems().add(u);
        }
        
        
         Callback<ListView<User>, ListCell<User>> cellFactoryyy = new Callback<ListView<User>, ListCell<User>>() {

            @Override
            public ListCell<User> call(ListView<User> l) {
                return new ListCell<User>() {

                    protected void updateItem(User item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            setText(item.getEmail());
                        }
                    }
                };
            }
        };

        livreur.setButtonCell(cellFactoryyy.call(null));
        livreur.setCellFactory(cellFactoryyy);

        LivraisonServices ss = new LivraisonServices();
        List<User> arrr = new ArrayList<>();
        try {
            arrr = ss.findCatrgoory();
        } catch (SQLException ex) {
           // Logger.getLogger(AjouterLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (User x : arrr) {
            String email = x.getEmail();

            livreur.getItems().add(x);
        }
        
       Callback<ListView<Commande>, ListCell<Commande>> cellFactoryyyy = new Callback<ListView<Commande>, ListCell<Commande>>() {

            @Override
            public ListCell<Commande> call(ListView<Commande> l) {
                return new ListCell<Commande>() {

                    protected void updateItem(Commande item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            setText(item.getAdresse());
                        }
                    }
                };
            }
        };

        commande.setButtonCell(cellFactoryyyy.call(null));
        commande.setCellFactory(cellFactoryyyy);

        LivraisonServices sss = new LivraisonServices();
        List<Commande> arrrr = new ArrayList<>();
        try {
            arrrr = sss.findCatrgooory();
        } catch (SQLException ex) {
            //Logger.getLogger(AjouterLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Commande c : arrrr) {
            String adresse = c.getAdresse();

            commande.getItems().add(c);
        }
           ObservableList<String> langs2 = FXCollections.observableArrayList( "disponible", "non disponible");
        etat.setItems(langs2);
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
    private void AjouterLivraison(javafx.event.ActionEvent event) throws SQLException {
         LivraisonServices sf = new LivraisonServices();
         
          Date d1 = new Date();
             ZoneId defaultZoneId = ZoneId.systemDefault();
        Date date1 =Date.from(datelivraison.getValue().atStartOfDay(defaultZoneId).toInstant());
                 
         
         
            
         Date date2 =Date.from(finlivraison.getValue().atStartOfDay(defaultZoneId).toInstant());
       sf.ajouter(new Livraison(0, livreur.getValue().getId(),commande.getValue().getIdcommande(),vehicule.getValue().getId(), 
               etat.getValue().toString(),date1,prix.getText(),date2));
        JOptionPane.showMessageDialog(null, "Ajout effectué");
        
        
//        //SEND SMS
//        SMSApi sms = new SMSApi();
//        sms.sendSMS("+21651921240", "Admin.");
        
        
         //Notification
        String tilte;
        String message;
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tilte = "Added Successful";
        message ="Livraison créer avec success";
        tray.setTitle(tilte);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(2000));
        
        // SEND MAIL
        MailerApi mailer = new MailerApi();
        mailer.SendMail("sebai.mehdi@esprit.tn", "Admin.");
    /* displayAll();
        ProdName.clear();
        ProdPrice.clear();
        ProdQT.clear();
        ProdDiscount.clear();
        imageview.setImage(null);
        ProdDesc.setText(null);*/
}
    
}
