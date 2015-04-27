package fms.business.service;

import fms.business.archetype.Archetype;
import fms.business.form.Form;
import org.jcrom.Jcrom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;
import java.util.HashMap;
import java.util.Map;

/**
 * Slu�ba pro uchov�v�n� formul�ru v datab�zi.
 *
 * @author jinora
 * @version 1.0
 * @created 15-Apr-2015 12:39:48 PM
 */
@Service
public class FormService {

    private static final String ARCHETYPE_FORMS = "forms";

    private Session session;

    private Jcrom jcrom;

    @Autowired
    public FormService(Session session, Jcrom jcrom) {
        this.session = session;
        this.jcrom = jcrom;
    }


    /**
     * Vytvor� nov� formul�r v datab�zi.
     *
     * @param form
     */
    public void createForm(Form form) throws Exception {
        Node archetypeNode = session.getNode(jcrom.getPath(form.getArchetype()));
        if (archetypeNode == null) return;

        Node formsNode;
        if (!archetypeNode.hasNode(ARCHETYPE_FORMS)) {
            formsNode = archetypeNode.addNode(ARCHETYPE_FORMS);
        } else {
            formsNode = archetypeNode.getNode(ARCHETYPE_FORMS);
        }

        jcrom.addNode(formsNode, form);
        session.save();
    }

    /**
     * Odstran� formul�r z datab�ze.
     *
     * @param form
     */
    public void removeForm(Form form) throws Exception {
        Node formNode = session.getNode(jcrom.getPath(form));
        formNode.remove();
        session.save();
    }

    /**
     * Najde formul�r podle ID.
     *
     * @param id
     */
    public Form getFormById(Archetype archetype, int id) throws Exception {
        Node formNode = getFormNodeById(archetype, id);

        if (formNode == null) {
            return null;
        }

        return jcrom.fromNode(Form.class, formNode);
    }

    public Node getFormNodeById(Archetype archetype, int id) throws Exception {
        Node archetypeNode = session.getNode(jcrom.getPath(archetype));

        Node formsNode;
        if (!archetypeNode.hasNode(ARCHETYPE_FORMS)) {
            formsNode = archetypeNode.addNode(ARCHETYPE_FORMS);
        } else {
            formsNode = archetypeNode.getNode(ARCHETYPE_FORMS);
        }

        Node formNode = null;
        NodeIterator it = formsNode.getNodes();
        while (it.hasNext()) {
            Node n = it.nextNode();
            if (n.hasProperty("id") && n.getProperty("id").getLong() == id) {
                formNode = n;
                break;
            }
        }

        return formNode;
    }

    /**
     * Z�sk� v�echny formul�re z datab�ze.
     */
    public Map<Integer, Form> getAllForms(Archetype archetype) throws Exception {
        Map<Integer, Form> allForms = new HashMap<Integer, Form>();

        Node archetypeNode = session.getNode(jcrom.getPath(archetype));
        Node formsNode;
        if (!archetypeNode.hasNode(ARCHETYPE_FORMS)) {
            formsNode = archetypeNode.addNode(ARCHETYPE_FORMS);
        } else {
            formsNode = archetypeNode.getNode(ARCHETYPE_FORMS);
        }

        NodeIterator it = formsNode.getNodes();
        while (it.hasNext()) {
            Form f = jcrom.fromNode(Form.class, it.nextNode());
            allForms.put(f.getId(), f);
        }

        return allForms;
    }

    /**
     * Aktualizuje formul�r v datab�zi.
     *
     * @param form
     */
    public void updateForm(Form form) throws Exception {
        removeForm(form);
        createForm(form);
    }

}