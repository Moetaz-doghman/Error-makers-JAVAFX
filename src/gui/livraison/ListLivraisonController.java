/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.livraison;

import entities.Livraison;
import entities.Vehicule;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import services.LivraisonServices;
import services.VehiculeServices;

/**
 * FXML Controller class
 *
 * @author oaca
 */
public class ListLivraisonController implements Initializable {

    
    @FXML
    private TableView<Livraison> view;

    @FXML
    private TableColumn<Livraison, Number> cl_livreur;
    @FXML
    private TableColumn<Livraison, Number> cl_commande;
    @FXML
    private TableColumn<Livraison, Number> cl_vehicule;
    @FXML
    private TableColumn<Livraison, String> cl_etatlivraison;
    @FXML
    private TableColumn<Livraison, Date> cl_date;
 
    @FXML
    private TableColumn<Livraison, Date> cl_finlivraison;
    @FXML
    private TableColumn<Livraison, String> cl_prix;
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
           view.setEditable(true);
       
        cl_prix.setCellFactory(TextFieldTableCell.forTableColumn());
        cl_etatlivraison.setCellFactory(ChoiceBoxTableCell.forTableColumn("disponible", "non disponible"));
    
    
       try {
            afficher();
            addButtonToTable();
           
            

        } catch (SQLException ex) {
            Logger.getLogger(ListVehiculeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

     


// ajouter button supprimer pour chaque ligne de tableau 
    private void addButtonToTable() throws SQLException {
        TableColumn actionCol = new TableColumn("Action");
        actionCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

        Callback<TableColumn<Livraison, Void>, TableCell<Livraison, Void>> cellFactory;
        cellFactory = new Callback<TableColumn<Livraison, Void>, TableCell<Livraison, Void>>() {
            @Override
            public TableCell<Livraison, Void> call(final TableColumn<Livraison, Void> param) {
                final TableCell<Livraison, Void> cell = new TableCell<Livraison, Void>() {
                    private final Button delete = new Button("");
                    private final HBox pane = new HBox(delete);
                    //ajouter l'image pour button supprimer 

                    {
                        Image btn_delete = new Image(getClass().getResourceAsStream("delete.png"));
                        delete.setGraphic(new ImageView(btn_delete));
                        delete.setMaxSize(10, 10);

                        // ajouter message au survol sur button
                        final Tooltip tooltip = new Tooltip();
                        tooltip.setText("supprimer ");
                        delete.setTooltip(tooltip);
                        final Tooltip tooltip2 = new Tooltip();

                        // ajouter fonction supprimer au button avec message de confirmation 
                        delete.setOnAction((ActionEvent event) -> {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("suppression");
                            alert.setHeaderText("Voulez-vous vraiment supprimer ce Vehicule?");
                            Optional<ButtonType> option = alert.showAndWait();
                            if (option.get() == ButtonType.OK) {
                                LivraisonServices sf = new LivraisonServices();
                                Livraison p = getTableView().getItems().get(getIndex());
                                
                                try {
                                    sf.supprimerL(p.getId());
                                    afficher();
                                } catch (SQLException ex) {
                                    Logger.getLogger(ListVehiculeController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
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
        view.getColumns().add(actionCol);

    }

   public void afficher() throws SQLException {
        LivraisonServices P = new LivraisonServices();
        List lists = P.afficher();

        ObservableList ListLivraison = FXCollections.observableArrayList(lists);

        view.setItems(ListLivraison);

        //nombre.setText(Integer.toString(number));
        cl_livreur.setCellValueFactory(new PropertyValueFactory<>("livreur_id"));

        cl_commande.setCellValueFactory(new PropertyValueFactory<>("commande_id"));
        cl_vehicule.setCellValueFactory(new PropertyValueFactory<>("vehicule_id"));
        cl_etatlivraison.setCellValueFactory(new PropertyValueFactory<>("etat_livraison"));
        cl_date.setCellValueFactory(new PropertyValueFactory<>("date_livraison"));
       
        cl_prix.setCellValueFactory(new PropertyValueFactory<>("prix_livraison"));
        cl_finlivraison.setCellValueFactory(new PropertyValueFactory<>("fin_livraison"));
        
    }
   

    @FXML
    private void Updateetatlivraison(TableColumn.CellEditEvent edditedCell) throws SQLException {
        LivraisonServices P = new LivraisonServices();
        Livraison personSelected = view.getSelectionModel().getSelectedItem();

        int livreur_id=personSelected.getLivreur_id();
        int commande_id=personSelected.getCommande_id();
        int vehicule_id=personSelected.getVehicule_id();
        int id=personSelected.getId();
        String etat = personSelected.getEtat_livraison();
        Date d = personSelected.getDate_livraison();
        String Prix = personSelected.getPrix_livraison();

        Date d1 = personSelected.getFin_livraison();
    personSelected.setEtat_livraison(edditedCell.getNewValue().toString());
    P.modifier(id,livreur_id,commande_id , vehicule_id,edditedCell.getNewValue().toString(),d, Prix ,d1);
        afficher();}

    

    @FXML
    private void Updateprix(TableColumn.CellEditEvent edditedCell) throws SQLException {
        LivraisonServices P = new LivraisonServices();
        Livraison personSelected = view.getSelectionModel().getSelectedItem();

        int livreur_id=personSelected.getLivreur_id();
        int commande_id=personSelected.getCommande_id();
        int vehicule_id=personSelected.getVehicule_id();
        int id=personSelected.getId();
        String etat = personSelected.getEtat_livraison();
        Date d = personSelected.getDate_livraison();
        String Prix = personSelected.getPrix_livraison();

        Date d1 = personSelected.getFin_livraison();
    personSelected.setEtat_livraison(edditedCell.getNewValue().toString());
    P.modifier(id,livreur_id,commande_id , vehicule_id,etat,d, edditedCell.getNewValue().toString() ,d1);
        afficher();}
/*
    @FXML
    private void Updatelivreur(TableColumn.CellEditEvent edditedCell) throws SQLException {
        LivraisonServices P = new LivraisonServices();
        Livraison personSelected = view.getSelectionModel().getSelectedItem();

        int livreur_id=personSelected.getLivreur_id();
        int commande_id=personSelected.getCommande_id();
        int vehicule_id=personSelected.getVehicule_id();
        int id=personSelected.getId();
        String etat = personSelected.getEtat_livraison();
        Date d = personSelected.getDate_livraison();
        String Prix = personSelected.getPrix_livraison();

        Date d1 = personSelected.getFin_livraison();
    personSelected.setCommande_id((int)edditedCell.getNewValue());
    
    
    P.modifier(id, (int) edditedCell.getNewValue(),commande_id , vehicule_id,etat,d, Prix ,d1);
       
    afficher();}*/

   

   

   
    
}
