/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;

/**
 *
 * @author Skander
 */
public interface IService<U> {
    void ajouter(U t);
    void modifier(U t);
    void supprimer(int id);
    List<U> recuperer();
    U recuperer(int id);
}
