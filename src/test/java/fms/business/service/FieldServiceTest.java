package fms.business.service;

import fms.business.archetype.Field;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jcr.Node;
import javax.jcr.Session;

import java.util.Map;

import static org.junit.Assert.*;


public class FieldServiceTest extends ServiceTest {

    @Autowired
    private FieldService fieldService;


    @Test
    public void testCreateField() throws Exception {
        String name = "my_field_sname";

        Field field = new Field();
        field.setName(name);

        fieldService.createField(field);

        Field fieldA = fieldService.getFieldByName(name);

        assertNotNull(fieldA);
        assertEquals(field.getName(), fieldA.getName());
    }

    @Test(expected = javax.jcr.PathNotFoundException.class)
    public void testUpdateField() throws Exception {
        String name = "my_field_name";
        String newName = "my_new_field_name";

        Field field = new Field();
        field.setName(name);

        fieldService.createField(field);
        field.setName(newName);
        fieldService.updateField(field);

        Field fieldA = fieldService.getFieldByName(newName);
        assertNotNull(fieldA);
        assertEquals(newName, fieldA.getName());

        fieldService.getFieldByName(name);
        assertFalse("Stary uzel byl nalezen.", true);
    }

    @Test
    public void testGetFields() throws Exception {

        int numOfFields = 3;
        String nameA = "A";
        String nameB = "B";
        String nameC = "C";

        Field fieldA = new Field();
        fieldA.setName(nameA);

        Field fieldB = new Field();
        fieldB.setName(nameB);

        Field fieldC = new Field();
        fieldC.setName(nameC);

        fieldService.createField(fieldA);
        fieldService.createField(fieldB);
        fieldService.createField(fieldC);


        Map<String, Field> fields = fieldService.getAllFields();
        assertEquals(numOfFields, fields.size());


        fieldService.removeField(fieldB);
        Map<String, Field> fieldsB = fieldService.getAllFields();
        assertSame(fields, fieldsB);
        assertEquals(--numOfFields, fields.size());
    }
}