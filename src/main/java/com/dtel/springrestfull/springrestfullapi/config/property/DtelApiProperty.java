package com.dtel.springrestfull.springrestfullapi.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * DtelApiProperty
 */
@ConfigurationProperties("dtel")
public class DtelApiProperty {

    private final Seguranca seguranca = new Seguranca();

    /**
     * @return the seguranca
     */
    public Seguranca getSeguranca() {
        return seguranca;
    }

    /**
     * InnerDtelApiProperty
     */
    public static class Seguranca {
        private boolean enableHttps;

        /**
         * @return the enableHttps
         */
        public boolean isEnableHttps() {
            return enableHttps;
        }

        /**
         * @param enableHttps the enableHttps to set
         */
        public void setEnableHttps(boolean enableHttps) {
            this.enableHttps = enableHttps;
        }
    }

}