package fms.business.form;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class PersonTest {

    protected PaperForm makeForm(int id) {
        PaperForm form = new PaperForm();
        form.setId(id);

        return form;
    }

    @Test
    public void testAddingAndRemovingPaperForms() {
        int numOfForms = 0;
        PaperForm formA = makeForm(++numOfForms);
        PaperForm formB = makeForm(++numOfForms);
        PaperForm formC = makeForm(++numOfForms);
        PaperForm formD = makeForm(++numOfForms);

        Person person = new Person();

        person.addPaperForm(formA);
        assertEquals(person, formA.getPerson());

        person.addPaperForm(formB);
        assertEquals(person, formB.getPerson());

        person.addPaperForm(formC);
        assertEquals(person, formC.getPerson());

        person.addPaperForm(formD);
        assertEquals(person, formD.getPerson());


        Map<Long, PaperForm>  forms = person.getPaperForms();
        assertEquals(numOfForms, forms.size());

        //Remove and add
        person.removePaperForm(formA);
        assertEquals(--numOfForms, forms.size());
        assertNull(formA.getPerson());

        person.addPaperForm(formA);
        assertEquals(++numOfForms, forms.size());
        assertEquals(person, formA.getPerson());

        //Remove all
        person.removePaperForm(formA);
        assertEquals(--numOfForms, forms.size());
        assertNull(formA.getPerson());

        person.removePaperForm(formB);
        assertEquals(--numOfForms, forms.size());
        assertNull(formB.getPerson());

        person.removePaperForm(formC);
        assertEquals(--numOfForms, forms.size());
        assertNull(formC.getPerson());

        person.removePaperForm(formD);
        assertEquals(--numOfForms, forms.size());
        assertNull(formD.getPerson());
    }

    @Test
    public void testChangeOwner() {
        Person personA = new Person();
        Person personB = new Person();

        PaperForm form = makeForm(1);

        personA.addPaperForm(form);
        assertEquals(personA, form.getPerson());

        personB.addPaperForm(form);
        assertEquals(personB, form.getPerson());

        Map<Long, PaperForm> formsA = personA.getPaperForms();
        assertEquals(0, formsA.size());

        Map<Long, PaperForm> formsB = personB.getPaperForms();
        assertEquals(1, formsB.size());
    }
}