package de.hardt.vaadinDemo.menuItems.absender;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class AbsenderView extends VerticalLayout implements View {
	private static final long serialVersionUID = 7691687151567540869L;
	
	public static final String VIEW_NAME = "Absender DB";

    public AbsenderView() {
        CustomLayout deusContent = new CustomLayout("defaultview");
        deusContent.setStyleName("default-content");
        deusContent.addComponent(new Label("<h1>Absender DB View</h1>", ContentMode.HTML), "header");
        deusContent.addComponent(new Label("Alles zur Administration der Absender DB ...", ContentMode.TEXT), "info");
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
