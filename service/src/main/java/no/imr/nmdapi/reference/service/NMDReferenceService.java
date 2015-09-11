package no.imr.nmdapi.reference.service;

import no.imr.nmd.commons.dataset.jaxb.DatasetType;
import no.imr.nmd.commons.dataset.jaxb.DatasetsType;
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
     * @param data
     */
    void updateData(String name, Object data);

    /**
     * Insert
     *
     * @param name
     * @param data
     */
    void insertData(String name, Object data);

    /**
     *
     * @return
     */
    ListElementType list();

    /**
     *
     * @return
     */
    DatasetsType listDatasets();

    /**
     *
     * @param datasetType
     */
    void updateDatasets(DatasetType datasetType);

    /**
     *
     * @param name
     * @return
     */
    Object getInfo(String name);

}
