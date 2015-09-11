package no.imr.nmdapi.reference.controller;

import no.imr.framework.logging.slf4j.aspects.stereotype.PerformanceLogging;
import no.imr.nmd.commons.dataset.jaxb.DatasetType;
import no.imr.nmd.commons.dataset.jaxb.DatasetsType;
import no.imr.nmdapi.generic.response.v1.ListElementType;
import no.imr.nmdapi.reference.service.NMDReferenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Controller object for mission requests.
 *
 * @author kjetilf
 */
@Controller
public class ReferenceController {

    /**
     * Url part that defines it as mission.
     */
    public static final String REFERENCE_URL = "/reference";

    /**
     * Class logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ReferenceController.class);

    /**
     * Service layer object for nmd mission queries.
     */
    @Autowired
    private NMDReferenceService refService;

    /**
     * Get data.
     *
     * @param name
     * @return
     */
    @PerformanceLogging
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object find(@PathVariable(value = "name") String name) {
        LOGGER.info("Start ReferenceController.find");
        return refService.getData(name);
    }

    /**
     * Delete cruiseserie data for mission.
     *
     * @param name
     */
    @PerformanceLogging
    @RequestMapping(value = "/{name}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void delete(@PathVariable(value = "name") String name) {
        LOGGER.info("Start ReferenceController.delete");
        refService.deleteData(name);
    }

    /**
     *  Insert mission data for mission.
     *
     * @param name
     * @param data
     */
    @PerformanceLogging
    @RequestMapping(value = "/{name}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void insert(@PathVariable(value = "name") String name, @RequestBody Object data) {
        LOGGER.info("Start ReferenceController.insert");
        refService.insertData(name, data);
    }

    /**
     * Update  mission data for mission.
     *
     * @param name
     * @param data
     */
    @PerformanceLogging
    @RequestMapping(value = "/{name}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void update(@PathVariable(value = "name") String name, @RequestBody Object data) {
        LOGGER.info("Start ReferenceController.update");
        refService.updateData(name, data);
    }

    /**
     * List all reference tables.
     *
     * @return
     */
    @PerformanceLogging
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ListElementType list() {
        LOGGER.info("Start ReferenceController.list");
        return refService.list();
    }

    /**
     * List all dataset information.
     *
     * @return
     */
    @PerformanceLogging
    @RequestMapping(params = "dataset",value = "/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public DatasetsType listDatasets() {
        LOGGER.info("Start ReferenceController.listDatasets");
        return refService.listDatasets();
    }

    /**
     * Update dataset information.
     *
     * @param dataset
     */
    @PerformanceLogging
    @RequestMapping(params = "dataset",value = "/", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void updateDatasetInfo(@RequestBody DatasetType dataset) {
        LOGGER.info("Start ReferenceController.updateDatasetInfo");
        refService.updateDatasets(dataset);
    }

    /**
     * Get data.
     *
     * @param name
     * @return
     */
    @PerformanceLogging
    @RequestMapping(value = "/{name}/info", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object findInfo(@PathVariable(value = "name") String name) {
        LOGGER.info("Start ReferenceController.findInfo");
        return refService.getInfo(name);
    }

}

