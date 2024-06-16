package com.example.game_log_parser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class GameLogParserApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameLogParserApplication.class, args);
	}

}
