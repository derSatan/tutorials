package de.jenshardt.springbootfeatures.fileaccess;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ReadWithSpel {

	// This SPeL needs a custom utility class to be called
	@Value("#{T(de.jenshardt.springbootfeatures.fileaccess.ResourceReader).getContent('items.csv')}")
	private String resourceStringUsingSpel;

	public String getContentFromResource() {
		return resourceStringUsingSpel;
	}
}
