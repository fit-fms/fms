package fms.business.service;

import fms.business.archetype.Template;
import org.jcrom.Jcrom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;
import java.util.ArrayList;
import java.util.List;

/**
 * Slu�ba pro uchov�v�n� formul�ru v datab�zi.
 *
 * @author jinora
 * @version 1.0
 * @created 15-Apr-2015 12:39:49 PM
 */
@Service
public class TemplateService {

    private Session session;

    private Jcrom jcrom;

    private List<Template> templates;

    @Autowired
    public TemplateService(Session session, Jcrom jcrom) {
        this.session = session;
        this.jcrom = jcrom;
    }


    /**
     * Vytvor� novou templatu v datab�zi.
     *
     * @param template
     */
    public void createTemplate(Template template) throws Exception {
        Node templatesNode = session.getNode("/templates");
        jcrom.addNode(templatesNode, template);
        session.save();
    }

    public Template getTemplateByName(String name) throws Exception {
        Node templateNode = session.getNode("/templates/" + name);
        Template template = jcrom.fromNode(Template.class, templateNode);

        return template;
    }
    /**
     * Aktualizuje templatu v datab�zi.
     *
     * @param template
     */
    public void updateTemplate(Template template) throws Exception {
        removeTemplate(template);
        createTemplate(template);
    }

    /**
     * Odstran� templatu z datab�ze.
     *
     * @param template
     */
    public void removeTemplate(Template template) throws Exception {
        Node templateNode = session.getNode("/templates/" + template.getName());
        templateNode.remove();
    }

    /**
     * Z�sk� v�echny templaty v datab�zi pro archetyp.
     *
     * @param archwetypName
     */
    public List<Template> getAllTemplates(String archwetypName) throws Exception {

        if (templates == null) {
            templates = new ArrayList<Template>();
        }
        else {
            templates.clear();
        }

        NodeIterator it = session.getNode("/templates").getNodes();
        while (it.hasNext()) {
            Node tn = it.nextNode();
            Template t = jcrom.fromNode(Template.class, tn);
            templates.add(t);
        }

        return templates;
    }

}