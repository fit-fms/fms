package fms.business.service;

import fms.Application;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jcr.Node;
import javax.jcr.Session;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
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
            session.getNode(PersonService.PERSON_ROOT).remove();
        } catch (Exception e) {}

        rootN.addNode(FieldService.FIELDS_ROOT);
        rootN.addNode(ArchetypeService.ARCHETYPES_ROOT);
        rootN.addNode(TemplateService.TEMPLATES_ROOT);
        rootN.addNode(ValidatorService.VALIDATOR_ROOT);
        rootN.addNode(FieldTypeService.FIELD_TYPE_ROOT);
        rootN.addNode(PersonService.PERSON_ROOT);
        session.save();
    }

}
