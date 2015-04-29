package fms.business.service;

import fms.business.archetype.Archetype;
import fms.business.archetype.UnpublisdedArchertype;
import fms.business.form.DigitalForm;
import fms.business.form.Form;
import fms.business.form.PaperForm;
import fms.business.form.Scan;
import org.junit.Before;
import org.junit.Test;
import org.omg.PortableInterceptor.DISCARDING;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
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
    public void testCreateForm() throws Exception {
        long id;

        Archetype archetype = new UnpublisdedArchertype();
        archetype.setName("arname");
        archetypeService.createArchetype(archetype);

        Form form = new PaperForm();
        form.setArchetype(archetype);

        formService.createForm(form);
        id = form.getId();

        Form formA = formService.getFormById(archetype, id);

        assertNotNull(formA);
        assertTrue(formA instanceof PaperForm);
        assertEquals(form.getId(), formA.getId());
    }

    @Test
    public void testUpdateForm() throws Exception {
        long id;

        Archetype archetypeA = new UnpublisdedArchertype();
        archetypeA.setName("arname");
        archetypeService.createArchetype(archetypeA);

        Archetype archetypeB = new UnpublisdedArchertype();
        archetypeB.setName("Arch B");
        archetypeService.createArchetype(archetypeB);

        Form form = new PaperForm();
        form.setArchetype(archetypeA);
        formService.createForm(form);
        id = form.getId();

        form.setArchetype(archetypeB);
        formService.updateForm(form);

        Form formB = formService.getFormById(archetypeB, id);
        assertNotNull(formB);
        assertNotNull(formB.getArchetype());
        assertEquals(archetypeB, formB.getArchetype());
    }

    @Test
    public void testGetForms() throws Exception {

        Archetype archetype = new UnpublisdedArchertype();
        archetype.setName("arname");
        archetypeService.createArchetype(archetype);

        int numOfForms = 3;
        long idA;
        long idB;
        long idC;

        Form formA = new PaperForm();
        formA.setArchetype(archetype);

        Form formB = new PaperForm();
        formB.setArchetype(archetype);

        Form formC = new PaperForm();
        formC.setArchetype(archetype);

        formService.createForm(formA);
        idA = formA.getId();

        formService.createForm(formB);
        idB = formB.getId();

        formService.createForm(formC);
        idC = formC.getId();


        Map<Long, Form> forms = formService.getAllForms(archetype);
        assertEquals(numOfForms, forms.size());


        formService.removeForm(formB);
        Map<Long, Form> formsB = formService.getAllForms(archetype);
        assertEquals(--numOfForms, formsB.size());
    }

    @Test
    public void testDigital() throws Exception {
        Archetype archetype = new UnpublisdedArchertype();
        archetype.setName("arname");
        archetypeService.createArchetype(archetype);

        long id;
        String browser = "Google Chrome Pelikan";
        String ip = "255.255.255.-1";

        DigitalForm form = new DigitalForm();
        form.setArchetype(archetype);
        form.setIp(ip);
        form.setBrowser(browser);
        formService.createForm(form);
        id = form.getId();

        Form digitalForm = formService.getFormById(archetype, id);
        assertNotNull(digitalForm);
        assertTrue(digitalForm instanceof DigitalForm);
        assertEquals(ip, ((DigitalForm) digitalForm).getIp());
        assertEquals(browser, ((DigitalForm)digitalForm).getBrowser());
    }

    @Test
    public void testPaper() throws Exception {
        Archetype archetype = new UnpublisdedArchertype();
        archetype.setName("arname");
        archetypeService.createArchetype(archetype);

        Date filledAt = new Date();
        Date signedAd = new Date();

        long id;
        PaperForm form = new PaperForm();
        form.setArchetype(archetype);
        form.setFilledAt(filledAt);
        form.setSignedAt(signedAd);
//        form.setPerson(person);
        formService.createForm(form);
        id = form.getId();

        Form formA = formService.getFormById(archetype, id);
        assertNotNull(formA);
        assertTrue(formA instanceof PaperForm);
        assertEquals(filledAt, formA.getFilledAt());
        assertEquals(signedAd, ((PaperForm)formA).getSignedAt());
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