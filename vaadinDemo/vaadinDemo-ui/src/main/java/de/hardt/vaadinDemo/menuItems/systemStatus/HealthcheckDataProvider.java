package de.hardt.vaadinDemo.menuItems.systemStatus;

import java.util.Objects;
import java.util.stream.Stream;

import com.vaadin.data.provider.AbstractDataProvider;
import com.vaadin.data.provider.Query;

import de.hardt.vaadinDemo.samples.backend.HealthcheckDataService;
import de.hardt.vaadinDemo.samples.backend.healthcheck.data.Healthcheck;

public class HealthcheckDataProvider extends AbstractDataProvider<Healthcheck, String> {
	private static final long serialVersionUID = 7556235649224487211L;

	@Override
    public Integer getId(Healthcheck product) {
        Objects.requireNonNull(product, "Cannot provide an id for a null product.");
        
        return product.getId();
    }
    
    @Override
    public boolean isInMemory() {
        return true;
    }

    @Override
    public int size(Query<Healthcheck, String> t) {
        return (int) fetch(t).count();
    }

    @Override
    public Stream<Healthcheck> fetch(Query<Healthcheck, String> query) {
        
        return HealthcheckDataService.get().getAllHealthchecks().stream();
    }
}
