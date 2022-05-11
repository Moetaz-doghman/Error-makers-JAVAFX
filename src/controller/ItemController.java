/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import GUI.NewFXMain1;
import entity.Produit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import utils.MyListener;


public class ItemController {
    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLable;

    @FXML
    private ImageView img;

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(prod);
    }

    private Produit prod;
    private MyListener myListener;

    public void setData(Produit prod, MyListener myListener) throws FileNotFoundException {
        this.prod = prod;
        this.myListener = myListener;
        nameLabel.setText(prod.getNom_produit());
        priceLable.setText(NewFXMain1.CURRENCY + prod.getPrix_produit());
        FileInputStream input = new FileInputStream ("C:\\Users\\doghm\\Desktop\\Error-makers-JAVAFX\\src\\images\\"+prod.getImage()) ;
        Image image = new Image(input) ; 
        img.setImage(image);
    }
}
