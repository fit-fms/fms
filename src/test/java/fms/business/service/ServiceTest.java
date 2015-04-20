package fms.business.service;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jcr.Node;
import javax.jcr.Session;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/component.xml" })
public abstract class ServiceTest {

    @Autowired
    private Session session;

    public void resetWorkspace() throws Exception {
        try {
            Node n = session.getNode("/fields");
            n.remove();

            Node a = session.getNode("/archetypes");
            a.remove();

            Node f = session.getNode("/forms");
            f.remove();

            Node t = session.getNode("/templates");
            t.remove();

            session.save();
        }
        catch (Exception e) {}

        session.getRootNode().addNode("/fields");
        session.getRootNode().addNode("/archetypes");
        session.getRootNode().addNode("/forms");
        session.getRootNode().addNode("/templates");
        session.save();
    }

}
