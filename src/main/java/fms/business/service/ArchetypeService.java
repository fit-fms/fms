package fms.business.service;

import fms.business.archetype.Archetype;

import java.util.Map;

/**
 * Sluuba pro uchovavani archetypu v databazi.
 */
public interface ArchetypeService {

    /**
     * Najde v databazi archetyp podle jmena.
     *
     * @param name
     */
    public Archetype findByName(String name) throws Exception;

    /**
     * Odstrani archetyp z databaze.
     *
     * @param archetype
     */
    public void removeArchetype(Archetype archetype) throws Exception;

    /**
     * Prida novy archetyp do databaze.
     *
     * @param archetype
     */
    public void createArchetype(Archetype archetype) throws Exception;

    /**
     * Aktualizuje archetyp v databazi.
     *
     * @param archetype
     */
    public void updateArchetype(Archetype archetype) throws Exception;

    /**
     * Ziska vsechny formulare.
     */
    public Map<String, Archetype> getAllArchetypes() throws Exception;

}