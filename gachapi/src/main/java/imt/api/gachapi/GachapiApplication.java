package imt.api.gachapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class GachapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GachapiApplication.class, args);
	}

}
