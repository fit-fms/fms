package fms.business.fieldtype;

import fms.business.archetype.Field;
import org.jcrom.annotations.JcrName;
import org.jcrom.annotations.JcrNode;
import org.jcrom.annotations.JcrPath;
import org.jcrom.annotations.JcrProperty;

import java.util.Map;

/**
 * Z�kladn� informace o pol�cku pro budouc� zpracov�n�.
 */
@JcrNode
public class FieldType {

    /**
     * N�zev typu
     */
    @JcrName
    private String jcrName = "fms_field_type";

    @JcrPath
    private String jcrPath;

    @JcrProperty
    private String name;

    /**
     * Popis typu
     */
    @JcrProperty
    private String description;

    //@TODO
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