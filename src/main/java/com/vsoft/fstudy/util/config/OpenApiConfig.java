package com.vsoft.fstudy.util.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {

    private static final String TITLE = "FStudy API";
    private static final String VERSION = "1.0.0";
    private static final String DESCRIPTION = "REST API for FStudy";
    private static final String CONTACT_NAME = "Terisu";
    private static final String CONTACT_EMAIL = "tranlenhat123456@gmail.com";
    private static final String LICENSE_NAME = "Apache 2.0";
    private static final String LICENSE_URL = "https://www.apache.org/licenses/LICENSE-2.0";
    private static final String SECURITY_SCHEME_NAME = "bearerAuth";
    private static final String SECURITY_SCHEME = "bearer";
    private static final String BEARER_FORMAT = "JWT";
    
    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title(TITLE)
                .version(VERSION)
                .description(DESCRIPTION)
                .contact(new Contact()
                    .name(CONTACT_NAME)
                    .email(CONTACT_EMAIL)
                )
                .license(new License()
                    .name(LICENSE_NAME)
                    .url(LICENSE_URL)
                )
            )
            .addSecurityItem(new SecurityRequirement()
                .addList(SECURITY_SCHEME_NAME)
            )
            .components(new Components()
                .addSecuritySchemes(SECURITY_SCHEME_NAME, new SecurityScheme()
                    .name(SECURITY_SCHEME_NAME)
                    .type(SecurityScheme.Type.HTTP)
                    .scheme(SECURITY_SCHEME)
                    .bearerFormat(BEARER_FORMAT)
                )
            );
    }
}
