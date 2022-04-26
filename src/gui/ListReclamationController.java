/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Reclamation;
import entities.Reponse;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.ReclamationService;
import services.ReponseService;

/**
 * FXML Controller class
 *
 * @author mariem
 */
public class ListReclamationController implements Initializable {
private Stage stage ;

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
    private TextField getSearch;
ReclamationService Sp = new ReclamationService();
         List<Reclamation> lt = Sp.recuperer();
         
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
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
        
       
    } 

    @FXML
    private void btnUpdate(ActionEvent event) {
         Reclamation r = tableRec.getSelectionModel().getSelectedItem(); //récuperer ligne sélectionné 
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Modifier.fxml"));
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListReclamation.fxml"));
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
        
         
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutReponse.fxml"));
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
    private void search(ActionEvent event) {
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
        
         
       
    };
    
  
}    
    

