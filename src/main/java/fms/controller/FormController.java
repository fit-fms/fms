/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fms.controller;

import fms.business.archetype.Archetype;
import fms.business.archetype.Field;
import fms.business.archetype.UnpublisdedArchertype;
import fms.business.fieldtype.FieldType;
import fms.business.fieldtype.TextField;
import fms.business.form.DigitalForm;
import fms.business.form.FilledField;
import fms.business.form.Form;
import fms.business.service.ArchetypeService;
import fms.business.service.FormService;
import fms.presentation.view.FormParser;
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
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
    @Autowired
    private FormParser parser;
    
    
    @RequestMapping(value = "/form/{formUrl}", method = RequestMethod.GET)
    public String displayForm(@PathVariable("formUrl") String formUrl, ModelMap map){//@TODO prepsat nazvy
        Archetype arch;
        
        try {
            arch = archService.findByName(formUrl);
        } catch (Exception ex) {
            Logger.getLogger(FormController.class.getName()).log(Level.SEVERE, null, ex);
            List<String> errors = new ArrayList();
            errors.add("archetyp se nenasel");
            map.addAttribute("errors", errors);
            return "errors";           
        }
        map.addAttribute("archetype", arch);
        return "showForm";   
    }
    
    @RequestMapping(value = "/form/{formUrl}", method = RequestMethod.POST)
    public String submitForm(@RequestParam Map<String, String> params, @PathVariable("formUrl") String formUrl, ModelMap map){
        Archetype arch;
        List<String> errors = new ArrayList();
        try {
            arch = archService.findByName(formUrl);
        } catch (Exception ex) {            
            Logger.getLogger(FormController.class.getName()).log(Level.SEVERE, null, ex);
            errors.add("archetyp se nenasel");
            map.addAttribute("errors", errors);
            return "errors";
        }
        
        Form form = parser.fillOutForm(params, arch, errors);
        if(form == null){
            Logger.getLogger(FormController.class.getName()).log(Level.SEVERE, null, params);
            map.addAttribute("errors", errors);
            return "errors";
        }
        try {
            formService.createForm(form);
        } catch (Exception ex) {
            Logger.getLogger(FormController.class.getName()).log(Level.SEVERE, null, ex);
            errors.add("formular se nepodarilo odeslat");
            map.addAttribute("errors", errors);
            return "errors";
            
        }
        
        map.addAttribute("form", form);
        return "showForm";
    }
    
 
} 