package de.jenshardt.springbootfeatures.fileaccess;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

@Component
public class ResourceReader {
	
	private static final String UTF_8 = "UTF-8";

	@Autowired
	ResourceLoader loader;
	
    public static String getContent(String path) throws IOException {
    	Resource resource = new ClassPathResource(path);
		Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8);
		
		return FileCopyUtils.copyToString(reader);
    }
}