package imt.api.gachapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import java.security.NoSuchAlgorithmException;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class GachapiApplication {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		SpringApplication.run(GachapiApplication.class, args);
	}

}
