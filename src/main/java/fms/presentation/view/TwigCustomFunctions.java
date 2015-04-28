package fms.presentation.view;

import fms.business.fieldtype.*;
import org.jtwig.functions.annotations.JtwigFunction;
import org.jtwig.functions.annotations.Parameter;

/**
 * Created by root on 4/28/15.
 */
public class TwigCustomFunctions {


    @JtwigFunction(name = "fieldtype")
    public String format(@Parameter FieldType field) {
        if( field instanceof DateField)
            return "date";
        if( field instanceof DateTimeField)
            return "datetime";
        if( field instanceof TimeField)
            return "time";
        if( field instanceof IntegerField)
            return "integer";
        if( field instanceof DecimalField)
            return "decimal";
        return "text";
    }

}
