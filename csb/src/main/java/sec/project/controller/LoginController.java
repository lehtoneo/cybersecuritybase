package sec.project.controller;

import java.sql.SQLException;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.securityContext;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import org.springframework.stereotype.Controller;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.config.CustomUserDetailsService;
import sec.project.domain.Account;

@Controller
public class LoginController {
    private String loggedIn = null;
    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private CustomUserDetailsService service;
    
    @Autowired
    MainController mainController;
    @RequestMapping("*")
    public String defaultMapping() {
        return "main";
    }
    
    @RequestMapping("/login")
    public String a() {
        return "login";
    }
    
    @RequestMapping("/perform_login")
    public String logins(@RequestParam String username, @RequestParam String password) {
        return "redirect:/main";
    }
    
    public String getLoggedIn() {
        return loggedIn;
    }

}
