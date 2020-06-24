package de.jenshardt.springbootfeatures.fileaccess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
public class GetResourceLoader {
	
	@Autowired
	ResourceLoader resourceLoader;
	
	public Resource getResource() {
		
		return resourceLoader.getResource("classpath:items.csv");
	}
}
