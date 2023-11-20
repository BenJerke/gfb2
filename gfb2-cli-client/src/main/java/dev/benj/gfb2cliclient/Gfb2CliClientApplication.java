package dev.benj.gfb2cliclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.benj.gfb2cliclient.services.security.AuthService;
import dev.benj.gfb2cliclient.services.security.LoginDto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import java.nio.file.AccessDeniedException;
import java.util.Scanner;

@SpringBootApplication
public class Gfb2CliClientApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Gfb2CliClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String token = "";
		AuthService authService = new AuthService(new RestTemplate(), new ObjectMapper());
		Scanner userInput = new Scanner(System.in);
		System.out.println("Hello!");
		String input = "";
		while (!input.equals("quit") ){
			System.out.println("Choose an option: ");
			System.out.println("1) Log In");
			System.out.println("2) Register");
			input = userInput.nextLine();
			if (input.equals("1")){
				String username = "";
				String password = "";
				System.out.println("Enter your username: ");
				username = userInput.next();
				System.out.println("Enter your password: ");
				password = userInput.next();
				try{
					LoginDto login = new LoginDto();
					login.setPassword(password);
					login.setUsername(username);
					authService.login(login);
					System.out.println("Login successful.");
				} catch (RuntimeException e){
					System.out.println(e.getMessage());
					throw e;
				}
			}




		}



	}
}
