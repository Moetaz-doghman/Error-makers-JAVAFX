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
    private int idd;
    private String nomBoutique,descBoutique, adresseBoutique,img;

    public Boutique(int idd, String nomBoutique, String descBoutique, String adresseBoutique, String img) {
        this.idd = idd;
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

    public int getIdd() {
        return idd;
    }

    public void setIdd(int idd) {
        this.idd = idd;
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
        return "Boutique{" + "idd=" + idd + ", nomBoutique=" + nomBoutique + ", descBoutique=" + descBoutique + ", adresseBoutique=" + adresseBoutique + ", img=" + img + '}';
    }

   


    

    

    


  
}

