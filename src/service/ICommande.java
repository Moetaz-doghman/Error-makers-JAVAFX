/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;

/**
 *
 *
 */
public interface ICommande <C>  {
    
    public boolean AjouterCommande(C c);
    public boolean ModifierCommande(C c);
    public boolean SupprimerCommande(int id);
    public List<C> AfficherCommande(C c);
    public int countTotalCommande();
}
