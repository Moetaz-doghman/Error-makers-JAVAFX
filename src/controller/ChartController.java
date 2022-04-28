/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Commande;
import java.net.URL;
import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;


/**
 * FXML Controller class
 *
 * @author doghm
 */
public class ChartController implements Initializable {

    @FXML
    private BarChart<String, Integer> barChart;
    @FXML
    private NumberAxis numberAxis;
    @FXML
    private CategoryAxis xAxis;
    private PieChart piechart;
     private ObservableList<String> monthNames = FXCollections.observableArrayList();



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         // Get an array with the FRENCH month names.
        String[] months = DateFormatSymbols.getInstance(Locale.FRENCH).getMonths();
        // Convert it to a list and add it to our ObservableList of months.
        monthNames.addAll(Arrays.asList(months));
        
        // Assign the month names as categories for the horizontal axis.
        xAxis.setCategories(monthNames);
        xAxis.setLabel("mois");
        numberAxis.setLabel("Nombre de commande");
    } 
    
     public void setReclamationData(List<Commande> t) {
    	// Count the number of reviews in a specific month.
        int[] monthCounter = new int[12];
        for (Commande p : t) {
            int month = p.getDateCommande().getMonth();
            monthCounter[month]++;
        }

        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        
        // Create a XYChart.Data object for each month. Add it to the series.
        for (int i = 0; i < monthCounter.length; i++) {
        	series.getData().add(new XYChart.Data<>(monthNames.get(i), monthCounter[i]));
        }
        
        barChart.getData().add(series);
    }
    
}
