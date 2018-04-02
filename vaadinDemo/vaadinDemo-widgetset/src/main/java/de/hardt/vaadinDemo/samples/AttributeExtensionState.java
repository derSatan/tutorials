package de.hardt.vaadinDemo.samples;

import com.vaadin.shared.JavaScriptExtensionState;

import java.util.HashMap;

/**
 * Shared state class for {@link AttributeExtension} communication from server
 * to client.
 */
public class AttributeExtensionState extends JavaScriptExtensionState {
	private static final long serialVersionUID = -6285075680149633113L;
	
	public HashMap<String, String> attributes = new HashMap<String, String>();
}
