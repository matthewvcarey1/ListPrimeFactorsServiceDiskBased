package uk.co.example.ListPrimeFactorsService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class ListPrimeFactorsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ListPrimeFactorsServiceApplication.class, args);
	}

}
