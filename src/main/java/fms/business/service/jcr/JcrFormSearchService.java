package fms.business.service.jcr;

import fms.business.service.FormSearchService;
import fms.business.service.SearchCondition;
import fms.business.form.Form;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jcr.Session;
import java.util.Map;

/**
 * @inheritDoc
 */
@Service
public class JcrFormSearchService implements FormSearchService {

    @Autowired
    private Session session;

    public JcrFormSearchService() {

    }


    /**
     * @inheritDoc
     */
    @Override
    public Map<Integer, Form> findByConditions(SearchCondition condition) {
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int findCountByConditions(SearchCondition condition) {
        return 0;
    }

}