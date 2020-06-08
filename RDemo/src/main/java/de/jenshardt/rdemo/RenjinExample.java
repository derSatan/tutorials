package de.jenshardt.rdemo;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.script.ScriptException;

import org.renjin.script.RenjinScriptEngine;
import org.renjin.sexp.DoubleArrayVector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/* 
 * The main advantage of Renjin is that it doesn't require an R installation 
 * as it uses a JVM-based interpreter. 
 * However, Renjin is currently not 100% compatible with GNU R.
 */
@Component
public class RenjinExample {

	private RHelper rHelper;
	
	@Autowired
	public RenjinExample(RHelper rHelper) {
		this.rHelper = rHelper;
	}
	
	public double mean(int[] values) throws IOException, URISyntaxException, ScriptException {
	    RenjinScriptEngine engine = new RenjinScriptEngine();
	    String meanScriptContent = rHelper.getMeanScriptContent();
	    engine.put("input", values);
	    engine.eval(meanScriptContent);
	    DoubleArrayVector result = (DoubleArrayVector) engine.eval("customMean(input)");
	 
	    return result.asReal();
	}	
}
