package fms.business.service;


import fms.business.archetype.Archetype;
import fms.business.archetype.Field;
import fms.business.fieldtype.FieldType;
import fms.business.form.FilledField;
import fms.business.form.Form;
import org.junit.Before;
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
    private FormService formService;

    private  final String  fieldName = "field_name";
    private final String archetypeName = "archetype_name";
    private final String fieldTypeName = "field_type_name";
    private final int formId = 55;

    @Test
    public void AcreateFieldType() throws Exception{
        resetWorkspace();
        FieldType fieldType = new FieldType();
        fieldType.setName(fieldTypeName);
        fieldTypeService.createFieldType(fieldType);

        BcreateField();
    }


    public void BcreateField() throws Exception {
        FieldType fieldType = fieldTypeService.getByName(fieldTypeName);
        assertNotNull(fieldType);

        Field field  = new Field();
        field.setName(fieldName);
        field.setType(fieldType);
        fieldService.createField(field);

        CcreateArchetype();
    }


    public void CcreateArchetype() throws Exception {
        Field field = fieldService.getFieldByName(fieldName);
        assertNotNull(field);

        Archetype archetype = new Archetype();
        archetype.setName(archetypeName);
        archetype.addOptionalField(field);
        archetypeService.createArchetype(archetype);

        DcreateForm();
    }


    public void DcreateForm() throws Exception {
        Archetype archetype = archetypeService.findByName(archetypeName);
        assertNotNull(archetype);

        Map<String, Field> fields = archetype.getOptionalFields();
        assertNotNull(fields);
        assertEquals(1, fields.size());

        Form form = new Form();
        form.setId(formId);
        form.setArchetype(archetype);

        for ( Map.Entry<String, Field> entry: fields.entrySet() ) {
            FilledField filledField = new FilledField();
            filledField.setField(entry.getValue());
            form.addfilledfield(filledField);
        }

        formService.createForm(form);
        EvalidateForm();
    }

    public void EvalidateForm() throws Exception {
        Archetype archetype = archetypeService.findByName(archetypeName);
        assertNotNull(archetype);

        Form form = formService.getFormById(archetype, formId);
        assertNotNull(form);

        List<FilledField> fields = form.getFilledFields();
        assertEquals(1, fields.size());

        form.validate();
    }
}
