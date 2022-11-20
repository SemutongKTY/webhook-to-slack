package com.semutong.webhook.config;

import com.semutong.webhook.util.ServerSettingValues;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	private final ServerSettingValues serverSettingValues;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html")
				.addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		if(serverSettingValues.getIsProduction()) {
			registry.addMapping("/**")
					.allowedOriginPatterns("*.the33refund.com", "*.semutong.com", "*.fincube.co.kr")
					.allowedHeaders("*")
					.allowedMethods(
							"OPTIONS",
							"GET",
							"POST",
							"PUT",
							"DELETE")
					.exposedHeaders("X-Total-Count");
		} else {
			registry.addMapping("/**")
					.allowedOriginPatterns("*")
					.allowedHeaders("*")
					.allowedMethods(
							"OPTIONS",
							"GET",
							"POST",
							"PUT",
							"DELETE")
					.exposedHeaders("X-Total-Count");
		}
	}
}
