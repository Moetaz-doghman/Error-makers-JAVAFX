/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.livraison;

import Config.MailerApi;
import Config.SMSApi;
import entities.Commande;
import entities.Livraison;
import entities.Utilisateur;
import entities.Vehicule;
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
import services.LivraisonServices;
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
 * @author oaca
 */
public class AjouterLController implements Initializable {

    @FXML
    private Button btnOrders;
    @FXML
    private Button btnstock;
    @FXML
    private Button btnOrder;
    @FXML
    private Button logout;
    @FXML
    private ComboBox<Utilisateur> livreur;
    @FXML
    private ComboBox<Commande> commande;
    @FXML
    private ComboBox<Vehicule> vehicule;
    @FXML
    private ChoiceBox<String> etat;
    @FXML
    private DatePicker datelivraison;
    @FXML
    private TextField prix;
    @FXML
    private DatePicker finlivraison;
    @FXML
    private Button ajout;
    @FXML
    private Button listaa;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
            Logger.getLogger(AjouterLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Vehicule u : arr) {
            String Matricule = u.getMatricule();

            vehicule.getItems().add(u);
        }
        
        
         Callback<ListView<Utilisateur>, ListCell<Utilisateur>> cellFactoryyy = new Callback<ListView<Utilisateur>, ListCell<Utilisateur>>() {

            @Override
            public ListCell<Utilisateur> call(ListView<Utilisateur> l) {
                return new ListCell<Utilisateur>() {

                    protected void updateItem(Utilisateur item, boolean empty) {
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
        List<Utilisateur> arrr = new ArrayList<>();
        try {
            arrr = ss.findCatrgoory();
        } catch (SQLException ex) {
            Logger.getLogger(AjouterLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Utilisateur x : arrr) {
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
            Logger.getLogger(AjouterLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Commande c : arrrr) {
            String adresse = c.getAdresse();

            commande.getItems().add(c);
        }
           ObservableList<String> langs2 = FXCollections.observableArrayList( "disponible", "non disponible");
        etat.setItems(langs2);
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
        
        
        //SEND SMS
        SMSApi sms = new SMSApi();
        sms.sendSMS("+21651921240", "Admin.");
        
        
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
    
    
    
   
@FXML
    private void PageListL(javafx.event.ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/gui/livraison/ListLivraison.fxml")));
        stage.setScene(scene);
        stage.show();
    }

}

