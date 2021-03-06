package fja.edu.br.legal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "fja.edu.br.legal.controller")
public class Startup {

	public static void main(String[] args) {
		SpringApplication.run(Startup.class, args);
	}
	
}

//fja.edu.br.legal.controller