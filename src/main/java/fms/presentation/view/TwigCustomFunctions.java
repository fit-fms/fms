package fms.presentation.view;

import fms.business.archetype.Field;
import fms.business.fieldtype.*;
import fms.business.form.FilledField;
import org.jtwig.exceptions.AssetResolveException;
import org.jtwig.functions.annotations.JtwigFunction;
import org.jtwig.functions.annotations.Parameter;
import org.jtwig.mvc.JtwigViewResolver;
import org.jtwig.services.impl.assets.BaseAssetResolver;
import org.jtwig.util.UrlPath;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by root on 4/28/15.
 */

/**
 * Custom set of functions for jTwig template
 */
public class TwigCustomFunctions {

    /**
     * Finds a propriate type of field (to render the right input type in form)
     * @param field field to get fieldtype from (Field or FilledField)
     * @return type of field as a String
     * @throws Exception if field is none of known classes
     */
    @JtwigFunction(name = "fieldtype")
    public String format(@Parameter Object field) throws Exception {
        if( field instanceof FilledField)
            return format(((FilledField) field).getField().getType());
        if( field instanceof Field)
            return format(((Field) field).getType());
        throw new Exception("jTwig function - unnown type : " + field.getClass().getSimpleName() );
    }

    /**
     * Find fieldtype for given field. Called by another format function.
     * @param field given field
     * @return fieldtype as a String
     */
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
