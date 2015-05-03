/**
 * Implementation of service interfaces via JCR
 */
package fms.business.service.jcr;

import fms.business.archetype.Archetype;
import fms.business.service.ArchetypeService;
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
public class JcrArchetypeService implements ArchetypeService {

    /**
     * Path to archetypes root
     */
    public static final String ARCHETYPES_ROOT = "//archetypes";

    private Map<String, Archetype> archetypes = new HashMap<String, Archetype>();
    private Session session;
    private Jcrom jcrom;

    @Autowired
    public JcrArchetypeService(Session session, Jcrom jcrom) {
        this.session = session;
        this.jcrom = jcrom;
    }


    /**
     * @inheritDoc
     */
    @Override
    public Archetype findByName(String name) throws Exception {
        Node n = this.findNodeByName(name);
        if (n == null) return null;

        return jcrom.fromNode(Archetype.class, n);
    }

    protected Node findNodeByName(String name) throws Exception {
        QueryManager queryManager = session.getWorkspace().getQueryManager();
        String queryStr = "/jcr:root" + ARCHETYPES_ROOT + "/*[@name='" + name + "']";
        Query query = queryManager.createQuery(queryStr, Query.XPATH);
        QueryResult queryResult = query.execute();

        NodeIterator it = queryResult.getNodes();
        if (!it.hasNext()) {
            return null;
        }

        return it.nextNode();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void removeArchetype(Archetype archetype) throws Exception {
        Node archetypeNode = session.getNode(jcrom.getPath(archetype));
        archetypeNode.remove();
        session.save();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void createArchetype(Archetype archetype) throws Exception {
        Node archetypesNode = session.getNode(ARCHETYPES_ROOT);
        jcrom.addNode(archetypesNode, archetype);
        session.save();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void updateArchetype(Archetype archetype) throws Exception {
        Node archetypeNode = session.getNode(jcrom.getPath(archetype));
        jcrom.updateNode(archetypeNode, archetype);
        session.save();
    }

    /**
     * @inheritDoc
     */
    @Override
    public Map<String, Archetype> getAllArchetypes() throws Exception {
        archetypes.clear();

        NodeIterator it = session.getNode(ARCHETYPES_ROOT).getNodes();
        while (it.hasNext()) {
            Archetype a = jcrom.fromNode(Archetype.class, it.nextNode());
            archetypes.put(a.getName(), a);
        }

        return archetypes;
    }

}