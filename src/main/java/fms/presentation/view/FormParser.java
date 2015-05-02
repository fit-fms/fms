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
import org.springframework.stereotype.Component;

/**
 * @author Jaroslav molík
 * @version 1.0
 * @created 23-4-2015 21:04:54
 */

/**
 * Parses http request regarding forms
 */
@Component
public class FormParser {

    /**
     * Konstuktor
     */
    public FormParser() {

    }

    /**
     * Processes http request to approve form
     * @param map Request to parse
     * @param form Form to approve
     * @param archetype Form's Archetype
     * @param errors Error list
     * @return Apporved form
     */
    public Form approveForm(Map<String, String> map, Form form, Archetype archetype, List<String> errors) {
        return null;
    }

    /**
     * Processes http request to edit form
     * @param map Request to parse
     * @param form Form to edit
     * @param archetype Form's Archetype
     * @param errors Error list
     * @return Edited form
     */
    public Form editForm(Map<String, String> map, Form form, Archetype archetype, List<String> errors) {
        return null;
    }

    /**
     * Processes http request to fill out form
     * @param map Request to parse
     * @param archetype Form's Archetype
     * @param errors Error list
     * @return Created form
     */
    public Form fillOutForm(Map<String, String> map, Archetype archetype, List<String> errors) {
        Form form = new DigitalForm();
        form.setArchetype(archetype);

        boolean error = false;
        for (Map.Entry<String, Field> item : archetype.getRequiredFields().entrySet()) {
            String fieldname = item.getKey();
            Field field = item.getValue();

            //todo get from request
            String filled = map.get(field.getName());
            if (filled == null) {
                errors.add("Required field not found: " + field.getName());
                error = true;
            }

            FilledField filledField = new FilledField();
            filledField.setData(filled);
            filledField.setField(field);
            form.addfilledfield(filledField);

        }

        for (Map.Entry<String, Field> item : archetype.getOptionalFields().entrySet()) {
            String fieldname = item.getKey();
            Field field = item.getValue();

            //todo get from request
            String filled = map.get(field.getName());

            FilledField filledField = new FilledField();
            filledField.setData(filled);
            filledField.setField(field);
            form.addfilledfield(filledField);

        }
        if (form.validate(errors) == false || error) {
            return null;
        }

        return form;
    }
}