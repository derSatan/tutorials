package de.hardt.vaadinDemo;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;

import de.hardt.vaadinDemo.menuItems.about.AboutView;
import de.hardt.vaadinDemo.menuItems.absender.AbsenderView;
import de.hardt.vaadinDemo.menuItems.demoZone.DemoZoneView;
import de.hardt.vaadinDemo.menuItems.deus.DeusView;
import de.hardt.vaadinDemo.menuItems.helperSuite.HelperSuiteView;
import de.hardt.vaadinDemo.menuItems.opmBulk.OpmBulkView;
import de.hardt.vaadinDemo.menuItems.status.SystemStatusView;

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
        menu.addView(new SystemStatusView(), SystemStatusView.VIEW_NAME,
        		SystemStatusView.VIEW_NAME, VaadinIcons.BAR_CHART);
        menu.addView(new DeusView(), DeusView.VIEW_NAME,
        		DeusView.VIEW_NAME, VaadinIcons.DATABASE);
        menu.addView(new AbsenderView(), AbsenderView.VIEW_NAME,
        		AbsenderView.VIEW_NAME, VaadinIcons.DATABASE);
        menu.addView(new OpmBulkView(), OpmBulkView.VIEW_NAME,
        		OpmBulkView.VIEW_NAME, VaadinIcons.DATABASE);
        menu.addView(new HelperSuiteView(), HelperSuiteView.VIEW_NAME,
        		HelperSuiteView.VIEW_NAME, VaadinIcons.HAMMER);
        menu.addView(new DemoZoneView(), DemoZoneView.VIEW_NAME,
        		DemoZoneView.VIEW_NAME, VaadinIcons.TOOLBOX);
        menu.addView(new AboutView(), AboutView.VIEW_NAME, AboutView.VIEW_NAME,
                VaadinIcons.INFO_CIRCLE);

        navigator.addViewChangeListener(viewChangeListener);
        
        addComponent(menu);
        addComponent(viewContainer);
        setExpandRatio(viewContainer, 1);
        setSizeFull();
    }
    
//    private void showNotification(Notification notification) {
//        // keep the notification visible a little while after moving the
//        // mouse, or until clicked
//        notification.setDelayMsec(2000);
//        notification.show(Page.getCurrent());
//    }

	// notify the view menu about view changes so that it can display which view is currently active
    ViewChangeListener viewChangeListener = new ViewChangeListener() {

        @Override
        public boolean beforeViewChange(ViewChangeEvent event) {
            return true;
        }

        @Override
        public void afterViewChange(ViewChangeEvent event) {
//        	showNotification(new Notification("You clicked '" + event.getViewName() + "'", Notification.Type.HUMANIZED_MESSAGE));
            menu.setActiveView(event.getViewName());
        }

    };
}
