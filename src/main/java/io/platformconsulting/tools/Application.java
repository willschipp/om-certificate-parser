package io.platformconsulting.tools;

import io.platformconsulting.tools.parser.OmResponseParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.Assert;
import org.yaml.snakeyaml.Yaml;

@SpringBootApplication
public class Application {


    public static void main(String... args) {
        SpringApplication.run(Application.class,args);
    }

    @Bean
    public Yaml yaml() {
        return new Yaml();
    }
}
