package com.semutong.webhook.util;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ServerSettingValues {

    @Value("${server-setting-values.is-production}")
    private Boolean isProduction;

    @Value("${server-setting-values.enable-scheduler}")
    private Boolean enableScheduler;
}
