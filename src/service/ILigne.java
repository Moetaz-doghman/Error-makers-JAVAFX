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
public interface ILigne <C>  {
    
    public boolean SupprimerLigne(int id);
    public List<C> AfficherLigne(C c);
    public int countTotalCommande();
    public boolean AjouterligneCommande(C c);

}
