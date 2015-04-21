package fms.business.service;

import fms.business.archetype.Archetype;
import fms.business.archetype.Template;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/component.xml" })
public abstract class ServiceTest {

    @Autowired
    private Session session;

    public void resetWorkspace() throws Exception {

        Node rootN = session.getRootNode();

        try {
            session.getNode(ArchetypeService.ARCHETYPES_ROOT).remove();
            session.getNode(FieldService.FIELDS_ROOT).remove();
            session.getNode(TemplateService.TEMPLATES_ROOT).remove();
            session.getNode(ValidatorService.VALIDATOR_ROOT).remove();
            session.getNode(FieldTypeService.FIELD_TYPE_ROOT).remove();
        } catch (Exception e) {}

        rootN.addNode(FieldService.FIELDS_ROOT);
        rootN.addNode(ArchetypeService.ARCHETYPES_ROOT);
        rootN.addNode(TemplateService.TEMPLATES_ROOT);
        rootN.addNode(ValidatorService.VALIDATOR_ROOT);
        rootN.addNode(FieldTypeService.FIELD_TYPE_ROOT);
        session.save();
    }

}
