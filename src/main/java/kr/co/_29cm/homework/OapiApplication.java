package kr.co._29cm.homework;

import kr.co._29cm.homework.command.service.CommandService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OapiApplication implements CommandLineRunner {
	private final CommandService commandService;

	public OapiApplication(CommandService commandService) {
		this.commandService = commandService;
	}

	public static void main(String[] args) {
		SpringApplication.run(OapiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		commandService.process();
	}
}
