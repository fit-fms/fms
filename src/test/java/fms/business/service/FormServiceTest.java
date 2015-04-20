package fms.business.service;

import fms.business.form.Form;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import static org.junit.Assert.*;


public class FormServiceTest extends ServiceTest {

    @Autowired
    private FormService formService;


    @Before
    public void setUp() throws Exception {
        resetWorkspace();
    }

    @Test
    public void testCreateField() throws Exception {
        int id = 5;

        Form form = new Form();
        form.setId(id);

        formService.createForm(form);

        Form formA = formService.getFormById(id);

        assertNotNull(formA);
        assertEquals(form.getId(), formA.getId());
    }

    @Test(expected = javax.jcr.PathNotFoundException.class)
    public void testUpdateField() throws Exception {
        int id = 5;
        int newId = id*3;

        Form form = new Form();
        form.setId(id);

        formService.updateForm(form);
        form.setId(newId);
        formService.updateForm(form);

        Form fieldA = formService.getFormById(newId);
        assertNotNull(fieldA);
        assertEquals(newId, fieldA.getId());

        formService.getFormById(id);
        assertFalse("Stary uzel byl nalezen.", true);
    }

    @Test
    public void testGetFields() throws Exception {

        int numOfForms = 3;
        int idA = 3;
        int idB = idA*5;
        int idC = idB*2;

        Form formA = new Form();
        formA.setId(idA);

        Form formB = new Form();
        formB.setId(idB);

        Form formC = new Form();
        formC.setId(idC);

        formService.createForm(formA);
        formService.createForm(formB);
        formService.createForm(formC);


        Map<Integer, Form> forms = formService.getAllForms();
        assertEquals(numOfForms, forms.size());


        formService.removeForm(formB);
        Map<Integer, Form> formsB = formService.getAllForms();
        assertSame(forms, formsB);
        assertEquals(--numOfForms, forms.size());
    }
}