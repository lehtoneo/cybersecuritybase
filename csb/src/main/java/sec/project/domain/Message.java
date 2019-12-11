/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sec.project.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author ossij
 */
@Entity
public class Message {
    
    @Column
    private String username;
    @Column
    private String message;
    
    public Message(String user, String message) {
        this.username = user;
        this.message = message;
    }

    public String getUser() {
        return username;
    }

    public void setUser(String user) {
        this.username = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
    
}
