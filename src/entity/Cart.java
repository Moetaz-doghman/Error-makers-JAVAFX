/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;

/**
 *
 * @author doghm
 */
public final class Cart {

    public static Cart instance;

    private final ArrayList<Produit> c;

    public Cart() {
        c = new ArrayList<Produit>();

    }
     public static Cart getInstance() {
        if (instance == null) {
            instance = new Cart();
        }
        return instance;
    }

    public ArrayList<Produit> getCartList() {
        return c;
    }
    
  


    public void AddProduct(Produit e) {
        this.c.add(e);
    }

    public void RemoveProduct(Produit e) {
        
       int productid = e.getId();
        for(int i=0 ; i<this.c.size();i++){
            Produit product = this.c.get(i);
            if(product.getNom_produit().equals(e.getNom_produit())){
                this.c.remove(i);
            }
        }
    }
    public void RemoveAll(){
    this.c.clear();
    }

    public ArrayList<Produit> getC() {
        return c;
    }

   

    public void cleanCartSession() {
        instance = null;
    }

    @Override
    public String toString() {
        return "Cart{"
                + "c=" + c
                + '}';
    }
    public double total(){
        double total=0;
     for(Produit product : this.c){
                    total = total + (Double.parseDouble(product.getPrix_produit())*Integer.parseInt(product.getQuantite_produit()));
                }
    return total;
    }
}
