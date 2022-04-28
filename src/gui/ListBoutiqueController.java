/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Boutique;
import java.io.IOException;
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
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
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
    private TableColumn<Boutique, String> image;
    @FXML
    private Button o;
    @FXML
    private Button c;
    @FXML
    private Button t;
    @FXML
    private Button e;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            view.setEditable(true);
        cl_nom.setCellFactory(TextFieldTableCell.forTableColumn());
        cl_desc.setCellFactory(TextFieldTableCell.forTableColumn());
        cl_adresse.setCellFactory(TextFieldTableCell.forTableColumn());
        image.setCellFactory(TextFieldTableCell.forTableColumn());

        try {
            afficher();
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
                                BoutiqueService bs = new BoutiqueService();
                                Boutique b = getTableView().getItems().get(getIndex());
                                
                                    bs.supprimer(b.getIdd());
                                try {
                                    afficher();
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
        Boutique personSelected =  view.getSelectionModel().getSelectedItem();
        String nom = personSelected.getNomBoutique();
        int id = personSelected.getIdd();
        String desc = personSelected.getDescBoutique();
        String add = personSelected.getAdresseBoutique();
        String img = personSelected.getImg();
        
        personSelected.setDescBoutique(edditedCell.getNewValue().toString());

         P.Modifier(id, edditedCell.getNewValue().toString(), desc,add, img);
        afficher();
    }

    @FXML
    private void UpdateDesc(CellEditEvent edditedCell) throws SQLException {
        BoutiqueService P = new BoutiqueService();
        Boutique personSelected = (Boutique) view.getSelectionModel().getSelectedItem();
        String nom = personSelected.getNomBoutique();
int id = personSelected.getIdd();
String desc = personSelected.getDescBoutique();
        String add = personSelected.getAdresseBoutique();
        String img = personSelected.getImg();
        
        personSelected.setDescBoutique(edditedCell.getNewValue().toString());

         P.Modifier(id, nom, edditedCell.getNewValue().toString(),add, img);
        afficher();
    }

    @FXML
    private void UpdateAdresse(CellEditEvent edditedCell) throws SQLException {
        BoutiqueService P = new BoutiqueService();
        Boutique personSelected = (Boutique) view.getSelectionModel().getSelectedItem();
        String nom = personSelected.getNomBoutique();
        int id = personSelected.getIdd();
        String desc = personSelected.getDescBoutique();
        String add = personSelected.getAdresseBoutique();
        String img = personSelected.getImg();
        
        personSelected.setDescBoutique(edditedCell.getNewValue().toString());

         P.Modifier(id, nom, desc,edditedCell.getNewValue().toString(), img);
        afficher();
    }

 

   
    public void afficher() throws SQLException {
        BoutiqueService b = new BoutiqueService();
        List lists = b.afficher();

        ObservableList ListBoutique = FXCollections.observableArrayList(lists);

        view.setItems(ListBoutique);

        //nombre.setText(Integer.toString(number));
        cl_nom.setCellValueFactory(new PropertyValueFactory<>("nomBoutique"));

        cl_desc.setCellValueFactory(new PropertyValueFactory<>("descBoutique"));
        cl_adresse.setCellValueFactory(new PropertyValueFactory<>("adresseBoutique"));
        image.setCellValueFactory(new PropertyValueFactory<>("img"));
       
        
        
    }
    
    @FXML
    private void PageAddP(javafx.event.ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/gui/AddProduit.fxml")));
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void PageListP (javafx.event.ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/gui/ListProduit.fxml")));
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void PageAddB(javafx.event.ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/gui/AddBoutique.fxml")));
        stage.setScene(scene);
        stage.show();
    }
     @FXML
    private void Front (javafx.event.ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/gui/ProduitsF.fxml")));
        stage.setScene(scene);
        stage.show();
    }
}
