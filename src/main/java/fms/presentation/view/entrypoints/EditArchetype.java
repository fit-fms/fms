package fms.presentation.view.entrypoints;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.*;
import fms.business.archetype.Archetype;
import fms.business.archetype.Field;
import fms.business.archetype.Template;

/**
 * Created by smolijar on 4/24/15.
 */
public class EditArchetype implements EntryPoint {

    private Archetype arch = new Archetype();
    private VerticalPanel mainPanel = new VerticalPanel();

    private FlexTable archBasic = new FlexTable();
    private FlexTable archReq = new FlexTable();
    private FlexTable archOpt = new FlexTable();
    private FlexTable archTem = new FlexTable();

    private TextBox name = new TextBox();
    private TextBox publicDescription = new TextBox();
    private TextBox privateDescription = new TextBox();

    @Override
    public void onModuleLoad() {

        //prepare table basic
        archBasic.setTitle("Basic Info");
        archBasic.setText(0, 0, "name");
        archBasic.setWidget(0, 1, name);
        archBasic.setText(1, 0, "public description");
        archBasic.setWidget(1, 1, publicDescription);
        archBasic.setText(2, 0, "private description");
        archBasic.setWidget(2, 1, privateDescription);

        //prepare table req
        archReq.setTitle("Required fields");
        for (int i = 0; i < arch.getRequiredFields().size(); i++) {
            Field f = arch.getRequiredFields().get(i);
            Button remove = new Button("x");
            archReq.setText(i+1, 0, f.getLabel() );
            archReq.setText(i+1, 1, f.getName() );
            archReq.setWidget(i + 1, 2, remove);
        }
        //todo add req widget

        //prepare table opt
        archReq.setTitle("Required fields");
        for (int i = 0; i < arch.getOptionalFields().size(); i++) {
            Field f = arch.getOptionalFields().get(i);
            Button remove = new Button("x");
            archOpt.setText(i+1, 0, f.getLabel() );
            archOpt.setText(i+1, 1, f.getName() );
            archOpt.setWidget(i + 1, 2, remove);
        }
        //todo add opt widget

        //prepare table opt
        archReq.setTitle("Templates");
        for (int i = 0; i < arch.getTemplates().size(); i++) {
            Template f = arch.getTemplates().get(i);
            Button remove = new Button("x");
            archTem.setText(i+1, 0, f.getName() );
            archTem.setText(i+1, 1, f.getDescription() );
            archTem.setWidget(i + 1, 2, remove);
        }
        //todo add template widget


        //todo event listeners

        //fill the main panel
        mainPanel.add(archBasic);
        mainPanel.add(archReq);
        mainPanel.add(archOpt);
        mainPanel.add(archTem);

        //associate to RootPanel
        RootPanel.get().add(mainPanel);


    }
}
