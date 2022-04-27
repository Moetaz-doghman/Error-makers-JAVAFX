/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author HP
 */

public class Boutique {
    private int id;
    private String nomBoutique,descBoutique, adresseBoutique,img;

    public Boutique(int id, String nomBoutique, String descBoutique, String adresseBoutique, String img) {
        this.id = id;
        this.nomBoutique = nomBoutique;
        this.descBoutique = descBoutique;
        this.adresseBoutique = adresseBoutique;
        this.img = img;
    }

    public Boutique(String nomBoutique, String descBoutique, String adresseBoutique, String img) {
        this.nomBoutique = nomBoutique;
        this.descBoutique = descBoutique;
        this.adresseBoutique = adresseBoutique;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomBoutique() {
        return nomBoutique;
    }

    public void setNomBoutique(String nomBoutique) {
        this.nomBoutique = nomBoutique;
    }

    public String getDescBoutique() {
        return descBoutique;
    }

    public void setDescBoutique(String descBoutique) {
        this.descBoutique = descBoutique;
    }

    public String getAdresseBoutique() {
        return adresseBoutique;
    }

    public void setAdresseBoutique(String adresseBoutique) {
        this.adresseBoutique = adresseBoutique;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Boutique{" + "id=" + id + ", nomBoutique=" + nomBoutique + ", descBoutique=" + descBoutique + ", adresseBoutique=" + adresseBoutique + ", img=" + img + '}';
    }
    


    

    

    


  
}

