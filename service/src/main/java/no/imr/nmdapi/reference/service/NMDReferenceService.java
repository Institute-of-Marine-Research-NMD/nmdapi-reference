package no.imr.nmdapi.reference.service;

import no.imr.nmdapi.generic.response.v1.ListElementType;


/**
 * Service API for reference data.
 *
 * @author kjetilf
 */
public interface NMDReferenceService {

    /**
     * Get .
     *
     * @param name
     * @return
     */
    Object getData(String name);

    /**
     * Delete
     *
     * @param name
     */
    void deleteData(String name);

    /**
     * Update
     *
     * @param name
     * @param projectType
     */
    void updateData(String name, Object data);

    /**
     * Insert
     *
     * @param name
     * @param projectType
     */
    void insertData(String name, Object data);

    /**
     *
     * @return
     */
    ListElementType list();

}
