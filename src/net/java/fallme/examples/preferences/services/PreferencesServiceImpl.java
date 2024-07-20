package net.java.fallme.examples.preferences.services;

import net.java.fallme.examples.preferences.data.PreferenceDAO;
import net.java.fallme.examples.preferences.data.PreferenceDAOImpl;


public class PreferencesServiceImpl implements PreferencesService {

  private static PreferenceDAO prefDAO = new PreferenceDAOImpl();

  public String get(String name) {
    return prefDAO.get(name);
  }

  public void put(String name, String value) {
    prefDAO.put(name, value);
  }
}
