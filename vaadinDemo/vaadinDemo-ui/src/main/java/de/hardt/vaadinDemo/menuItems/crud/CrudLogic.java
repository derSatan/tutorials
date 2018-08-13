package de.hardt.vaadinDemo.menuItems.crud;

import de.hardt.vaadinDemo.VaadinDemoUI;
import de.hardt.vaadinDemo.samples.backend.CrudDataService;
import de.hardt.vaadinDemo.samples.backend.crud.data.Product;

import java.io.Serializable;
import com.vaadin.server.Page;

/**
 * This class provides an interface for the logical operations between the CRUD
 * view, its parts like the product editor form and the data source, including
 * fetching and saving products.
 *
 * Having this separate from the view makes it easier to test various parts of
 * the system separately, and to e.g. provide alternative views for the same
 * data.
 */
public class CrudLogic implements Serializable {
	private static final long serialVersionUID = -5401211019656607917L;
	
	private CrudView view;

    public CrudLogic(CrudView simpleCrudView) {
        view = simpleCrudView;
    }

    public void init() {
        editProduct(null);
        // Hide and disable if not admin
        if (!VaadinDemoUI.get().getAccessControl().isUserInRole("admin")) {
            view.setNewProductEnabled(false);
        }
    }

    public void cancelProduct() {
        setFragmentParameter("");
        view.clearSelection();
    }

    /**
     * Update the fragment without causing navigator to change view
     */
    private void setFragmentParameter(String productId) {
        String fragmentParameter;
        if (productId == null || productId.isEmpty()) {
            fragmentParameter = "";
        } else {
            fragmentParameter = productId;
        }

        Page page = VaadinDemoUI.get().getPage();
        page.setUriFragment(
                "!" + CrudView.VIEW_NAME + "/" + fragmentParameter,
                false);
    }

    public void enter(String productId) {
        if (productId != null && !productId.isEmpty()) {
            if (productId.equals("new")) {
                newProduct();
            } else {
                // Ensure this is selected even if coming directly here from
                // login
                try {
                    int pid = Integer.parseInt(productId);
                    Product product = findProduct(pid);
                    view.selectRow(product);
                } catch (NumberFormatException e) {
                }
            }
        }
    }

    private Product findProduct(int productId) {
        return CrudDataService.get().getProductById(productId);
    }

    public void saveProduct(Product product) {
        view.showSaveNotification(product.getProductName() + " ("
                + product.getId() + ") updated");
        view.clearSelection();
        view.updateProduct(product);
        setFragmentParameter("");
    }

    public void deleteProduct(Product product) {
        view.showSaveNotification(product.getProductName() + " ("
                + product.getId() + ") removed");
        view.clearSelection();
        view.removeProduct(product);
        setFragmentParameter("");
    }

    public void editProduct(Product product) {
        if (product == null) {
            setFragmentParameter("");
        } else {
            setFragmentParameter(product.getId() + "");
        }
        view.editProduct(product);
    }

    public void newProduct() {
        view.clearSelection();
        setFragmentParameter("new");
        view.editProduct(new Product());
    }

    public void rowSelected(Product product) {
        if (VaadinDemoUI.get().getAccessControl().isUserInRole("admin")) {
            view.editProduct(product);
        }
    }
}
