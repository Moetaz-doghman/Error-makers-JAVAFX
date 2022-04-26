/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author mariem
 */
public class Reclamation {
     private int id;
    private String name,subject,message,etat,type,email;

    public Reclamation() {
    }

    
   
    public Reclamation(int id, String name, String subject, String message, String etat, String type,String email) {
        this.id = id;
        this.name = name;
        this.subject = subject;
        this.message = message;
        this.etat = etat;
        this.type = type;
        this.email = email;
        
        
    }

    public Reclamation(String name, String subject, String message, String etat, String type,String email) {
        this.name = name;
        this.subject = subject;
        this.message = message;
        this.etat = etat;
        this.type = type;
        this.email = email;
    }
 public Reclamation(String name, String subject, String message, String type, String email) {
        this.name = name;
        this.subject = subject;
        this.message = message;
        this.type = type;
        this.email = email;

    }
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    
    
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override

    
    public String toString() {
        return "Reclamation{" + "id=" + id + ", name=" + name + ", subject=" + subject + ", message=" + message + ", etat=" + etat + ", type=" + type + ", email=" + email + '}';
    }
    

   
    
    
    
}
