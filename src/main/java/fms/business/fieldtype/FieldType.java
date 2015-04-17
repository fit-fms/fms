package fms.business.fieldtype;

import fms.business.archetype.Field;

import java.util.Map;

/**
 * Z�kladn� informace o pol�cku pro budouc� zpracov�n�.
 *
 * @author jinora
 * @version 1.0
 * @created 15-Apr-2015 12:39:48 PM
 */
public class FieldType {

    /**
     * N�zev typu
     */
    private String name;
    /**
     * Popis typu
     */
    private String description;
    private Map<String, Field> fields;

    public FieldType() {

    }


    public Map<String, Field> getFields() {
        return fields;
    }

    /**
     * N�zev typu
     */
    public String getName() {
        return name;
    }

    /**
     * N�zev typu
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Popis typu
     */
    public String getDescription() {
        return description;
    }

    /**
     * Popis typu
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public boolean validate() {
        return false;
    }

}