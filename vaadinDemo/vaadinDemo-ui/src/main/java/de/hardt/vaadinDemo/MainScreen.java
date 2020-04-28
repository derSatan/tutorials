package de.hardt.vaadinDemo;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

import de.hardt.vaadinDemo.menuItems.about.AboutView;
import de.hardt.vaadinDemo.menuItems.absender.AbsenderView;
import de.hardt.vaadinDemo.menuItems.crud.CrudView;
import de.hardt.vaadinDemo.menuItems.demoZone.DemoZoneView;
import de.hardt.vaadinDemo.menuItems.deus.DeusView;
import de.hardt.vaadinDemo.menuItems.helperSuite.HelperSuiteView;
import de.hardt.vaadinDemo.menuItems.opmBulk.OpmBulkView;
import de.hardt.vaadinDemo.menuItems.systemStatus.SystemStatusView;

/**
 * Content of the UI when the user is logged in.
 * 
 * 
 */
public class MainScreen extends HorizontalLayout {
	private static final long serialVersionUID = -7999181203596829318L;
	
	private Menu menu;

    public MainScreen(VaadinDemoUI ui) {

        setSpacing(false);
        setStyleName("main-screen");

        CssLayout viewContainer = new CssLayout();
        viewContainer.addStyleName("valo-content");
        viewContainer.setSizeFull();
        
        // Create the accordion
        Accordion accordion = new Accordion();
        
        // Create the first tab for DEUS DB, specify caption when adding
        Layout tab1 = new VerticalLayout(); // Wrap in a layout
        tab1.addComponent(new Image("Dokumenttypen",
        		VaadinIcons.ACCORDION_MENU));
        tab1.addComponent(new Image("Dokumenttypgruppen",
        		VaadinIcons.ACCORDION_MENU));
        tab1.addComponent(new Image("Zustelltypen",
        		VaadinIcons.ACCORDION_MENU));
        tab1.addComponent(new Image("Pakettypen",
        		VaadinIcons.ACCORDION_MENU));
        tab1.addComponent(new Image("Versandwege",
        		VaadinIcons.ACCORDION_MENU));
        tab1.addComponent(new Image("Zuordnungen",
        		VaadinIcons.ACCORDION_MENU));
        accordion.addTab(tab1, "DEUS DB",
        		VaadinIcons.DATABASE);
        
        addComponent(accordion);
        
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
        menu.addView(new CrudView(), CrudView.VIEW_NAME,
        		CrudView.VIEW_NAME, VaadinIcons.BAR_CHART);
        menu.addView(new AboutView(), AboutView.VIEW_NAME, AboutView.VIEW_NAME,
                VaadinIcons.INFO_CIRCLE);

        navigator.addViewChangeListener(viewChangeListener);
        navigator.navigateTo(SystemStatusView.VIEW_NAME);
        
        addComponent(menu);
        addComponent(viewContainer);
        setExpandRatio(viewContainer, 1);
        setSizeFull();
    }
    
	// notify the view menu about view changes so that it can display which view is currently active
    ViewChangeListener viewChangeListener = new ViewChangeListener() {

		private static final long serialVersionUID = 5395039040269517284L;

		@Override
        public boolean beforeViewChange(ViewChangeEvent event) {
            return true;
        }

        @Override
        public void afterViewChange(ViewChangeEvent event) {
            menu.setActiveView(event.getViewName());
        }

    };
}
