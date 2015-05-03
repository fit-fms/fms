package fms.business.service;

import fms.business.archetype.Archetype;
import fms.business.form.Form;

import javax.jcr.Node;
import java.util.Map;

/**
 * Slu�ba pro uchov�v�n� formul�ru v datab�zi.
 *
 * @author jinora
 * @version 1.0
 * @created 15-Apr-2015 12:39:48 PM
 */
public interface FormService {


    /**
     * Vytvor� nov� formul�r v datab�zi.
     *
     * @param form
     */
    public void createForm(Form form) throws Exception;

    /**
     * Odstran� formul�r z datab�ze.
     *
     * @param form
     */
    public void removeForm(Form form) throws Exception;

    /**
     * Najde formul�r podle ID.
     *
     * @param id
     */
    public Form getFormById(Archetype archetype, long id) throws Exception;

    /**
     * Z�sk� v�echny formul�re z datab�ze.
     */
    public Map<Long, Form> getAllForms(Archetype archetype) throws Exception;

    /**
     * Aktualizuje formul�r v datab�zi.
     *
     * @param form
     */
    public void updateForm(Form form) throws Exception;

}