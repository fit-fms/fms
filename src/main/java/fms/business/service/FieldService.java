package fms.business.service;

import fms.business.archetype.Field;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jcr.Session;
import java.util.Map;

/**
 * Slu�ba pro uchov�v�n� archetypu v datab�zi.
 *
 * @author jinora
 * @version 1.0
 * @created 15-Apr-2015 12:39:48 PM
 */
public class FieldService {

    @Autowired
    private Session session;

    public FieldService() {

    }


    /**
     * Z�sk� pol�cko podle jm�na.
     *
     * @param name
     */
    public Field getFieldByName(String name) {
        return null;
    }

    /**
     * Vytvor� nov� pol�cko v datab�zi.
     *
     * @param field
     */
    public Field createField(Field field) {
        return field;
    }

    /**
     * Aktualizuje pol�cko v datab�zi.
     *
     * @param field
     */
    public void updateField(Field field) {

    }

    /**
     * Odstran� pol�cko z datab�ze.
     *
     * @param field
     */
    public void removeField(Field field) {

    }

    /**
     * Z�sk� v�echna pol�cka z datab�ze.
     */
    public Map<String, Field> getAllFields() {
        return null;
    }

}