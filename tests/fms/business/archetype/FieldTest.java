package fms.business.archetype;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.Map;

import static org.junit.Assert.*;


public class FieldTest {

    protected Archetype makeArchetype(String name) {
        Archetype archetype = new Archetype();
        archetype.setName(name);

        return archetype;
    }

    protected Validator makeValidator(String name) {
        Validator validator = new Validator();
        validator.setName(name);

        return validator;
    }

    @Test
    public void testAddingAndRemovingArchetypes() {
        int numOfArchetypes = 4;
        Archetype archetypeA = makeArchetype("A");
        Archetype archetypeB = makeArchetype("B");
        Archetype archetypeC = makeArchetype("C");
        Archetype archetypeD = makeArchetype("D");


        Field field = new Field();
        field.addArchetype(archetypeA);
        field.addArchetype(archetypeB);
        field.addArchetype(archetypeC);
        field.addArchetype(archetypeD);

        Map<String, Archetype> archetypes = field.getArchetypes();
        assertEquals(numOfArchetypes, archetypes.size());


        //Remove and add
        field.removeArchetype(archetypeA);
        assertEquals(--numOfArchetypes, archetypes.size());

        field.addArchetype(archetypeA);
        assertEquals(++numOfArchetypes, archetypes.size());

        //Remove all
        field.removeArchetype(archetypeA);
        assertEquals(--numOfArchetypes, archetypes.size());

        field.removeArchetype(archetypeB);
        assertEquals(--numOfArchetypes, archetypes.size());

        field.removeArchetype(archetypeC);
        assertEquals(--numOfArchetypes, archetypes.size());

        field.removeArchetype(archetypeD);
        assertEquals(--numOfArchetypes, archetypes.size());
    }

    @Test
    public void testAddingAndRemovingValidators() {
        int numOfValidators = 4;
        Validator validatorA = makeValidator("A");
        Validator validatorB = makeValidator("B");
        Validator validatorC = makeValidator("C");
        Validator validatorD = makeValidator("D");


        Field field = new Field();
        field.addValidator(validatorA);
        field.addValidator(validatorB);
        field.addValidator(validatorC);
        field.addValidator(validatorD);

        Map<String, Validator> validators = field.getValidators();
        assertEquals(numOfValidators, validators.size());


        //Remove and add
        field.removeValidator(validatorA);
        assertEquals(--numOfValidators, validators.size());

        field.addValidator(validatorA);
        assertEquals(++numOfValidators, validators.size());

        //Remove all
        field.removeValidator(validatorA);
        assertEquals(--numOfValidators, validators.size());

        field.removeValidator(validatorB);
        assertEquals(--numOfValidators, validators.size());

        field.removeValidator(validatorC);
        assertEquals(--numOfValidators, validators.size());

        field.removeValidator(validatorD);
        assertEquals(--numOfValidators, validators.size());
    }

    @Test
    public void testValidate() {
        Field field = new Field();

        int numOfValidators = 5;
        Validator[] validators = new Validator[numOfValidators];
        for (int i = 0; i < numOfValidators; ++i) {
            validators[i] = Mockito.spy(makeValidator(((Integer) i).toString()));
            field.addValidator(validators[i]);
        }

        String data = "data";
        field.validate(data);

        for (int i = 0; i < numOfValidators; ++i) {
            Mockito.verify(validators[i]).validate(data);
        }
    }
}