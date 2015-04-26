package fms.presentation.view.entrypoints;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import fms.business.archetype.Archetype;
import fms.business.archetype.Field;
import fms.business.archetype.Template;
import fms.business.service.ArchetypeService;
import fms.business.service.FieldService;
import fms.business.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by smolijar on 4/24/15.
 */
public class EditArchetype implements EntryPoint {

    private Archetype arch = new Archetype();

    //services
    FieldService fieldService;
    TemplateService templateService;
    ArchetypeService archetypeService;

    // elements
    private VerticalPanel mainPanel = new VerticalPanel();

    private FlexTable archBasicTable = new FlexTable();
    private FlexTable archReqTable = new FlexTable();
    private FlexTable archOptTable = new FlexTable();
    private FlexTable archTemTable = new FlexTable();

    private TextBox nameTextBox = new TextBox();
    private TextBox publicDescriptionTextBox = new TextBox();
    private TextBox privateDescriptionTextBox = new TextBox();

    private TextBox newReqTextBox = new TextBox();
    private TextBox newOptTextBox = new TextBox();
    private TextBox newTemTextBox = new TextBox();

    private Button submitButton = new Button();

    @Autowired
    public EditArchetype(FieldService fieldService, TemplateService templateService, ArchetypeService archetypeService) {
        this.fieldService = fieldService;
        this.templateService = templateService;
        this.archetypeService = archetypeService;
    }

    @Override
    public void onModuleLoad() {

        //prepare table basic
        archBasicTable.setTitle("Basic Info");
        archBasicTable.setText(0, 0, "name");
        archBasicTable.setWidget(0, 1, nameTextBox);
        archBasicTable.setText(1, 0, "public description");
        archBasicTable.setWidget(1, 1, publicDescriptionTextBox);
        archBasicTable.setText(2, 0, "private description");
        archBasicTable.setWidget(2, 1, privateDescriptionTextBox);

        //prepare table Req
        archReqTable.setTitle("Required fields");
        int i;
        for (i = 0; i < arch.getRequiredFields().size(); i++) {
            Field f = arch.getRequiredFields().get(i);
            Button remove = new Button("x");
            archReqTable.setText(i, 0, f.getLabel() );
            archReqTable.setText(i, 1, f.getName() );
            archReqTable.setWidget(i, 2, remove);
        }
        //adding Req
        Button addReqButton = new Button("add");
        archReqTable.setWidget(i+1,0,newReqTextBox);
        archReqTable.setWidget(i+1,1,addReqButton);

        //prepare table Opt
        archReqTable.setTitle("Required fields");
        for (i = 0; i < arch.getOptionalFields().size(); i++) {
            Field f = arch.getOptionalFields().get(i);
            Button remove = new Button("x");
            archOptTable.setText(i+1, 0, f.getLabel() );
            archOptTable.setText(i+1, 1, f.getName() );
            archOptTable.setWidget(i + 1, 2, remove);
        }
        //adding Opt
        Button addOptButton = new Button("add");
        archOptTable.setWidget(i+1,0,newOptTextBox);
        archOptTable.setWidget(i+1,1,addOptButton);

        //prepare table Tem
        archReqTable.setTitle("Templates");
        for (i = 0; i < arch.getTemplates().size(); i++) {
            Template f = arch.getTemplates().get(i);
            Button remove = new Button("x");
            archTemTable.setText(i+1, 0, f.getName() );
            archTemTable.setText(i+1, 1, f.getDescription() );
            archTemTable.setWidget(i + 1, 2, remove);
        }
        //adding Tem
        Button addTemButton = new Button("add");
        archTemTable.setWidget(i+1,0,newTemTextBox);
        archTemTable.setWidget(i + 1, 1, addTemButton);



        //event listeners
        addReqButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                addRequiredField();
            }
        });
        addOptButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                addOptionalField();
            }
        });
        addTemButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                addTemplate();
            }
        });
        submitButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                submit();
            }
        });


        //fill the main panel
        mainPanel.add(archBasicTable);
        mainPanel.add(archReqTable);
        mainPanel.add(archOptTable);
        mainPanel.add(archTemTable);
        mainPanel.add(submitButton);

        //associate to RootPanel
        RootPanel.get().add(mainPanel);

    }

    public void addRequiredField(){
        String fieldName = newReqTextBox.getText().trim();
        Field newField = null;

        // persist
        try {
            newField = fieldService.getFieldByName(fieldName);
        } catch (Exception e) {
            //todo create new field
            //newField = ...
        }
        arch.addRequiredField(newField); //no need to test if already added

        // add to table
        int row = archReqTable.getRowCount();
        archReqTable.setText(row, 0, fieldName);
        // add remove button
        Button rmButton = new Button("x");
        rmButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                //todo remove
            }
        });
        archReqTable.setWidget(row, 1, rmButton);
    }
    public void addOptionalField(){
        String fieldName = newOptTextBox.getText().trim();
        Field newField = null;

        // persist
        try {
            newField = fieldService.getFieldByName(fieldName);
        } catch (Exception e) {
            //todo create new field
            //newField = ...
        }
        arch.addOptionalField(newField); //no need to test if already added

        // add to table
        int row = archOptTable.getRowCount();
        archOptTable.setText(row, 0, fieldName);
        // add remove button
        Button rmButton = new Button("x");
        rmButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                //todo remove
            }
        });
        archOptTable.setWidget(row, 1, rmButton);
    }
    public void addTemplate(){
        String templateName = newTemTextBox.getText().trim();
        Template newTemplate = null;

        // persist
        try {
            newTemplate = templateService.getTemplateByName(templateName);
        } catch (Exception e) {
            //todo create new template
            //newTemplate = ...
        }
        arch.addTemplate(newTemplate); //no need to test if already added

        // add to table
        int row = archTemTable.getRowCount();
        archTemTable.setText(row, 0, templateName);
        // add remove button
        Button rmButton = new Button("x");
        rmButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                //todo remove
            }
        });
        archTemTable.setWidget(row, 1, rmButton);
    }
    public void submit(){
        try {
            archetypeService.updateArchetype(arch);
        } catch (Exception e) {
            // arch odes not exist
            try {
                archetypeService.createArchetype(arch);
            } catch (Exception e1) {
            }
        }
    }
}
