/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author mariem
 */
public class Reponse {
     private int id;
    private String subject,message;
    private String created_at;

    public Reponse() {
    }

    public Reponse(String subject, String message, String created_at) {
        this.subject = subject;
        this.message = message;
        this.created_at = created_at;
    }

    public Reponse(String subject, String message) {
        this.subject = subject;
        this.message = message;
       
    }
    public Reponse(int id, String subject, String message) {
        this.id = id;
        this.subject = subject;
        this.message = message;
       
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    
    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "Reponse{" + "id=" + id + ", subject=" + subject + ", message=" + message + ", created_at=" + created_at + '}';
    }

  
    
    
}
