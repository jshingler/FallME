package net.java.fallme.examples.preferences.services;

public interface PreferencesService {
	
  static final String POINTMASTER = "pointmaster";
  static final String PHONE = "phone";
  static final String WATCH = "watch";
  static final String WATCHTIMERINTERVAL = "watchTimerInterval";
  static final String ID = "id";
  static final String URL = "url";
  
  String get(String name);

  void put(String name, String value);

}
