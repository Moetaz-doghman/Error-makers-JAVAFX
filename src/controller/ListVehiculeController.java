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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import Config.JfreeChartApi;
import animations.Animations;
import entity.Vehicule;
import entity.userSession;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.sql.SQLException;
import java.util.HashMap;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Callback;
import service.VehiculeServices;

/**
 * FXML Controller class
 *
 * @author doghm
 */
public class ListVehiculeController implements Initializable {

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
    private TableView<Vehicule> view;
    @FXML
    private TableColumn<Vehicule, String> cl_matricule;
        @FXML
    private TableColumn<Vehicule, Date> cl_date;
    @FXML
    private TableColumn<Vehicule, String> cl_couleur;
    @FXML
    private TableColumn<Vehicule, String> cl_typevehicule;
    @FXML
    private TableColumn<Vehicule, String> cl_marque;
    @FXML
    private TableColumn<Vehicule, String> cl_etatvehicule;
    @FXML
    private AnchorPane ContainerDeleteCommande;
    @FXML
    private Circle imgOnline;
    @FXML
    private ImageView print;
    @FXML
    private RadioButton tri3;
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
    private RadioButton tri2;
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
       
        cl_marque.setCellFactory(TextFieldTableCell.forTableColumn());
        cl_matricule.setCellFactory(TextFieldTableCell.forTableColumn());
        cl_couleur.setCellFactory(ChoiceBoxTableCell.forTableColumn("Rouge", "Verte", "Bleue", "Noir","Grise","blanche"));
        cl_etatvehicule.setCellFactory(ChoiceBoxTableCell.forTableColumn("disponible", "non disponible"));
         cl_typevehicule.setCellFactory(ChoiceBoxTableCell.forTableColumn("camion", "voiture", "moto"));
       try {
            afficher();
            addButtonToTable();
            

        } catch (SQLException ex) {
            Logger.getLogger(ListVehiculeController.class.getName()).log(Level.SEVERE, null, ex);
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
    
      private void addButtonToTable() throws SQLException {
        TableColumn actionCol = new TableColumn("Action");
        actionCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

        Callback<TableColumn<Vehicule, Void>, TableCell<Vehicule, Void>> cellFactory;
        cellFactory = new Callback<TableColumn<Vehicule, Void>, TableCell<Vehicule, Void>>() {
            @Override
            public TableCell<Vehicule, Void> call(final TableColumn<Vehicule, Void> param) {
                final TableCell<Vehicule, Void> cell = new TableCell<Vehicule, Void>() {
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
                                VehiculeServices sf = new VehiculeServices();
                                Vehicule p = getTableView().getItems().get(getIndex());
                                
                                try {
                                    sf.supprimerV(p.getId());
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
        VehiculeServices P = new VehiculeServices();
        List lists = P.afficher();

        ObservableList ListProduit = FXCollections.observableArrayList(lists);

        view.setItems(ListProduit);

        //nombre.setText(Integer.toString(number));
        cl_matricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));

        cl_couleur.setCellValueFactory(new PropertyValueFactory<>("couleur"));
        cl_typevehicule.setCellValueFactory(new PropertyValueFactory<>("type_vehicule"));
        cl_marque.setCellValueFactory(new PropertyValueFactory<>("marque"));
        cl_etatvehicule.setCellValueFactory(new PropertyValueFactory<>("etat_vehicule"));
       
           cl_date.setCellValueFactory(new PropertyValueFactory<>("date_entretient"));
        
    }


   
     @FXML
    private void Updatematricule(CellEditEvent edditedCell) throws SQLException {
        VehiculeServices P = new VehiculeServices();
        Vehicule personSelected = view.getSelectionModel().getSelectedItem();
        String mat = personSelected.getMatricule();
      int id=personSelected.getId();
        String clr = personSelected.getCouleur();
        String type = personSelected.getType_vehicule();
        String marque = personSelected.getMarque();
        String etat = personSelected.getEtat_vehicule();
     Date d = personSelected.getDate_entretient();
        personSelected.setMatricule(edditedCell.getNewValue().toString());
         P.Modifier(id,edditedCell.getNewValue().toString(),clr , type,marque, etat );
        afficher();
    }

 
    @FXML
    private void Updatecouleur(CellEditEvent edditedCell) throws SQLException {
      VehiculeServices P = new VehiculeServices();
        Vehicule personSelected =view.getSelectionModel().getSelectedItem();
        String mat = personSelected.getMatricule();
      int id=personSelected.getId();
        String clr = personSelected.getCouleur();
        String type = personSelected.getType_vehicule();
        String marque = personSelected.getMarque();
        String etat = personSelected.getEtat_vehicule();
     Date d = personSelected.getDate_entretient();
        personSelected.setCouleur(edditedCell.getNewValue().toString());
         P.Modifier(id,mat,edditedCell.getNewValue().toString() , type,marque, etat );
        afficher();
    }


    @FXML
    private void updatetype_vehicule(CellEditEvent edditedCell) throws SQLException {
      VehiculeServices P = new VehiculeServices();
        Vehicule personSelected =  view.getSelectionModel().getSelectedItem();
        String mat = personSelected.getMatricule();
      int id=personSelected.getId();
        String clr = personSelected.getCouleur();
        String type = personSelected.getType_vehicule();
        String marque = personSelected.getMarque();
        String etat = personSelected.getEtat_vehicule();
     Date d = personSelected.getDate_entretient();
        personSelected.setCouleur(edditedCell.getNewValue().toString());
         P.Modifier(id,mat, clr, edditedCell.getNewValue().toString(),marque, etat);
        afficher();
    }

    @FXML
    private void Updatemarque(CellEditEvent edditedCell) throws SQLException {
         VehiculeServices P = new VehiculeServices();
        Vehicule personSelected =  view.getSelectionModel().getSelectedItem();
        String mat = personSelected.getMatricule();
      int id=personSelected.getId();
        String clr = personSelected.getCouleur();
        String type = personSelected.getType_vehicule();
        String marque = personSelected.getMarque();
        String etat = personSelected.getEtat_vehicule();
     Date d = personSelected.getDate_entretient();
        personSelected.setMarque(edditedCell.getNewValue().toString());
         P.Modifier(id,mat, clr,type, edditedCell.getNewValue().toString(), etat );
        afficher();
    }
    @FXML
    private void UpdateEtat_vehicule(CellEditEvent edditedCell) throws SQLException {
         VehiculeServices P = new VehiculeServices();
        Vehicule personSelected =  view.getSelectionModel().getSelectedItem();
        String mat = personSelected.getMatricule();
      int id=personSelected.getId();
        String clr = personSelected.getCouleur();
        String type = personSelected.getType_vehicule();
        String marque = personSelected.getMarque();
        String etat = personSelected.getEtat_vehicule();
     Date d = personSelected.getDate_entretient();
        personSelected.setEtat_vehicule(edditedCell.getNewValue().toString());
         P.Modifier(id,mat, clr,type,marque , edditedCell.getNewValue().toString());
        afficher();
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
    private void showchart(MouseEvent event) {
        
          VehiculeServices rvs = new VehiculeServices();
        HashMap<String, Double> data = rvs.StatistiqueParType();
        JfreeChartApi chart = new JfreeChartApi(data);
        chart.afficherStatistique();
    }

    @FXML
    private void triPrix(ActionEvent event) throws SQLException {
      VehiculeServices sf = new VehiculeServices();
        List listcs = sf.triParMatricule();

        ObservableList listFormations = FXCollections.observableArrayList(listcs);

        view.setItems(listFormations);

        cl_matricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));

        cl_couleur.setCellValueFactory(new PropertyValueFactory<>("couleur"));
        cl_typevehicule.setCellValueFactory(new PropertyValueFactory<>("type_vehicule"));
        cl_marque.setCellValueFactory(new PropertyValueFactory<>("marque"));
        cl_etatvehicule.setCellValueFactory(new PropertyValueFactory<>("etat_vehicule"));
       
        cl_date.setCellValueFactory(new PropertyValueFactory<>("date_entretient"));
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
    private void trimarque(ActionEvent event) throws SQLException {
          VehiculeServices sf = new VehiculeServices();
        List listcs = sf.triParMarque();

        ObservableList listFormations = FXCollections.observableArrayList(listcs);

        view.setItems(listFormations);

        cl_matricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));

        cl_couleur.setCellValueFactory(new PropertyValueFactory<>("couleur"));
        cl_typevehicule.setCellValueFactory(new PropertyValueFactory<>("type_vehicule"));
        cl_marque.setCellValueFactory(new PropertyValueFactory<>("marque"));
        cl_etatvehicule.setCellValueFactory(new PropertyValueFactory<>("etat_vehicule"));
       
           cl_date.setCellValueFactory(new PropertyValueFactory<>("date_entretient"));

    }

    
}
