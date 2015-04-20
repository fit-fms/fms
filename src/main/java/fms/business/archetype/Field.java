package fms.business.archetype;

import fms.business.fieldtype.FieldType;
import org.jcrom.annotations.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Policko formulare ktere uzivatel vyplni.
*/
@JcrNode
public class Field {

    @JcrName
    private String jcrName = "fms_field";

    @JcrPath
    private String jcrPath;

    /**
     * Jmeno policka pro statistiky a filtrovani
     */
    @JcrProperty
    private String name;

    /**
     * Interni popis pro spr�vce, vhodn� pro vyhled�v�n�
     */
    @JcrProperty
    private String privateDescription;

    /**
     * Popis pou�it� pro verejnou n�povedu.
     */
    @JcrProperty
    private String publicDescription;

    /**
     * Popisek k zobrazeni ve formulari (HTML label)
     */
    @JcrProperty
    private String label;

    /**
     * Typ policka
     */
    @JcrReference(byPath = true)
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

        for (Map.Entry<String, Validator> entry : validators.entrySet()) {
            if (!entry.getValue().validate(data)) {
                status = false;
            }
        }

        return status;
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