package com.johnnyparra.real_time_chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import foo.bar.WelcomeMessage;

@SpringBootApplication
public class RealTimeChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(RealTimeChatApplication.class, args);

		var welcomeMessage = new WelcomeMessage();
		System.out.println(welcomeMessage.getWelcomeMessage());
	}

}
