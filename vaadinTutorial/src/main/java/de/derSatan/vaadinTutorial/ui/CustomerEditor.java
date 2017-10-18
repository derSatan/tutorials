package de.derSatan.vaadinTutorial.ui;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import de.derSatan.vaadinTutorial.entity.Customer;
import de.derSatan.vaadinTutorial.repository.CustomerRepository;

@SpringComponent
@UIScope
public class CustomerEditor extends VerticalLayout {

	private static final long serialVersionUID = 6584434900701167243L;

	private final CustomerRepository repository;
	private Customer customer;

	TextField firstName = new TextField("First name");
	TextField lastName = new TextField("Last name");
	Button save = new Button("Save", VaadinIcons.SAFE);
	Button cancel = new Button("Cancel", VaadinIcons.CLOSE_CIRCLE);
	Button delete = new Button("Delete", VaadinIcons.TRASH);
	CssLayout actions = new CssLayout(save, cancel, delete);

	Binder<Customer> binder = new Binder<>(Customer.class);

	@Autowired
	public CustomerEditor(CustomerRepository repository) {
		this.repository = repository;

		addComponents(firstName, lastName, actions);

		// bind using naming convention
		binder.bindInstanceFields(this);

		// Configure and style components
		setSpacing(true);
		actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
		save.setStyleName(ValoTheme.BUTTON_PRIMARY);
		save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

		// wire action buttons to save, delete and reset
		save.addClickListener(e -> repository.save(customer));
		delete.addClickListener(e -> repository.delete(customer));
		cancel.addClickListener(e -> editCustomer(customer));
		setVisible(false);
	}

	public interface ChangeHandler {

		void onChange();
	}

	public final void editCustomer(Customer c) {
		if (c == null) {
			setVisible(false);
			return;
		}
		final boolean persisted = c.getId() != null;
		if (persisted) {
			// Find fresh entity for editing
			customer = repository.findOne(c.getId());
		}
		else {
			customer = c;
		}
		cancel.setVisible(persisted);

		binder.setBean(customer);

		setVisible(true);

		save.focus();

		firstName.selectAll();
	}

	public void setChangeHandler(ChangeHandler h) {
		save.addClickListener(e -> h.onChange());
		delete.addClickListener(e -> h.onChange());
	}

}
