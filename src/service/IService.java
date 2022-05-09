/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;

/**
 *
 * @author mariem
 */
public interface IService<T> {
     void ajouter(T t);
     void ajouterR(T t,int id);
    void modifier(T t);
    void supprimer(int id);
    List<T> recuperer();
    T recuperer(int id);
    
}
