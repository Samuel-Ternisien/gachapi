package imt.api.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class AccountApp {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		SpringApplication.run(AccountApp.class, args);
	}

}
