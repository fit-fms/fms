package fms.business.archetype;

import fms.business.fieldtype.FieldType;
import org.jcrom.annotations.*;

/**
 * Valid�tor kontroluje data podle typu pol�cka a predem dan�ch specifikac�. To umo�nuje udr�ovat validn� datab�zi dat. Napr�klad umo�n� nad libovoln�m typed dat vytvorit v�cet mo�n�ch hodnot(enum) se zachov�n�m vlastnost� typu pro vyhled�v�n�,  filtrov�n� a statistiky.
 *
 * @author jinora
 * @version 1.0
 * @created 15-Apr-2015 12:39:49 PM
 */
@JcrNode
public class Validator {

    /**
     * N�zev valid�toru
     */
    @JcrName
    private String name;

    @JcrPath
    private String jcrPath;

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
    public boolean validate(String data) {
        return false;
    }

}