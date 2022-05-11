
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import utils.Myconnexion;

/**
 * FXML Controller class
 *
 * @author mariem
 */
public class DashboardController implements Initializable {

    @FXML
    private PieChart PieChart;
    ArrayList<Integer> nbr= new ArrayList<Integer>();
    ArrayList<String> name= new ArrayList<String>();
    
     ObservableList<PieChart.Data> pieChartData;
    @FXML
    private HBox btnAfficheRec;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          Connection  cnx = Myconnexion.getInstance().getCnx();
          
         List<Reclamation> list = new ArrayList<Reclamation>();
         
         pieChartData=FXCollections.observableArrayList();

        try {
            String req1 = "select type,count(*) from reclamation where type = 'Hardware' ";
            String req2 = "select type,count(*) from reclamation where type = 'Software' ";
            String req3 = "select type,count(*) from reclamation where type = 'Reparation' ";
            
            PreparedStatement pst1 = cnx.prepareStatement(req1);
            ResultSet rs1 = pst1.executeQuery();
            
            PreparedStatement pst2 = cnx.prepareStatement(req2);
            ResultSet rs2 = pst2.executeQuery();
            
            PreparedStatement pst3 = cnx.prepareStatement(req3);
            ResultSet rs3 = pst3.executeQuery();
            
            
            
            
            
            while (rs1.next()) {
                pieChartData.add(new PieChart.Data(rs1.getString("type"),rs1.getInt("count(*)")));
                name.add(rs1.getString("type"));
                nbr.add(rs1.getInt("count(*)"));
                
            }
            while (rs2.next()) {
                pieChartData.add(new PieChart.Data(rs2.getString("type"),rs2.getInt("count(*)")));
                name.add(rs2.getString("type"));
                nbr.add(rs2.getInt("count(*)"));
                
            }
            while (rs3.next()) {
                pieChartData.add(new PieChart.Data(rs3.getString("type"),rs3.getInt("count(*)")));
                name.add(rs3.getString("type"));
                nbr.add(rs3.getInt("count(*)"));
                
            }
            
            
        } catch (SQLException ex) {
           
            System.out.println(ex.getMessage());
        }


     PieChart.setData(pieChartData);
     PieChart.setTitle("les reclamation selon leurs types");
    
  
    }    

    @FXML
    private void afficherReclamations(MouseEvent event) {
    
         
        try {
        Stage stage = (Stage) btnAfficheRec.getScene().getWindow();
        stage.close();
        
        FXMLLoader fxmlloader = new FXMLLoader (getClass().getResource("ListReclamation.fxml"));
        Parent root;
        
            root = (Parent) fxmlloader.load();
        
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(root));
        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(AjoutReclController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
}
