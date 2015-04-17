package fms.business.form;


import java.net.InetAddress;

/**
 * U�ivatelem na PC vyplnen� formul�r, nevy�aduje predzpracov�n� pred schv�len�m.
 * Neumo�nuje evidovat podpis.
 *
 * @author jinora
 * @version 1.0
 * @created 15-Apr-2015 12:39:48 PM
 */
public class DigitalForm extends Form {

    /**
     * ip adresa odes�latele formul�re
     */
    private InetAddress ip;
    /**
     * Informace o prohl�eci ze kter�ho byl formul�r odesl�n
     */
    private String browser;

    public DigitalForm() {

    }


    /**
     * ip adresa odes�latele formul�re
     */
    public InetAddress getIp() {
        return ip;
    }

    /**
     * ip adresa odes�latele formul�re
     *
     * @param ip
     */
    public void setIp(InetAddress ip) {
        this.ip = ip;
    }

    /**
     * Informace o prohl�eci ze kter�ho byl formul�r odesl�n
     */
    public String getBrowser() {
        return browser;
    }

    /**
     * Informace o prohl�eci ze kter�ho byl formul�r odesl�n
     *
     * @param browser
     */
    public void setBrowser(String browser) {
        this.browser = browser;
    }

}