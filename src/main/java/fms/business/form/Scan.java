package fms.business.form;


import org.jcrom.annotations.JcrName;
import org.jcrom.annotations.JcrNode;
import org.jcrom.annotations.JcrPath;
import org.jcrom.annotations.JcrProperty;

import java.util.Date;

/**
 * Obr�zek formul�re naskenovan� do poc�tace.
 */
@JcrNode
public class Scan {

    @JcrName
    private String jcrName = "fms_scan";

    @JcrPath
    private String path;

    @JcrProperty
    private int id;

    /**
     * Datum nahr�n� skanu do IS.
     */
    @JcrProperty
    private Date date;

    /**
     * Rozli�en� obr�zku
     */
    @JcrProperty
    private String resolution;

    /**
     * Naskenovan� soubor.
     */
    @JcrProperty
    private byte[] data;

    public Scan() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Z�sk� rozli�en� obr�zku.
     */
    public String getResolution() {
        return resolution;
    }

    /**
     * Nastav� rozli�en� obr�zku.
     *
     * @param resolution
     */
    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    /**
     * Z�sk� datum nahr�n� skanu do IS.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Nastav� datum nahr�n� skanu do IS.
     *
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Z�sk� skan formul�re.
     */
    public byte[] getData() {
        return data;
    }

    /**
     * Nastav� skan formul�re.
     *
     * @param file
     */
    public void setData(byte[] file) {
        data = file;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Scan scan = (Scan) o;

        return id == scan.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}