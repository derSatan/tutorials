package de.jenshardt.rdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/*
 * FastR is only available on Linux and Darwin x64 systems.
 */
@Component
public class FastRExample {
	
	private static Logger LOG = LoggerFactory.getLogger(FastRExample.class);

	public double mean(int[] values) {
		
		LOG.info("Need to install and use GraalVM for that ...");
//	    Context polyglot = Context.newBuilder().allowAllAccess(true).build();
//	    String meanScriptContent = RUtils.getMeanScriptContent(); 
//	    polyglot.eval("R", meanScriptContent);
//	    Value rBindings = polyglot.getBindings("R");
//	    Value rInput = rBindings.getMember("c").execute(values);
//	    return rBindings.getMember("customMean").execute(rInput).asDouble();
		
	    return 0;
	}
}
