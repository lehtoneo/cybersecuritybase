
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
import sec.project.config.MessageService;
import sec.project.domain.Message;

@Controller
public class MainController {
    @Autowired
    MessageService service;
    
    @Autowired
    LoginController logInController;
    
    @Autowired
    AuthenticationManager authManager;
    
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String loadMessages(Model model) throws SQLException {
        
        
        
        
        List<Message> messageList = service.getMessages();
        List<String> messages = new ArrayList<>();
        for (Message m: messageList) {
            messages.add(m.getUser() + ": " + m.getMessage());
        }
        
        model.addAttribute("list", messages);
        
        return "main";
    }
    
    @RequestMapping(value = "/main", method = RequestMethod.POST)
    public String sendMessage(@RequestParam String message) throws SQLException {
        //tänne, että pitää authenticatea
        String user = logInController.getLoggedIn();
        if(user != null)
        service.sendMessage(user, message);

        
        
        return "redirect:/main";
    }
    
    
    
}
