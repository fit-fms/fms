package fms.business.service;

import fms.business.archetype.Archetype;
import fms.business.archetype.Field;
import fms.business.archetype.Template;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;


public class TemplateServiceTest extends ServiceTest {

    @Before
    public void setUp() throws Exception {
        resetWorkspace();
    }

    @Autowired
    private TemplateService templateService;

    @Test
    public void testCreate() throws Exception {
        String name = "my_field_sname";

        Template template = new Template();
        template.setName(name);

        templateService.createTemplate(template);

        Template templateA = templateService.getTemplateByName(name);

        assertNotNull(templateA);
        assertEquals(template.getName(), templateA.getName());
    }

    @Test
    public void testRemove() throws Exception {
        String name = "my_field_sname";

        Template template = new Template();
        template.setName(name);

        templateService.createTemplate(template);
        templateService.removeTemplate(template);

        Template templateA = templateService.getTemplateByName(name);
        assertNull(templateA);
    }

    @Test
    public void testUpdate() throws Exception {
        String name = "my_field_name";
        String newName = "my_new_field_name";

        Template template = new Template();
        template.setName(name);

        templateService.createTemplate(template);
        template.setName(newName);
        templateService.updateTemplate(template);

        Template templateA = templateService.getTemplateByName(newName);
        assertNotNull(templateA);
        assertEquals(newName, templateA.getName());

        Template templateNo =  templateService.getTemplateByName(name);
        assertNull(templateNo);
    }

    @Test
    public void testGet() throws Exception {

        int numOfFields = 3;
        String nameA = "A";
        String nameB = "B";
        String nameC = "C";

        Template templateA = new Template();
        templateA.setName(nameA);

        Template templateB = new Template();
        templateB.setName(nameB);

        Template templateC = new Template();
        templateC.setName(nameC);

        templateService.createTemplate(templateA);
        templateService.createTemplate(templateB);
        templateService.createTemplate(templateC);


        List<Template> fields = templateService.getAllTemplates("");
        assertEquals(numOfFields, fields.size());


        templateService.removeTemplate(templateB);
        List<Template> fieldsB = templateService.getAllTemplates("");
        assertSame(fields, fieldsB);
        assertEquals(--numOfFields, fields.size());
    }
}