package pl.pjatk.jazs24310nbp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI myOpenApi() {
        Contact contact = new Contact();
        contact.setEmail("s24310@pjwstk.edu.pl");
        contact.setName("Michał");

        Info info = new Info()
                .title("Rest API do kolosa")
                .version("5.0? B)")
                .contact(contact)
                .description("Kolosowe API do zarządzania zapytaniami do API NBP");

        return new OpenAPI().info(info);
    }

}
