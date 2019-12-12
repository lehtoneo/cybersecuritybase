/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sec.project.config;

import org.springframework.security.crypto.password.PasswordEncoder;


public class GreatPasswordEncoder implements PasswordEncoder{

    @Override
    public String encode(CharSequence cs) {
        
        return (String) cs;
        
    }

    @Override
    public boolean matches(CharSequence cs, String string) {
        String encoded = this.encode(cs);
        if(encoded.equals(string)) {
            return true;
        }
        return false;
    }
    
}
