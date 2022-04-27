/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Produit;
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
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import services.ProduitService;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ListProduitController implements Initializable {
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
        cl_img.setCellFactory(TextFieldTableCell.forTableColumn());

        try {
            afficher();
            addButtonToTable();
            

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

    @FXML
    private void rechercher(KeyEvent event) {
    }

 

   

  
    
}
