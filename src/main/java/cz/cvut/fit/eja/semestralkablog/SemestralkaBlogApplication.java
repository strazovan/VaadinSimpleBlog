package cz.cvut.fit.eja.semestralkablog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "cz")
public class SemestralkaBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(SemestralkaBlogApplication.class, args);
	}
}
