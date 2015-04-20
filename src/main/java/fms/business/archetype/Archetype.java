package fms.business.archetype;


import org.jcrom.annotations.*;

import java.util.*;

/**
 * Archetyp formul�re je kostra pro data. Udr�uje informace o jednotliv�ch pol�ck�ch pro s�mantick� zpracov�n� dat.
 *
 * @author jinora
 * @version 1.0
 * @created 15-Apr-2015 12:39:48 PM
 */
@JcrNode
public class Archetype {

    /**
     * Jm�no formul�re
     */
    @JcrName
    private String name;

    @JcrPath
    private String jcrPath;

    /**
     * Intern� popis urcen� pro spr�vce
     */
    @JcrProperty
    private String privateDescription;

    /**
     * Verejn� popis pou�it jako n�poveda
     */
    @JcrProperty
    private String publicDescription;

    /**
     * Templaty pomoci kterych jde archetyp zobrazit
     */
    private Map<String, Template> templates;

    /**
     * Policka archetypu.
     */
    private Map<String, Field> optionalFields;

    @JcrReference(byPath=true)  //Protoze umi ulozit jen <String, Object>
    private List<Field> jcrOptionalFields;

    private Map<String, Field> requiredFields;

    @JcrReference(byPath=true)
    private List<Field> jcrRequiredFields;

    public Archetype() {
        jcrOptionalFields = new ArrayList<Field>();
        jcrRequiredFields = new ArrayList<Field>();
        templates = new HashMap<String, Template>();
    }

    /**
     * Protoze Jcrom umi jen Map<String, Object>
     */
    private void createFields() {
        if (optionalFields == null) {
            optionalFields = new HashMap<String, Field>();
            requiredFields = new HashMap<String, Field>();


            for ( Field f: jcrOptionalFields ) {
                optionalFields.put(f.getName(), f);
            }

            for ( Field f: jcrRequiredFields ) {
                requiredFields.put(f.getName(), f);
            }
        }
    }

    /**
     * Jm�no formul�re
     */
    public String getName() {
        return name;
    }

    /**
     * Jm�no formul�re
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Verejn� popis pou�it jako n�poveda
     */
    public String getPublicDescription() {
        return publicDescription;
    }

    /**
     * Verejn� popis pou�it jako n�poveda
     *
     * @param description
     */
    public void setPublicDescription(String description) {
        publicDescription = description;
    }

    /**
     * Intern� popis urcen� pro spr�vce
     */
    public String getPrivateDescription() {
        return privateDescription;
    }

    /**
     * Intern� popis urcen� pro spr�vce
     *
     * @param description
     */
    public void setPrivateDescription(String description) {
        privateDescription = description;
    }

    /**
     * Prida templatu
     *
     * @param template
     */
    public void addTemplate(Template template) {
        templates.put(template.getName(), template);
    }

    /**
     * odebere templatu
     *
     * @param template
     */
    public void removeTemplate(Template template) {
        templates.remove(template.getName());
    }

    /**
     * Vrati templaty
     */
    public Map<String, Template> getTemplates() {
        return templates;
    }

    /**
     * Prida field
     *
     * @param field
     */
    public void addOptionalField(Field field) {
        createFields();
        field.addArchetype(this);
        optionalFields.put(field.getName(), field);
        jcrOptionalFields.add(field);
    }

    /**
     * @param field
     */
    public void addRequiredField(Field field) {
        createFields();
        field.addArchetype(this);
        requiredFields.put(field.getName(), field);
        jcrRequiredFields.add(field);
    }

    /**
     * @param field
     */
    public void removeRequiredField(Field field) {
        createFields();
        field.removeArchetype(this);
        requiredFields.remove(field.getName());
        jcrRequiredFields.remove(field);
    }

    public Map<String, Field> getRequiredFields() {
        createFields();
        return requiredFields;
    }

    /**
     * Odebere field
     *
     * @param field
     */
    public void removeOptionalField(Field field) {
        createFields();
        field.removeArchetype(this);
        optionalFields.remove(field.getName());
        jcrOptionalFields.remove(field);
    }

    /**
     * Vrati vsechny fieldy
     */
    public Map<String, Field> getOptionalFields() {
        createFields();
        return optionalFields;
    }
}