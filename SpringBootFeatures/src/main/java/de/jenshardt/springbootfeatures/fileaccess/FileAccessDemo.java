package de.jenshardt.springbootfeatures.fileaccess;

import java.io.IOException;
import java.io.UncheckedIOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class FileAccessDemo {

	@Autowired
	GetResourceManually rm;

	@Autowired
	GetResourceValue rv;

	@Autowired
	GetResourceLoader rl;

	@Autowired
	ReadResourceAsFile readAsFile;

	@Autowired
	ReadResourceAsInputStream readAsStream;

	@Autowired
	ReadWithFileCopyUtils readWithFileCopyUtils;
	
	@Autowired
	ReadWithSpel readWithSpel;

	private static Logger LOG = LoggerFactory.getLogger(FileAccessDemo.class);

	public void readFileContentFromClasspath() {

		try {
			LOG.info("Load file manually from classpath");
			Resource loadedManually = rm.getResource();
			LOG.info("File exists {}", loadedManually.exists());

			LOG.info("Load file by value annotation");
			Resource loadedByValue = rv.getResource();
			LOG.info("File exists {}", loadedByValue.exists());

			LOG.info("Load file with ResourceLoaded");
			Resource loadedByLoader = rl.getResource();
			LOG.info("File exists {}", loadedByLoader.exists());

			LOG.info("Read content from resource as file");
			String contentFile = readAsFile.getContent(loadedByValue);
			LOG.info("Content is >{}<", contentFile);

			LOG.info("Read content from resource as stream");
			String contentStream = readAsStream.getContent(loadedByValue);
			LOG.info("Content is >{}<", contentStream);

			LOG.info("Read content from resource with FileCopyUtils stream");
			String contentFileUtils = readWithFileCopyUtils.getContent(loadedByValue);
			LOG.info("Content is >{}<", contentFileUtils);
			
			LOG.info("Read content direct with SpEL");
			String contentWithSpel = readWithSpel.getContentFromResource();
			LOG.info("Content is >{}<", contentWithSpel);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

}
