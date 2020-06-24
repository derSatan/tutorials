package de.jenshardt.springbootfeatures.fileaccess;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class GetResourceValue {
	@Value("classpath:items.csv")
	Resource resourceFile;
	
	public Resource getResource() {
		
		return resourceFile;
	}
}
