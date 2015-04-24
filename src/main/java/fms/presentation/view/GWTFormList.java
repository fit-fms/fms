package fms.presentation.view;

import fms.business.service.SearchCondition;
import fms.business.form.Form;
import fms.business.archetype.Archetype;
import com.google.gwt.http.client.Response;
import com.google.gwt.core.client.EntryPoint;
import org.springframework.http.HttpRequest;

import java.util.Map;

/**
 * Created by smolijar on 4/24/15.
 */
public class GWTFormList {

    public void finalize() throws Throwable {

    }

    public GWTFormList(){

    }

    /**
     * Zpracuje pozadavek na seznam formularu. (Vyhledavani, filtrovani)
     *
     * @param reqeust
     * @param entry
     * @param archetype
     */
    public SearchCondition constructCondition(HttpRequest  reqeust, EntryPoint entry, Archetype archetype){
        return null;
    }

    /**
     * Vyrenderuje form list + menu pro vyhledavani + menu pro filtrovani (na zaklade
     * fieldu v archetypu)
     *
     * @param forms
     */
    public Response RenderFormList(Map<Integer, Form> forms){
        return null;
    }

}
