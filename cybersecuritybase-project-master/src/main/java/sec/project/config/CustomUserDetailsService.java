package sec.project.config;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import org.h2.tools.RunScript;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private Map<String, String> accountDetails;

    @PostConstruct
    public void init() throws SQLException {
        // this data would typically be retrieved from a database
        Connection connection = DriverManager.getConnection("jdbc:h2:file:./database", "sa", "");
        
        try {
    // If database has not yet been created, create it
            RunScript.execute(connection, new FileReader("database-schema.sql"));
            RunScript.execute(connection, new FileReader("database-import.sql"));
            
            } catch (Throwable t) {
                System.out.println(t.getMessage());
                }        
        
        try {
            connection.createStatement().executeUpdate("CREATE TABLE Messages (message varchar, username varchar(30))");
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }    
        
        try {
            connection.createStatement().executeUpdate("CREATE TABLE Users (username varchar(30), password varchar(30), PRIMARY KEY(username))");
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
        
        
        connection.close();
    }
    
    
    public boolean saveUserToDB(String username, String password) throws SQLException {
        
        Connection connection = DriverManager.getConnection("jdbc:h2:file:./database", "sa", "");
        
        ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM Users WHERE Username = '" + username + "'");
        
        
        if(rs.next()) {
            return false;
        }
        
        
        connection.createStatement().executeUpdate("INSERT INTO Users (Username, password) "
                + "VALUES ('"+ username + "', '"+password+ "')");
        connection.close();
        return true;
        
    }
    
    public boolean userNameAndPasswordMatch(String username, String password) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:h2:file:./database", "sa", "");
        
        //password' OR 1=1;--
        ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM Users WHERE Username = '" + username+ "' AND Password ='" + password+"'");
        
        
        
        if(rs.next()) {
            return true;
        }
        return false;
    }
    
    

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        
        
        
        if (!this.accountDetails.containsKey(username)) {
            throw new UsernameNotFoundException("No such user: " + username);
        }

        return new org.springframework.security.core.userdetails.User(
                username,
                this.accountDetails.get(username),
                true,
                true,
                true,
                true,
                Arrays.asList(new SimpleGrantedAuthority("USER")));
    }
}
