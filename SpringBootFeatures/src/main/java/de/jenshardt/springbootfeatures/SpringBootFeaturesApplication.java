package de.jenshardt.springbootfeatures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.jenshardt.springbootfeatures.fileaccess.FileAccessDemo;

@SpringBootApplication
public class SpringBootFeaturesApplication implements CommandLineRunner {

	
	@Autowired
	FileAccessDemo fad;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootFeaturesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {		
		fad.readFileContentFromClasspath(); 
		
	}
	
}
