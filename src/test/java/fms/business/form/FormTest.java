package fms.business.form;

import fms.business.archetype.Archetype;
import fms.business.archetype.Field;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;


public class FormTest {

    protected FilledField makeFilledField(String name) {
        Field field = new Field();
        field.setName(name);

        FilledField filledField = new FilledField();
        filledField.setField(field);

        return filledField;
    }

    @Test
    public void testAddingAndRemovingFilledFields() {
        int numOfFields = 4;
        FilledField ffieldA = makeFilledField("A");
        FilledField ffieldB = makeFilledField("B");
        FilledField ffieldC = makeFilledField("C");
        FilledField ffieldD = makeFilledField("D");


        Form form = new Form();
        form.addfilledfield(ffieldA);
        form.addfilledfield(ffieldB);
        form.addfilledfield(ffieldC);
        form.addfilledfield(ffieldD);

        List<FilledField> fields = form.getFilledFields();
        assertEquals(numOfFields, fields.size());


        //Remove and add
        form.removeFilledFIeld(ffieldA);
        assertEquals(--numOfFields, fields.size());

        form.addfilledfield(ffieldA);
        assertEquals(++numOfFields, fields.size());

        //Remove all
        form.removeFilledFIeld(ffieldA);
        assertEquals(--numOfFields, fields.size());

        form.removeFilledFIeld(ffieldB);
        assertEquals(--numOfFields, fields.size());

        form.removeFilledFIeld(ffieldC);
        assertEquals(--numOfFields, fields.size());

        form.removeFilledFIeld(ffieldD);
        assertEquals(--numOfFields, fields.size());
    }

    @Test
    public void testUniqueFields() {
        FilledField ffieldA = makeFilledField("A");
        FilledField ffieldAA = makeFilledField("A");

        Form form = new Form();
        form.addfilledfield(ffieldA);

        List<FilledField> fields = form.getFilledFields();
        assertEquals(1, fields.size());

        form.addfilledfield(ffieldA);
        assertEquals(1, fields.size());

        form.addfilledfield(ffieldAA);
        assertEquals(1, fields.size());
    }

    @Test
    public void testInitialFields() {
        Form form = new Form();

        List<FilledField> fields = form.getFilledFields();
        assertEquals(0, fields.size());
    }

    @Test
    public void testIsApproved() {
        Form form = new Form();
        assertFalse(form.isApproved());

        Date approvedAt = new Date();
        form.setApprovedAt(approvedAt);
        assertEquals(approvedAt, form.getApprovedAt());

        assertTrue(form.isApproved());
    }

    @Test
    public void testIsFilled() {
        Form form = new Form();
        assertFalse(form.isFilled());

        Date filledAt = new Date();
        form.setFilledAt(filledAt);
        assertEquals(filledAt, form.getFilledAt());

        assertTrue(form.isFilled());
    }

    @Test
    public void testValidate() {
        //@TODO validation messages, test validation fail
        Form form = new Form();

        FilledField fieldA = Mockito.spy(makeFilledField("A"));
        FilledField fieldB = Mockito.spy(makeFilledField("B"));

        form.addfilledfield(fieldA);
        form.addfilledfield(fieldB);

        form.validate();

        Mockito.verify(fieldA).validate();
        Mockito.verify(fieldB).validate();
    }
}
