package com.maxPotential.MaxxEnergy;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class ApplicationConfiguration {

    /*
     * This class only exists to enable component scan at this package level and let all Spring configuration
     * happen automatically
     */
    public ApplicationConfiguration() {
        super();
    }

}