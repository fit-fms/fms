package fms.business.service;

import fms.business.archetype.Field;
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
 * Slu�ba pro uchov�v�n� archetypu v datab�zi.
 */
@Service
public class FieldService {

    public static final String FIELDS_ROOT = "/fields";
    Map<String, Field> fields = new HashMap<String, Field>();
    private Session session;
    private Jcrom jcrom;
    ;

    @Autowired
    public FieldService(Session session, Jcrom jcrom) {
        this.session = session;
        this.jcrom = jcrom;
    }


    /**
     * Z�sk� pol�cko podle jm�na.
     *
     * @param name
     */
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
     * Vytvor� nov� pol�cko v datab�zi.
     *
     * @param field
     */
    public Field createField(Field field) throws Exception {
        Node fieldsNode = session.getNode(FIELDS_ROOT);

        jcrom.addNode(fieldsNode, field);
        session.save();

        return field;
    }

    /**
     * Aktualizuje pol�cko v datab�zi.
     *
     * @param field
     */
    public void updateField(Field field) throws Exception {
        Node fieldNode = session.getNode(jcrom.getPath(field));
        jcrom.updateNode(fieldNode, field);
        session.save();
    }

    /**
     * Odstran� pol�cko z datab�ze.
     *
     * @param field
     */
    public void removeField(Field field) throws Exception {
        Node fieldNode = session.getNode(jcrom.getPath(field));
        fieldNode.remove();
        session.save();
    }

    /**
     * Z�sk� v�echna pol�cka z datab�ze.
     */
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