/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Boutique;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import services.BoutiqueService;
import services.BoutiqueService;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ListBoutiqueController implements Initializable {

    @FXML
    private TableView<Boutique> view;
    @FXML
    private TableColumn<Boutique, String> cl_nom;
    @FXML
    private TableColumn<Boutique, String> cl_desc;
    @FXML
    private TableColumn<Boutique, String> cl_adresse;
    @FXML
    private TableColumn<Boutique, String> cl_img;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            view.setEditable(true);
        cl_nom.setCellFactory(TextFieldTableCell.forTableColumn());
        cl_desc.setCellFactory(TextFieldTableCell.forTableColumn());
        cl_adresse.setCellFactory(TextFieldTableCell.forTableColumn());
        cl_img.setCellFactory(TextFieldTableCell.forTableColumn());

        try {
            afficherB();
            addButtonToTable();
            

        } catch (SQLException ex) {
            Logger.getLogger(ListBoutiqueController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    // ajouter button supprimer pour chaque ligne de tableau 
    private void addButtonToTable() throws SQLException {
        TableColumn actionCol = new TableColumn("Action");
        actionCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

        Callback<TableColumn<Boutique, Void>, TableCell<Boutique, Void>> cellFactory;
        cellFactory = new Callback<TableColumn<Boutique, Void>, TableCell<Boutique, Void>>() {
            @Override
            public TableCell<Boutique, Void> call(final TableColumn<Boutique, Void> param) {
                final TableCell<Boutique, Void> cell = new TableCell<Boutique, Void>() {
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
                            alert.setHeaderText("Voulez-vous vraiment supprimer ce produit?");
                            Optional<ButtonType> option = alert.showAndWait();
                            if (option.get() == ButtonType.OK) {
                                BoutiqueService sf = new BoutiqueService();
                                Boutique p = getTableView().getItems().get(getIndex());
                                try {
                                    sf.supprimer(p.getId());
                                    afficherB();
                                } catch (SQLException ex) {
                                    Logger.getLogger(ListBoutiqueController.class.getName()).log(Level.SEVERE, null, ex);
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

    @FXML
    private void updateNom(CellEditEvent edditedCell) throws SQLException {
        BoutiqueService P = new BoutiqueService();
        Boutique personSelected = (Boutique) view.getSelectionModel().getSelectedItem();
        String nom = personSelected.getNomBoutique();
        int id = personSelected.getId();
        String prix = personSelected.getDescBoutique();
        String qt = personSelected.getAdresseBoutique();
        String description = personSelected.getImg();
        
        personSelected.setDesc_produit(edditedCell.getNewValue().toString());

         P.Modifier(id, prix, qt,description, Image,   edditedCell.getNewValue().toString());
        afficher();
    }

    @FXML
    private void UpdateDesc(TableColumn.CellEditEvent<S, T> event) {
    }

    @FXML
    private void UpdateAdresse(TableColumn.CellEditEvent<S, T> event) {
    }

    @FXML
    private void UpdateImage(TableColumn.CellEditEvent<S, T> event) {
    }

    @FXML
    private void rechercher(KeyEvent event) {
    }
    public void afficherB() throws SQLException {
        BoutiqueService b = new BoutiqueService();
        List lists = b.afficherB();

        ObservableList ListBoutique = FXCollections.observableArrayList(lists);

        view.setItems(ListBoutique);

        //nombre.setText(Integer.toString(number));
        cl_nom.setCellValueFactory(new PropertyValueFactory<>("nom_Boutique"));

        cl_desc.setCellValueFactory(new PropertyValueFactory<>("desc_boutique"));
        cl_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse_boutique"));
        cl_img.setCellValueFactory(new PropertyValueFactory<>("image"));
       
        
        
    }
}
