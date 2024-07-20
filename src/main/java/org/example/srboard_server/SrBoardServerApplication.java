package org.example.srboard_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@SpringBootApplication
public class SrBoardServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SrBoardServerApplication.class, args);
	}
}
