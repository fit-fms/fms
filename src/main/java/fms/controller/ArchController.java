/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fms.controller;

import fms.business.archetype.Archetype;
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
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author michal
 */
@Controller
public class ArchController {
    
    @Autowired
    private FormService formService;
    @Autowired
    private ArchetypeService archService;
    
    @RequestMapping(value = "/arch", method = RequestMethod.GET)
    public String displayArchList(ModelMap map){
        
        try {
           Map<String, Archetype> arch = archService.getAllArchetypes();
           map.addAttribute("archetypes", arch);
        } catch (Exception ex) {
            Logger.getLogger(ArchController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "archetypeList";
    }
}