package de.hardt.vaadinDemo.samples.backend;

import java.io.Serializable;
import java.util.Collection;

import de.hardt.vaadinDemo.samples.backend.healthcheck.data.Healthcheck;
import de.hardt.vaadinDemo.samples.backend.healthcheck.mock.MockDataService;

/**
 * Back-end service interface for retrieving and updating data.
 */
public abstract class HealthcheckDataService implements Serializable {
	private static final long serialVersionUID = 7891845553172466832L;

	public abstract Collection<Healthcheck> getAllHealthchecks();

    public abstract Healthcheck getHealthcheckById(int hcId);

    public static HealthcheckDataService get() {
        return MockDataService.getInstance();
    }

}
