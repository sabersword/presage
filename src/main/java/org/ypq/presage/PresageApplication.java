package org.ypq.presage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.ypq")
public class PresageApplication {

	public static void main(String[] args) {
		SpringApplication.run(PresageApplication.class, args);
	}

}

