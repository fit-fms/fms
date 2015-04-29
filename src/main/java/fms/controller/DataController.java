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
import java.util.ArrayList;
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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author michal
 */

@Controller
public class DataController {
    
    @Autowired
    private FormService formService;
    @Autowired
    private ArchetypeService archService;
    
    @RequestMapping(value = "/form/{formArch}/{formId}", method = RequestMethod.GET)
    public String showData(@PathVariable("formArch") String arch, @PathVariable("formId") int formId, ModelMap map){
        Form form;
        Archetype archetype;
        List<String> errors = new ArrayList();
        try {
            archetype = archService.findByName(arch);
        } catch (Exception ex) {
            Logger.getLogger(FormController.class.getName()).log(Level.SEVERE, null, ex);
            
            errors.add("archetyp se nenasel");
            map.addAttribute("errors", errors);
            return "errors";           
        }
        try {
            form = formService.getFormById(archetype, formId);
        } catch (Exception ex) {
            Logger.getLogger(DataController.class.getName()).log(Level.SEVERE, null, ex);
            errors.add("formular se nenasel");  
            map.addAttribute("errors", errors);
            return "errors";
        }
        map.addAttribute("form", form);
        return "showForm";
    }
    
    
}
