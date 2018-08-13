package de.derSatan.tutorial.simpleProcessConsumer.ui.views.categorieslist;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.validator.StringLengthValidator;

import de.derSatan.tutorial.simpleProcessConsumer.backend.Category;
import de.derSatan.tutorial.simpleProcessConsumer.backend.CategoryService;
import de.derSatan.tutorial.simpleProcessConsumer.backend.ReviewService;
import de.derSatan.tutorial.simpleProcessConsumer.ui.common.AbstractEditorDialog;

/**
 * A dialog for editing {@link Category} objects.
 */
public class CategoryEditorDialog extends AbstractEditorDialog<Category> {

    private final TextField categoryNameField = new TextField("Name");

    public CategoryEditorDialog(BiConsumer<Category, Operation> itemSaver,
            Consumer<Category> itemDeleter) {
        super("category", itemSaver, itemDeleter);

        addNameField();
    }

    private void addNameField() {
        getFormLayout().add(categoryNameField);

        getBinder().forField(categoryNameField)
                .withConverter(String::trim, String::trim)
                .withValidator(new StringLengthValidator(
                        "Category name must contain at least 3 printable characters",
                        3, null))
                .withValidator(
                        name -> CategoryService.getInstance()
                                .findCategories(name).size() == 0,
                        "Category name must be unique")
                .bind(Category::getName, Category::setName);
    }

    @Override
    protected void confirmDelete() {
        int reviewCount = ReviewService.getInstance()
                .findReviews(getCurrentItem().getName()).size();
        if (reviewCount > 0) {
            openConfirmationDialog("Delete category",
                    "Are you sure you want to delete the “"
                            + getCurrentItem().getName()
                            + "” category? There are " + reviewCount
                            + " reviews associated with this category.",
                    "Deleting the category will mark the associated reviews as “undefined”. "
                            + "You can edit individual reviews to select another category.");
        } else {
            doDelete(getCurrentItem());
        }
    }
}
