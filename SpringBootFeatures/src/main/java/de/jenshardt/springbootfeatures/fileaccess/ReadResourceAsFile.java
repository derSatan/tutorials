package de.jenshardt.springbootfeatures.fileaccess;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class ReadResourceAsFile {

	
	public String getContent(Resource resource) throws IOException {
		File file = resource.getFile();
		
		return new String(Files.readAllBytes(file.toPath()));
	}
}
