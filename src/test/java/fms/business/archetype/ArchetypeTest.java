package fms.business.archetype;

import fms.business.archetype.template.A4Landscape;
import fms.business.archetype.template.Template;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;


public class ArchetypeTest {

    protected Field makeField(String name) {
        Field field = new Field();
        field.setName(name);

        return field;
    }

    protected Template makeTemplate (String name) {
        Template template = new A4Landscape();
        template.setName(name);

        return template;
    }


    @Test
    public void testAddRemoveRequiredField() {
        int numOfValidators = 4;
        Field fieldA = makeField("A");
        Field fieldB = makeField("B");
        Field fieldC = makeField("C");
        Field fieldD = makeField("D");


        Archetype archetype = new UnpublisdedArchertype();
        archetype.addRequiredField(fieldA);
        archetype.addRequiredField(fieldB);
        archetype.addRequiredField(fieldC);
        archetype.addRequiredField(fieldD);

        Map<String, Field> validators = archetype.getRequiredFields();
        assertEquals(numOfValidators, validators.size());


        //Remove and add
        archetype.removeRequiredField(fieldA);
        assertEquals(--numOfValidators, validators.size());

        archetype.addRequiredField(fieldA);
        assertEquals(++numOfValidators, validators.size());

        //Remove all
        archetype.removeRequiredField(fieldA);
        assertEquals(--numOfValidators, validators.size());

        archetype.removeRequiredField(fieldB);
        assertEquals(--numOfValidators, validators.size());

        archetype.removeRequiredField(fieldC);
        assertEquals(--numOfValidators, validators.size());

        archetype.removeRequiredField(fieldD);
        assertEquals(--numOfValidators, validators.size());
    }

    @Test
    public void testAddRemoveOptionalFields() {
        int numOfValidators = 4;
        Field fieldA = makeField("A");
        Field fieldB = makeField("B");
        Field fieldC = makeField("C");
        Field fieldD = makeField("D");


        Archetype archetype = new UnpublisdedArchertype();
        archetype.addOptionalField(fieldA);
        archetype.addOptionalField(fieldB);
        archetype.addOptionalField(fieldC);
        archetype.addOptionalField(fieldD);

        Map<String, Field> validators = archetype.getOptionalFields();
        assertEquals(numOfValidators, validators.size());


        //Remove and add
        archetype.removeOptionalField(fieldA);
        assertEquals(--numOfValidators, validators.size());

        archetype.addOptionalField(fieldA);
        assertEquals(++numOfValidators, validators.size());

        //Remove all
        archetype.removeOptionalField(fieldA);
        assertEquals(--numOfValidators, validators.size());

        archetype.removeOptionalField(fieldB);
        assertEquals(--numOfValidators, validators.size());

        archetype.removeOptionalField(fieldC);
        assertEquals(--numOfValidators, validators.size());

        archetype.removeOptionalField(fieldD);
        assertEquals(--numOfValidators, validators.size());
    }

    @Test
    public void testAddRemoveTemplate() {
        int numOfTemplates = 4;
        Template fieldA = makeTemplate("A");
        Template fieldB = makeTemplate("B");
        Template fieldC = makeTemplate("C");
        Template fieldD = makeTemplate("D");


        Archetype archetype = new UnpublisdedArchertype();
        archetype.addTemplate(fieldA);
        archetype.addTemplate(fieldB);
        archetype.addTemplate(fieldC);
        archetype.addTemplate(fieldD);

        Map<String, Template> validators = archetype.getTemplates();
        assertEquals(numOfTemplates, validators.size());


        //Remove and add
        archetype.removeTemplate(fieldA);
        assertEquals(--numOfTemplates, validators.size());

        archetype.addTemplate(fieldA);
        assertEquals(++numOfTemplates, validators.size());

        //Remove all
        archetype.removeTemplate(fieldA);
        assertEquals(--numOfTemplates, validators.size());

        archetype.removeTemplate(fieldB);
        assertEquals(--numOfTemplates, validators.size());

        archetype.removeTemplate(fieldC);
        assertEquals(--numOfTemplates, validators.size());

        archetype.removeTemplate(fieldD);
        assertEquals(--numOfTemplates, validators.size());
    }
}