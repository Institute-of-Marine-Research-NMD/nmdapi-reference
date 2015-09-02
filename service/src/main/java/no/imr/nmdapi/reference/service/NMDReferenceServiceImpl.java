package no.imr.nmdapi.reference.service;

import java.util.List;
import no.imr.nmdapi.dao.file.NMDSeriesReferenceDao;
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
     * Data type.
     */
    private static final String TYPE = "reference";

    @Autowired
    private Configuration configuration;

    @Autowired
    private NMDSeriesReferenceDao seriesReferenceDao;

    @Override
    public Object getData(final String name) {
        return seriesReferenceDao.get(name, "no.imr.commons.nmdreference.domain.v1");
    }

    @Override
    public void deleteData(final String name) {
        seriesReferenceDao.delete(TYPE, name, true);
    }

   @Override
    public void insertData(final String name, final Object data) {
        String readRole = configuration.getString("default.readrole");
        String writeRole = configuration.getString("default.writerole");
        String owner = configuration.getString("default.owner");
        seriesReferenceDao.insert(writeRole, readRole, owner, TYPE, name, data, true);
    }

    @Override
    public void updateData(final String name, final Object data) {
        seriesReferenceDao.update(name, data);

    }

    @Override
    public ListElementType list() {
        List<String> names = seriesReferenceDao.list();
        ListElementType elementType = new ListElementType();
        for (String name : names) {
            ResultElementType resultElementType = new ResultElementType();
            resultElementType.setResult(name);
            elementType.getElement().add(resultElementType);
        }
        return elementType;
    }

}
