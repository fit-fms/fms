/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fms.controller;

import fms.business.archetype.Archetype;
import fms.business.form.DigitalForm;
import fms.business.form.Form;
import fms.business.service.ArchetypeService;
import fms.business.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
/**
 *
 * @author michal
 */

@Controller
public class FormController {
    
    
    @Autowired
    private FormService formService;
    @Autowired
    private ArchetypeService archService;
    
    
    @RequestMapping(value = "/form/{formUrl}", method = RequestMethod.GET)
    public String displayForm(@PathVariable("formUrl") String formUrl, ModelMap map){
        Archetype arch;
        try {
            arch = archService.findByName(formUrl);
        } catch (Exception ex) {
            Logger.getLogger(FormController.class.getName()).log(Level.SEVERE, null, ex);
            return "index";           
        }
        map.addAttribute("archetype", arch);
        map.addAttribute("formObject", new DigitalForm());//formObject by se mel shodovat s nazvem promenne v twigu
        return "showForm";
        
        
        
    }
    
    @RequestMapping(value = "/form/{formUrl}", method = RequestMethod.POST)
    public String submitForm(@ModelAttribute("formObject") Form form, ModelMap map){
        try {
            formService.createForm(form);
        } catch (Exception ex) {
            System.out.println("chyba pri ukladani formulare");
            Logger.getLogger(FormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "index";
    }
    
}
