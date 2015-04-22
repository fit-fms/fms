package fms.business.form;


import org.jcrom.annotations.JcrProperty;

/**
 * U�ivatelem na PC vyplnen� formul�r, nevy�aduje predzpracov�n� pred schv�len�m.
 * Neumo�nuje evidovat podpis.
 */
public class DigitalForm extends Form {

    /**
     * ip adresa odes�latele formul�re
     */
    @JcrProperty
    private String ip;

    /**
     * Informace o prohl�eci ze kter�ho byl formul�r odesl�n
     */
    @JcrProperty
    private String browser;

    public DigitalForm() {

    }


    /**
     * ip adresa odes�latele formul�re
     */
    public String getIp() {
        return ip;
    }

    /**
     * ip adresa odes�latele formul�re
     *
     * @param ip
     */
    public void setIp(String ip) {
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