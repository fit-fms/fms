package fms.business.archetype;


import fms.business.archetype.template.Template;
import org.jcrom.annotations.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Archetyp formul�re je kostra pro data. Udr�uje informace o jednotliv�ch pol�ck�ch pro s�mantick� zpracov�n� dat.
 */
@JcrNode(classNameProperty = "className")
public class Archetype {

    public static final String UNPUBLISHED = "UNPUBLISHED";
    public static final String PUBLISHED = "PUBLISHED";
    public static final String FINISHED = "FINISHED";

    @JcrName
    private String jcrName = "fms_archetype";

    @JcrPath
    private String jcrPath;

    /**
     * Jmeno formulare
     */
    @JcrProperty
    private String name;


    /**
     * Status
     */
    @JcrProperty
    private String state;

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

    @JcrReference(byPath = true)  //Protoze umi ulozit jen <String, Object>
    private List<Field> jcrOptionalFields;

    private Map<String, Field> requiredFields;

    @JcrReference(byPath = true)
    private List<Field> jcrRequiredFields;

    public Archetype() {
        state = UNPUBLISHED;
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


            for (Field f : jcrOptionalFields) {
                optionalFields.put(f.getName(), f);
            }

            for (Field f : jcrRequiredFields) {
                requiredFields.put(f.getName(), f);
            }
        }
    }

    /**
     * Jmeno formulare
     */
    public String getName() {
        return name;
    }

    /**
     * Jmeno formulare
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    /**
     * Verejny popis pouzit jako napoveda
     */
    public String getPublicDescription() {
        return publicDescription;
    }

    /**
     * Verejny popis pouzit jako napoveda
     *
     * @param description
     */
    public void setPublicDescription(String description) {
        publicDescription = description;
    }

    /**
     * Interni popis urceny pro spravce
     */
    public String getPrivateDescription() {
        return privateDescription;
    }

    /**
     * Interni popis urceny pro spravce
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Archetype)) return false;

        Archetype archetype = (Archetype) o;

        return name.equals(archetype.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}