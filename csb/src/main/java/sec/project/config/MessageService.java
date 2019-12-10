/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sec.project.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import sec.project.domain.Message;

/**
 *
 * @author ossij
 */
@Service
public class MessageService {
    
    
    public List<Message> getMessages() throws SQLException {
        List<Message> messages = new ArrayList<>();
        Connection connection = DriverManager.getConnection("jdbc:h2:file:./database", "sa", "");
        
        ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM Messages");
        
        while(rs.next()) {
            String user = rs.getString("username");
            String message = rs.getString("message");
            messages.add(new Message(user,message));
        }
        connection.close();
        return messages;
        
        
    }
    
    public void sendMessage(String user, String message) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:h2:file:./database", "sa", "");
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Messages (username, message) VALUES (?,?)");
        
        stmt.setString(1, user);
        stmt.setString(2, message);
        
        stmt.executeUpdate();
        
        connection.close();
    }
    
}
