/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import entity.Livraison;
import entity.Vehicule;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
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
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Callback;
import service.LivraisonServices;
import service.VehiculeServices;

/**
 * FXML Controller class
 *
 * @author doghm
 */
public class ListLivraisonController implements Initializable {

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
    private Button Boutique;
    @FXML
    private Button addboutique;
    @FXML
    private Button produit;
    @FXML
    private Button addproduit;
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
    @FXML
    private AnchorPane ContainerDeleteCommande;
    @FXML
    private Circle imgOnline;
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
    private ImageView print;
    @FXML
    private RadioButton tri3;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         view.setEditable(true);
       
        cl_prix.setCellFactory(TextFieldTableCell.forTableColumn());
        cl_etatlivraison.setCellFactory(ChoiceBoxTableCell.forTableColumn("disponible", "non disponible"));
    
    
       try {
            afficher();
            addButtonToTable();
           
            

        } catch (SQLException ex) {
            Logger.getLogger(ListLivraisonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
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
                        Image btn_delete = new Image(getClass().getResourceAsStream("../images/delete.png"));
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
                                    Logger.getLogger(ListLivraisonController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void ListUser(ActionEvent event) {
    }

    @FXML
    private void ListDemande(ActionEvent event) {
    }

    @FXML
    private void gerercommande(ActionEvent event) {
    }

    @FXML
    private void gererligne(ActionEvent event) {
    }

    @FXML
    private void gererevent(ActionEvent event) {
    }

    @FXML
    private void gererreclamation(ActionEvent event) {
    }

    @FXML
    private void gererboutique(ActionEvent event) {
    }

    @FXML
    private void gereraddboutique(ActionEvent event) {
    }

    @FXML
    private void gererproduit(ActionEvent event) {
    }

    @FXML
    private void gereraddproduit(ActionEvent event) {
    }

    @FXML
    private void Logout(ActionEvent event) {
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
    private void showchart(MouseEvent event) {
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

    @FXML
    private void print(MouseEvent event) {
        Printer printer = Printer.getDefaultPrinter();
     Node node = new Circle(400, 800, 800);
 PrinterJob job = PrinterJob.createPrinterJob(printer);
 if (job != null) {
    boolean success = job.printPage(view);
    if (success) {
        job.endJob();
    }
 }
    }

    @FXML
    private void triPrix(ActionEvent event) throws SQLException {
         LivraisonServices sf = new LivraisonServices();
        List listcs = sf.triParPrix();

        ObservableList listFormations = FXCollections.observableArrayList(listcs);

        view.setItems(listFormations);

         cl_livreur.setCellValueFactory(new PropertyValueFactory<>("livreur_id"));

        cl_commande.setCellValueFactory(new PropertyValueFactory<>("commande_id"));
        cl_vehicule.setCellValueFactory(new PropertyValueFactory<>("vehicule_id"));
        cl_etatlivraison.setCellValueFactory(new PropertyValueFactory<>("etat_livraison"));
        cl_date.setCellValueFactory(new PropertyValueFactory<>("date_livraison"));
       
        cl_prix.setCellValueFactory(new PropertyValueFactory<>("prix_livraison"));
        cl_finlivraison.setCellValueFactory(new PropertyValueFactory<>("fin_livraison"));
    }
    
}
