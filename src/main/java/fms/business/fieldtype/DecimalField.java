package fms.business.fieldtype;


import java.util.List;

/**
 * @author jinora
 * @version 1.0
 * @created 15-Apr-2015 12:39:48 PM
 */
public class DecimalField extends FieldType {

    public DecimalField() {

    }


    /**
     * @param data
     */
    public double getDecimal(String data) {
        return 0;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean validate(String data, List<String> errors) {
        return false;
    }
}