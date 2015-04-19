package fms.business.service;

import fms.business.archetype.Archetype;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Map;

import static org.junit.Assert.*;


public class ArchetypeServiceTest extends ServiceTest {

    @Autowired
    private ArchetypeService archetypeService;

    @Test
    public void testCreate() throws Exception {
        String name = "my_field_sname";

        Archetype archetype = new Archetype();
        archetype.setName(name);

        archetypeService.createArchetype(archetype);

        Archetype archetypeA = archetypeService.findByName(name);

        assertNotNull(archetypeA);
        assertEquals(archetype.getName(), archetypeA.getName());
    }

    @Test(expected = javax.jcr.PathNotFoundException.class)
    public void testUpdate() throws Exception {
        String name = "my_field_name";
        String newName = "my_new_field_name";

        Archetype archetype = new Archetype();
        archetype.setName(name);

        archetypeService.createArchetype(archetype);
        archetype.setName(newName);
        archetypeService.updateArchetype(archetype);

        Archetype archetypeA = archetypeService.findByName(newName);
        assertNotNull(archetypeA);
        assertEquals(newName, archetypeA.getName());

        archetypeService.findByName(name);
        fail("Stary uzel byl nalezen.");
    }

    @Test
    public void testFind() throws Exception {

        int numOfFields = 3;
        String nameA = "A";
        String nameB = "B";
        String nameC = "C";

        Archetype archetypeA = new Archetype();
        archetypeA.setName(nameA);

        Archetype archetypeB = new Archetype();
        archetypeB.setName(nameB);

        Archetype archetypeC = new Archetype();
        archetypeC.setName(nameC);

        archetypeService.createArchetype(archetypeA);
        archetypeService.createArchetype(archetypeB);
        archetypeService.createArchetype(archetypeC);


        Map<String, Archetype> fields = archetypeService.getAllArchetypes();
        assertNotNull(fields);
        assertEquals(numOfFields, fields.size());


        archetypeService.removeArchetype(archetypeB);
        Map<String, Archetype> fieldsB = archetypeService.getAllArchetypes();
        assertNotNull(fieldsB);
        assertSame(fields, fieldsB);
        assertEquals(--numOfFields, fields.size());
    }
}