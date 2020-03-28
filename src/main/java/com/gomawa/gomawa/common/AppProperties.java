package com.gomawa.gomawa.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;

@Component
@ConfigurationProperties(prefix = "gomawa")
@Getter @Setter
public class AppProperties {

    @NotEmpty
    private String loginClientIdNaver;

    @NotEmpty
    private String loginRedirectUrlNaver;
}
