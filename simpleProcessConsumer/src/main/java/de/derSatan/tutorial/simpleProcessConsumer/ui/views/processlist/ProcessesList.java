package de.derSatan.tutorial.simpleProcessConsumer.ui.views.processlist;

import java.util.List;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.ModelItem;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.Encode;
import com.vaadin.flow.templatemodel.TemplateModel;

import de.derSatan.tutorial.simpleProcessConsumer.backend.Review;
import de.derSatan.tutorial.simpleProcessConsumer.backend.ReviewService;
import de.derSatan.tutorial.simpleProcessConsumer.ui.MainLayout;
import de.derSatan.tutorial.simpleProcessConsumer.ui.common.AbstractEditorDialog;
import de.derSatan.tutorial.simpleProcessConsumer.ui.encoders.LocalDateToStringEncoder;
import de.derSatan.tutorial.simpleProcessConsumer.ui.encoders.LongToStringEncoder;
import de.derSatan.tutorial.simpleProcessConsumer.ui.views.processlist.ProcessesList.ReviewsModel;

/**
 * Displays the list of available categories, with a search filter as well as
 * buttons to add a new category or edit existing ones.
 *
 * Implemented using a simple template.
 */
@Route(value = "", layout = MainLayout.class)
@PageTitle("Processes List")
@Tag("processes-list")
@HtmlImport("frontend://src/views/processeslist/processes-list.html")
public class ProcessesList extends PolymerTemplate<ReviewsModel> {

    public interface ReviewsModel extends TemplateModel {
        @Encode(value = LongToStringEncoder.class, path = "id")
        @Encode(value = LocalDateToStringEncoder.class, path = "date")
        @Encode(value = LongToStringEncoder.class, path = "category.id")
        void setReviews(List<Review> reviews);
    }

    @Id("search")
    private TextField search;
    @Id("newProcess")
    private Button newProcess;
    @Id("header")
    private H2 header;

    private ProcessEditorDialog reviewForm = new ProcessEditorDialog(
            this::saveUpdate, this::deleteUpdate);

    public ProcessesList() {
        search.setPlaceholder("Search processes");
        search.addValueChangeListener(e -> updateList());
        search.setValueChangeMode(ValueChangeMode.EAGER);

        newProcess.addClickListener(e -> openForm(new Review(),
                AbstractEditorDialog.Operation.ADD));

        updateList();

    }

    public void saveUpdate(Review review,
            AbstractEditorDialog.Operation operation) {
        ReviewService.getInstance().saveReview(review);
        updateList();
        Notification.show(
                "Process successfully " + operation.getNameInText() + "ed.",
                3000, Position.BOTTOM_START);
    }

    public void deleteUpdate(Review review) {
        ReviewService.getInstance().deleteReview(review);
        updateList();
        Notification.show("Process successfully deleted.", 3000,
                Position.BOTTOM_START);
    }

    private void updateList() {
        List<Review> reviews = ReviewService.getInstance()
                .findReviews(search.getValue());
        if (search.isEmpty()) {
            header.setText("Reviews");
            header.add(new Span(reviews.size() + " in total"));
        } else {
            header.setText("Search for “" + search.getValue() + "”");
            if (!reviews.isEmpty()) {
                header.add(new Span(reviews.size() + " results"));
            }
        }
        getModel().setReviews(reviews);
    }

    @EventHandler
    private void edit(@ModelItem Review review) {
        openForm(review, AbstractEditorDialog.Operation.EDIT);
    }

    private void openForm(Review review,
            AbstractEditorDialog.Operation operation) {
        // Add the form lazily as the UI is not yet initialized when
        // this view is constructed
        if (reviewForm.getElement().getParent() == null) {
            getUI().ifPresent(ui -> ui.add(reviewForm));
        }
        reviewForm.open(review, operation);
    }

}
