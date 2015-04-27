package fms.business.fieldtype;


import java.util.List;

/**
 * @author jinora
 * @version 1.0
 * @created 15-Apr-2015 12:39:48 PM
 */
public class IntegerField extends FieldType {

    public IntegerField() {

    }


    /**
     * @param data
     */
    public int getInteger(String data) {
        return 0;
    }

    @Override
    public boolean validate(String data, List<String> errors) {
        return false;
    }
}