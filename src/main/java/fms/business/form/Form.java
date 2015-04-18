package fms.business.form;

import fms.business.archetype.Archetype;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Formul�r je vyplnen� instance Archetypu fomul�re.
 *
 * @author jinora
 * @version 1.0
 * @created 15-Apr-2015 12:39:48 PM
 */
public class Form {

    /**
     * Identifikacn� c�slo
     */
    private int id;
    /**
     * Datum kdy byl formul�r vyplnen
     */
    private Date filledAt;
    /**
     * Datum kdy byl formul�r schv�len spr�vcem
     */
    private Date approvedAt;
    /**
     * Admin kter� formul�r schv�lil
     */
    private Admin admin;
    /**
     * Archetyp kter� popisuje data formul�re.
     */
    private Archetype archetype;
    /**
     * Vyplnen� pol�cka
     */
    private Map<String, FilledField> filleFields;

    public Form() {
        this.filleFields = new HashMap<String, FilledField>();
    }

    /**
     * Zvaliduje vsechny FilledFields.
     */
    public boolean validate() {

        boolean status = true;


        for (Map.Entry<String, FilledField> entry : filleFields.entrySet()) {
            if (!entry.getValue().validate()) {
                status = false;
            }
        }

        return status;
    }

    /**
     * Z�sk� v�echny filled fields.
     */
    public Map<String, FilledField> getFilledFields() {
        return filleFields;
    }

    /**
     * Prid� vyplnen� pol�cko.
     *
     * @param field
     */
    public void addfilledfield(FilledField field) {
        filleFields.put(field.getField().getName(), field);
    }

    /**
     * Odstran� vyplnen� pol�cko.
     *
     * @param field
     */
    public void removeFilledFIeld(FilledField field) {
        filleFields.remove(field.getField().getName());
    }

    /**
     * Z�sk� archetyp kter� popisuje data Formul�re.
     */
    public Archetype getArchetype() {
        return archetype;
    }

    /**
     * Nastav� archetyp kter� popisuje data Formul�re.
     *
     * @param archetype
     */
    public void setArchetype(Archetype archetype) {
        this.archetype = archetype;
    }

    /**
     * Identifikacn� c�slo
     */
    public int getId() {
        return id;
    }

    /**
     * Identifikacn� c�slo
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Datum kdy byl formul�r vyplnen
     */
    public Date getFilledAt() {
        return filledAt;
    }

    /**
     * Datum kdy byl formul�r vyplnen
     *
     * @param filledAt
     */
    public void setFilledAt(Date filledAt) {
        this.filledAt = filledAt;
    }

    public boolean isFilled() {
        return filledAt != null;
    }

    /**
     * Datum kdy byl formul�r schv�len spr�vcem
     */
    public Date getApprovedAt() {
        return approvedAt;
    }

    /**
     * Datum kdy byl formul�r schv�len spr�vcem
     *
     * @param approvedAt
     */
    public void setApprovedAt(Date approvedAt) {
        this.approvedAt = approvedAt;
    }

    public boolean isApproved() {
        return approvedAt != null;
    }

    /**
     * Z�ska admina kter� formul�r schv�lil.
     */
    public Admin getAdmin() {
        return admin;
    }

    /**
     * Nastav�  admina kter� formul�r schv�lil.
     *
     * @param admin
     */
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

}