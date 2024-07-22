package id.beny.spring_api.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;


@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI definOpenAPI(){
        Server server = new Server();
        
        server.setUrl("http://localhost:8080");
        server.setDescription("controller");
        
        Contact contact = new Contact();
        contact.setName("Beny Irawan");
        contact.setEmail("Email");
        
        Info information = new Info()
        .title("Management System API")
        .version("1.0")
        .description("This API exposes endpont to manage ")
        .contact(contact);

        return new OpenAPI().info(information).servers(List.of(server));
    }
}
