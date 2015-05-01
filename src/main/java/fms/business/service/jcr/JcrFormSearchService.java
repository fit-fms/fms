package fms.business.service.jcr;

import fms.business.service.FormSearchService;
import fms.business.service.SearchCondition;
import fms.business.form.Form;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jcr.Session;
import java.util.Map;

/**
 * JcrFormSearchService je oddelena od FormService kvuli rozdilne logice hledani. FormService je urcen pro praci se specifickym formularem (dopredu vime ktery to je).   (Search muze zprostredkovavat jina sluzba jako elasticsearch)
 *
 * @author jinora
 * @version 1.0
 * @created 15-Apr-2015 12:39:48 PM
 */
public class JcrFormSearchService implements FormSearchService {

    @Autowired
    private Session session;

    public JcrFormSearchService() {

    }


    /**
     * Najde formul�re podle po�adavku.
     *
     * @param condition
     */
    @Override
    public Map<Integer, Form> findByConditions(SearchCondition condition) {
        return null;
    }

    /**
     * Zjist� pocet formul�ru vyhovuj�c�ch po�adavkum.
     *
     * @param condition
     */
    @Override
    public int findCountByConditions(SearchCondition condition) {
        return 0;
    }

}