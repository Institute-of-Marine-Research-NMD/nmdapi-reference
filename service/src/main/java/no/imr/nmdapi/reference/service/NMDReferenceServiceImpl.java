package no.imr.nmdapi.reference.service;

import java.util.List;
import javax.xml.namespace.QName;
import no.imr.nmd.commons.dataset.jaxb.DataTypeEnum;
import no.imr.nmd.commons.dataset.jaxb.DatasetType;
import no.imr.nmd.commons.dataset.jaxb.DatasetsType;
import no.imr.nmdapi.dao.file.NMDSeriesReferenceDao;
import no.imr.nmdapi.generic.response.v1.ElementWithParamsType;
import no.imr.nmdapi.generic.response.v1.ListElementType;
import no.imr.nmdapi.generic.response.v1.ListElementWithParamsType;
import no.imr.nmdapi.generic.response.v1.OptionKeyValueListType;
import no.imr.nmdapi.generic.response.v1.OptionKeyValueType;
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

    @Autowired
    private Configuration configuration;

    @Autowired
    private NMDSeriesReferenceDao seriesReferenceDao;

    @Override
    public Object getData(final String name) {
        return seriesReferenceDao.get(name);
    }

    @Override
    public void deleteData(final String name) {
        seriesReferenceDao.delete(DataTypeEnum.REFERENCE, name, true);
    }

   @Override
    public void insertData(final String name, final Object data) {
        String readRole = configuration.getString("default.readrole");
        String writeRole = configuration.getString("default.writerole");
        String owner = configuration.getString("default.owner");
        seriesReferenceDao.insert(writeRole, readRole, owner, DataTypeEnum.REFERENCE, name, data, true);
    }

    @Override
    public void updateData(final String name, final Object data) {
        seriesReferenceDao.update(name, data);

    }

    @Override
    public ListElementWithParamsType list() {
        DatasetsType datasets = seriesReferenceDao.getDatasets();
        ListElementWithParamsType elementType = new ListElementWithParamsType();
        for (DatasetType dataset : datasets.getDataset()) {
            ElementWithParamsType resultElementType = new ElementWithParamsType();
            resultElementType.setValue(dataset.getDatasetName());
            QName qNameCreated = new QName("created");
            resultElementType.getOtherAttributes().put(qNameCreated, String.valueOf(dataset.getCreated().toGregorianCalendar().getTimeInMillis()));
            QName qNameUpdated = new QName("updated");
            resultElementType.getOtherAttributes().put(qNameUpdated, String.valueOf(dataset.getUpdated().toGregorianCalendar().getTimeInMillis()));
            elementType.getElement().add(resultElementType);
        }
        return elementType;
    }

    @Override
    public DatasetsType listDatasets() {
        return seriesReferenceDao.getDatasets();
    }

    @Override
    public void updateDatasets(DatasetType datasetType) {
        seriesReferenceDao.updateDataset(datasetType);
    }

    @Override
    public Object getInfo(String name) {
        String format = seriesReferenceDao.getRootNamespace(name);
        long checksum = seriesReferenceDao.getChecksum(name);
        long lastModified = seriesReferenceDao.getLastModified(name);
        OptionKeyValueListType keyValueListType = new OptionKeyValueListType();
        keyValueListType.getElement().add(getOptionKeyValueType("format", format));
        keyValueListType.getElement().add(getOptionKeyValueType("checksum", String.valueOf(checksum)));
        keyValueListType.getElement().add(getOptionKeyValueType("lastModified", String.valueOf(lastModified)));
        return keyValueListType;
    }

    private OptionKeyValueType getOptionKeyValueType(String key, String value) {
        OptionKeyValueType formatType = new OptionKeyValueType();
        formatType.setKey(key);
        formatType.setValue(value);
        return formatType;
    }

}
