package de.jenshardt.rdemo;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class RHelper {
	@Value("classpath:script.R")
	Resource resourceFile;
	
	public RHelper() { }

	public String getMeanScriptContent() throws IOException, URISyntaxException {
	    return Files.readString(resourceFile.getFile().toPath());
	}
}
