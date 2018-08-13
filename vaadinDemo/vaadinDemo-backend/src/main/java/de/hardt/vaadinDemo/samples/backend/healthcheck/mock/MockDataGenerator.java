package de.hardt.vaadinDemo.samples.backend.healthcheck.mock;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.hardt.vaadinDemo.samples.backend.healthcheck.data.Healthcheck;
import de.hardt.vaadinDemo.samples.backend.healthcheck.data.HealthcheckStatus;

public class MockDataGenerator {
    private static int nextHealthcheckId = 1;
    private static final String hcDescriptions[] = new String[] {
            "Healthcheck 1", "Healthcheck 2", "Healthcheck 3" };

	public static List<Healthcheck> createHealthchecks() {
		 List<Healthcheck> hcList = new ArrayList<Healthcheck>();
        for (String name : hcDescriptions) {
            Healthcheck hc = createHealthcheck(name);
            hcList.add(hc);
        }
        return hcList;
	}

	private static Healthcheck createHealthcheck(String name) {
		Healthcheck hc = new Healthcheck();
		hc.setDescription(name);
		hc.setId(nextHealthcheckId++);
		hc.setStatus(HealthcheckStatus.OK);
		Set<String> testcases = new HashSet<String>();
		testcases.add("1. Testfall");
		testcases.add("2. Testfall");
		hc.setTestcases(testcases);
		return hc;
	}
}
