package fms.business.service.jcr;

import fms.business.archetype.template.Template;
import fms.business.service.TemplateService;
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

/**
 * @inheritDoc
 */
@Service
public class JcrTemplateService implements TemplateService {

    /**
     * Path to templates root
     */
    public static final String TEMPLATES_ROOT = "/templates";

    private Session session;

    private Jcrom jcrom;

    private List<Template> templates = new ArrayList<Template>();

    @Autowired
    public JcrTemplateService(Session session, Jcrom jcrom) {
        this.session = session;
        this.jcrom = jcrom;
    }


    /**
     * @inheritDoc
     */
    @Override
    public void createTemplate(Template template) throws Exception {
        Node templatesNode = session.getNode(TEMPLATES_ROOT);
        jcrom.addNode(templatesNode, template);
        session.save();
    }

    /**
     * @inheritDoc
     */
    @Override
    public Template getTemplateByName(String name) throws Exception {
        QueryManager queryManager = session.getWorkspace().getQueryManager();
        String queryStr = "/jcr:root" + TEMPLATES_ROOT + "/*[@name='" + name + "']";
        Query query = queryManager.createQuery(queryStr, Query.XPATH);
        QueryResult queryResult = query.execute();

        NodeIterator it = queryResult.getNodes();
        if (!it.hasNext()) {
            return null;
        }

        return jcrom.fromNode(Template.class, it.nextNode());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void updateTemplate(Template template) throws Exception {
        Node templateNode = session.getNode(jcrom.getPath(template));
        jcrom.updateNode(templateNode, template);
        session.save();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void removeTemplate(Template template) throws Exception {
        Node templateNode = session.getNode(jcrom.getPath(template));
        templateNode.remove();
        session.save();
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<Template> getAllTemplates(String archwetypName) throws Exception {
        templates.clear();

        NodeIterator it = session.getNode(TEMPLATES_ROOT).getNodes();
        while (it.hasNext()) {
            Template t = jcrom.fromNode(Template.class, it.nextNode());
            templates.add(t);
        }

        return templates;
    }

}