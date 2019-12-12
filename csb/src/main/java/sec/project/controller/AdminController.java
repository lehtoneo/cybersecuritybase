/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sec.project.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sec.project.domain.Account;
import sec.project.repository.AccountRepository;

@Controller
public class AdminController {
    @Autowired
    AccountRepository accRep;
    
    @RequestMapping("/admin")
    public String loadAdminPage(Model model) {
        List<Account> list = accRep.findAll();
        
        List<String> usernames = new ArrayList<>();
        
        for(Account acc: list) {
            usernames.add(acc.getName());
        }
        
        model.addAttribute("users", usernames);
        
        return "admin";
        
        
    }
    
    
    
}
