package fms.business.service;

import fms.business.archetype.validator.Validator;
import fms.business.fieldtype.FieldType;

import java.util.List;

/**
 * Slu�ba pro uchov�v�n� valid�toru v datab�zi.
 */
public interface ValidatorService {

    /**
     * Vytvor� valid�tor v datab�zi.
     *
     * @param validator
     */
    public void createValidator(Validator validator) throws Exception;

    public Validator getValidatorByName(String name) throws Exception;

    /**
     * Aktualizuje valid�tor v datab�zi.
     *
     * @param validator
     */
    public void updateValidator(Validator validator) throws Exception;

    /**
     * odstran� valid�tor z datab�ze.
     *
     * @param validator
     */
    public void removeValidator(Validator validator) throws Exception;

    /**
     * Z�sk� v�echny valid�tory schopn� validovat dan� typ.
     *
     * @param fieldType
     */
    public List<Validator> getValidatorsByFieldType(FieldType fieldType) throws Exception;

}