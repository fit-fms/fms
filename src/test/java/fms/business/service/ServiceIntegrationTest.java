package fms.business.service;


import fms.business.archetype.Archetype;
import fms.business.archetype.Field;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jcr.Node;
import javax.jcr.Session;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/component.xml" })
public class ServiceIntegrationTest {
    @Autowired
    private ArchetypeService archetypeService;

    @Autowired
    private FieldService fieldService;

    @Autowired
    private Session session;

    public void resetWorkspace() throws Exception {
        try {
            Node n = session.getNode("/fields");
            n.remove();

            Node a = session.getNode("/archetypes");
            a.remove();

            session.save();
        }
        catch (Exception e) {}

        session.refresh(true);
        session.getRootNode().addNode("/fields");
        session.getRootNode().addNode("/archetypes");
        session.save();
    }

    private  final String  fieldName = "field_name";
    private final String archetypeName = "archetype_name";

    @Test
    public void AcreateArchetype() throws Exception {
        resetWorkspace();
        Field field  = new Field();
        field.setName(fieldName);
        fieldService.createField(field);

        Archetype archetype = new Archetype();
        archetype.setName(archetypeName);
        archetypeService.createArchetype(archetype);

        archetype.addOptionalField(field);
        archetypeService.updateArchetype(archetype);
    }

    @Test
    public void BgetArchetype() throws Exception {
        Field field = fieldService.getFieldByName(fieldName);
        assertNotNull(field);

        Archetype archetype = archetypeService.findByName(archetypeName);
        assertNotNull(archetype);

        Map<String, Field> fields = archetype.getOptionalFields();
        assertNotNull(fields);
        assertEquals(1, fields.size());
        assertNotNull(fields.get(fieldName));
    }
}
