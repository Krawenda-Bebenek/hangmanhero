package com.ekb.hangmanhero.login.security.autoconfiguration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

public class SecurityAutoconfiguration {


    @ConditionalOnMissingBean(SecurityProperties.class)
    @Import(SecurityProperties.class)
    @Configuration
    public static class SecurityPropertiesConfiguration {
    }

}
