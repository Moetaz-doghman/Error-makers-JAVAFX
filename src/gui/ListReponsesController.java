/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Reponse;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.ReclamationService;
import services.ReponseService;

/**
 * FXML Controller class
 *
 * @author mariem
 */
public class ListReponsesController implements Initializable {

    @FXML
    private TableView<?> tableRec;
    @FXML
    private TableColumn<?, ?> sujet;
    @FXML
    private TableColumn<String, String> NomClient;
    @FXML
    private TableColumn<?, ?> message;
    @FXML
    private TableColumn<?, ?> createdat;
    @FXML
    private TableColumn<?, ?> etatRec;
    @FXML
    private TableView<String> nomtab;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           ArrayList<Reponse> array = new ArrayList<Reponse>();
           ArrayList<String> sar = new ArrayList<>();
         
           ReponseService rs = new ReponseService();
           ReclamationService rss = new ReclamationService();
          
           array = (ArrayList) rs.recuperer();
          for (Reponse r : array  ){
              System.out.println((rs.recupererIdRec(r.getId())) );
              sar.add((rss.recupererNom(rs.recupererIdRec(r.getId()))));
               
          } 
         System.out.println(sar );
         System.out.println(sar.size());
         
          
        
        ObservableList list = FXCollections.observableArrayList(array); //objet list de type observableList : permet de détecter les changes en temps réél 
        ObservableList list1 = FXCollections.observableArrayList(sar); //objet list de type observableList : permet de détecter les changes en temps réél 
        tableRec.setItems(list);
        //tableRec.setItems(list1);//alimenter tableView avec la list
        
        
       

        NomClient.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
        nomtab.setItems(list1);
        
      //  NomClient.setCellValueFactory(new PropertyValueFactory<>("name"));
        sujet.setCellValueFactory(new PropertyValueFactory<>("subject"));
        message.setCellValueFactory(new PropertyValueFactory<>("message"));
        createdat.setCellValueFactory(new PropertyValueFactory<>("created_at"));
       // etatRec.setCellValueFactory(new PropertyValueFactory<>("etat"));
    }    

    @FXML
    private void btnUpdate(ActionEvent event) {
    }

    @FXML
    private void btnDelete(ActionEvent event) {
    }

    @FXML
    private void btnAddResp(ActionEvent event) {
    }
    
}
