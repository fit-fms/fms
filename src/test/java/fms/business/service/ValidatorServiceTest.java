package fms.business.service;

import fms.business.archetype.Validator;
import fms.business.fieldtype.FieldType;
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

    @Autowired
    private FieldTypeService fieldTypeService;

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

    @Test
    public void testRemove() throws Exception {
        String name = "my_field_sname";

        Validator validator = new Validator();
        validator.setName(name);

        validatorService.createValidator(validator);
        validatorService.removeValidator(validator);

        Validator validatorB = validatorService.getValidatorByName(name);
        assertNull(validatorB);
    }

    @Test
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

        Validator validatorNo = validatorService.getValidatorByName(name);
        assertNull(validatorNo);
    }

    @Test
    public void testGetAll() throws Exception {

        int numOFValidators = 3;
        String nameA = "A";
        String nameB = "B";
        String nameC = "C";

        FieldType f = new FieldType();
        f.setName("f_names");
        fieldTypeService.createFieldType(f);

        Validator validatorA = new Validator();
        validatorA.setName(nameA);
        validatorA.setFieldType(f);

        Validator validatorB = new Validator();
        validatorB.setName(nameB);
        validatorB.setFieldType(f);

        Validator validatorC = new Validator();
        validatorC.setName(nameC);
        validatorC.setFieldType(f);

        validatorService.createValidator(validatorA);
        validatorService.createValidator(validatorB);
        validatorService.createValidator(validatorC);


        List<Validator> validators = validatorService.getValidatorsByFieldType(f);
        assertEquals(numOFValidators, validators.size());


        validatorService.removeValidator(validatorB);
        List<Validator> validatorsB = validatorService.getValidatorsByFieldType(f);
        assertEquals(--numOFValidators, validatorsB.size());
    }
}