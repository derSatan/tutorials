package de.jenshardt.rdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RDemoApplication implements CommandLineRunner {
	
	private static final int[] VALUES = {0,1,2,2,3,5,7,7,8,3,6,2,8,5,43,12,0,0,0,6};
	private static Logger LOG = LoggerFactory.getLogger(RDemoApplication.class);

	@Autowired
	RCallerExample rCaller;
	
	@Autowired
	RenjinExample renjin;
	
	@Autowired
	FastRExample fastR;
	
	public static void main(String[] args) {
		SpringApplication.run(RDemoApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {		
		LOG.info("Using RCaller, the rasult is {}", rCaller.mean(VALUES));
		LOG.info("Using Renjin, the rasult is {}", renjin.mean(VALUES));
		LOG.info("Using FastR, the rasult is {}", fastR.mean(VALUES));
	}

}
