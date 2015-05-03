package fms.business.fieldtype;


import java.util.List;

/**
 * Cas v r�mci dne, vhodn� pro opakuj�c� se ud�losti, napr�klad zac�tek vyucov�n�. (hodiny, minuty, sekundy)
 *
 * @author jinora
 * @version 1.0
 * @created 15-Apr-2015 12:39:49 PM
 */
public class TimeField extends FieldType {

    public TimeField() {

    }


    /**
     * @param data
     */
    public TimeField getTime(String data) {
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean validate(String data, List<String> errors) {
        return false;
    }
}