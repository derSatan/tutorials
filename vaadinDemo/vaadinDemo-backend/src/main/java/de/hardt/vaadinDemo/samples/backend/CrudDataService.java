package de.hardt.vaadinDemo.samples.backend;

import java.io.Serializable;
import java.util.Collection;

import de.hardt.vaadinDemo.samples.backend.crud.data.Category;
import de.hardt.vaadinDemo.samples.backend.crud.data.Product;
import de.hardt.vaadinDemo.samples.backend.crud.mock.MockDataService;

/**
 * Back-end service interface for retrieving and updating product data.
 */
public abstract class CrudDataService implements Serializable {
	private static final long serialVersionUID = 7891845553172466832L;

	public abstract Collection<Product> getAllProducts();

    public abstract Collection<Category> getAllCategories();

    public abstract void updateProduct(Product p);

    public abstract void deleteProduct(int productId);

    public abstract Product getProductById(int productId);

    public static CrudDataService get() {
        return MockDataService.getInstance();
    }

}
