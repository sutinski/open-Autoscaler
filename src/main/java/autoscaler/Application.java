package autoscaler;

import java.io.File;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import autoscaler.model.Catalog;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {
	@Bean
	public Catalog beanCatalog() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper jsonMapper = new ObjectMapper();
		File file = new File("config/catalog.json");
		return jsonMapper.readValue(file, Catalog.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}