package fms.business.form;

import fms.business.archetype.Field;

/**
 * @author jinora
 * @version 1.0
 * @created 15-Apr-2015 12:39:48 PM
 */
public class FilledField {

    /**
     * Ulo�en� data v textove podobe.
     */
    private String data;
    /**
     * Policko jehoz data FilledField uchovava.
     */
    private Field field;

    public FilledField() {

    }


    /**
     * Ziska policko jehoz data FilledField uchovava.
     */
    public Field getField() {
        return field;
    }

    /**
     * Nastav� policko jehoz data FilledField uchovava.
     *
     * @param newVal
     */
    public void setField(Field newVal) {
        field = newVal;
    }

    /**
     * Z�sk� text vyplnen� do pol�cka.
     */
    public String getData() {
        return data;
    }

    /**
     * Nastav� text vyplnen� do pol�cka.
     *
     * @param data
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * Zvaliduje data ulo�en� v objektu vuci Fieldu
     */
    public boolean validate() {
        return this.field.validate(data);
    }

}