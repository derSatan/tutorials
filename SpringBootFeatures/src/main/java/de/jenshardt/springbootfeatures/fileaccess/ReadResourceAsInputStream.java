package de.jenshardt.springbootfeatures.fileaccess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class ReadResourceAsInputStream {

	public String getContent(Resource resource) throws IOException {
		String response;
		
		InputStream stream = resource.getInputStream();
		
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
			response = reader.lines().collect(Collectors.joining("\n"));			
		}
		
		return response;
	}
}
