package fms.business.archetype;

import fms.business.fieldtype.FieldType;

/**
 * Valid�tor kontroluje data podle typu pol�cka a predem dan�ch specifikac�. To umo�nuje udr�ovat validn� datab�zi dat. Napr�klad umo�n� nad libovoln�m typed dat vytvorit v�cet mo�n�ch hodnot(enum) se zachov�n�m vlastnost� typu pro vyhled�v�n�,  filtrov�n� a statistiky.
 *
 * @author jinora
 * @version 1.0
 * @created 15-Apr-2015 12:39:49 PM
 */
public class Validator {

    /**
     * N�zev valid�toru
     */
    private String name;
    /**
     * Popis valid�toru
     */
    private String description;
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