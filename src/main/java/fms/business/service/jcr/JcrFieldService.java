package fms.business.service.jcr;

import fms.business.archetype.Field;
import fms.business.service.FieldService;
import org.jcrom.Jcrom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;
import java.util.HashMap;
import java.util.Map;

/**
 * @inheritDoc
 */
@Service
public class JcrFieldService implements FieldService {

    /**
     * Path to fields root
     */
    public static final String FIELDS_ROOT = "/fields";

    private Map<String, Field> fields = new HashMap<String, Field>();
    private Session session;
    private Jcrom jcrom;
    ;

    @Autowired
    public JcrFieldService(Session session, Jcrom jcrom) {
        this.session = session;
        this.jcrom = jcrom;
    }


    /**
     * @inheritDoc
     */
    @Override
    public Field getFieldByName(String name) throws Exception {
        QueryManager queryManager = session.getWorkspace().getQueryManager();
        String queryStr = "/jcr:root" + FIELDS_ROOT + "/*[@name='" + name + "']";
        Query query = queryManager.createQuery(queryStr, Query.XPATH);
        QueryResult queryResult = query.execute();

        NodeIterator it = queryResult.getNodes();
        if (!it.hasNext()) {
            return null;
        }

        return jcrom.fromNode(Field.class, it.nextNode());
    }

    /**
     * @inheritDoc
     */
    @Override
    public Field createField(Field field) throws Exception {
        Node fieldsNode = session.getNode(FIELDS_ROOT);

        jcrom.addNode(fieldsNode, field);
        session.save();

        return field;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void updateField(Field field) throws Exception {
        Node fieldNode = session.getNode(jcrom.getPath(field));
        jcrom.updateNode(fieldNode, field);
        session.save();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void removeField(Field field) throws Exception {
        Node fieldNode = session.getNode(jcrom.getPath(field));
        fieldNode.remove();
        session.save();
    }

    /**
     * @inheritDoc
     */
    @Override
    public Map<String, Field> getAllFields() throws Exception {
        fields.clear();

        NodeIterator it = session.getNode(FIELDS_ROOT).getNodes();
        while (it.hasNext()) {
            Field f = jcrom.fromNode(Field.class, it.nextNode());
            fields.put(f.getName(), f);
        }

        return fields;
    }

}