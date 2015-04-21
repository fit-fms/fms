package fms.business.form;

import fms.business.archetype.Archetype;
import org.jcrom.annotations.*;

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
@JcrNode
public class Form {

    @JcrName
    private String jrcName = "fms_form";

    @JcrPath
    private String jcrPath;

    /**
     * Identifikacn� c�slo
     */
    @JcrProperty
    private int id;

    /**
     * Datum kdy byl formul�r vyplnen
     */
    @JcrProperty
    private Date filledAt;

    /**
     * Datum kdy byl formul�r schv�len spr�vcem
     */
    @JcrProperty()
    private Date approvedAt;

    /**
     * Admin kter� formul�r schv�lil
     */
    @JcrReference(byPath = true)
    private Admin admin;

    /**
     * Archetyp kter� popisuje data formul�re.
     */
    @JcrReference(byPath = true)
    private Archetype archetype;

    /**
     * Vyplnen� pol�cka
     */
    private Map<String, FilledField> filledFields;

    public Form() {
        this.filledFields = new HashMap<String, FilledField>();
    }

    /**
     * Zvaliduje vsechny FilledFields.
     */
    public boolean validate() {

        boolean status = true;


        for (Map.Entry<String, FilledField> entry : filledFields.entrySet()) {
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
        return filledFields;
    }

    /**
     * Prid� vyplnen� pol�cko.
     *
     * @param field
     */
    public void addfilledfield(FilledField field) {
        filledFields.put(field.getField().getName(), field);
    }

    /**
     * Odstran� vyplnen� pol�cko.
     *
     * @param field
     */
    public void removeFilledFIeld(FilledField field) {
        filledFields.remove(field.getField().getName());
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