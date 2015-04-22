package fms.business.service;

import fms.business.form.Admin;
import fms.business.form.Person;
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

@Service
public class PersonService {

    public static final String PERSON_ROOT =  "/persons";

    private Session session;

    private Jcrom jcrom;

    @Autowired
    public PersonService(Session session, Jcrom jcrom) {
        this.session = session;
        this.jcrom = jcrom;
    }

    public void createPerson(Person person) throws Exception {
        Node personsNode = session.getNode(PERSON_ROOT);
        if (personsNode == null) return;
        jcrom.addNode(personsNode, person);
        session.save();
    }

    public Node findPersonNodeById(int id) throws Exception {
        QueryManager queryManager = session.getWorkspace().getQueryManager();
        String queryStr = "/jcr:root" + PERSON_ROOT + "/*[@id='"+ id +"']";
        Query query = queryManager.createQuery(queryStr, Query.XPATH);
        QueryResult queryResult = query.execute();

        NodeIterator it =  queryResult.getNodes();
        if (!it.hasNext()) {
            return null;
        }

        return it.nextNode();
    }

    public Person findPersonById(int id) throws Exception {
        Node personNode = findPersonNodeById(id);
        if (personNode == null) return null;
        return jcrom.fromNode(Person.class, personNode);
    }

    public Admin findAdminById(int id) throws Exception {
        Person p = findPersonById(id);
        if (p instanceof Admin) {
            return (Admin)p;
        }
        return null;
    }

    public void removePerson(Person person) throws Exception {
        Node personNode = session.getNode(jcrom.getPath(person));
        personNode.remove();
        session.save();
    }

    public void updatePerson(Person person) throws Exception {
        removePerson(person);
        createPerson(person);
    }

    public List<Person> getAllPersons() throws Exception {
        List<Person> persons = new ArrayList<Person>();

        NodeIterator it = session.getNode(PERSON_ROOT).getNodes();
        while (it.hasNext()) {
            Person p = jcrom.fromNode(Person.class, it.nextNode());
            persons.add(p);
        }

        return persons;
    }

    public List<Admin> getAllAdmins() throws Exception {
        List<Admin> persons = new ArrayList<Admin>();

        NodeIterator it = session.getNode(PERSON_ROOT).getNodes();
        while (it.hasNext()) {
            Person p = jcrom.fromNode(Person.class, it.nextNode());
            if (p instanceof Admin) persons.add((Admin)p);
        }

        return persons;
    }
}
