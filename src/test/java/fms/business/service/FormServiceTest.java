package fms.business.service;

import fms.business.archetype.Archetype;
import fms.business.archetype.UnpublisdedArchertype;
import fms.business.form.Form;
import fms.business.form.PaperForm;
import fms.business.form.Scan;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
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

        Archetype archetype = new UnpublisdedArchertype();
        archetype.setName("arname");
        archetypeService.createArchetype(archetype);

        Form form = new PaperForm();
        form.setId(id);
        form.setArchetype(archetype);

        formService.createForm(form);

        Form formA = formService.getFormById(archetype, id);

        assertNotNull(formA);
        assertTrue(formA instanceof PaperForm);
        assertEquals(form.getId(), formA.getId());
    }

    @Test
    public void testUpdateField() throws Exception {
        int id = 5;
        int newId = id*3;

        Archetype archetype = new UnpublisdedArchertype();
        archetype.setName("arname");
        archetypeService.createArchetype(archetype);

        Form form = new PaperForm();
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

        Archetype archetype = new UnpublisdedArchertype();
        archetype.setName("arname");
        archetypeService.createArchetype(archetype);

        int numOfForms = 3;
        int idA = 3;
        int idB = idA*5;
        int idC = idB*2;

        Form formA = new PaperForm();
        formA.setId(idA);
        formA.setArchetype(archetype);

        Form formB = new PaperForm();
        formB.setId(idB);
        formB.setArchetype(archetype);

        Form formC = new PaperForm();
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

//    TODO implement
//    @Test
    public void testScans() throws Exception {
        Archetype archetype = new UnpublisdedArchertype();
        archetype.setName("arname");
        archetypeService.createArchetype(archetype);


        int id = 3;
        PaperForm form = new PaperForm();
        form.setId(id);
        form.setArchetype(archetype);

        int numberOfScans = 4;
        Scan scanA = new Scan();
        scanA.setId(1);
        Scan scanB = new Scan();
        scanB.setId(2);
        Scan scanC = new Scan();
        scanC.setId(3);
        Scan scanD = new Scan();
        scanD.setId(4);

        form.addScan(scanA);
        form.addScan(scanB);
        form.addScan(scanC);
        form.addScan(scanD);

        formService.createForm(form);
        PaperForm formA = (PaperForm)formService.getFormById(archetype, id);
        assertNotNull(formA);
        List<Scan> fieldsA = formA.getScans();
        assertEquals(numberOfScans, fieldsA.size());

        //Remove and add
        form.removeScan(scanB);
        assertEquals(--numberOfScans, fieldsA.size());
        formService.updateForm(formA);

        PaperForm formB = (PaperForm)formService.getFormById(archetype, id);
        assertNotNull(formB);
        List<Scan> fieldsB = formB.getScans();
        assertEquals(numberOfScans, fieldsB.size());
    }
}