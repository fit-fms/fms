/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fms.controller;

import fms.business.archetype.Archetype;
import fms.business.archetype.Field;
import fms.business.fieldtype.FieldType;
import fms.business.fieldtype.TextField;
import fms.business.form.DigitalForm;
import fms.business.form.FilledField;
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
    
    private Form form;
    
    @RequestMapping(value = "/form/{formUrl}", method = RequestMethod.GET)
    public String displayForm(@PathVariable("formUrl") String formUrl, ModelMap map){
        Archetype arch;
        try {
            arch = archService.findByName(formUrl);
        } catch (Exception ex) {
            Logger.getLogger(FormController.class.getName()).log(Level.SEVERE, null, ex);
            return "index";           
        }
        form = createForm(arch);
        map.addAttribute("form", form);
        return "showForm";   
    }
    
    @RequestMapping(value = "/form/{formUrl}", method = RequestMethod.POST)
    public String submitForm(@RequestBody String body, ModelMap map){
        int i = -1, j = -1;
        String s = "";
        for(FilledField x : form.getFilledFields()){
            s = x.getField().getName();
            i = body.indexOf(s, i + 1);
            j = body.indexOf('&', j + 1);
            if( j == -1){
                s = body.substring(i + s.length() + 1);
                x.setData(s);
                break;
            }              
            s = body.substring(i + s.length() + 1, j);
            x.setData(s);
        }
        try {
            formService.createForm(form);
        } catch (Exception ex) {
            System.out.println("chyba pri ukladani formulare");
            Logger.getLogger(FormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "showForm";
    }
    
    private Form createTest(){
        Form form = new DigitalForm();
        FilledField field = new FilledField();
        field.setData("zkouska1");
        Field field1 = new Field();
        FieldType type = new TextField();
        field1.setType(type);
        field1.setName("field1");
        field.setField(field1);
        form.addfilledfield(field);
        
        field = new FilledField();
        field1 = new Field();
        type = new TextField();
        field1.setType(type);
        field1.setName("field2");
        field.setData("zkouska2");
        field.setField(field1);
        form.addfilledfield(field);
        return form;
    }
    
    private Form createForm (Archetype arch){
        Form form = new DigitalForm();
        if(arch == null)
            return new DigitalForm();
        for(Map.Entry<String, Field> x : arch.getRequiredFields().entrySet()){
        FilledField field = new FilledField();
        field.setField(x.getValue());
        form.addfilledfield(field);
        }
        return form;
    }
    
} 