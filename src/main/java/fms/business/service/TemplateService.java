package fms.business.service;

import fms.business.archetype.template.Template;

import java.util.List;

/**
 * Slu�ba pro uchov�v�n� formul�ru v datab�zi.
 */
public interface TemplateService {


    /**
     * Vytvor� novou templatu v datab�zi.
     *
     * @param template
     */
    public void createTemplate(Template template) throws Exception;

    /**
     * Najde templatu podle jmena
     * @param name jmeno templaty
     * @return
     * @throws Exception
     */
    public Template getTemplateByName(String name) throws Exception;

    /**
     * Aktualizuje templatu v datab�zi.
     *
     * @param template
     */
    public void updateTemplate(Template template) throws Exception;

    /**
     * Odstran� templatu z datab�ze.
     *
     * @param template
     */
    public void removeTemplate(Template template) throws Exception;

    /**
     * Z�sk� v�echny templaty v datab�zi pro archetyp.
     *
     * @param archwetypName
     */
    public List<Template> getAllTemplates(String archwetypName) throws Exception;

}