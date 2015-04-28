package fms.presentation.view;

import fms.business.archetype.Field;
import fms.business.archetype.validator.Validator;
import fms.business.form.DigitalForm;
import fms.business.form.FilledField;
import fms.business.form.Form;
import fms.business.archetype.Archetype;
import org.springframework.http.HttpRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Jaroslav molík
 * @version 1.0
 * @created 23-4-2015 21:04:54
 */
public class GWTForm {



    public void finalize() throws Throwable {

    }

    /**
     * Konstuktor
     */
    public GWTForm(){

    }

    /**
     * Zpracuje HTTP request a vytvori domenove objekty
     *
     * @param request
     * @param form
     * @param archetype
     */
    public Form approveForm(HttpRequest request, Form form, Archetype archetype){
        return null;
    }

    /**
     * Zpracuje HTTP request a vytvori domenove objekty
     *
     * @param request
     * @param form
     * @param archetype
     */
    public Form editForm(HttpRequest request, Form form, Archetype archetype){
        return null;
    }

    /**
     * Zpracuje HTTP request a vytvori domenove objekty
     *
     * @param request
     * @param archetype
     */
    public Form fillOutForm(HttpRequest request, Archetype archetype){
        Form form = new DigitalForm();

        for( Map.Entry<String, Field> item : archetype.getRequiredFields().entrySet() ) {
            String fieldname = item.getKey();
            Field field = item.getValue();

            //todo get from request
            String filled = "";

            if( false ){ //todo if filled is set
                FilledField filledField = new FilledField();
                filledField.setData(filled);
                filledField.setField(field);
                form.addfilledfield(filledField);
            }
        }

        for( Map.Entry<String, Field> item : archetype.getOptionalFields().entrySet() ) {
            String fieldname = item.getKey();
            Field field = item.getValue();

            //todo get from request
            String filled = "";

            if( false ){ //todo if filled is set
                FilledField filledField = new FilledField();
                filledField.setData(filled);
                filledField.setField(field);
                form.addfilledfield(filledField);
            }
        }
        List<String> err = new ArrayList<String>();
        if( form.validate(err) == false )
            return null;

        return form;
    }
//
//    /**
//     *
//     * @param form
//     */
//    public Response renderForm(Form form){
//        return null;
//    }

}