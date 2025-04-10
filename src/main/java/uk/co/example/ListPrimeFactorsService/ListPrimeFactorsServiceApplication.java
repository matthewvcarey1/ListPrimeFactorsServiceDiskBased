package uk.co.example.ListPrimeFactorsService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication
public class ListPrimeFactorsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(
				ListPrimeFactorsServiceApplication.class, args
		);
	}

}
