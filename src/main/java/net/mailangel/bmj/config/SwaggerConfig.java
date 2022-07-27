package net.mailangel.bmj.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI(@Value("${springdoc.version}") String appVersion) {
        return new OpenAPI()
                .components(new Components())
                .info( getApiInfo( appVersion) );

    }

    private Info getApiInfo(String appVersion) {
        return new Info()
                .title("BMJ Java Programming challenge")
                .description("BMJ API Documentation")
                .license(new License().name("Apache 2.0").url("http://springdoc.org") )
                .contact(new Contact().name("Mo").email("Mo@MailAngel.net").url("https://payzool.net"))
                .version( appVersion );
    }

}
