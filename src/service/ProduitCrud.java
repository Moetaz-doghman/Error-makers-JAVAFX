/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import utils.Myconnexion;
import entity.Product;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 *
 */
public class ProduitCrud implements IProduit<Product> {

    @Override
    public List<Product> AfficherProduit(Product t) {
        List<Product> list = new ArrayList<>();
        try {
            String requete = "SELECT * FROM produit ";
            Statement pst = Myconnexion.getInstance().getCnx().createStatement();
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Product r = new Product();
                r.setId(rs.getInt("id"));
                r.setProduct_name(rs.getString("nom_produit"));
                r.setImg(rs.getString("image"));
               r.setPrice(rs.getDouble("prix_produit"));
                r.setStock(rs.getInt("quantite_produit"));
                 


                
                list.add(r);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return list;
    }

 

}