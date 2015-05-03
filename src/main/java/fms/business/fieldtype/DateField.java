package fms.business.fieldtype;


import java.util.Date;
import java.util.List;

/**
 * Souradnice dne, vhodn� pro informace u kter�ch nen� potreba velk� presnost, napr�klad datum narozen�. (rok, mesic, den)
 *
 * @author jinora
 * @version 1.0
 * @created 15-Apr-2015 12:39:48 PM
 */
public class DateField extends FieldType {

    public DateField() {

    }

    /**
     * @param data
     */
    public Date getDate(String data) {
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