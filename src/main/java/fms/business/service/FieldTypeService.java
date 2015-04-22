package fms.business.service;

import fms.business.fieldtype.FieldType;
import org.jcrom.Jcrom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;
import java.util.ArrayList;
import java.util.List;

@Service
public class FieldTypeService {

    public static final String FIELD_TYPE_ROOT = "/fieldTypes";

    private Session session;

    private Jcrom jcrom;

    private List<FieldType> fieldTypes = new ArrayList<FieldType>();

    @Autowired
    public FieldTypeService(Session session, Jcrom jcrom) {
        this.session = session;
        this.jcrom = jcrom;
    }

    public void createFieldType(FieldType fieldType) throws Exception {
        Node fieldTypes = session.getNode(FIELD_TYPE_ROOT);
        jcrom.addNode(fieldTypes, fieldType);
        session.save();
    }

    public void removeFieldType(FieldType fieldType) throws Exception {
        Node fieldTypeNode = session.getNode(jcrom.getPath(fieldType));
        fieldTypeNode.remove();
        session.save();
    }

    public FieldType getByName(String name) throws Exception {
        QueryManager queryManager = session.getWorkspace().getQueryManager();
        String queryStr = "/jcr:root" + FIELD_TYPE_ROOT + "/*[@name='" + name + "']";
        Query query = queryManager.createQuery(queryStr, Query.XPATH);
        QueryResult queryResult = query.execute();

        NodeIterator it = queryResult.getNodes();
        if (!it.hasNext()) {
            return null;
        }

        return jcrom.fromNode(FieldType.class, it.nextNode());
    }

    public void updateFieldType(FieldType fieldType) throws Exception {
        Node fieldTypeNode = session.getNode(jcrom.getPath(fieldType));
        jcrom.updateNode(fieldTypeNode, fieldType);
        session.save();
    }

    public List<FieldType> getFieldTypes() throws Exception {
        fieldTypes.clear();

        NodeIterator it = session.getNode(FIELD_TYPE_ROOT).getNodes();
        while (it.hasNext()) {
            FieldType f = jcrom.fromNode(FieldType.class, it.nextNode());
            fieldTypes.add(f);
        }

        return fieldTypes;
    }
}
