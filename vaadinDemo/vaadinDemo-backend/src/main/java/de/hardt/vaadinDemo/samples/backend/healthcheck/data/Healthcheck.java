package de.hardt.vaadinDemo.samples.backend.healthcheck.data;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Healthcheck implements Serializable {
	private static final long serialVersionUID = 475046333703212062L;
	
	@NotNull
    private int id = -1;
    @NotNull
    @Size(min = 2, message = "Healthcheck name must have at least a description with two characters")
    private String description = "";
    private Date startDate;
    private Date endDate;
    private Date plannedDate;
    private Set<String> testcases;
    @NotNull
    private HealthcheckStatus status = HealthcheckStatus.PLANNED;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<String> getTestcases() {
		return testcases;
	}

	public void setTestcases(Set<String> testcases) {
		this.testcases = testcases;
	}

	public HealthcheckStatus getStatus() {
		return status;
	}

	public void setStatus(HealthcheckStatus status) {
		this.status = status;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getPlannedDate() {
		return plannedDate;
	}

	public void setPlannedDate(Date plannedDate) {
		this.plannedDate = plannedDate;
	}
}
