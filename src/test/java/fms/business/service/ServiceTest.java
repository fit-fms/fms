package fms.business.service;

import fms.Application;
import fms.jcr.InitDB;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jcr.Node;
import javax.jcr.Session;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
public abstract class ServiceTest {

    @Autowired
    private InitDB init;

    public void resetWorkspace() throws Exception {
        init.initStructure();
    }

}
