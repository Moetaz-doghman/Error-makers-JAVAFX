/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Cart;
import entity.Commande;
import entity.Lignecommande;
import entity.Product;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import service.CommandeCrud;
import service.LigneCrud;
import service.ProduitCrud;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.HttpURLConnection;
import java.net.*;
import java.util.Base64;
import java.io.*;
import javafx.scene.Parent;


/**
 * FXML Controller class
 *
 * @author doghm
 */
public class CommandeFrontController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private BorderPane body;
    @FXML
    private VBox sideArea;
    @FXML
    private HBox sideControls;
    @FXML
    private Label closeButton;
    @FXML
    private VBox sideNav;
    @FXML
    private Region navHome;
    @FXML
    private Region navCart;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Pane handPaneMac;
    @FXML
    private TextField nomfld;
    @FXML
    private TextField prenomfld;
    @FXML
    private TextField adressefld;
    @FXML
    private TextField phonefld;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void closeApp(MouseEvent event) {
    }

    @FXML
    private void showHomeView(MouseEvent event) {
    }

    @FXML
    private void showCartView(MouseEvent event) {
    }

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        
        long millis = System.currentTimeMillis();
        java.sql.Date DateRapport = new java.sql.Date(millis);

        String DateLyoum = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(DateRapport);//yyyyMMddHHmmss
        System.out.println("DateLyoummmmmmmmmmmmmmmmmmmmm   " + DateLyoum);
        
        String adresse=adressefld.getText();
        String prenom =prenomfld.getText();
        String nom =nomfld.getText();
        String phone = phonefld.getText();
        
        Double d  = Cart.getInstance().total() ;
        String montant=String.valueOf(d);  
        System.out.println(montant);
        
        
         
        StringBuilder errors =new StringBuilder(); 
        if(errors.length()>0){
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errors");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        }
        else
        {
            
           Commande e=new Commande(nom,prenom,phone,DateRapport,adresse,montant,0,0);
            CommandeCrud sp = new CommandeCrud();
            sp.AjouterCommande(e);
            
            int lastid = sp.LASTINSERTID();
            System.out.println(lastid);
            
        for (Product p : Cart.getInstance().getC()){
                    Lignecommande lg = new Lignecommande(lastid,p.getId(),p.getQuantity());
                    System.out.println(p.getId());
                    CommandeCrud order_ligne = new CommandeCrud();
                   LigneCrud dd = new LigneCrud();
                   dd.AjouterligneCommande(lg);

            }
        
        String message ="Votre commande votre commande est bien prise en compte "; 
             //SMSController smsc= new SMSController();
             sms("moetaz00", "Narjes1234+", "216"+phone, message);
             System.out.println("216"+phone);
              Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("../GUI/Paiement.fxml"));
             navHome.getScene().setRoot(root);
             
           
             
//clean();
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setHeaderText(null);
//            alert.setContentText("Commande Ajouté");
//            alert.showAndWait();
            
            
        }
        
        
    }
    
     public void sms(String username, String password, String to,String message){
            try{
        
        String myURI = "https://api.bulksms.com/v1/messages";

    // change these values to match your own account
    // new compte pour envoyer sms ***********************************************************
    String myUsername = "moetaz00";
    String myPassword = "Narjes1234+";

    // the details of the message we want to send
    String myData = "{to: \""+to+"\", encoding: \"UNICODE\", body: \""+message+"\"}";

    // if your message does not contain unicode, the "encoding" is not required:
    // String myData = "{to: \"1111111\", body: \"Hello Mr. Smith!\"}";

    // build the request based on the supplied settings
    URL url = new URL(myURI);
    HttpURLConnection request = (HttpURLConnection) url.openConnection();
    request.setDoOutput(true);

    // supply the credentials
    String authStr = myUsername + ":" + myPassword;
    String authEncoded = Base64.getEncoder().encodeToString(authStr.getBytes());
    request.setRequestProperty("Authorization", "Basic " + authEncoded);

    // we want to use HTTP POST
    request.setRequestMethod("POST");
    request.setRequestProperty( "Content-Type", "application/json");

    // write the data to the request
    OutputStreamWriter out = new OutputStreamWriter(request.getOutputStream());
    out.write(myData);
    out.close();

    // try ... catch to handle errors nicely
    try {
      // make the call to the API
      InputStream response = request.getInputStream();
      BufferedReader in = new BufferedReader(new InputStreamReader(response));
      String replyText;
      while ((replyText = in.readLine()) != null) {
        System.out.println(replyText);
      }
      in.close();
    } catch (IOException ex) {
      System.out.println("An error occurred:" + ex.getMessage());
      BufferedReader in = new BufferedReader(new InputStreamReader(request.getErrorStream()));
      // print the detail that comes with the error
      String replyText;
      while ((replyText = in.readLine()) != null) {
        System.out.println(replyText);
      }
      in.close();
    }
    request.disconnect();
        
    }catch(Exception e)
    {
        
        
        System.out.println(e);
    }}
    
}
