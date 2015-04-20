package fms.business.service;


import fms.business.archetype.Field;
import fms.business.fieldtype.FieldType;
import org.jcrom.Jcrom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;
import java.util.ArrayList;
import java.util.List;

@Service
public class FieldTypeService {

    private Session session;

    private Jcrom jcrom;

    private List<FieldType> fieldTypes;

    @Autowired
    public FieldTypeService(Session session, Jcrom jcrom) {
        this.session = session;
        this.jcrom = jcrom;
    }

    public void createFieldType(FieldType fieldType) throws Exception {
        Node fieldTypes = session.getNode("/fieldTypes");
        jcrom.addNode(fieldTypes, fieldType);
        session.save();
    }

    public void removeFieldType(FieldType fieldType) throws Exception {
        Node fieldTypeNode = session.getNode("/fieldTypes/" + fieldType.getName());
        fieldTypeNode.remove();
        session.save();
    }

    public FieldType getByName(String name) throws Exception{
        Node fieldNode = session.getNode("/fieldTypes/" + name);
        FieldType fieldType = jcrom.fromNode(FieldType.class, fieldNode);
        return fieldType;
    }

    public void updateFieldType(FieldType fieldType) throws Exception {
        removeFieldType(fieldType);
        createFieldType(fieldType);
    }

    public List<FieldType> getFieldTypes() throws Exception {
        if (fieldTypes == null) {
            fieldTypes = new ArrayList<FieldType>();
        }
        else {
            fieldTypes.clear();
        }

        NodeIterator it = session.getNode("/fieldTypes").getNodes();
        while(it.hasNext()) {
            Node fn = it.nextNode();
            FieldType f = jcrom.fromNode(FieldType.class, fn);
            fieldTypes.add(f);
        }

        return fieldTypes;
    }
}
