package fms.jcr;


import org.jcrom.Jcrom;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class JcromFactory implements FactoryBean<Jcrom> {

    private static Jcrom instance = null;

    @Override
    public Jcrom getObject() throws Exception {

        if (instance == null) {
            instance = new Jcrom(true, true);
            //Map all @JcrNode annotated classes
            instance.map(fms.business.archetype.Field.class);
            instance.map(fms.business.archetype.Template.class);
            instance.map(fms.business.archetype.validator.EmailValidator.class);
            instance.map(fms.business.archetype.Archetype.class);
            instance.map(fms.business.fieldtype.FieldType.class);
            instance.map(fms.business.form.Admin.class);
            instance.map(fms.business.form.Form.class);
        }

        return instance;
    }

    @Override
    public Class<?> getObjectType() {
        return Jcrom.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
