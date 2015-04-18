package fms.business.service;

import fms.business.archetype.Archetype;

import java.util.Map;

/**
 * Slu�ba pro uchov�v�n� archetypu v datab�zi.
 *
 * @author jinora
 * @version 1.0
 * @created 15-Apr-2015 12:39:48 PM
 */
public class ArchetypeService {


    public ArchetypeService() {

    }


    /**
     * Najde v datab�zi archetyp podle jm�na.
     *
     * @param name
     */
    public Archetype findByName(String name) {
        return null;
    }

    /**
     * Odstran� archetyp z datab�ze.
     *
     * @param archetype
     */
    public void removeArchetype(Archetype archetype) {

    }

    /**
     * Prid� nov� archetyp do datab�ze.
     *
     * @param archetype
     */
    public void createArchetype(Archetype archetype) {

    }

    /**
     * Aktualizuje archetyp v datab�zi.
     *
     * @param archetype
     */
    public void updateArchetype(Archetype archetype) {

    }

    /**
     * Z�sk� v�echny formul�re.
     */
    public Map<String, Archetype> getAllArchetypes() {
        return null;
    }

}