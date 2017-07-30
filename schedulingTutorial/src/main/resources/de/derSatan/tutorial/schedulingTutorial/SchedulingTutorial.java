package de.derSatan.tutorial.schedulingTutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SchedulingTutorial {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SchedulingTutorial.class);
    }
}