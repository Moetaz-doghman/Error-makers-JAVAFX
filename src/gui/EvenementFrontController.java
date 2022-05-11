/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entité.Commentaire;
import entité.Evenement;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import service.CommentaireService;
import service.EvenementService;
import util.Badwords;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class EvenementFrontController implements Initializable {
     EvenementService evenements;
   CommentaireService commentaires;
   int idE;

    @FXML
    private Button BackBtn;
    @FXML
    private Button nextBtn;
    @FXML
    private ImageView imgEvent;
    @FXML
    private TextField nomEvent;
    @FXML
    private TextField adresseEvent;
    @FXML
    private TextField dateDebEvent;
    @FXML
    private TextField DateFinEvent;
    @FXML
    private TextField descEvent;
    @FXML
    private TextField LongDescEvent;
    @FXML
    private TextArea contenuComment;
    @FXML
    private Button sendCommentBtn;
    @FXML
    private TextField pseudo;
    @FXML
    private TextField idEvent;
    @FXML
    private VBox CommentaireLayout;
    @FXML
    private TextField pseudo1;
    @FXML
    private TextArea contenu;
    @FXML
    private Button home;
    @FXML
    private Button boutique;
    @FXML
    private Button event;
    @FXML
    private Button reclamation;
    @FXML
    private Button profil;
    @FXML
    private Button cart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        evenements = new EvenementService();
        commentaires = new CommentaireService();
        EvenementService es = new EvenementService();
        
        List<Integer> ListEvent= es.getIdEvent();
        System.out.println(ListEvent.size());
       
       idE=ListEvent.get(0);
       afficherEvenement(ListEvent.get(0)); 
        load(idE);
    }
     private List<Commentaire> getData(int id) {
        List<Commentaire> comm = new ArrayList<>();
        
        CommentaireService prs = new CommentaireService();
        List<Commentaire> l = prs.findByEvenement(id);
        for (Commentaire p : l) {
            comm.add(p);//adding  product object to list
        }

        return comm ;
    }
     private void load(int id){
            CommentaireLayout.getChildren().removeAll(CommentaireLayout.getChildren());

            List<Commentaire> Commentaires = new ArrayList<>(getData(id));
        for (int i=0; i<Commentaires.size(); i++){
            
         
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("ItemCommentaire.fxml"));
                    try{
                    HBox hBox = fxmlLoader.load();
                    ItemCommentaireController ric = fxmlLoader.getController();
                    ric.setData(Commentaires.get(i));
                    
                    CommentaireLayout.getChildren().add(hBox);
                    }catch(IOException e){
                        System.out.println(e.getMessage());
                    }
        
        }
    }
     
 public void afficherEvenement(int id){
        EvenementService es = new EvenementService();
        
        Evenement event = es.findById(id);
        
        idEvent.setText(String.valueOf(event.getId()));
        nomEvent.setText(event.getNom());
        adresseEvent.setText(event.getAdresse());
        dateDebEvent.setText(event.getDate().toString());
        DateFinEvent.setText(event.getDatefin().toString());
        descEvent.setText(event.getDescription());
        LongDescEvent.setText(event.getLongdesc());
        
        idEvent.setEditable(false);
        nomEvent.setEditable(false);
        dateDebEvent.setEditable(false);
        LongDescEvent.setEditable(false);
        DateFinEvent.setEditable(false);
        descEvent.setEditable(false);

        adresseEvent.setEditable(false);

        
        if(event.getAffiche()!=null){
                Image image =new Image(event.getAffiche());
                imgEvent.setImage(image);
                }else{
                    Image image =new Image("images/vide.png");
                    imgEvent.setImage(image);

                }      
    }    


    @FXML
    private void BackBtn(ActionEvent event) {
         EvenementService es = new EvenementService();
        List<Integer> ListEvent= es.getIdEvent();
        System.out.println(ListEvent);
        
        int i=2;
        if(i<ListEvent.size()){
             afficherEvenement(ListEvent.get(i));
             i--;
             idE=ListEvent.get(i);
        }
        load(idE);
        System.out.println(i);
    }

    @FXML
    private void nextBtn(ActionEvent event) {
         EvenementService es = new EvenementService();
        List<Integer> ListEvent= es.getIdEvent();
        System.out.println(ListEvent);
        
        int i=1;
        if(i<ListEvent.size()){
             afficherEvenement(ListEvent.get(i));
             idE=ListEvent.get(i);
             i++;
             
        }
        load(idE);
        System.out.println(i);
    }

    @FXML
    private void sendComment(ActionEvent event) {
        
        CommentaireService cs = new CommentaireService();
        Commentaire c = new Commentaire(Integer.valueOf(idEvent.getText()),Badwords.filter(contenuComment.getText()),pseudo.getText());
        cs.insert(c);
        pseudo.clear();
        contenuComment.clear();
        load(idE);
        
    }

    @FXML
    private void home(ActionEvent event) {
    }

    @FXML
    private void boutique(ActionEvent event) {
    }

    @FXML
    private void event(ActionEvent event) {
    }

    @FXML
    private void reclamation(ActionEvent event) {
    }

    @FXML
    private void addToCart(ActionEvent event) {
    }
    
}
