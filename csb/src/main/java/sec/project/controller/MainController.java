
package sec.project.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Account;
import sec.project.domain.Message;
import sec.project.repository.AccountRepository;
import sec.project.repository.MessageRepository;

@Controller
public class MainController {

    @Autowired
    AccountRepository accRep;
    @Autowired
    LoginController logInController;
    
    @Autowired
    MessageRepository messageRep;
    
    @Autowired
    AuthenticationManager authManager;
    
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String loadMessages(Authentication authentication, Model model) throws SQLException {
        
        if(authentication.getName() == null) {
            return "redirect:/login";
        }
        List<Message> list = messageRep.findAll();
        
        List<String> messages = new ArrayList<>();
        
        for (Message m: list) {
            messages.add(m.getUser() + ": " + m.getMessage());
        }
        
        model.addAttribute("list", messages);
        
        return "main";
    }
    
    @RequestMapping(value = "/main", method = RequestMethod.POST)
    public String sendMessage(Authentication authentication, @RequestParam String message) throws SQLException {
        //tänne, että pitää authenticatea
        Account acc = accRep.findByUsername(authentication.getName());
        
        messageRep.saveAndFlush(new Message(acc.getName(), message));

        return "redirect:/main";
    }
    
    
    
}
