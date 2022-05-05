/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entité;

import java.util.Date;

/**
 *
 * @author Asus
 */
public class Commentaire {
 private int id;
 private int annoncesId;
 private int parentId;
 private String contenu;
 private int active;
 private String email;
 private Date createdAt;
 private String pseudo;

    public Commentaire() {
    }

    public Commentaire( int annoncesId, int parentId, String contenu, int active, String email, Date createdAt, String pseudo) {
        this.id = 0;
        this.annoncesId = annoncesId;
        this.parentId = parentId;
        this.contenu = contenu;
        this.active = active;
        this.email = email;
        this.createdAt = createdAt;
        this.pseudo = pseudo;
    }
   public Commentaire(int id, int annoncesId, int parentId, String contenu, int active, String email, Date createdAt, String pseudo) {
        this.id = id;
        this.annoncesId = annoncesId;
        this.parentId = parentId;
        this.contenu = contenu;
        this.active = active;
        this.email = email;
        this.createdAt = createdAt;
        this.pseudo = pseudo;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAnnoncesId() {
        return annoncesId;
    }

    public void setAnnoncesId(int annoncesId) {
        this.annoncesId = annoncesId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id=" + id + ", annoncesId=" + annoncesId + ", parentId=" + parentId + ", contenu=" + contenu + ", active=" + active + ", email=" + email + ", createdAt=" + createdAt + ", pseudo=" + pseudo + '}';
    }
 
}
