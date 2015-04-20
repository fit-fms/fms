package fms.business.service;

import fms.business.archetype.Archetype;
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
 * Sluuba pro uchovavani archetypu v databazi.
 *
 */
@Service
public class ArchetypeService {

    private static final String ARCHETYPES_ROOT = "/archetypes";

    private Session session;

    private Jcrom jcrom;

    Map<String, Archetype> archetypes = new HashMap<String, Archetype>();

    @Autowired
    public ArchetypeService(Session session, Jcrom jcrom) {
        this.session = session;
        this.jcrom = jcrom;
    }


    /**
     * Najde v databazi archetyp podle jmena.
     *
     * @param name
     */
    public Archetype findByName(String name) throws Exception {
        QueryManager queryManager = session.getWorkspace().getQueryManager();
        String queryStr = "/jcr:root" + ARCHETYPES_ROOT + "/*[@name='"+ name +"']";
        Query query = queryManager.createQuery(queryStr, Query.XPATH);
        QueryResult queryResult = query.execute();

        NodeIterator it =  queryResult.getNodes();
        if (!it.hasNext()) {
            return null;
        }

        return jcrom.fromNode(Archetype.class, it.nextNode());
    }

    /**
     * Odstrani archetyp z databaze.
     *
     * @param archetype
     */
    public void removeArchetype(Archetype archetype) throws Exception {
        Node archetypeNode = session.getNode(jcrom.getPath(archetype));
        archetypeNode.remove();
        session.save();
    }

    /**
     * Prida novy archetyp do databaze.
     *
     * @param archetype
     */
    public void createArchetype(Archetype archetype) throws Exception {
        Node archetypesNode = session.getNode(ARCHETYPES_ROOT);
        jcrom.addNode(archetypesNode, archetype);
        session.save();
    }

    /**
     * Aktualizuje archetyp v databazi.
     *
     * @param archetype
     */
    public void updateArchetype(Archetype archetype) throws Exception {
        Node archetypeNode = session.getNode(jcrom.getPath(archetype));
        jcrom.updateNode(archetypeNode, archetype);
        session.save();
    }

    /**
     * Ziska vsechny formulare.
     */
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