package fms.business.service;

import fms.business.archetype.Validator;
import fms.business.fieldtype.FieldType;
import org.jcrom.Jcrom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;
import java.util.ArrayList;
import java.util.List;

/**
 * Slu�ba pro uchov�v�n� valid�toru v datab�zi.
 *
 * @author jinora
 * @version 1.0
 * @created 15-Apr-2015 12:39:49 PM
 */
@Service
public class ValidatorService {

    private Session session;

    private Jcrom jcrom;

    private List<Validator> validators;

    @Autowired
    public ValidatorService(Session session, Jcrom jcrom) {
        this.session = session;
        this.jcrom = jcrom;
    }


    /**
     * Vytvor� valid�tor v datab�zi.
     *
     * @param validator
     */
    public void createValidator(Validator validator) throws Exception {
        Node validatorsNode = session.getNode("/validators");
        jcrom.addNode(validatorsNode, validator);

        session.save();
    }

    public Validator getValidatorByName(String name) throws Exception {
        Node validatorNode = session.getNode("/validators/" + name);
        Validator validator = jcrom.fromNode(Validator.class, validatorNode);

        return validator;
    }

    /**
     * Aktualizuje valid�tor v datab�zi.
     *
     * @param validator
     */
    public void updateValidator(Validator validator) throws Exception {
        removeValidator(validator);
        createValidator(validator);
    }

    /**
     * odstran� valid�tor z datab�ze.
     *
     * @param validator
     */
    public void removeValidator(Validator validator) throws Exception {
        Node validatorNode = session.getNode("/validators/" + validator.getName());
        validatorNode.remove();
        session.save();
    }

    /**
     * Z�sk� v�echny valid�tory schopn� validovat dan� typ.
     *
     * @param fieldType
     */
    public List<Validator> getValidatorsByFieldType(FieldType fieldType) throws Exception {
        if (validators == null) {
            validators = new ArrayList<Validator>();
        }
        else {
            validators.clear();
        }

        NodeIterator it = session.getNode("/validators").getNodes();
        while (it.hasNext()) {
            Node vn = it.nextNode();
            Validator v = jcrom.fromNode(Validator.class, vn);
            validators.add(v);
        }

        return validators;
    }

}