package net.java.fallme.examples.preferences.data;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import javax.microedition.rms.RecordFilter;

/**
 * Filters a recored base on a particular name value.
 */
class NameFilter implements RecordFilter {

  String name;

  public NameFilter(String name) {
    this.name = name;
  }

  public boolean matches(byte[] data) {
    boolean result = false;
    ByteArrayInputStream bin = new ByteArrayInputStream(data);
    DataInputStream din = new DataInputStream(bin);

    try {
      String recordName = din.readUTF();
      if (name.equals(recordName)) {
        result = true;
        ;
      }
      din.close();
      bin.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return result;
  }
}