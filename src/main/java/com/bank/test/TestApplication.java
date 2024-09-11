package com.bank.test;

import com.bank.test.services.dto.TransferRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
		TransferRequest t = new TransferRequest("1231231233", "1231231233", BigDecimal.valueOf(20), LocalDate.now());
		System.out.println(t);
	}

}
