    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

//import com.google.zxing.qrcode.encoder.QRCode;
import entities.Reclamation;
import entities.Reponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import net.glxn.qrgen.image.ImageType;
import net.glxn.qrgen.QRCode;
import services.ReponseService;

/**
 * FXML Controller class
 *
 * @author mariem
 */
public class Reclamation_itemController implements Initializable {

    
   
    @FXML
    private Label name;
    @FXML
    private Label sujet;
    @FXML
    private Label Typer;
    @FXML
    private Label email;
    @FXML
    private Label message;
    @FXML
    private ImageView qrcode;
    
    Reponse rep = new Reponse();
    ReponseService rs = new ReponseService();
    
    
    public void setData(Reclamation recl) throws FileNotFoundException{
        name.setText(recl.getName());
        sujet.setText(recl.getSubject());
        Typer.setText(recl.getType());
        email.setText(recl.getEmail());
        message.setText(recl.getMessage());
        int id = recl.getId();
        String details = "vous avez reçu une réponse :"+rs.recuperer(id).getMessage(); 
        ByteArrayOutputStream out = QRCode.from(details).to(ImageType.JPG).stream();
        File f = new File("C:\\Users\\mariem\\Documents\\NetBeansProjects\\PiDev java\\src\\image\\qr"+recl.getId()+".jpg");
        FileOutputStream fos = new FileOutputStream(f);
        
        try {
            
            fos.write(out.toByteArray());
            
        } catch (IOException ex) {
            Logger.getLogger(Reclamation_itemController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        FileInputStream input = new FileInputStream("C:\\Users\\mariem\\Documents\\NetBeansProjects\\PiDev java\\src\\image\\qr"+recl.getId()+".jpg");
        Image image = new Image(input) ; 
        qrcode.setImage(image);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
