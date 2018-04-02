package de.hardt.vaadinDemo.menuItems.systemStatus;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class SystemStatusView extends VerticalLayout implements View {

	private static final long serialVersionUID = -3793303480491021116L;
	
	public static final String VIEW_NAME = "Status Dashboard";

    public SystemStatusView() {
        CustomLayout deusContent = new CustomLayout("defaultview");
        deusContent.setStyleName("default-content");
        deusContent.addComponent(new Label("<h1>Strategic Output Management System</h1>", ContentMode.HTML), "header");
        deusContent.addComponent(new Label("Staus des OPM-Systems in einer View / Dashboard ...", ContentMode.TEXT), "info");
        setSizeFull();
        setMargin(false);
        setStyleName("default-view");
        addComponent(deusContent);
        setComponentAlignment(deusContent, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void enter(ViewChangeEvent event) {
    }
    
//  private void showNotification(Notification notification) {
//  // keep the notification visible a little while after moving the
//  // mouse, or until clicked
//  notification.setDelayMsec(2000);
//  notification.show(Page.getCurrent());
//}
}
