package net.java.fallme.examples.preferences;

import net.java.fallme.beans.BeanConfig;
import net.java.fallme.beans.BeanConfigs;
import net.java.fallme.beans.BeanRef;
import net.java.fallme.examples.preferences.services.PreferencesServiceImpl;
import net.java.fallme.examples.preferences.ui.MainForm;
import net.java.fallme.examples.preferences.ui.MainFormBuilder;
import net.java.fallme.examples.preferences.ui.PreferenceForm;
import net.java.fallme.examples.preferences.ui.PreferenceFormBuilder;

public class PreferencesMobileBeanConfigs extends BeanConfigs {
	//MIDlet
	public static final String PREFERENCES_MOBILE = "PreferencesMobile";
	
	// UI Forms
	public static final String MAINFORM = "MainForm";
	public static final String PREFERENCE_FORM = "PreferenceForm";
	
	// Services
	public static final String PREFERENCES_SERVICE = "PreferencesService";
	
	// Integration points
	
	// Configuration
	static {
		// UI Layer
		set(new BeanConfig(MAINFORM)
			.setBean(MainForm.class)
		    .property(PREFERENCES_SERVICE, new BeanRef(PREFERENCES_SERVICE))
		    .property(PREFERENCE_FORM, new BeanRef(PREFERENCE_FORM))
			.setBuilder(MainFormBuilder.class));
		
		set(new BeanConfig(PREFERENCE_FORM)
			.setBean(PreferenceForm.class)
			.property(PREFERENCES_SERVICE, new BeanRef(PREFERENCES_SERVICE))
			.setBuilder(PreferenceFormBuilder.class));
		
		// Services
		set(new BeanConfig(PREFERENCES_SERVICE)
			.setBean(PreferencesServiceImpl.class));
		
		// Integration Points
		

	}
	
	
	
	
	
}
