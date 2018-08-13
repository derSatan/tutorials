package de.hardt.vaadinDemo.samples.backend.healthcheck.data;

public enum HealthcheckStatus {
    OK("Alles ok"), WARNUNG("Einzelne Fehler"), ERROR("Fehler"), PLANNED("Ist geplant"), RUNNING("Läuft gerade");

    private final String name;

    private HealthcheckStatus(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
