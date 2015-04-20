package fms.jcr;


import org.modeshape.jcr.ModeShapeEngine;
import org.modeshape.jcr.RepositoryConfiguration;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import javax.jcr.Repository;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@Component
public class ModeShapeRepositoryFactory implements FactoryBean<Repository> {

    private static final ModeShapeEngine ENGINE = new ModeShapeEngine();

    private static Repository repository;


    public static void make() throws Exception {
        ENGINE.start();
        URL url = ModeShapeRepositoryFactory.class.getClassLoader().getResource("repository-config.json");
        RepositoryConfiguration repositoryConfiguration = RepositoryConfiguration.read(url);
        repository = ENGINE.deploy(repositoryConfiguration);
        ENGINE.startRepository(repositoryConfiguration.getName());
    }

    @PreDestroy
    public void post() throws Exception {
        ENGINE.shutdown().get(10, TimeUnit.SECONDS);
    }

    @Override
    public Repository getObject() throws Exception {

        if (ModeShapeRepositoryFactory.repository == null) {
            ModeShapeRepositoryFactory.make();
        }

        return ModeShapeRepositoryFactory.repository;
    }

    @Override
    public Class<?> getObjectType() {
        return javax.jcr.Repository.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
