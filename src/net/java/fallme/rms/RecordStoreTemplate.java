/**
 * FallME from java.net (https://fallme.dev.java.net)
 * Copyright Jim Shingler (jimshing@aol.com) and Christopher M. Judd (cjudd@juddsolutions.com)
 * License: Apache 2.0
 */
package net.java.fallme.rms;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import javax.microedition.rms.RecordComparator;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordFilter;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreFullException;
import javax.microedition.rms.RecordStoreNotFoundException;

public abstract class RecordStoreTemplate {

    /**
     * Gets instance of record store.
     * 
     * @return record store.
     * @throws RecordStoreFullException
     *           if the operation cannot be completed because the record store is
     *           full.
     * @throws RecordStoreNotFoundException
     *           if the record store could not be found.
     * @throws RecordStoreException
     *           if a record store-related exception occurred.
     */
    protected RecordStore getRecordStore() throws RecordStoreFullException, RecordStoreNotFoundException,
            RecordStoreException {
        return RecordStore.openRecordStore(getRecordStoreName(), createIfNecessary());
    }

    /**
     * Find records in a record store.
     * 
     * @param filter
     *          if non-null, will be used to determine what subset of the record
     *          store records will be used.
     * @param comparator
     *          if non-null, will be used to determine the order in which the
     *          records are returned.
     * @param keepUpdated
     *          if true, the enumerator will keep its enumeration current with any
     *          changes in the records of the record store. Use with caution as
     *          there are possible performance consequences. If false the
     *          enumeration will not be kept current and may return recordIds for
     *          records that have been deleted or miss records that are added
     *          later. It may also return records out of order that have been
     *          modified after the enumeration was built. Note that any changes to
     *          records in the record store are accurately reflected when the
     *          record is later retrieved, either directly or through the
     *          enumeration. The thing that is risked by setting this parameter
     *          false is the filtering and sorting order of the enumeration when
     *          records are modified, added, or deleted.
     * @param handler
     *          object that will extract records, one row at a time
     */
    protected void find(RecordFilter filter, RecordComparator comparator, boolean keepUpdated,
            RecordCallbackHandler handler) {
        try {
            RecordStore rs = getRecordStore();
            RecordEnumeration records = rs.enumerateRecords(filter, comparator, keepUpdated);
            while (records.hasNextElement()) {
                int id = records.nextRecordId();
                byte[] data = rs.getRecord(id);
                handler.processRecord(id, data);
            }
        } catch (RecordStoreException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Unable to read all records from " + getRecordStoreName() + " RecordStore.");
        }
    }

    /**
     * Finds all records in a record store.
     */
    protected void find(RecordCallbackHandler handler) {
        find(null, null, false, handler);
    }

    /**
     * Save data to record store.
     * 
     * @param data
     * @return recordId
     */
    protected int save(byte[] data) {
        int recId;
        try {
            RecordStore rs = getRecordStore();
            recId = rs.addRecord(data, 0, data.length);

        } catch (RecordStoreException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Unable to save record to " + getRecordStoreName() + " RecordStore.");
        }
        return recId;
    }

    /**
     * update record in record store.
     * 
     * @param data
     * @return recordId
     */
    protected void update(int recId, byte[] data) {
        try {
            RecordStore rs = getRecordStore();
            rs.setRecord(recId, data, 0, data.length);

        } catch (RecordStoreException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Unable to update record to " + getRecordStoreName() + " RecordStore.");
        }
    }

    /**
     * Find by id.
     * 
     * @param recordId
     *          Id of record to delete.
     */
    public void find(int recordId, RecordCallbackHandler handler) {
        try {
            RecordStore rs = getRecordStore();
            byte[] data = rs.getRecord(recordId);
            handler.processRecord(recordId, data);
        } catch (RecordStoreException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to find recored " + recordId + " from " + getRecordStoreName() + " RecordStore.");
        }
    }

    /**
     * Delete record by id.
     * 
     * @param recordId
     *          Id of record to delete.
     */
    public void delete(int recordId) {
        try {
            RecordStore rs = getRecordStore();
            // Can't delete recordId 0
            if (recordId != 0) {
                rs.deleteRecord(recordId);
            }
        } catch (RecordStoreException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to delete recored " + recordId + " from " + getRecordStoreName() + " RecordStore.");
        }
    }

    /**
     * Utility to convert objects to bytes.
     * 
     * @param mapper
     *          ByteMapper.
     * @return objects as a byte.
     */
    protected byte[] convert(ByteMapper mapper) {

        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        DataOutputStream dout = new DataOutputStream(bout);

        try {
            mapper.map(dout);
            dout.flush();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to close stream during convertions");
        }
        return bout.toByteArray();
    }

    /**
     * Name of record store the record store to store data in.<br>
     * This is abstract because sub classes must provide specific RecordStore
     * name.
     * 
     * @return Name of RecordStore.
     */
    protected abstract String getRecordStoreName();

    /**
     * If true, the record store will be created if necessary.<br>
     * Defaults to true but can be overriden by sub classes.
     * 
     * @return if records store should be created.
     */
    protected boolean createIfNecessary() {
        return true;
    }
}
