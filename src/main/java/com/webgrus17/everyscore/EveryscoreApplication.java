package com.webgrus17.everyscore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // JPA Auditing 어노테이션 활성화 위함
@SpringBootApplication
public class EveryscoreApplication {
	public static void main(String[] args) {
		SpringApplication.run(EveryscoreApplication.class, args);
	}

}
