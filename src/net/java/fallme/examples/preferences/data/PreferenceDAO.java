package net.java.fallme.examples.preferences.data;

public interface PreferenceDAO {

  public void put(String name, String value);

  public String get(String name);

  public void delete(String name);

}
