package app.mony.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MonyApiApplication {

	static void main(String[] args) {
		SpringApplication.run(MonyApiApplication.class, args);
	}

}
