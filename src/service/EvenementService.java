/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Evenement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asus
 */
public class EvenementService implements Service<Evenement> {

    @Override
    public Boolean insert(Evenement e) {
        try {
            String req = "INSERT into `Evenement` ("
                    + "`id`,`nom`,`prix`, `adresse`, `date`, `datefin`, `description`, `affiche`, `longdesc` ) "
                    + "VALUES("
                    + " ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = connection.prepareStatement(req);
            Integer i = 0;
            pst.setInt(++i, e.getId());
            pst.setString(++i, e.getNom());
            pst.setDouble(++i, e.getPrix());
            pst.setString(++i, e.getAdresse());
            pst.setDate(++i, new java.sql.Date(e.getDate().getTime()));
            pst.setDate(++i, new java.sql.Date(e.getDatefin().getTime()));
            pst.setString(++i, e.getDescription());
            pst.setString(++i, e.getAffiche());
            pst.setString(++i, e.getLongdesc());
            return pst.executeUpdate() > 0;
        } catch (SQLException ee) {
            System.err.println(ee.getMessage());
        }

        return false;
    }

    @Override
    public List<Evenement> find(Integer offset, Integer rowCount, String filter, String order) {
        List<Evenement> l = new ArrayList<>();
        try {
            String req = "SELECT * FROM `Evenement`"
                    + ";";
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(req);
            while (rs.next()) {
                l.add(new Evenement(
                        
                rs.getInt( "id"),
                rs.getString( "Nom"),
                rs.getDouble( "Prix"),
                rs.getString( "Adresse"),
                rs.getDate("date"),
                rs.getDate("datefin"),
                rs.getString( "Description"),
                rs.getString( "Affiche"),
                rs.getString( "Longdesc")
                ));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return l;
    }
    public List<Integer> getIdEvent(){
        List<Integer> l = new ArrayList<>();

        try {
            String req = "SELECT id FROM `Evenement`";
            System.out.println(req);
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(req);
            while (rs.next()) {
                 l.add(rs.getInt("id"));
            }
        } catch (SQLException ex) {
        }
        return l;             
    }

    public Evenement findById(int id) {
        List<Evenement> l = new ArrayList<>();
        try {
            String req = "SELECT * FROM `Evenement`"
                    + "WHERE `id`="+id
                    + ";";
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(req);
            if (rs.next()) {
                return new Evenement(
                        
                rs.getInt( "id"),
                rs.getString( "Nom"),
                rs.getDouble( "Prix"),
                rs.getString( "Adresse"),
                rs.getDate("date"),
                rs.getDate("datefin"),
                rs.getString( "Description"),
                rs.getString( "Affiche"),
                rs.getString( "Longdesc")
                );
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Boolean delete(int id) {
     try {
            String req = "DELETE FROM `evenement` "
                    +"WHERE id="+id
                    + ";";
            System.out.println(req);
            Statement s = connection.createStatement();

            return s.executeUpdate(req) > 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return false;
    }
    

    @Override
    public Boolean modify(Evenement e) {
        try {
            String req = "UPDATE `Evenement` SET "
                    + "`nom`=?, `adresse`=?, `date`=?, `datefin`=?, `description`=?, `affiche`=?, `longdesc`=? WHERE `id`=" + e.getId();
            PreparedStatement pst = connection.prepareStatement(req);
            Integer i = 0;
            pst.setString(++i, e.getNom());

            pst.setString(++i, e.getAdresse());
            pst.setDate(++i, new java.sql.Date(e.getDate().getTime()));
            pst.setDate(++i, new java.sql.Date(e.getDatefin().getTime()));
            pst.setString(++i, e.getDescription());
            pst.setString(++i, e.getAffiche());
            pst.setString(++i, e.getLongdesc());
            return pst.executeUpdate() > 0;
        } catch (SQLException ee) {
            System.err.println(ee.getMessage());
        }

        return false;
    }
    
    

}
