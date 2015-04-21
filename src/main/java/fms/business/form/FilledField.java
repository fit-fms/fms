package fms.business.form;

import fms.business.archetype.Field;
import org.jcrom.annotations.*;

/**
 * @author jinora
 * @version 1.0
 * @created 15-Apr-2015 12:39:48 PM
 */
@JcrNode
public class FilledField {

    @JcrName
    private String jcrName = "fms_filled_field";

    @JcrPath
    private String jcrPath;

    /**
     * Ulo�en� data v textove podobe.
     */
    @JcrProperty
    private String data;

    /**
     * Policko jehoz data FilledField uchovava.
     */
    @JcrReference(byPath = true)
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FilledField that = (FilledField) o;

        return field.equals(that.field);

    }

    @Override
    public int hashCode() {
        return field.hashCode();
    }
}