package fms.business.service;

import fms.business.archetype.Archetype;
import fms.business.archetype.Field;
import org.jcrom.Jcrom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
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
public class ArchetypeService {

    private Session session;

    private FieldService fieldService;

    private Jcrom jcrom;

    Map<String, Archetype> archetypes;

    @Autowired
    public ArchetypeService(FieldService fieldService, Session session, Jcrom jcrom) {
        this.fieldService = fieldService;
        this.session = session;
        this.jcrom = jcrom;
    }


    /**
     * Najde v datab�zi archetyp podle jm�na.
     *
     * @param name
     */
    public Archetype findByName(String name) throws Exception {
        Node archerypeNode = session.getNode("/archetypes/"+name);
        Archetype archetype = jcrom.fromNode(Archetype.class, archerypeNode);

        return archetype;
    }

    /**
     * Odstran� archetyp z datab�ze.
     *
     * @param archetype
     */
    public void removeArchetype(Archetype archetype) throws Exception {
        Node archetypeNode = session.getNode(archetype.getJcrPath());
        archetypeNode.remove();
        session.save();
    }

    /**
     * Prid� nov� archetyp do datab�ze.
     *
     * @param archetype
     */
    public void createArchetype(Archetype archetype) throws Exception {
        Node archetypesNode = session.getNode("/archetypes");

        jcrom.addNode(archetypesNode, archetype);
        session.save();
    }

    /**
     * Aktualizuje archetyp v datab�zi.
     *
     * @param archetype
     */
    public void updateArchetype(Archetype archetype) throws Exception {
        removeArchetype(archetype);
        createArchetype(archetype);
    }

    /**
     * Z�sk� v�echny formul�re.
     */
    public Map<String, Archetype> getAllArchetypes() throws Exception {

        if (archetypes == null) {
            archetypes = new HashMap<String, Archetype>();
        }
        else  {
            archetypes.clear();
        }

        NodeIterator it = session.getNode("/archetypes").getNodes();
        while (it.hasNext()) {
            Node n = it.nextNode();
            Archetype a = jcrom.fromNode(Archetype.class, n);
            archetypes.put(a.getName(), a);
        }

        return archetypes;
    }

}