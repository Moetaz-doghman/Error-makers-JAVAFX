/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entité.Commentaire;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class ItemCommentaireController implements Initializable {

    @FXML
    private TextField pseudo;
    @FXML
    private TextArea contenu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    public void setData(Commentaire comm) {
        pseudo.setText(comm.getPseudo());
        contenu.setText(comm.getContenu());
        pseudo.setEditable(false);
        contenu.setEditable(false);

        int id = comm.getAnnoncesId();
    }
       
}
