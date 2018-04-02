package de.hardt.vaadinDemo.menuItems.helperSuite;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class HelperSuiteView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "HelperSuite";

    public HelperSuiteView() {
        CustomLayout deusContent = new CustomLayout("defaultview");
        deusContent.setStyleName("default-content");

        // you can add Vaadin components in predefined slots in the custom layout
        deusContent.addComponent(
                new Label("<h1>Helper Suite View</h1>", ContentMode.HTML), "header");
        deusContent.addComponent(
                new Label("Hier kommen alle Elemente der HelperSuite hin ...", ContentMode.TEXT), "info");

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
