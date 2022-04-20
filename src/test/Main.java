/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Personne;
import services.PersonneService;

/**
 *
 * @author Skander
 */
public class Main {

    public static void main(String[] args) {
        Personne p = new Personne(1, 19, "Ben Arous", "Mourouj");
        PersonneService ps = new PersonneService();
        System.out.println(ps.recuperer());
        
         

    }
}
