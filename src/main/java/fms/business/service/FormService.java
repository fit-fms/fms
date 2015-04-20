package fms.business.service;

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

    private Session session;

    private Jcrom jcrom;

    private Map<Integer, Form> allForms;

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
        Node formsNode = session.getNode("/forms");
        jcrom.addNode(formsNode, form);

        session.save();
    }

    /**
     * Odstran� formul�r z datab�ze.
     *
     * @param form
     */
    public void removeForm(Form form) throws Exception {
        Node formNode = session.getNode("/forms/" + form.getId());
        formNode.remove();
        session.save();
    }

    /**
     * Najde formul�r podle ID.
     *
     * @param id
     */
    public Form getFormById(int id) throws Exception {
        Node formNode = session.getNode("/forms/" + id);
        Form form = jcrom.fromNode(Form.class, formNode);
        return form;
    }

    /**
     * Z�sk� v�echny formul�re z datab�ze.
     */
    public Map<Integer, Form> getAllForms() throws Exception {
        if (allForms == null) {
            allForms = new HashMap<Integer, Form>();
        }
        else {
            allForms.clear();
        }

        NodeIterator it = session.getNode("/forms").getNodes();
        while (it.hasNext()) {
            Node fn = it.nextNode();
            Form f = jcrom.fromNode(Form.class, fn);
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