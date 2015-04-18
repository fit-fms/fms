package fms.business.form;


import java.util.Date;

/**
 * Obr�zek formul�re naskenovan� do poc�tace.
 *
 * @author jinora
 * @version 1.0
 * @created 15-Apr-2015 12:39:49 PM
 */
public class Scan {

    /**
     * Datum nahr�n� skanu do IS.
     */
    private Date date;
    /**
     * Rozli�en� obr�zku
     */
    private String resolution;
    /**
     * Naskenovan� soubor.
     */
    private byte[] data;

    public Scan() {

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

}