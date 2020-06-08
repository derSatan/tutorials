package de.jenshardt.rdemo;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.rcaller.rstuff.RCaller;
import com.github.rcaller.rstuff.RCallerOptions;
import com.github.rcaller.rstuff.RCode;

/*
 * RCaller is not suitable for small and frequent computations because of the time it 
 * takes to start the R process. This is a noticeable drawback. 
 * Also, RCaller works only with R installed on the local machine.
 */
@Component
public class RCallerExample {
	
	private RHelper rHelper;
	
	@Autowired
	public RCallerExample(RHelper rHelper) {
		this.rHelper = rHelper;
	}
		
	public double mean(int[] values) throws IOException, URISyntaxException {
	    String meanScriptContent = rHelper.getMeanScriptContent();
	    RCode code = RCode.create();
	    code.addRCode(meanScriptContent);
	    code.addIntArray("input", values);
	    code.addRCode("result <- customMean(input)");
	    RCaller caller = RCaller.create(code, RCallerOptions.create());
	    caller.runAndReturnResult("result");
	    return caller.getParser().getAsDoubleArray("result")[0];
	}
}
