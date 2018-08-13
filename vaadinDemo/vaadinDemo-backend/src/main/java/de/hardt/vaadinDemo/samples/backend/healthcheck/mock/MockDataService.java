package de.hardt.vaadinDemo.samples.backend.healthcheck.mock;

import java.util.Collection;
import java.util.List;

import de.hardt.vaadinDemo.samples.backend.HealthcheckDataService;
import de.hardt.vaadinDemo.samples.backend.healthcheck.data.Healthcheck;

/**
 * Mock data model. This implementation has very simplistic locking and does not
 * notify users of modifications.
 */
public class MockDataService extends HealthcheckDataService {
	private static final long serialVersionUID = 2475620906138049479L;

	private static HealthcheckDataService INSTANCE;
	
	private List<Healthcheck> hcList;
	
    private MockDataService() {
    	hcList = MockDataGenerator.createHealthchecks();
    }
    
	public static HealthcheckDataService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MockDataService();
        }
        return INSTANCE;
	}

	@Override
	public Collection<Healthcheck> getAllHealthchecks() {
		return hcList;
	}


	@Override
	public Healthcheck getHealthcheckById(int hcId) {
		for (Healthcheck h : hcList) {
            if (h.getId() == hcId) {
                return h;
            }
        }
        return null;
	}	
}
