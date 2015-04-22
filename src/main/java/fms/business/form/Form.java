package fms.business.form;

import fms.business.archetype.Archetype;
import org.jcrom.JcrEntity;
import org.jcrom.annotations.*;

import java.util.*;

/**
 * Formul�r je vyplnen� instance Archetypu fomul�re.
 */
@JcrNode(classNameProperty = "className")
abstract public class Form implements JcrEntity {

    @JcrName
    private String jcrName = "fms_form";

    @JcrPath
    private String jcrPath;

    @Override
    public void setName(String s) {
        jcrName = s;
    }

    @Override
    public String getName() {
        return jcrName;
    }

    @Override
    public void setPath(String s) {
        jcrPath = s;
    }

    @Override
    public String getPath() {
        return jcrPath;
    }

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

    @JcrChildNode
    private List<FilledField> filledFields;

    public Form() {
        this.filledFields = new ArrayList<FilledField>();
    }

    /**
     * Zvaliduje vsechny FilledFields.
     */
    public boolean validate(List<String> errors) {

        boolean status = true;

        for (FilledField filledField : filledFields) {
            if (!filledField.validate(errors)) {
                status = false;
            }
        }

        return status;
    }

    /**
     * Z�sk� v�echny filled fields.
     */
    public List<FilledField> getFilledFields() {
        return filledFields;
    }

    /**
     * Prid� vyplnen� pol�cko.
     *
     * @param field
     */
    public void addfilledfield(FilledField field) {
        Set<FilledField> foo = new HashSet<FilledField>(filledFields);
        if (foo.contains(field)) return;
        filledFields.add(field);
    }

    /**
     * Odstran� vyplnen� pol�cko.
     *
     * @param field
     */
    public void removeFilledFIeld(FilledField field) {
        filledFields.remove(field);
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