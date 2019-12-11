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
import org.springframework.web.bind.annotation.RequestMapping;
import sec.project.domain.Message;
import sec.project.repository.MessageRepository;

@Controller
public class MessageController {
    @Autowired
    MessageRepository messageRep;
    
    @RequestMapping(value = "/messages")
    public String load() {
        
        List<Message> list = messageRep.findAll();
        
        List<String> messages = new ArrayList<>();
        
        for (Message m: list) {
            messages.add(m.getUser() + ": " + m.getMessage());
        }
        
        return "posts";
        
        
    }
    
}
