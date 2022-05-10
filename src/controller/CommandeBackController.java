/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Notifcation.AlertType;
import Notifcation.AlertsBuilder;
import Notifcation.NotificationType;
import Notifcation.NotificationsBuilder;
import animations.Animations;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import entity.Commande;
import entity.userSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import service.CommandeCrud;
import utils.Constants;
import utils.JFXDialogTool;
import java.awt.Desktop;
import static java.awt.SystemColor.desktop;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import static javax.management.Query.lt;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utils.Myconnexion;


/**
 * FXML Controller class
 *
 * @author doghm
 */
public class CommandeBackController implements Initializable {

    @FXML
    private Button listUserButton;
    @FXML
    private Button ListDemandeButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button btnev1;
    @FXML
    private Button btnjo;
    @FXML
    private StackPane stckCommande;
    @FXML
    private AnchorPane rootCommande;
    @FXML
    private Pane ContainerUsersAdmin;
    @FXML
    private TabPane PaneTableau;
    @FXML
    private TableView<Commande> TableViewCommande;
    @FXML
    private TableColumn<Commande, String> col_NomCommande;
    @FXML
    private TableColumn<Commande, String> col_PrenomCommande;
    @FXML
    private TableColumn<Commande, String> col_AdresseCommande;
    @FXML
    private TableColumn<Commande, String> col_MontantCommande;
    @FXML
    private TableColumn<Commande, String> col_ActionCommande;
    @FXML
    private TableColumn<Commande, String> col_DateCommande;
    @FXML
    private TableColumn<Commande, String> col_EtatCommande;
    @FXML
    private TableColumn<Commande, String> col_PhoneCommande;
    
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
    private JFXComboBox<String> comboEtat;
    @FXML
    private ImageView iconRole;
    
     private ObservableList<Commande> FiltreCommande;
    private ObservableList<Commande> ListCommande;
    private JFXDialogTool dialogDeleteCommande;
     private AnchorPane containerAjouterCategorie;
    private JFXDialogTool dialogAjouterCommande;
    private static final Stage stage = new Stage();
    private JFXTextField txtprenom;




    Commande commande = new Commande(); 
    CommandeCrud crudCommande = new CommandeCrud();
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
      
        LoadTableCommande();

        FiltreCommande = FXCollections.observableArrayList();
        comboEtat.getItems().addAll("Non Livrée", "Livree");
        LoadStat();
        Animations.fadeInUp(rootCommande);
    }  
    
    private void LoadStat() {

        // Changing random data after every 1 second.
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int Total = crudCommande.countTotalCommande();
                txtStatTotal.setText(String.valueOf(Total));
                String MaxGenre = crudCommande.MaxUsedAdresse();
                txtStatMax.setText(String.valueOf(MaxGenre));

            }
        }));
        ///Repeat indefinitely until stop() method is called.
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.setAutoReverse(true);
        timeline.play();

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

   

    @FXML
    private void iconAddCommandeClicked(MouseEvent event) {
    }

    @FXML
    private void hideDialogDeleteCommande(MouseEvent event) {
         if (dialogDeleteCommande != null) {
            dialogDeleteCommande.close();
        }
    }


    @FXML
    private void deleteCommandeClicked(MouseEvent event) {
          if (TableViewCommande.getSelectionModel().getSelectedItem() != null) {
            commande = TableViewCommande.getSelectionModel().getSelectedItem();
            Boolean result = crudCommande.SupprimerCommande(commande.getIdcommande());
            if (result) {
                hideDialogDeleteCategorie();
                AlertsBuilder.create(AlertType.SUCCES, stckCommande, rootCommande, TableViewCommande, "commande: " + commande.getNom_client()+ " " + commande.getPrenom_client()+ " \n" + "a été supprimé");
            } else {
                NotificationsBuilder.create(NotificationType.ERROR, Constants.MESSAGE_ERROR_CONNECTION_MYSQL);
            }
        }
    
    }

    @FXML
    private void hideDialogDeleteCategorie() {
    }

    @FXML
    private void SearchAnything(KeyEvent event) {
          String WordTyped = txtSearch.getText().trim();
        if (WordTyped.isEmpty()) {
            TableViewCommande.setItems(ListCommande);
            LoadTableCommande();
        } else {
           FiltreCommande.clear();
            for (Commande p : ListCommande) {
                if ((p.getNom_client().toLowerCase().contains(WordTyped.toLowerCase())) || (p.getPrenom_client().toLowerCase().contains(WordTyped.toLowerCase())) 
                        || (p.getPrenom_client().toLowerCase().contains(WordTyped.toLowerCase()))
                        || (p.getAdresse().toLowerCase().contains(WordTyped.toLowerCase()))
                         || (p.getMontant().toLowerCase().contains(WordTyped.toLowerCase()))
                        || (p.getTelephone().toLowerCase().contains(WordTyped.toLowerCase()))
                        || (p.getDateCommande().toString().contains(WordTyped.toLowerCase())) 
                        ) {
                    FiltreCommande.add(p);
                }
            }
           TableViewCommande.setItems(FiltreCommande);
        }
        
      
        
      
    }


    @FXML
    private void GeneratePDF(MouseEvent event) {
        
        long millis = System.currentTimeMillis();
        java.sql.Date DateRapport = new java.sql.Date(millis);

        String DateLyoum = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH).format(DateRapport);//yyyyMMddHHmmss
        System.out.println("DateLyoummmmmmmmmmmmmmmmmmmmm   " + DateLyoum);

        com.itextpdf.text.Document document = new com.itextpdf.text.Document();

        try {

            PdfWriter.getInstance(document, new FileOutputStream(String.valueOf(DateLyoum + ".pdf")));//yyyy-MM-dd
            document.open();
            Paragraph ph1 = new Paragraph("Rapport Pour :" + DateRapport);
            Paragraph ph2 = new Paragraph(".");
            PdfPTable table = new PdfPTable(6);

            //On créer l'objet cellule.
            PdfPCell cell;

            //contenu du tableau.
            table.addCell("Id");
            table.addCell("Nom Client");
            table.addCell("Prenom Client");
            table.addCell("Adress");
            table.addCell("Phone");
            table.addCell("Montant");


            //     table.addCell("Image : ");

            crudCommande.AfficherCommande(commande).forEach(e
                    -> {
                table.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(String.valueOf(e.getIdcommande()));
                table.addCell(e.getNom_client());
                table.addCell(e.getPrenom_client());
                table.addCell(e.getAdresse());
                table.addCell(e.getTelephone());
                table.addCell(String.valueOf(e.getMontant()));
            }
            );
            document.add(ph1);
            document.add(ph2);
            document.add(table);
            //  document.addAuthor("Bike");
            // AlertDialog.showNotification("Creation PDF ", "Votre fichier PDF a ete cree avec success", AlertDialog.image_checked);
        } catch (Exception e) {
            System.out.println(e);
        }
        document.close();

        ///Open FilePdf
        File file = new File(DateLyoum + ".pdf");
        if (file.exists()) //checks file exists or not  
        {
            Desktop desktop = Desktop.getDesktop();      
            try {
                desktop.open(file); //opens the specified file   
            } catch (IOException ex) {
                Logger.getLogger(CommandeBackController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void exportExcel(MouseEvent event) throws FileNotFoundException, IOException, SQLException {
        
// 
//            XSSFWorkbook wb = new XSSFWorkbook();
//            XSSFSheet sheet = wb.createSheet("Détails evenement");
//            XSSFRow header = sheet.createRow(0);
//            header.createCell(0).setCellValue("Nom");
////            header.createCell(1).setCellValue("Date");
////            header.createCell(2).setCellValue("Lieu");
////            header.createCell(3).setCellValue("Description");
////             header.createCell(4).setCellValue("Nb_place");
//      
//              
//            
//         
//           crudCommande.AfficherCommande(commande).forEach(e
//                    -> {
//               for (int i=0 ; i<5 ;i++){
//                XSSFRow row = sheet.createRow(i);
//                row.createCell(i).setCellValue(e.getNom_client());
//               }
//                
//                
//            }
//            );
//            
//            
//            
//            FileOutputStream file = new FileOutputStream("Détails evenement.xlsx");
//            wb.write(file);
//            file.close();
//            
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Information Dialog");
//            alert.setHeaderText(null);
//            alert.setContentText("Exportation effectuée!!!");
//            alert.showAndWait();
//          
//            ///Open File
//        File myfile = new File("tt.xlsx");
//        if (myfile.exists()) //checks file exists or not  
//        {
//            Desktop desktop = Desktop.getDesktop();      
//            try {
//                desktop.open(myfile); //opens the specified file   
//            } catch (IOException ex) {
//                Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
    
      Connection cnx = Myconnexion.getInstance().getCnx();
        String query = "Select * from commande";
         PreparedStatement pst = cnx.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("Détails commande");
            XSSFRow header = sheet.createRow(0);
            header.createCell(0).setCellValue("ID");
            header.createCell(1).setCellValue("Nom Client");
            header.createCell(2).setCellValue("Prenom Client");
            header.createCell(3).setCellValue("Adresse");
             header.createCell(4).setCellValue("Telephone");
        
              
            
            int index = 1;
            while(rs.next()){
                XSSFRow row = sheet.createRow(index);
                row.createCell(0).setCellValue(rs.getInt("id"));
                row.createCell(1).setCellValue(rs.getString("nom_client"));
                row.createCell(2).setCellValue(rs.getString("prenom_client"));
                row.createCell(3).setCellValue(rs.getString("adresse"));
                row.createCell(4).setCellValue(rs.getString("phone"));
                System.out.println(rs.getString("nom_client"));
                
               
                index++;
            }
            
//             crudCommande.AfficherCommande(commande).forEach(e
//                    -> {
//               for (int i=0 ; i<5 ;i++){
//                XSSFRow row = sheet.createRow(i);
//                row.createCell(0).setCellValue(e.getIdcommande());
//                row.createCell(1).setCellValue(e.getNom_client());
//                row.createCell(2).setCellValue(e.getPrenom_client());
//                row.createCell(3).setCellValue(e.getAdresse());
//                row.createCell(4).setCellValue(e.getAdresse());
//                  System.out.println(e.getNom_client());
//               }
//                
//                
//            }
//            );
            
            FileOutputStream file = new FileOutputStream("Détails commande.xlsx");
            wb.write(file);
            file.close();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Exportation effectuée!!!");
            alert.showAndWait();
            pst.close();
            rs.close();
            
            File myFile = new File("C:/Users/doghm/Desktop/Pidev---Tech-masters-main/gestionEve_Spon/Détails commande.xlsx");
             Desktop.getDesktop().open(myFile);
             
       
    
    
    }

    @FXML
    private void AjouterCommande(MouseEvent event) {
    }

    @FXML
    private void closeDialogAjouterCommande(MouseEvent event) {
        
        if (dialogAjouterCommande != null) {
            dialogAjouterCommande.close();

            btnModifierCommande.setVisible(true);
            btnCancelAddCommande.setVisible(true);
        }
       // txtNom.clear();
        comboEtat.getSelectionModel().clearSelection();
        LoadTableCommande();
    }

    @FXML
    private void ModifierCommande(MouseEvent event) {
          int idcommande = 0;
          
          if (comboEtat.getSelectionModel().getSelectedItem() == null) {
            comboEtat.requestFocus();
            Animations.shake(comboEtat);
            return;
        }
          else {
          
          if (TableViewCommande.getSelectionModel().getSelectedItem() != null) {
            idcommande = Integer.valueOf((TableViewCommande.getSelectionModel().getSelectedItem().getIdcommande()));
          String test = comboEtat.getValue();
          if (test == "Livree")
          {
              int etat = 1 ;
              commande.setEtat_commande(etat);
              commande.setIdcommande(idcommande);
            Boolean result = crudCommande.ModifierCommande(commande);
            if (result) {
                closeDialogAddCommande(event);
            AlertsBuilder.create(AlertType.SUCCES, stckCommande, rootCommande, TableViewCommande, Constants.MESSAGE_UPDATED);
          
        } else {
            NotificationsBuilder.create(NotificationType.ERROR, Constants.MESSAGE_ERROR_CONNECTION_MYSQL);
        }
        LoadTableCommande();

          }
          else 
          {
              int etat = 0 ; 
               commande.setEtat_commande(etat);
              commande.setIdcommande(idcommande);
            Boolean result = crudCommande.ModifierCommande(commande);
             if (result) {
                closeDialogAddCommande(event);
            AlertsBuilder.create(AlertType.SUCCES, stckCommande, rootCommande, TableViewCommande, Constants.MESSAGE_UPDATED);
          
        } else {
            NotificationsBuilder.create(NotificationType.ERROR, Constants.MESSAGE_ERROR_CONNECTION_MYSQL);
        }
        LoadTableCommande();
          }
            
        }
        
        
    
    
    }
    }

    @FXML
    private void closeDialogAddCommande(MouseEvent event) {
        
//          if (dialogAjouterCommande != null) {
//            dialogAjouterCommande.close();
//
//            btnModifierCommande.setVisible(true);
//            btnCancelAddCommande.setVisible(true);
//        }
//        txtNom.clear();
//        comboEtat.getSelectionModel().clearSelection();
//        LoadTableCommande();
    }
    
          private void LoadTableCommande() {

        List<Commande> listeCommande = new ArrayList<>();
        listeCommande = crudCommande.AfficherCommande(commande);
        ObservableList<Commande> Listeeee = FXCollections.observableArrayList(listeCommande);

        
        col_NomCommande.setCellValueFactory(new PropertyValueFactory<>("nom_client"));
        col_PrenomCommande.setCellValueFactory(new PropertyValueFactory<>("prenom_client"));
        col_AdresseCommande.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        col_MontantCommande.setCellValueFactory(new PropertyValueFactory<>("montant"));
        col_DateCommande.setCellValueFactory(new PropertyValueFactory<>("dateCommande"));
        col_EtatCommande.setCellValueFactory(new PropertyValueFactory<>("etat_commande"));
        col_PhoneCommande.setCellValueFactory(new PropertyValueFactory<>("telephone"));




                

        ListCommande = FXCollections.observableArrayList(listeCommande);
        TableViewCommande.setItems(ListCommande);
        

        //add cell of button edit 
        Callback<TableColumn<Commande, String>, TableCell<Commande, String>> cellFoctory = (TableColumn<Commande, String> param) -> {
            //make cell containing buttons

            final TableCell<Commande, String> cell = new TableCell<Commande, String>() {

                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    // that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                    } else {

                        ImageView DeleteCommande, EditCommande;
                        EditCommande = new ImageView(new Image("/ressource/editcateg.png"));
                        EditCommande.setFitHeight(30);
                        EditCommande.setFitWidth(30);
                        setGraphic(EditCommande);

                        DeleteCommande = new ImageView(new Image("/ressource/deletecateg.png"));
                        DeleteCommande.setFitHeight(30);
                        DeleteCommande.setFitWidth(30);
                        setGraphic(DeleteCommande);

                        EditCommande.setOnMouseClicked((MouseEvent event) -> {
                            System.out.println("icon edit is pressed !");

                          
                            showDialogModifierCommande();
                        });

                        DeleteCommande.setOnMouseClicked((MouseEvent event) -> {
                            System.out.println("icon delete is pressed !");
                            int id = Integer.valueOf((TableViewCommande.getSelectionModel().getSelectedItem().getIdcommande()));
                            comboEtat.setValue(TableViewCommande.getSelectionModel().getSelectedItem().getNom_client());
                            //btnUpdateReclam.toFront();
                            textTitreCategorie.setText("Modifier La commande");

                            showDialogDeleteCommande();
                        });
                  //      managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(DeleteCommande, new Insets(2, 2, 0, 3));
                        HBox.setMargin(EditCommande, new Insets(2, 3, 0, 2));
                        HBox managebtn = new HBox(EditCommande, DeleteCommande);
                        setGraphic(managebtn);
                    }
                }
            };
            return cell;
        };
        col_ActionCommande.setCellFactory(cellFoctory);
    }
          
    private void showDialogDeleteCommande() {
        rootCommande.setEffect(Constants.BOX_BLUR_EFFECT);
        ContainerDeleteCommande.setVisible(true);

        dialogDeleteCommande = new JFXDialogTool(ContainerDeleteCommande, stckCommande);
        dialogDeleteCommande.show();

        dialogDeleteCommande.setOnDialogClosed(ev -> {
            TableViewCommande.setDisable(false);
            rootCommande.setEffect(null);
            ContainerDeleteCommande.setVisible(false);
            LoadTableCommande();

        });

    }
    
    private void showDialogModifierCommande() {

       rootCommande.setEffect(Constants.BOX_BLUR_EFFECT);
        //imageContainer.toFront();
        containerAjouterCommande.setVisible(true);
        // btnSaveReclam.setDisable(false);

        dialogAjouterCommande = new JFXDialogTool(containerAjouterCommande, stckCommande);
        dialogAjouterCommande.show();
        dialogAjouterCommande.setOnDialogOpened(ev -> {
            txtprenom.requestFocus();
        });

        dialogAjouterCommande.setOnDialogClosed(ev -> {
            closeStage();
            TableViewCommande.setDisable(false);
            rootCommande.setEffect(null);
            containerAjouterCategorie.setVisible(false);
            LoadTableCommande();
        });
    }
    
     public static void closeStage() {
        if (stage != null) {
            stage.hide();
        }
    }

    @FXML
    private void showchart(MouseEvent event) {
         try {
                 CommandeCrud se=new CommandeCrud();
            Commande c = new Commande();
            List<Commande> le=se.AfficherCommande(c);
        ObservableList<Commande> data =FXCollections.observableArrayList(le);
            FXMLLoader chart= new FXMLLoader(getClass().getResource("../GUI/chart.fxml"));
            Parent root = chart.load();
            ChartController mc = chart.getController();
           
           
             Scene scene = new Scene(root);
           Stage modifStage = new Stage();
            
            modifStage.setTitle("Chart commande!");
            modifStage.setScene(scene);
            modifStage.show();
            
             ChartController controller = chart.getController();
        controller.setReclamationData(data);
            
            
            
        } catch (IOException ex) {
        }
    }

      
      
    
}
