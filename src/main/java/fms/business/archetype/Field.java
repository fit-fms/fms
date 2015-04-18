package fms.business.archetype;

import fms.business.fieldtype.FieldType;

import java.util.HashMap;
import java.util.Map;

/**
 * Pol�cko formul�re kter� u�ivatel vypln�.
 *
 * @author jinora
 * @version 1.0
 * @created 15-Apr-2015 12:39:48 PM
 */
public class Field {

    /**
     * Jm�no pol�cka pro statistiky a filtrov�n�
     */
    private String name;
    /**
     * Intern� popis pro spr�vce, vhodn� pro vyhled�v�n�
     */
    private String privateDescription;
    /**
     * Popis pou�it� pro verejnou n�povedu.
     */
    private String publicDescription;
    /**
     * Popisek k zobrazeni ve formulari (HTML label)
     */
    private String label;
    /**
     * Typ policka
     */
    private FieldType type;
    /**
     * Validatory policka
     */
    private Map<String, Validator> validators;

    /**
     * Archetypy ktere pouzivaji toto policko
     */
    private Map<String, Archetype> archetypes;

    public Field() {
        this.validators = new HashMap<String, Validator>();
        this.archetypes = new HashMap<String, Archetype>();
    }


    /**
     * Vrati vsechny archetypy ve kterych je policko pouzito.
     */
    public Map<String, Archetype> getArchetypes() {
        return archetypes;
    }

    public void removeArchetype(Archetype archetype) {
        archetypes.remove(archetype.getName());
    }

    public void addArchetype(Archetype archetype) {
        archetypes.put(archetype.getName(), archetype);
    }

    /**
     * Jm�no pol�cka pro statistiky a filtrov�n�
     */
    public String getName() {
        return name;
    }

    /**
     * Jm�no pol�cka pro statistiky a filtrov�n�
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Intern� popis pro spr�vce, vhodn� pro vyhled�v�n�
     */
    public String getPrivateDescription() {
        return privateDescription;
    }

    /**
     * Intern� popis pro spr�vce, vhodn� pro vyhled�v�n�
     *
     * @param description
     */
    public void setPrivateDescription(String description) {
        privateDescription = description;
    }

    public String getLabel() {
        return label;
    }

    /**
     * @param label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Popis pou�it� pro verejnou n�povedu.
     */
    public String getPublicDescription() {
        return publicDescription;
    }

    /**
     * Popis pou�it� pro verejnou n�povedu.
     *
     * @param description
     */
    public void setPublicDescription(String description) {
        publicDescription = description;
    }

    /**
     * @param data
     */
    public boolean validate(String data) {
        boolean status = true;

        for (Map.Entry<String, Validator> entry: validators.entrySet()) {
            if (!entry.getValue().validate(data)) {
                status = false;
            }
        }

        return false;
    }

    /**
     * Prida validator
     *
     * @param validator
     */
    public void addValidator(Validator validator) {
        this.validators.put(validator.getName(), validator);
    }

    /**
     * @param validator
     */
    public void removeValidator(Validator validator) {
        this.validators.remove(validator.getName());
    }

    public Map<String, Validator> getValidators() {
        return validators;
    }

    public FieldType getType() {
        return type;
    }

    /**
     * @param newVal
     */
    public void setType(FieldType newVal) {
        type = newVal;
    }

}