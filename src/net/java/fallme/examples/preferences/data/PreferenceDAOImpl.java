package net.java.fallme.examples.preferences.data;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.java.fallme.rms.ByteMapper;
import net.java.fallme.rms.RecordCallbackHandler;
import net.java.fallme.rms.RecordStoreTemplate;

public class PreferenceDAOImpl extends RecordStoreTemplate implements
		PreferenceDAO {

	private static final String RECORD_STORE = "preferenceRS";

	public void delete(String name) {
		find(new NameFilter(name), null, true, new RecordCallbackHandler() {
			public void processRecord(int id, byte[] data) {
				delete(id);
			}
		});
	}

	public String get(String name) {
		final String result[] = new String[1]; 
		// to get around assignment in the inner class.
		find(new NameFilter(name), null, true, new RecordCallbackHandler() {
			public void processRecord(int id, byte[] data) {
				ByteArrayInputStream bin = new ByteArrayInputStream(data);
				DataInputStream din = new DataInputStream(bin);

				try {
					din.readUTF();
					result[0] = din.readUTF();
					din.close();
					bin.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		return result[0];
	}

	public void put(String name, String value) {
		delete(name);
		byte[] data = convert(name, value);
		int recid = save(data);
	}

	/**
	 * Converts a name/value to a byte array.
	 */
	private byte[] convert(final String name, final String value) {

		byte[] data = convert(new ByteMapper() {
			public void map(DataOutputStream out) throws Exception {
				out.writeUTF(name);
				out.writeUTF(value);
			}
		});
		return data;
	}

	protected String getRecordStoreName() {
		return RECORD_STORE;
	}

}
