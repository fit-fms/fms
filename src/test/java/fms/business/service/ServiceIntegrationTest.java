package fms.business.service;


import fms.business.archetype.Archetype;
import fms.business.archetype.Field;
import fms.business.archetype.validator.EmailValidator;
import fms.business.archetype.validator.Validator;
import fms.business.fieldtype.FieldType;
import fms.business.form.FilledField;
import fms.business.form.Form;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class ServiceIntegrationTest extends ServiceTest {
    @Autowired
    private ArchetypeService archetypeService;

    @Autowired
    private FieldService fieldService;

    @Autowired
    private FieldTypeService fieldTypeService;

    @Autowired
    private ValidatorService validatorService;

    @Autowired
    private FormService formService;

    private  final String  fieldName = "field_name";
    private final String archetypeName = "archetype_name";
    private final String fieldTypeName = "field_type_name";
    private final String validatorName = "Email validator";
    private final String invalidEmail = "This is not a @ valid email!";
    private final String validEmail = "hagrid@fit.cvut.cz";
    private final int formId = 55;

    @Test
    public void createFieldType() throws Exception{
        resetWorkspace();
        FieldType fieldType = new FieldType();
        fieldType.setName(fieldTypeName);
        fieldTypeService.createFieldType(fieldType);

        createField();
    }


    public void createField() throws Exception {
        FieldType fieldType = fieldTypeService.getByName(fieldTypeName);
        assertNotNull(fieldType);

        Field field  = new Field();
        field.setName(fieldName);
        field.setType(fieldType);
        fieldService.createField(field);

        createValidator();
    }

    public void createValidator() throws Exception {
        FieldType fieldType = fieldTypeService.getByName(fieldTypeName);
        assertNotNull(fieldType);

        Validator validator = new EmailValidator();
        validator.setName(validatorName);
        validator.setFieldType(fieldType);
        validatorService.createValidator(validator);

        addValidatorToField();
    }

    public void addValidatorToField() throws Exception {
        Field field = fieldService.getFieldByName(fieldName);
        assertNotNull(field);

        Validator validator = validatorService.getValidatorByName(validatorName);
        assertNotNull(validator);

        field.addValidator(validator);
        fieldService.updateField(field);

        checkHasValidator();
    }

    public void checkHasValidator() throws Exception {

        Field field = fieldService.getFieldByName(fieldName);
        assertNotNull(field);

        List<Validator> validators = field.getValidators();
        assertNotNull(validators);
        assertEquals(1, validators.size());

        createArchetype();
    }

    public void createArchetype() throws Exception {
        Field field = fieldService.getFieldByName(fieldName);
        assertNotNull(field);

        Archetype archetype = new Archetype();
        archetype.setName(archetypeName);
        archetype.addOptionalField(field);
        archetypeService.createArchetype(archetype);

        fillForm();
    }


    public void fillForm() throws Exception {
        Archetype archetype = archetypeService.findByName(archetypeName);
        assertNotNull(archetype);

        Map<String, Field> fields = archetype.getOptionalFields();
        assertNotNull(fields);
        assertEquals(1, fields.size());

        Form form = new Form();
        form.setId(formId);
        form.setArchetype(archetype);

        FilledField filledField = new FilledField();
        filledField.setField(fields.get(fieldName));
        filledField.setData(invalidEmail);
        form.addfilledfield(filledField);

        formService.createForm(form);
        retrieveForm();
    }

    public void retrieveForm() throws Exception {
        Archetype archetype = archetypeService.findByName(archetypeName);
        assertNotNull(archetype);

        Form form = formService.getFormById(archetype, formId);
        assertNotNull(form);

        List<FilledField> filledFields = form.getFilledFields();
        assertNotNull(filledFields);
        assertEquals(1, filledFields.size());
        assertEquals(invalidEmail, filledFields.get(0).getData());

        validateForm(false);
        fixForm();
    }

    public void validateForm(boolean expected) throws Exception {
        Archetype archetype = archetypeService.findByName(archetypeName);
        assertNotNull(archetype);

        Form form = formService.getFormById(archetype, formId);
        assertNotNull(form);

        List<FilledField> fields = form.getFilledFields();
        assertEquals(1, fields.size());

        assertEquals(expected, form.validate(null));
    }

    public void fixForm() throws Exception {
        Archetype archetype = archetypeService.findByName(archetypeName);
        assertNotNull(archetype);

        Form form = formService.getFormById(archetype, formId);
        assertNotNull(form);

        FilledField filledField = form.getFilledFields().get(0);
        assertNotNull(filledField);

        filledField.setData(validEmail);
        formService.updateForm(form);

        validateForm(true);
    }

}
