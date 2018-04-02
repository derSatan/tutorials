package de.hardt.vaadinDemo.menuItems.opmBulk;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class OpmBulkView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "OPM DB";

    public OpmBulkView() {
        CustomLayout deusContent = new CustomLayout("defaultview");
        deusContent.setStyleName("default-content");

        // you can add Vaadin components in predefined slots in the custom layout
        deusContent.addComponent(
                new Label("<h1>OPM DB View</h1>", ContentMode.HTML), "header");
        deusContent.addComponent(
                new Label("Alles zur Administration der OPM Masse DB ...", ContentMode.TEXT), "info");

        setSizeFull();
        setMargin(false);
        setStyleName("default-view");
        addComponent(deusContent);
        setComponentAlignment(deusContent, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void enter(ViewChangeEvent event) {
    }
}
