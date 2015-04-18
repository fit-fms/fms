package fms.modeshape;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import javax.jcr.Repository;
import javax.jcr.Session;


@Component
public class ModeShapeSessionFactory implements FactoryBean<Session> {

    @Autowired
    private Repository repository;
    private Session session;

    @Override
    public Session getObject() throws Exception {
        if (session == null) {
            session = repository.login();
        }
        return session;
    }

    @Override
    public Class<?> getObjectType() {
        return Session.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @PreDestroy
    public void logout() throws Exception {
        session.logout();
        session = null;
    }
}