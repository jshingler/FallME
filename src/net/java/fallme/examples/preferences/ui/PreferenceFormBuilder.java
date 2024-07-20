package net.java.fallme.examples.preferences.ui;

import net.java.fallme.beans.BeanBuilder;
import net.java.fallme.beans.BeanConfig;
import net.java.fallme.examples.preferences.PreferencesMobileBeanConfigs;
import net.java.fallme.examples.preferences.services.PreferencesService;

public class PreferenceFormBuilder extends BeanBuilder {
	
	protected void map(Object bean, String key, BeanConfig value) {
		PreferenceForm b = (PreferenceForm)bean;
		
		if (PreferencesMobileBeanConfigs.PREFERENCES_SERVICE.equals(key)) {
        	b.setPreferenceService((PreferencesService)value.getBean());
        } 
	}
}
