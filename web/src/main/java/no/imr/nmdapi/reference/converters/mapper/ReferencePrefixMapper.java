package no.imr.nmdapi.reference.converters.mapper;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

/**
 * Echosounder namespace prefix mapper.
 *
 * @author kjetilf
 */
public class ReferencePrefixMapper extends NamespacePrefixMapper {

    public static final String ECHO_NS = "http://www.imr.no/formats/reference/v1";

    @Override
    public String getPreferredPrefix(String namespaceUri,
                               String suggestion,
                               boolean requirePrefix) {
        return "";
    }

}
