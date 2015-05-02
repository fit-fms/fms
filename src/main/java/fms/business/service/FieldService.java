package fms.business.service;

import fms.business.archetype.Field;

import java.util.Map;

/**
 * Slu�ba pro uchov�v�n� archetypu v datab�zi.
 */
public interface FieldService {

    /**
     * Z�sk� pol�cko podle jm�na.
     *
     * @param name
     */
    public Field getFieldByName(String name) throws Exception;

    /**
     * Vytvor� nov� pol�cko v datab�zi.
     *
     * @param field
     */
    public Field createField(Field field) throws Exception;

    /**
     * Aktualizuje pol�cko v datab�zi.
     *
     * @param field
     */
    public void updateField(Field field) throws Exception;

    /**
     * Odstran� pol�cko z datab�ze.
     *
     * @param field
     */
    public void removeField(Field field) throws Exception;

    /**
     * Z�sk� v�echna pol�cka z datab�ze.
     */
    public Map<String, Field> getAllFields() throws Exception;

}