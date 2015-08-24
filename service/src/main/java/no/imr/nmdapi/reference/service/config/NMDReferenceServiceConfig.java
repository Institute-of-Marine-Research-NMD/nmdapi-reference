package no.imr.nmdapi.reference.service.config;

import no.imr.nmdapi.reference.service.NMDReferenceService;
import no.imr.nmdapi.reference.service.NMDReferenceServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This contains all configuration for the reference services.
 *
 * @author kjetilf
 */
@Configuration
public class NMDReferenceServiceConfig {

    /**
     * Creates the service implementation.
     *
     * @return  A reference service implementation.
     */
    @Bean(name="nmdReferenceService")
    public NMDReferenceService getNMDReferenceService() {
        return new NMDReferenceServiceImpl();
    }

}
