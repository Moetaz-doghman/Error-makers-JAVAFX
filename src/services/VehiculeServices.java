/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Vehicule;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import util.MyDB;

/**
 *
 * @author oaca
 */
public class VehiculeServices {
      private final Connection conn;
    private Statement ste;
    private PreparedStatement pste;

    public VehiculeServices() {
 conn = MyDB.getInstance().getConnection();
    }

    public void ajouterVehicule(Vehicule v){
        
        try {
            String requete ="INSERT INTO `vehicule`(`matricule`,`couleur`,`type_vehicule`,`marque`,`date_entretient`,`etat_vehicule`) VALUES(?,?,?,?,?,?)";
            pste = conn.prepareStatement(requete);
         java.sql.Date d=new java.sql.Date(v.getDate_entretient().getTime());
            pste.setString(1,v.getMatricule());
            pste.setString(2,v.getCouleur());
            pste.setString(3,v.getType_vehicule());
            pste.setString(4,v.getMarque());
            
            pste.setDate(5, d);
            pste.setString(6,v.getEtat_vehicule());

            pste.executeUpdate();
            System.out.println("vehicule ajoutée avec succès");
                
        } catch (SQLException ex) {
           System.err.println(ex.getMessage());
        }
    }
    
      public boolean Modifier(int id, String matricule, String couleur, String type_vehicule, String marque, String etat_vehicule) throws SQLException {
        PreparedStatement pre = conn.prepareStatement("UPDATE vehicule SET matricule= '" + matricule + "' , couleur= '" + couleur + "' ,type_vehicule= '" + type_vehicule + "'  ,marque= '" + marque + "'  ,etat_vehicule= '" + etat_vehicule + "' WHERE id='" + id + "' ;");

        JOptionPane.showMessageDialog(null, "vehicule modifié avec succées");
        pre.executeUpdate();

        return true;
    }
      public void supprimerV(int id) {
         try {
             String requete = "DELETE FROM vehicule  WHERE id =?";
       PreparedStatement pst = conn.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
                        System.out.println(" supprimée !");


        } catch (SQLException ex) {

            System.err.println(ex.getMessage());

        }
        
    }
       public ArrayList<Vehicule> afficher()throws SQLException {
        ArrayList<Vehicule> list = new ArrayList<>();

       
            String requete = "SELECT * FROM vehicule";
            PreparedStatement pst = conn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                
                list.add(new Vehicule(rs.getInt("id"),rs.getString("matricule"),rs.getString("couleur"),rs.getString("type_vehicule"),rs.getString("marque"),rs.getString("etat_vehicule"),rs.getDate("date_entretient")) );
                
            }

        

        return list;
    }
       public ArrayList<Vehicule> triParMatricule() throws SQLException {
        ArrayList<Vehicule> TabS = new ArrayList<>();
        String req = "SELECT * FROM vehicule order by  matricule Desc";
        PreparedStatement p;
        p = conn.prepareStatement(req);
        ResultSet rs = p.executeQuery();
        while (rs.next()) {

            TabS.add(new Vehicule(rs.getInt("id"),rs.getString("matricule"),rs.getString("couleur"),rs.getString("type_vehicule"),rs.getString("marque"),rs.getString("etat_vehicule"),rs.getDate("date_entretient")) );
        }    

        return TabS;
    }

    public ArrayList<Vehicule> triParMarque() throws SQLException {
        ArrayList<Vehicule> TabM = new ArrayList<>();
        String req = "SELECT * FROM vehicule order by  marque Desc";
      PreparedStatement p;
        p = conn.prepareStatement(req);
        ResultSet rs = p.executeQuery();
        while (rs.next()) {

            TabM.add(new Vehicule(rs.getInt("id"),rs.getString("matricule"),rs.getString("couleur"),rs.getString("type_vehicule"),rs.getString("marque"),rs.getString("etat_vehicule"),rs.getDate("date_entretient")) );
                
        }
        return TabM;
    }
    
    
    public HashMap<String, Double> StatistiqueParType() {
        HashMap<String, Double> data = new HashMap<>();
        try {
            Statement stm = conn.createStatement();
            String query = "SELECT type_vehicule, COUNT(*) as nb FROM vehicule GROUP BY type_vehicule;";
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                int nb = rs.getInt("nb");
                String key = nb + " " + rs.getString("type_vehicule");
                data.put(key, new Double(nb));
            }
            System.out.println(data.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return data;
}
       /*  public void ajouterVehicule1(Vehicule v){
        try {
            String requete ="INSERT INTO `vehicule`(`matricule`,`couleur`,`type_vehicule`,`marque`,`etat_vehicule`) VALUES(?,?,?,?,?)";
            pste = conn.prepareStatement(requete);
            pste.setInt(1,v.getId());
            pste.setString(2,v.getMatricule());
            pste.setString(3,v.getCouleur());
            pste.setString(4,v.getType_vehicule());
            pste.setString(5,v.getMarque());
            
  
            pste.setString(6,v.getEtat_vehicule());

            pste.executeUpdate();
            System.out.println("vehicule ajoutée avec succès");
                
        } catch (SQLException ex) {
           System.err.println(ex.getMessage());
        }
    }*/
        
        
}
