package no.imr.nmdapi.reference.service;

import java.util.List;
import no.imr.nmdapi.dao.file.NMDDataDao;
import no.imr.nmdapi.exceptions.BadRequestException;
import no.imr.nmdapi.generic.response.v1.ListElementType;
import no.imr.nmdapi.generic.response.v1.ResultElementType;
import org.apache.commons.configuration.Configuration;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * NMDCruiseseries service layer implementation.
 *
 * @author kjetilf
 */
public class NMDReferenceServiceImpl implements NMDReferenceService {

    /**
     * Class logger.
     */
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(NMDReferenceServiceImpl.class);

    /**
     * Application properties.
     */
    @Autowired
    private Configuration configuration;

    @Autowired
    private NMDDataDao nmdDataDao;

    @Override
    public Object getData(final String name) {
        Class c;
        try {
            c = Class.forName("table.".concat(name));
            return nmdDataDao.get(name, c);
        } catch (ClassNotFoundException ex) {
            LOG.info("Class not found. ".concat(name));
            throw new BadRequestException("No information about. ".concat(name));
        }
    }

    @Override
    public void deleteData(final String name) {
        nmdDataDao.delete(name);
    }

   @Override
    public void insertData(final String name, final Object data) {
    }

    @Override
    public void updateData(final String name, final Object data) {
    }

    @Override
    public ListElementType list() {
        List<String> names = nmdDataDao.listSeries();
        ListElementType elementType = new ListElementType();
        for (String name : names) {
            ResultElementType resultElementType = new ResultElementType();
            resultElementType.setResult(name);
            elementType.getElement().add(resultElementType);
        }
        return elementType;
    }

}
