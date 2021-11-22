package com.ekb.hangmanhero.login.security.autoconfiguration;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.Data;
import lombok.Getter;

@Data
@Component
@Validated
@ConfigurationProperties(prefix = "hangmanhero.security.autoconfiguration")
public class SecurityProperties {

    @NotNull
    private Long tokenValidityInMinutes;

    private String changePassUri;

}
