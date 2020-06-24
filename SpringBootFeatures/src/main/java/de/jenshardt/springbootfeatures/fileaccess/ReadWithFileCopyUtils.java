package de.jenshardt.springbootfeatures.fileaccess;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

@Component
public class ReadWithFileCopyUtils {

	private static final String UTF_8 = "UTF-8";

	public String getContent(Resource resource) throws IOException {
		String content;
		
		try(Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)) {
			content = FileCopyUtils.copyToString(reader);
		};
		
		return content;
	}
}
