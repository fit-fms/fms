package fms.jcr;

import fms.business.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.jcr.Node;
import javax.jcr.Session;

@Component
public class InitDB {


    protected Session session;

    @Autowired
    public InitDB(Session session) {
        this.session = session;
    }

    public void init() throws Exception {
        initStructure();
        initData();
    }

    public void initData() throws Exception {
        session.save();
    }

    public void initStructure() throws Exception {
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
