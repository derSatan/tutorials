package de.hardt.vaadinDemo.menuItems.about;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.Version;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class AboutView extends VerticalLayout implements View {
	private static final long serialVersionUID = -2440216779744583216L;
	
	public static final String VIEW_NAME = "About";

    public AboutView() {
        CustomLayout aboutContent = new CustomLayout("defaultview");
        aboutContent.setStyleName("default-content");
        aboutContent.addComponent(new Label("<h1>Demo OPM Vaadin Application</h1>", ContentMode.HTML), "header");
        aboutContent.addComponent(new Label("Dies ist eine Output Management Demo Applikation (Benutzt Vaadin " + Version.getFullVersion() + ")", ContentMode.TEXT), "info");
        setSizeFull();
        setMargin(false);
        setStyleName("default-view");
        addComponent(aboutContent);
        setComponentAlignment(aboutContent, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void enter(ViewChangeEvent event) {
    }

}
