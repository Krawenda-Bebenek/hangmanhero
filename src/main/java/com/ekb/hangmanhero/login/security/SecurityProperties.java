package com.ekb.hangmanhero.login.security;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.Data;

@Data
@Component
@Validated
@ConfigurationProperties(prefix = "plus.energia.security")
public class SecurityProperties {

    @NotNull
    private Long tokenValidityInMinutes;

    private String changePassUri;

}
