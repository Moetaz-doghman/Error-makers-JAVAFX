/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Reclamation;
import entities.Reponse;
import services.ReclamationService;
import services.ReponseService;
import util.MyDB;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    

/**
 *
 * @author mariem
 */
public class Main {
    public static void main(String[] args) {
          MyDB db = MyDB.getInstance();
        System.out.println(db);
// ajouter
//            Reclamation r = new Reclamation("Foulen", "Pc not fixed","hello please check my message its urgent !!!!", "En cours","Hardware","meddeb.mariem@esprit.tn");
//            ReclamationService rs = new ReclamationService();
//            rs.ajouter(r);
//modifier
//                Reclamation r = new Reclamation(45,"marioum","Pc not fiixed","hello please check my message its urgent !", "En cours", "Hardware","meddeb.mariem@esprit.tn");
//                ReclamationService rs = new ReclamationService();
//                rs.modifier(r);
//                
//supprimer
//                Reclamation r = new Reclamation();
//                ReclamationService rs = new ReclamationService();
//                rs.supprimer(45);

//afficher
                Reclamation r = new Reclamation();
                ReclamationService ps = new ReclamationService();
                System.out.println(ps.recuperer());




//ajout réponse
//            Reponse r = new Reponse("Pc is not fixed","hello please this is the response to your reclamationnnn !!");
//            DateTimeFormatter created_at = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
//            LocalDateTime now = LocalDateTime.now();
//            System.out.println(created_at.format(now));  
//            r.setCreated_at(created_at.format(now));
//            ReponseService rs = new ReponseService();
//            rs.ajouter(r);

//modifier 
//                Reponse r = new Reponse(20,"Pc not fiixed ??","hello please check your responses !");
//                DateTimeFormatter created_at = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
//                LocalDateTime now = LocalDateTime.now();
//                System.out.println(created_at.format(now));  
//                r.setCreated_at(created_at.format(now));
//                ReponseService rs = new ReponseService();
//                rs.modifier(r);

//supprimer
//                Reponse r = new Reponse();
//                ReponseService rs = new ReponseService();
//                rs.supprimer(21);

//afficher
//                Reponse r = new Reponse();
//                ReponseService ps = new ReponseService();
//                System.out.println(ps.recuperer());
    }
    
}
