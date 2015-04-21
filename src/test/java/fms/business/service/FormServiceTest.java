package fms.business.service;

import fms.business.archetype.Archetype;
import fms.business.form.Form;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import static org.junit.Assert.*;


public class FormServiceTest extends ServiceTest {

    @Before
    public void setUp() throws Exception {
        resetWorkspace();
    }

    @Autowired
    private FormService formService;

    @Autowired
    private ArchetypeService archetypeService;

    @Test
    public void testCreateField() throws Exception {
        int id = 5;

        Archetype archetype = new Archetype();
        archetype.setName("arname");
        archetypeService.createArchetype(archetype);

        Form form = new Form();
        form.setId(id);
        form.setArchetype(archetype);

        formService.createForm(form);

        Form formA = formService.getFormById(archetype, id);

        assertNotNull(formA);
        assertEquals(form.getId(), formA.getId());
    }

    @Test
    public void testUpdateField() throws Exception {
        int id = 5;
        int newId = id*3;

        Archetype archetype = new Archetype();
        archetype.setName("arname");
        archetypeService.createArchetype(archetype);

        Form form = new Form();
        form.setId(id);
        form.setArchetype(archetype);

        formService.createForm(form);
        form.setId(newId);
        formService.updateForm(form);

        Form fieldA = formService.getFormById(archetype, newId);
        assertNotNull(fieldA);
        assertEquals(newId, fieldA.getId());

        Form formNo = formService.getFormById(archetype, id);
        assertNull(formNo);
    }

    @Test
    public void testGetFields() throws Exception {

        Archetype archetype = new Archetype();
        archetype.setName("arname");
        archetypeService.createArchetype(archetype);

        int numOfForms = 3;
        int idA = 3;
        int idB = idA*5;
        int idC = idB*2;

        Form formA = new Form();
        formA.setId(idA);
        formA.setArchetype(archetype);

        Form formB = new Form();
        formB.setId(idB);
        formB.setArchetype(archetype);

        Form formC = new Form();
        formC.setId(idC);
        formC.setArchetype(archetype);

        formService.createForm(formA);
        formService.createForm(formB);
        formService.createForm(formC);


        Map<Integer, Form> forms = formService.getAllForms(archetype);
        assertEquals(numOfForms, forms.size());


        formService.removeForm(formB);
        Map<Integer, Form> formsB = formService.getAllForms(archetype);
        assertEquals(--numOfForms, formsB.size());
    }
}