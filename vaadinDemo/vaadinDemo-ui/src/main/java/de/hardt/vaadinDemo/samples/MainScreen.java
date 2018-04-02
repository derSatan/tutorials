package de.hardt.vaadinDemo.samples;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;

import de.hardt.vaadinDemo.VaadinDemoUI;
import de.hardt.vaadinDemo.samples.about.AboutView;
import de.hardt.vaadinDemo.samples.crud.SampleCrudView;

/**
 * Content of the UI when the user is logged in.
 * 
 * 
 */
public class MainScreen extends HorizontalLayout {
    private Menu menu;

    public MainScreen(VaadinDemoUI ui) {

        setSpacing(false);
        setStyleName("main-screen");

        CssLayout viewContainer = new CssLayout();
        viewContainer.addStyleName("valo-content");
        viewContainer.setSizeFull();
                
        final Navigator navigator = new Navigator(ui, viewContainer);
        navigator.setErrorView(ErrorView.class);
        menu = new Menu(navigator);
        menu.addView(new SampleCrudView(), SampleCrudView.VIEW_NAME,
                SampleCrudView.VIEW_NAME, VaadinIcons.EDIT);
        menu.addView(new AboutView(), AboutView.VIEW_NAME, AboutView.VIEW_NAME,
                VaadinIcons.INFO_CIRCLE);

        navigator.addViewChangeListener(viewChangeListener);
        
        addComponent(menu);
        addComponent(viewContainer);
        setExpandRatio(viewContainer, 1);
        setSizeFull();
    }
    
    private void showNotification(Notification notification) {
        // keep the notification visible a little while after moving the
        // mouse, or until clicked
        notification.setDelayMsec(2000);
        notification.show(Page.getCurrent());
    }

	// notify the view menu about view changes so that it can display which view is currently active
    ViewChangeListener viewChangeListener = new ViewChangeListener() {

        @Override
        public boolean beforeViewChange(ViewChangeEvent event) {
            return true;
        }

        @Override
        public void afterViewChange(ViewChangeEvent event) {
        	showNotification(new Notification("You clicked '" + event.getViewName() + "'", Notification.Type.HUMANIZED_MESSAGE));
            menu.setActiveView(event.getViewName());
        }

    };
}
