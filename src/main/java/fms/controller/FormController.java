/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fms.controller;

import fms.business.archetype.Archetype;
import fms.business.form.Form;
import fms.business.service.ArchetypeService;
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
import org.springframework.web.bind.annotation.PathVariable;
/**
 *
 * @author michal
 */

@Controller
public class FormController {
    
    
    @Autowired
    private ArchetypeService service;
    //private GWTForm gwt;
    private Archetype arch;
    private Form form;
    
    @RequestMapping(value = "/form/{formUrl}", method = RequestMethod.GET)
    public String displayForm(@PathVariable("formUrl") String formUrl, ModelMap map){
        //return "displayForm";
        map.addAttribute("name", formUrl);
        try {
            arch = service.findByName(formUrl);
        } catch (Exception ex) {
            map.addAttribute("name", "formular se nenasel");//tady se pak doplni nejaky chybova stranka 
            Logger.getLogger(FormController.class.getName()).log(Level.SEVERE, null, ex);
            return "showForm";           
        }
        //form = gwt.
        return "showForm";
    }
    
}
