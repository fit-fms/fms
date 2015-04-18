package fms.business.service;

import fms.business.archetype.Template;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jcr.Session;
import java.util.List;

/**
 * Slu�ba pro uchov�v�n� formul�ru v datab�zi.
 *
 * @author jinora
 * @version 1.0
 * @created 15-Apr-2015 12:39:49 PM
 */
public class TemplateService {

    @Autowired
    private Session session;

    public TemplateService() {

    }


    /**
     * Vytvor� novou templatu v datab�zi.
     *
     * @param template
     */
    public void createTemplate(Template template) {

    }

    /**
     * Aktualizuje templatu v datab�zi.
     *
     * @param template
     */
    public void updateTemplate(Template template) {

    }

    /**
     * Odstran� templatu z datab�ze.
     *
     * @param template
     */
    public void removeTemplate(Template template) {

    }

    /**
     * Z�sk� v�echny templaty v datab�zi pro archetyp.
     *
     * @param archwetypName
     */
    public List<Template> getAllTemplates(String archwetypName) {
        return null;
    }

}