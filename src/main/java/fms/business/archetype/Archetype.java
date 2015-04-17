package fms.business.archetype;


import java.util.Map;

/**
 * Archetyp formul�re je kostra pro data. Udr�uje informace o jednotliv�ch pol�ck�ch pro s�mantick� zpracov�n� dat.
 *
 * @author jinora
 * @version 1.0
 * @created 15-Apr-2015 12:39:48 PM
 */
public class Archetype {

    /**
     * Jm�no formul�re
     */
    private String name;
    /**
     * Intern� popis urcen� pro spr�vce
     */
    private String privateDescription;
    /**
     * Verejn� popis pou�it jako n�poveda
     */
    private String publicDescription;
    /**
     * Templaty pomoci kterych jde archetyp zobrazit
     */
    private Map<String, Template> templates;
    /**
     * Policka archetypu.
     */
    private Map<String, Field> optionalFields;
    private Map<String, Field> requiredFields;
    private Map<String, Template> Templates;

    public Archetype() {

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
        optionalFields.put(field.getName(), field);
    }

    /**
     * @param field
     */
    public void addRequiredField(Field field) {
        requiredFields.put(field.getName(), field);
    }

    /**
     * @param field
     */
    public void removeRequiredField(Field field) {
        requiredFields.remove(field.getName());
    }

    public Map<String, Field> getRequiredFields() {
        return requiredFields;
    }

    /**
     * Odebere field
     *
     * @param field
     */
    public void removeOptionalField(Field field) {
        optionalFields.remove(field.getName());
    }

    /**
     * Vrati vsechny fieldy
     */
    public Map<String, Field> getOptionalFields() {
        return optionalFields;
    }

}