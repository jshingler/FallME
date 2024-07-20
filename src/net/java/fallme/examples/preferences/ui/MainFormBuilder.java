package net.java.fallme.examples.preferences.ui;

import net.java.fallme.beans.BeanBuilder;
import net.java.fallme.beans.BeanConfig;
import net.java.fallme.examples.preferences.PreferencesMobileBeanConfigs;

public class MainFormBuilder extends BeanBuilder {

	protected void map(Object bean, String key, BeanConfig value) {
		MainForm b = (MainForm) bean;

		if (PreferencesMobileBeanConfigs.PREFERENCE_FORM.equals(key)) {
			b.setPreferenceForm((PreferenceForm) value.getBean());
		}
	}

	// public Object build(BeanConfig beanConfig) {
	// Display display = (Display)BeanFactory.getBean(BeanConfigs.DISPLAY);
	//		
	// // Std Stuff, Create Form and Set Dispaly
	// MainForm bean = new MainForm();
	// bean.setDisplay(display);
	//		
	// // Special By Form
	// bean.setPreferencesService((PreferencesService)BeanFactory.getBean(PreferencesMobileBeanConfigs.PREFERENCES_SERVICE));
	//		
	// // More Standard Stuff
	// bean.init();
	//		
	// return bean;
	// }

}
