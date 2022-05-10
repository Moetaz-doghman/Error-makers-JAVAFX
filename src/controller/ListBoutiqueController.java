/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import animations.Animations;
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
import entity.Boutique;
import entity.userSession;
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
import javafx.scene.Parent;
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
import service.BoutiqueService;

/**
 * FXML Controller class
 *
 * @author doghm
 */
public class ListBoutiqueController implements Initializable {

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
    private AnchorPane ContainerDeleteCommande;
    @FXML
    private Circle imgOnline;
    @FXML
    private TextField txtSearch;
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
    @FXML
    private Button Boutique;
    @FXML
    private Button addboutique;
    @FXML
    private Button produit;
    @FXML
    private Button addproduit;
    @FXML
    private Button reponse;
    @FXML
    private Button listlivrasion;
    @FXML
    private Button addlivrasion;
    @FXML
    private Button listvehicule;
    @FXML
    private Button addvehicule;

    /**
     * Initializes the controller class.
     */
      @Override
    public void initialize(URL url, ResourceBundle rb) {
         Animations.fadeInUp(rootCommande);
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
    private void SearchAnything(KeyEvent event) {
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
