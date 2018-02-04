package de.derSatan.vaadinTutorial.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import de.derSatan.vaadinTutorial.entity.Customer;
import de.derSatan.vaadinTutorial.repository.CustomerRepository;

@SpringUI
public class CustomerUI extends UI {

	private static final long serialVersionUID = -8505321228792500486L;

	// Repository
	private final CustomerRepository repo;
	
	// UI elements
	private final CustomerEditor editor;
	final Grid<Customer> grid;
	final TextField filter;
	private final Button addNewBtn;

	@Autowired
	public CustomerUI(CustomerRepository repo, CustomerEditor editor) {
		this.repo = repo;
		this.editor = editor;
		this.grid = new Grid<>(Customer.class);
		this.filter = new TextField();
		this.addNewBtn = new Button("New customer", VaadinIcons.USER);
	}

	@Override
	protected void init(VaadinRequest request) {
		// build layout
		HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
		VerticalLayout mainLayout = new VerticalLayout(actions, grid, editor);
		setContent(mainLayout);

		grid.setHeight(300, Unit.PIXELS);
		grid.setColumns("id", "firstName", "lastName");

		filter.setPlaceholder("Filter by last name");

		// Hook logic to components

		// Replace listing with filtered content when user changes filter
		filter.setValueChangeMode(ValueChangeMode.LAZY);
		filter.addValueChangeListener(e -> listCustomers(e.getValue()));

		// Connect selected Customer to editor or hide if none is selected
		grid.asSingleSelect().addValueChangeListener(e -> {
			editor.editCustomer(e.getValue());
		});

		// Instantiate and edit new Customer the new button is clicked
		addNewBtn.addClickListener(e -> editor.editCustomer(new Customer("", "")));

		// Listen changes made by the editor, refresh data from backend
		editor.setChangeHandler(() -> {
			editor.setVisible(false);
			listCustomers(filter.getValue());
		});

		// Initialize listing
		listCustomers(null);
		
		// Create the accordion
		Accordion accordion = new Accordion();

		// Create the first tab, specify caption when adding
		Layout tab1 = new VerticalLayout(); // Wrap in a layout
		tab1.addComponent(new Image(null, // No component caption
		    new ThemeResource("img/planets/Mercury.jpg")));
		accordion.addTab(tab1, "Mercury",
		    new ThemeResource("img/planets/Mercury_symbol.png"));

		// This tab gets its caption from the component caption
		Component tab2 = new Image("Venus",
		    new ThemeResource("img/planets/Venus.jpg"));
		accordion.addTab(tab2).setIcon(
		    new ThemeResource("img/planets/Venus_symbol.png"));

		// And so forth the other tabs...
		String[] tabs = {"Earth", "Mars", "Jupiter", "Saturn"};
		for (String caption: tabs) {
		    String basename = "img/planets/" + caption;
		    VerticalLayout tab = new VerticalLayout();
		    tab.addComponent(new Image(null,
		            new ThemeResource(basename + ".jpg")));
		    accordion.addTab(tab, caption,
		            new ThemeResource(basename + "_symbol.png"));
		}
		mainLayout.addComponent(accordion);
	}

	void listCustomers(String filterText) {
		if (StringUtils.isEmpty(filterText)) {
			grid.setItems(repo.findAll());
		}
		else {
			grid.setItems(repo.findByLastNameStartsWithIgnoreCase(filterText));
		}
	}
}
