
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
    AccountRepository accountRepository;
    @Autowired
    MessageRepository messageRepository;
    
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String loadMessages(Model model) throws SQLException {
        List<Message> list = new ArrayList<>();
        
        list = messageRepository.findAll();
       
        List<String> list2 = new ArrayList<>();
        for (Message m: list) {
            list2.add(m.getText());
        }
        model.addAttribute("list", list2);
        
        return "main";
    }
    
    @RequestMapping(value = "/main", method = RequestMethod.POST)
    public String sendMessage(@RequestParam String message) throws SQLException {
        
        Message m = new Message();
        m.setUser("o");
        m.setText("A");
        messageRepository.save(m);
        

        
        
        return "redirect:/main";
    }
    
    
    
}
