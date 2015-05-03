package fms.business.service;

import fms.business.fieldtype.FieldType;

import java.util.List;

/**
 * Sluzba pro uchovavani typu policek
 */
public interface FieldTypeService {


    /**
     * Vytvori novy typ policka
     * @param fieldType typ ktery ma byt ulozen
     * @throws Exception
     */
    public void createFieldType(FieldType fieldType) throws Exception;

    /**
     * Odstrani typ policka
     * @param fieldType typ ktery ma byt odstranen
     * @throws Exception
     */
    public void removeFieldType(FieldType fieldType) throws Exception;

    /**
     * Najde typ policka podle jmena
     * @param name jmeno typu policka
     * @return Policko s danym jmenem
     * @throws Exception
     */
    public FieldType getByName(String name) throws Exception;

    /**
     * Ulozi zmeny provedene na typu policka
     * @param fieldType Zmeneny typ policka
     * @throws Exception
     */
    public void updateFieldType(FieldType fieldType) throws Exception;

    /**
     * Ziska vsechny typy policek
     * @return Typy policek
     * @throws Exception
     */
    public List<FieldType> getFieldTypes() throws Exception;
}
