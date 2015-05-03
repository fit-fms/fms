package fms.business.fieldtype;

import fms.business.archetype.Field;
import org.jcrom.annotations.*;

import java.util.List;

/**
 * Z�kladn� informace o pol�cku pro budouc� zpracov�n�.
 */
@JcrNode(classNameProperty = "className")
abstract public class FieldType {

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

    @JcrReference(byPath = true)
    //@TODO implement
    private List<Field> fields;

    public FieldType() {

    }


    /**
     * Ziska vsechna policka tohoto typu
     *
     * @return Fields of this type
     */
    public List<Field> getFields() {
        return fields;
    }

    /**
     * N�zev typu
     * @return jmeno typu
     */
    public String getName() {
        return name;
    }

    /**
     * N�zev typu
     *
     * @param name jmeno typu
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Popis typu
     * @return Popis typu
     */
    public String getDescription() {
        return description;
    }

    /**
     * Popis typu
     *
     * @param description popis typu
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Validate string
     *
     * @param data   String to be validate
     * @param errors List to add errors to
     * @return success of validation
     */
    abstract public boolean validate(String data, List<String> errors);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FieldType fieldType = (FieldType) o;

        return name.equals(fieldType.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

}