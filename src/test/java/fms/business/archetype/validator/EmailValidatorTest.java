package fms.business.archetype.validator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class EmailValidatorTest {

    private Validator validator = new EmailValidator();

    @Test
    public void testValidate() throws Exception {
        assertTrue(validator.validate("email@email.cz", null));
        assertFalse(validator.validate("email@email", null));
        assertFalse(validator.validate("email.email", null));
        assertFalse(validator.validate("@email.email", null));
        assertFalse(validator.validate("email", null));
        assertFalse(validator.validate("This is not a @ valid email!", null));
    }

    @Test
    public void testNoError() throws Exception {
        List<String> errors = new ArrayList<String>();
        assertTrue(validator.validate("hermione.granger@hogwarts.uk", errors));
        assertEquals(0, errors.size());
    }

    @Test
    public void testError() throws Exception {
        List<String> errors = new ArrayList<String>();
        assertFalse(validator.validate("harry.potter@hogwarts", errors));
        assertEquals(1, errors.size());
    }
}