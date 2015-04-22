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
            instance.map(fms.business.archetype.template.A4Landscape.class);
            instance.map(fms.business.archetype.validator.EmailValidator.class);
            instance.map(fms.business.archetype.UnpublisdedArchertype.class);
            instance.map(fms.business.archetype.PublishedArchetype.class);
            instance.map(fms.business.archetype.FinishedArchetype.class);
            instance.map(fms.business.fieldtype.DateField.class);
            instance.map(fms.business.fieldtype.DateTimeField.class);
            instance.map(fms.business.fieldtype.DecimalField.class);
            instance.map(fms.business.fieldtype.IntegerField.class);
            instance.map(fms.business.fieldtype.TextField.class);
            instance.map(fms.business.fieldtype.TimeField.class);
            instance.map(fms.business.form.Admin.class);
            instance.map(fms.business.form.DigitalForm.class);
            instance.map(fms.business.form.PaperForm.class);
            instance.map(fms.business.form.Scan.class);
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
