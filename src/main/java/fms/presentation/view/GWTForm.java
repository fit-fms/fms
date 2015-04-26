package fms.presentation.view;

import fms.business.form.Form;
import fms.business.archetype.Archetype;
import com.google.gwt.http.client.Response;
import com.google.gwt.core.client.EntryPoint;
import org.springframework.http.HttpRequest;
/**
 * @author Jaroslav molík
 * @version 1.0
 * @created 23-4-2015 21:04:54
 */
public class GWTForm {



    public void finalize() throws Throwable {

    }

    /**
     * Konstuktor
     */
    public GWTForm(){

    }

    /**
     * Zpracuje HTTP request a vytvori domenove objekty
     *
     * @param request
     * @param entry
     * @param form
     * @param archetype
     */
    public Form approveForm(HttpRequest request, EntryPoint entry, Form form, Archetype archetype){
        return null;
    }

    /**
     * Zpracuje HTTP request a vytvori domenove objekty
     *
     * @param request
     * @param entry
     * @param form
     * @param archetype
     */
    public Form editForm(HttpRequest request, EntryPoint entry, Form form, Archetype archetype){
        return null;
    }

    /**
     * Zpracuje HTTP request a vytvori domenove objekty
     *
     * @param request
     * @param entry
     * @param archetype
     */
    public Form fillOutForm(HttpRequest request, EntryPoint entry, Archetype archetype){
        return null;
    }

    /**
     *
     * @param form
     */
    public Response renderForm(Form form){
        return null;
    }

}