package fms.jcr;

import fms.business.archetype.Archetype;
import fms.business.archetype.Field;
import fms.business.fieldtype.FieldType;
import fms.business.fieldtype.TextField;
import fms.business.service.*;
import fms.business.service.jcr.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jcr.Node;
import javax.jcr.Session;

@Component
public class InitDB {
    
    private FieldTypeService fieldTypeService;
    private FieldService fieldService;
    private ArchetypeService archetypeService;
    

    protected Session session;

    @Autowired
    public InitDB(Session session, FieldTypeService fieldTypeService, FieldService fieldService, ArchetypeService archetypeService) {
        this.session = session;
        this.fieldTypeService = fieldTypeService;
        this.fieldService = fieldService;
        this.archetypeService = archetypeService;
    }

    public void init() throws Exception {
        initStructure();
        initData();
    }

    public void initData() throws Exception {
        FieldType fieldType = new TextField();
        fieldType.setName("text");
        fieldTypeService.createFieldType(fieldType);
        
        Field field  = new Field();
        field.setName("email");
        field.setLabel("Your email:");
        field.setType(fieldType);
        fieldService.createField(field);
        
        Archetype archetype = new Archetype();
        archetype.setName("emailArchetype");
        archetype.addOptionalField(field);
        archetypeService.createArchetype(archetype);
        
        session.save();
    }

    public void initStructure() throws Exception {
        Node rootN = session.getRootNode();

        try {
            session.getNode(JcrArchetypeService.ARCHETYPES_ROOT).remove();
            session.getNode(JcrFieldService.FIELDS_ROOT).remove();
            session.getNode(JcrTemplateService.TEMPLATES_ROOT).remove();
            session.getNode(JcrValidatorService.VALIDATOR_ROOT).remove();
            session.getNode(JcrFieldTypeService.FIELD_TYPE_ROOT).remove();
            session.getNode(JcrPersonService.PERSON_ROOT).remove();
        } catch (Exception e) {}

        rootN.addNode(JcrFieldService.FIELDS_ROOT);
        rootN.addNode(JcrArchetypeService.ARCHETYPES_ROOT);
        rootN.addNode(JcrTemplateService.TEMPLATES_ROOT);
        rootN.addNode(JcrValidatorService.VALIDATOR_ROOT);
        rootN.addNode(JcrFieldTypeService.FIELD_TYPE_ROOT);
        rootN.addNode(JcrPersonService.PERSON_ROOT);
        session.save();
    }
}
