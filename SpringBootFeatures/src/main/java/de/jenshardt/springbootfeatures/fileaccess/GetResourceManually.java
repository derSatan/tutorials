package de.jenshardt.springbootfeatures.fileaccess;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class GetResourceManually {

	public Resource getResource() {

		return new ClassPathResource("items.csv");
	}

}
