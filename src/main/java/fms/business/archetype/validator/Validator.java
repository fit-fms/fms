package fms.business.archetype.validator;

import fms.business.fieldtype.FieldType;
import org.jcrom.JcrEntity;
import org.jcrom.annotations.*;

import java.util.List;

/**
 * Valid�tor kontroluje data podle typu pol�cka a predem dan�ch specifikac�. To umo�nuje udr�ovat validn� datab�zi dat. Napr�klad umo�n� nad libovoln�m typed dat vytvorit v�cet mo�n�ch hodnot(enum) se zachov�n�m vlastnost� typu pro vyhled�v�n�,  filtrov�n� a statistiky.
*/
@JcrNode(classNameProperty = "className")
abstract public class Validator implements JcrEntity {

    /**
     * N�zev valid�toru
     */
    @JcrName
    private String jcrName = "fms_validator";

    @JcrPath
    private String jcrPath;

    @Override
    public void setPath(String s) {
        jcrPath = s;
    }

    @Override
    public String getPath() {
        return jcrPath;
    }

    @JcrProperty
    private String name;

    /**
     * Popis valid�toru
     */
    @JcrProperty
    private String description;

    @JcrReference(byPath = true)
    private FieldType fieldType;

    public Validator() {

    }

    public FieldType getFieldType() {
        return fieldType;
    }

    /**
     * @param fieldType
     */
    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }

    /**
     * N�zev valid�toru
     */
    public String getName() {
        return name;
    }

    /**
     * N�zev valid�toru
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Popis valid�toru
     */
    public String getDescription() {
        return description;
    }

    /**
     * Popis valid�toru
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @param data
     */
    abstract public boolean validate(String data, List<String> errors);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Validator validator = (Validator) o;

        if (!name.equals(validator.name)) return false;
        return fieldType.equals(validator.fieldType);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + fieldType.hashCode();
        return result;
    }
}