package fms.business.service;

import fms.business.archetype.Field;
import fms.business.archetype.Validator;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;


public class ValidatorServiceTest extends ServiceTest {

    @Before
    public void setUp() throws Exception {
        resetWorkspace();
    }

    @Autowired
    private ValidatorService validatorService;

    @Test
    public void testCreate() throws Exception {
        String name = "my_field_sname";

        Validator validator = new Validator();
        validator.setName(name);

        validatorService.createValidator(validator);

        Validator validatorA = validatorService.getValidatorByName(name);

        assertNotNull(validatorA);
        assertEquals(validator.getName(), validatorA.getName());
    }

    @Test(expected = javax.jcr.PathNotFoundException.class)
    public void testUpdate() throws Exception {
        String name = "my_field_name";
        String newName = "my_new_field_name";

        Validator validator = new Validator();
        validator.setName(name);

        validatorService.createValidator(validator);
        validator.setName(newName);
        validatorService.updateValidator(validator);

        Validator validatorB = validatorService.getValidatorByName(newName);
        assertNotNull(validatorB);
        assertEquals(newName, validatorB.getName());

        validatorService.getValidatorByName(name);
        assertFalse("Stary uzel byl nalezen.", true);
    }

    @Test
    public void testGetAll() throws Exception {

        int numOFValidators = 3;
        String nameA = "A";
        String nameB = "B";
        String nameC = "C";

        Validator validatorA = new Validator();
        validatorA.setName(nameA);

        Validator validatorB = new Validator();
        validatorB.setName(nameB);

        Validator validatorC = new Validator();
        validatorC.setName(nameC);

        validatorService.createValidator(validatorA);
        validatorService.createValidator(validatorB);
        validatorService.createValidator(validatorC);


        List<Validator> validators = validatorService.getValidatorsByFieldType(null);
        assertEquals(numOFValidators, validators.size());


        validatorService.removeValidator(validatorB);
        List<Validator> validatorsB = validatorService.getValidatorsByFieldType(null);
        assertSame(validators, validatorsB);
        assertEquals(--numOFValidators, validators.size());
    }
}