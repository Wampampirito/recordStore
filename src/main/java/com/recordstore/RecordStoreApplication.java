package com.recordstore;

import java.io.IOException;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.recordstore.auxiliar.PrintFlag;

/**
 * Main class that runs the Spring Boot application.
 */
@SpringBootApplication
@EnableTransactionManagement
public class RecordStoreApplication implements CommandLineRunner {
	public static void main(String[] args) throws IOException {
		SpringApplication.run(RecordStoreApplication.class, args);

	}
	/**
	 * Method that runs the application.
	 */
	@Override
	public void run(String... args) throws Exception {
		// Print a flag to indicate that the application has started.
		PrintFlag.flag("Impreso desde RUN");
}
}