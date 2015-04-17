package fms.business.form;


import java.util.Date;
import java.util.List;

/**
 * Formul�r vyplnen� na pap�re. Vy�aduje preps�n� informac� do syst�mu. Umo�nuje evidovat podpis.
 * V pr�pade �e evidujeme pap�rovou formu, pova�uje se za smerodatnou i kdy� byl formul�r predem odesl�n online.  Online odesl�n� jen odstran� prepisov�n� dat z pap�ru do IS. Po potvrzen� podpisu "Digi�ln� forma" zan�k� a nad�le existuje jen "Pap�rov� forma"
 *
 * @author jinora
 * @version 1.0
 * @created 15-Apr-2015 12:39:49 PM
 */
public class PaperForm extends Form {

    /**
     * Datum kdy byl formul�r odes�latelem podeps�n
     */
    private Date signedAt;
    /**
     * Osoba uchovavaj�c� fyzick� formul�r.
     */
    private Person person;
    /**
     * Seznam skenu formul�ru.
     */
    private List<Scan> scans;
    private Person Person;

    public PaperForm() {

    }

    /**
     * Z�sk� seznam skenu.
     */
    public List<Scan> getScans() {
        return scans;
    }

    /**
     * Odstran� sken.
     *
     * @param scan
     */
    public void removeScan(Scan scan) {
        this.scans.remove(scan);
    }

    /**
     * Prid� sken.
     *
     * @param scan
     */
    public void addScan(Scan scan) {
        scans.add(scan);
    }

    /**
     * Datum kdy byl formul�r odes�latelem podeps�n
     */
    public Date getSignedAt() {
        return signedAt;
    }

    /**
     * Datum kdy byl formul�r odes�latelem podeps�n
     *
     * @param signedAt
     */
    public void setSignedAt(Date signedAt) {
        this.signedAt = signedAt;
    }

    /**
     * Nastav� osobu kter� uchov�v� fyzick� formul�r.
     */
    public Person getPerson() {
        return person;
    }

    /**
     * Z�sk�  osobu kter� uchov�v� fyzick� formul�r.
     *
     * @param person
     */
    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * Zjist� jestli byl formul�r podeps�n. (signedAt == NULL)
     */
    public boolean isSigned() {
        return signedAt != null;
    }

}