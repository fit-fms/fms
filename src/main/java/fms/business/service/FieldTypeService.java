package fms.business.service;

import fms.business.fieldtype.FieldType;

import java.util.List;


public interface FieldTypeService {


    public void createFieldType(FieldType fieldType) throws Exception;

    public void removeFieldType(FieldType fieldType) throws Exception;

    public FieldType getByName(String name) throws Exception;

    public void updateFieldType(FieldType fieldType) throws Exception;

    public List<FieldType> getFieldTypes() throws Exception;
}
