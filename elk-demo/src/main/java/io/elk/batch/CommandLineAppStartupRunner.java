package io.elk.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import io.elk.service.StarterService;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner{

	@Autowired
	private StarterService starterService;
	
	@Override
	public void run(String... args) throws Exception {
		
		starterService.welcome();
		
	}

}
