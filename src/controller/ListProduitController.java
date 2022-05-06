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
//import Config.JfreeChartApi;
import entity.Produit;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import service.ProduitService;

/**
 * FXML Controller class
 *
 * @author doghm
 */
public class ListProduitController implements Initializable {

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
    private TableView<Produit> view;
    @FXML
    private TableColumn<Produit, String> cl_nom;
    @FXML
    private TableColumn<Produit, String> cl_prix;
    @FXML
    private TableColumn<Produit, String>cl_qte;
    @FXML
    private TableColumn<Produit, String> cl_desc;
    @FXML
    private TableColumn<Produit, String> cl_img;
    @FXML
    private AnchorPane ContainerDeleteCommande;
    @FXML
    private Circle imgOnline;
    @FXML
    private TextField txtSearch;
    @FXML
    private Label txtStatTotal;
    @FXML
    private Label txtStatMax;
    @FXML
    private ImageView btnexcel;
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
       view.setEditable(true);
        cl_nom.setCellFactory(TextFieldTableCell.forTableColumn());
        cl_prix.setCellFactory(TextFieldTableCell.forTableColumn());
        cl_qte.setCellFactory(TextFieldTableCell.forTableColumn());
        cl_desc.setCellFactory(TextFieldTableCell.forTableColumn());
      // cl_img.setCellFactory(TextFieldTableCell.forTableColumn());

        try {
            afficher();
            addButtonToTable();
             SearchAnything();

        } catch (SQLException ex) {
            Logger.getLogger(ListProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
      // ajouter button supprimer pour chaque ligne de tableau 
    private void addButtonToTable() throws SQLException {
        TableColumn actionCol = new TableColumn("Action");
        actionCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

        Callback<TableColumn<Produit, Void>, TableCell<Produit, Void>> cellFactory;
        cellFactory = new Callback<TableColumn<Produit, Void>, TableCell<Produit, Void>>() {
            @Override
            public TableCell<Produit, Void> call(final TableColumn<Produit, Void> param) {
                final TableCell<Produit, Void> cell = new TableCell<Produit, Void>() {
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
                            alert.setHeaderText("Voulez-vous vraiment supprimer ce produit?");
                            Optional<ButtonType> option = alert.showAndWait();
                            if (option.get() == ButtonType.OK) {
                                ProduitService sf = new ProduitService();
                                Produit p = getTableView().getItems().get(getIndex());
                                try {
                                    sf.supprimer(p.getId());
                                    afficher();
                                } catch (SQLException ex) {
                                    Logger.getLogger(ListProduitController.class.getName()).log(Level.SEVERE, null, ex);
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
        ProduitService P = new ProduitService();
        List lists = P.afficher();

        ObservableList ListProduit = FXCollections.observableArrayList(lists);

        view.setItems(ListProduit);

        //nombre.setText(Integer.toString(number));
        cl_nom.setCellValueFactory(new PropertyValueFactory<>("nom_produit"));

        cl_prix.setCellValueFactory(new PropertyValueFactory<>("prix_produit"));
        cl_qte.setCellValueFactory(new PropertyValueFactory<>("quantite_produit"));
        cl_desc.setCellValueFactory(new PropertyValueFactory<>("desc_produit"));
        cl_img.setCellValueFactory(new PropertyValueFactory<>("image"));
            /*   cl_img.setImage(new Image(getClass().
                       getResourceAsStream("/gui/images/" + "image")));
*/
        
        
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
    private void Logout(ActionEvent event) {
    }

    @FXML
    private void updateNom(CellEditEvent edditedCell) throws SQLException {
        ProduitService P = new ProduitService();
        Produit personSelected = (Produit) view.getSelectionModel().getSelectedItem();
        String nom = personSelected.getNom_produit();
        int id = personSelected.getId();
        String prix = personSelected.getPrix_produit();
        String qt = personSelected.getQuantite_produit();
        String description = personSelected.getDesc_produit();
        String Image = personSelected.getImage();
        
        personSelected.setDesc_produit(edditedCell.getNewValue().toString());

         P.Modifier(id, prix, qt,description, Image,   edditedCell.getNewValue().toString());
        afficher();
    }


    @FXML
    private void updatePrix(CellEditEvent edditedCell) throws SQLException {
        ProduitService P = new ProduitService();
        Produit personSelected = (Produit) view.getSelectionModel().getSelectedItem();
        String nom = personSelected.getNom_produit();
        int id = personSelected.getId();
        String prix = personSelected.getPrix_produit();
        String qt = personSelected.getQuantite_produit();
        String description = personSelected.getDesc_produit();
        String Image = personSelected.getImage();
        
        personSelected.setDesc_produit(edditedCell.getNewValue().toString());

         P.Modifier(id,   edditedCell.getNewValue().toString(), qt,description, Image,nom );
        afficher();
    }


    @FXML
    private void UpdateQt(CellEditEvent edditedCell) throws SQLException {
        ProduitService P = new ProduitService();
        Produit personSelected = (Produit) view.getSelectionModel().getSelectedItem();
        String nom = personSelected.getNom_produit();
        int id = personSelected.getId();
        String prix = personSelected.getPrix_produit();
        String qt = personSelected.getQuantite_produit();
        String description = personSelected.getDesc_produit();
        String Image = personSelected.getImage();
        
        personSelected.setDesc_produit(edditedCell.getNewValue().toString());

         P.Modifier(id,  prix , edditedCell.getNewValue().toString(),description, Image,nom );
        afficher();
    }

    @FXML
    private void UpdateDesc(CellEditEvent edditedCell) throws SQLException {
        ProduitService P = new ProduitService();
        Produit personSelected = (Produit) view.getSelectionModel().getSelectedItem();
        String nom = personSelected.getNom_produit();
        int id = personSelected.getId();
        String prix = personSelected.getPrix_produit();
        String qt = personSelected.getQuantite_produit();
        String description = personSelected.getDesc_produit();
        String Image = personSelected.getImage();
        
        personSelected.setDesc_produit(edditedCell.getNewValue().toString());

         P.Modifier(id,  prix ,qt, edditedCell.getNewValue().toString(), Image,nom );
        afficher();
    }

  //fonction recherche sur le tableau


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
    private void SearchAnything() throws SQLException {
         ProduitService sf = new ProduitService();
        ArrayList listcs = (ArrayList) sf.afficher();
        ObservableList OFormation = FXCollections.observableArrayList(listcs);
        FilteredList<Produit> filteredData = new FilteredList<>(OFormation, p -> true);
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(myObject -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(myObject.getNom_produit()).toLowerCase().contains(lowerCaseFilter) || String.valueOf(myObject.getPrix_produit()).toLowerCase().contains(lowerCaseFilter) || String.valueOf(myObject.getDesc_produit()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;

                }
                return false;
            });
        });
        SortedList<Produit> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(view.comparatorProperty());
        view.setItems(sortedData);
    }
    

    @FXML
    private void GeneratePDF(MouseEvent event) {
    }

    @FXML
    private void exportExcel(MouseEvent event) {
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
    
}
