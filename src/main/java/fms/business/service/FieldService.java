package fms.business.service;

import fms.business.archetype.Field;
import org.jcrom.Jcrom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.jcr.NodeIterator;

import javax.jcr.Node;
import javax.jcr.Session;
import java.util.HashMap;
import java.util.Map;

/**
 * Slu�ba pro uchov�v�n� archetypu v datab�zi.
 *
 * @author jinora
 * @version 1.0
 * @created 15-Apr-2015 12:39:48 PM
 */
@Service
public class FieldService {


    private Session session;

    private Jcrom jcrom;

    Map<String, Field> fields;

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
        Node fieldNode = session.getNode("/fields/" + name);
        return jcrom.fromNode(Field.class, fieldNode);
    }

    /**
     * Vytvor� nov� pol�cko v datab�zi.
     *
     * @param field
     */
    public Field createField(Field field) throws Exception {

        Node root = session.getRootNode();
        root.addNode("fields");
        Node fieldsNode = session.getNode("/fields");

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
        removeField(field);
        createField(field);
    }

    /**
     * Odstran� pol�cko z datab�ze.
     *
     * @param field
     */
    public void removeField(Field field) throws Exception {
        Node fieldNode = session.getNode(field.getJcrPath());
        fieldNode.remove();
        session.save();
    }

    /**
     * Z�sk� v�echna pol�cka z datab�ze.
     */
    public Map<String, Field> getAllFields() throws Exception {

        if (fields == null) {
            fields = new HashMap<String, Field>();
        }
        else {
            fields.clear();
        }

        Node fieldsNode = session.getNode("/fields");
        NodeIterator it = fieldsNode.getNodes();

        while (it.hasNext()) {
            Node n = it.nextNode();
            Field f = jcrom.fromNode(Field.class, n);
            fields.put(f.getName(), f);
        }

        return fields;
    }

}