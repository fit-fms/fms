package fms.business.form;

import fms.business.archetype.Field;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;


public class FilledFieldTest {

    @Test
    public void testValidate() {
        //@TODO error messages
        Field field = Mockito.mock(Field.class);

        FilledField filledField = new FilledField();
        filledField.setField(field);

        String data = "data";
        filledField.setData(data);

        filledField.validate(null);

        Mockito.verify(field).validate(data, null);
    }
}