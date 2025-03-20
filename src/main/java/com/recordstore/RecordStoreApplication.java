package com.recordstore;

import java.io.IOException;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.recordstore.auxiliar.PrintFlag;


@SpringBootApplication
@EnableTransactionManagement
public class RecordStoreApplication implements CommandLineRunner {

	//*										MAIN										*/
	public static void main(String[] args) throws IOException {
		SpringApplication.run(RecordStoreApplication.class, args);
		PrintFlag.flag("Impreso desde MAIN");

		

	}






	
	@Override
	//*										RUN										*/
	public void run(String... args) throws Exception {
		PrintFlag.flag("Impreso desde RUN");

		// dbSeeder dbSeeder = new dbSeeder(userRepository, orderRepository, orderProductRepository, wishlistRepository,
		// 		wishlistProductRepository, albumRepository, vinylRepository, headphonesRepository, speakerRepository,
		// 		portableRepository, playerRepository, turntableRepository, productRepository);
				
		// dbSeeder.allSeeder();

}
}