package fms.business.service.jcr;

import fms.business.archetype.validator.Validator;
import fms.business.fieldtype.FieldType;
import fms.business.service.ValidatorService;
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
public class JcrValidatorService implements ValidatorService {

    /**
     * Path to validators root
     */
    public static final String VALIDATOR_ROOT = "/validators";

    private Session session;

    private Jcrom jcrom;

    @Autowired
    public JcrValidatorService(Session session, Jcrom jcrom) {
        this.session = session;
        this.jcrom = jcrom;
    }


    /**
     * @inheritDoc
     */
    @Override
    public void createValidator(Validator validator) throws Exception {
        Node validatorsNode = session.getNode(VALIDATOR_ROOT);
        jcrom.addNode(validatorsNode, validator);

        session.save();
    }

    /**
     * @inheritDoc
     */
    @Override
    public Validator getValidatorByName(String name) throws Exception {
        QueryManager queryManager = session.getWorkspace().getQueryManager();
        String queryStr = "/jcr:root" + VALIDATOR_ROOT + "/*[@name='" + name + "']";
        Query query = queryManager.createQuery(queryStr, Query.XPATH);
        QueryResult queryResult = query.execute();

        NodeIterator it = queryResult.getNodes();
        if (!it.hasNext()) {
            return null;
        }

        return jcrom.fromNode(Validator.class, it.nextNode());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void updateValidator(Validator validator) throws Exception {
        Node validatorNode = session.getNode(jcrom.getPath(validator));
        jcrom.updateNode(validatorNode, validator);
        session.save();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void removeValidator(Validator validator) throws Exception {
        Node validatorNode = session.getNode(jcrom.getPath(validator));
        validatorNode.remove();
        session.save();
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<Validator> getValidatorsByFieldType(FieldType fieldType) throws Exception {
        List<Validator> validators = new ArrayList<Validator>();

        if (fieldType == null) return validators;

        NodeIterator it = session.getNode(VALIDATOR_ROOT).getNodes();
        while (it.hasNext()) {
            Validator v = jcrom.fromNode(Validator.class, it.nextNode());
            if (v.getFieldType() == null) continue;
            if (!v.getFieldType().getName().equals(fieldType.getName())) continue;
            validators.add(v);
        }

        return validators;
    }

}