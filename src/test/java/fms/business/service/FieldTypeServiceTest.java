package fms.business.service;

import fms.business.fieldtype.FieldType;
import fms.business.fieldtype.TextField;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;


public class FieldTypeServiceTest extends ServiceTest {

    @Before
    public void setUp() throws Exception {
        resetWorkspace();
    }

    @Autowired
    private FieldTypeService fieldTypeService;

    @Test
    public void testCreateField() throws Exception {
        String name = "my_field_sname";

        FieldType fieldType = new TextField();
        fieldType.setName(name);

        fieldTypeService.createFieldType(fieldType);

        FieldType fieldTypeB = fieldTypeService.getByName(name);

        assertNotNull(fieldTypeB);
        assertTrue(fieldTypeB instanceof TextField);
        assertEquals(fieldType.getName(), fieldTypeB.getName());
    }

    @Test
    public void testRemove() throws Exception {
        String name = "my_field_sname";

        FieldType fieldType = new TextField();
        fieldType.setName(name);

        fieldTypeService.createFieldType(fieldType);
        fieldTypeService.removeFieldType(fieldType);

        FieldType fieldTypeB = fieldTypeService.getByName(name);
        assertNull(fieldTypeB);
    }

    @Test
    public void testUpdateField() throws Exception {
        String name = "my_field_name";
        String newName = "my_new_field_name";

        FieldType fieldType = new TextField();
        fieldType.setName(name);

        fieldTypeService.createFieldType(fieldType);
        fieldType.setName(newName);
        fieldTypeService.updateFieldType(fieldType);

        FieldType fieldTypeB = fieldTypeService.getByName(newName);
        assertNotNull(fieldTypeB);
        assertEquals(newName, fieldTypeB.getName());

        FieldType fieldTypeNo = fieldTypeService.getByName(name);
        assertNull(fieldTypeNo);
    }

    @Test
    public void testGetFields() throws Exception {

        int numOfFields = 3;
        String nameA = "A";
        String nameB = "B";
        String nameC = "C";

        FieldType fieldTypeA = new TextField();
        fieldTypeA.setName(nameA);

        FieldType fieldTypeB = new TextField();
        fieldTypeB.setName(nameB);

        FieldType fieldTypeC = new TextField();
        fieldTypeC.setName(nameC);

        fieldTypeService.createFieldType(fieldTypeA);
        fieldTypeService.createFieldType(fieldTypeB);
        fieldTypeService.createFieldType(fieldTypeC);


        List<FieldType> fields = fieldTypeService.getFieldTypes();
        assertEquals(numOfFields, fields.size());


        fieldTypeService.removeFieldType(fieldTypeB);
        List<FieldType> fieldsB = fieldTypeService.getFieldTypes();
        assertSame(fields, fieldsB);
        assertEquals(--numOfFields, fields.size());
    }
}