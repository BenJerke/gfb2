package dev.benj.gfb2cliclient;

import dev.benj.gfb2cliclient.services.ApiClient;
import dev.benj.gfb2cliclient.services.security.AuthService;
import dev.benj.gfb2cliclient.services.security.LoginDto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.AccessDeniedException;
import java.util.Scanner;

@SpringBootApplication
public class Gfb2CliClientApplication implements CommandLineRunner {

	private Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		SpringApplication.run(Gfb2CliClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ApiClient apiClient = new ApiClient();
		AuthService authService = new AuthService();

		System.out.println("Hello!");
		System.out.println("Enter your username: ");

		String username = scanner.nextLine();
		System.out.println("Enter your password: ");
		String password = scanner.nextLine();
		try{
			LoginDto login = new LoginDto();
			login.setPassword(password);
			login.setUsername(username);
			authService.login(login);
			System.out.println("Login successful.");
		} catch (AccessDeniedException e){
			System.out.println(e.getMessage());
		}


	}
}
