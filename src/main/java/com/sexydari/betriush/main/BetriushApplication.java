package com.sexydari.betriush.main;

import com.sexydari.betriush.mongodb.models.BettingCard;
import com.sexydari.betriush.mongodb.models.UserRole;
import com.sexydari.betriush.mongodb.repositories.UserRepository;
import com.sexydari.betriush.mongodb.repositories.UserRoleRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.sexydari.betriush.mongodb.repositories.BettingCardRepository;

import java.util.ArrayList;

// Specified Packages to be scanned
@SpringBootApplication(scanBasePackages = {"com.sexydari.betriush.main", "com.sexydari.betriush.mongodb.models", "com.sexydari.betriush.mongodb.repositories","com.sexydari.betriush.mongodb", "com.sexydari.betriush.auth", "com.sexydari.betriush.controllers"}, exclude = { SecurityAutoConfiguration.class })
@EnableMongoRepositories(basePackageClasses = {BettingCardRepository.class,  UserRepository.class, UserRoleRepository.class})
// Implements CommandLineRunner, to see what's going on
public class BetriushApplication implements CommandLineRunner{
	@Autowired
	public BettingCardRepository bettingCardRepository;

	public static void main(String[] args) {
		SpringApplication.run(BetriushApplication.class, args);
	}


	public void run(String...args) throws Exception{
		bettingCardRepository.save(new BettingCard(
				(new ObjectId("6405e1e6816213e3e62e8b50")), (new ObjectId("6505e1e6816213e3e62e8b52")), "Ana ZÜ wird gottlos", "Ja ihr wisst schon", new ArrayList<>(), true, "10.03.2023",true,new ArrayList<>() ));
		bettingCardRepository.save(new BettingCard(
				(new ObjectId("6405e1e6816213e3e62e8c50")), (new ObjectId("6505f1e6816213e3e62e8b52")), "Lin Alg ZÜ wird gottloser", "Ja ihr wisst schon", new ArrayList<>(), true, "11.03.2023",true,new ArrayList<>() ));

	}
	/*
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println("All the beans provided by SpringBoot: ");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}

		};
	}
	 */
}
