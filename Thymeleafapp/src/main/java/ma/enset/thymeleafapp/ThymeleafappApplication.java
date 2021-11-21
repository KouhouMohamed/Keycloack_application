package ma.enset.thymeleafapp;

import ma.enset.thymeleafapp.entities.Product;
import ma.enset.thymeleafapp.repositories.ProductRepository;
import org.keycloak.adapters.springsecurity.client.KeycloakClientRequestFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ThymeleafappApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThymeleafappApplication.class, args);
    }
    @Bean
    CommandLineRunner start(ProductRepository productRepository){
        return args -> {
            productRepository.save(new Product(null,"Computer",7600,34));
            productRepository.save(new Product(null,"Printer",1600,134));
            productRepository.save(new Product(null,"Smartphone",1600,34));
        };
    }



}
