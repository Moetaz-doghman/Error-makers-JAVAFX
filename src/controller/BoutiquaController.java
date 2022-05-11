/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import GUI.NewFXMain1;
import GUI.NewFXMain1;
import entity.Boutique;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import utils.MyListener;


public class BoutiquaController {
    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLable;

    @FXML
    private ImageView img;

    @FXML
    private void click(MouseEvent mouseEvent) {
      myListener.onClickListener2(bout);
    }

    private Boutique bout;
    private MyListener myListener;

    public void setData(Boutique bout, MyListener myListener) throws FileNotFoundException {
        this.bout = bout;
        this.myListener = myListener;
        nameLabel.setText(bout.getNomBoutique());
        priceLable.setText(NewFXMain1.CURRENCY + bout.getAdresseBoutique());
        FileInputStream input = new FileInputStream ("C:\\Users\\doghm\\Desktop\\Error-makers-JAVAFX\\src\\images\\"+bout.getImg()) ;
        Image image = new Image(input) ; 
        img.setImage(image);
    }
}
