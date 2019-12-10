package sec.project.controller;

import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
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
    CustomUserDetailsService service;
    
    @Autowired
    private AccountRepository loginRepository;
    
    @RequestMapping(value = "/createacc", method = RequestMethod.GET)
    public String loadCreateAcc() {
        return "createacc";
        
        
    }
    
    @RequestMapping(value = "/createacc", method = RequestMethod.POST)
    public String saveAccount(@RequestParam String name, @RequestParam String password, @RequestParam String funds) throws SQLException {
        
        int apu;
        try {
            apu = Integer.valueOf(funds);
        } catch (Exception e) {
            return "redirect:/createacc";
        }
        if(service.saveUserToDB(name, password, apu)) {
            return "redirect:/login";
        }
        
        return "redirect:/createacc";
        
        
    }
    
    
}
