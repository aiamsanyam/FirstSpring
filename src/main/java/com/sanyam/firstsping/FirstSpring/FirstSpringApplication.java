package com.sanyam.firstsping.FirstSpring;

import com.sanyam.firstsping.FirstSpring.model.WebhookRequest;
import com.sanyam.firstsping.FirstSpring.model.WebhookResponse;
import com.sanyam.firstsping.FirstSpring.service.SqlSolutionService;
import com.sanyam.firstsping.FirstSpring.service.WebhookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FirstSpringApplication {

	@Autowired
	private WebhookService webhookService;

	@Autowired
	private SqlSolutionService sqlSolutionService;

	public static void main(String[] args) {
		SpringApplication.run(FirstSpringApplication.class, args);
	}

	@Bean
	public CommandLineRunner runAtStartup() {
		return args -> {
			WebhookRequest request = new WebhookRequest(
					"John Doe",
					"REG12347",
					"john@example.com"
			);

			WebhookResponse response = webhookService.generateWebhook(request);
			System.out.println("Webhook URL: " + response.getWebhook());
			System.out.println("Access Token: " + response.getAccessToken());

			String sqlSolution = sqlSolutionService.solveSqlProblem(request.getRegNo());
			System.out.println("SQL Solution: " + sqlSolution);

			webhookService.submitSolution(response.getWebhook(), response.getAccessToken(), sqlSolution);
			System.out.println("Solution submitted successfully!");
		};
	}
}
