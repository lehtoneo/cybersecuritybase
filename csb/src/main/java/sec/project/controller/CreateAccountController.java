package sec.project.controller;

import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.config.CustomUserDetailsService;
import sec.project.domain.Account;
import sec.project.repository.AccountRepository;

@Controller
public class CreateAccountController {
    @Autowired
    AccountRepository accountRepository;
    
    @Autowired
    PasswordEncoder passwordencoder;
    
    @RequestMapping(value = "/createacc", method = RequestMethod.GET)
    public String loadCreateAcc() {
        return "createacc";
    }
    
    @RequestMapping(value = "/createacc", method = RequestMethod.POST)
    public String saveAccount(@RequestParam String name, @RequestParam String password) throws SQLException {
       
        
        
        if(accountRepository.findByUsername(name) == null) {
            
            accountRepository.saveAndFlush(new Account(name, passwordencoder.encode(password)));
            
            return "redirect:/login";
        }
        
        return "redirect:/createacc";
        
        
    }
    
    
}
