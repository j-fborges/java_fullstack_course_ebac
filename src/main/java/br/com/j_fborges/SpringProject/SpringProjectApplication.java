package br.com.j_fborges.SpringProject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.j_fborges.domain.Consumer;
import br.com.j_fborges.repository.IConsumerRepository;


@Configuration
@EnableJpaRepositories(basePackages = "br.com.j_fborges.repository")
@EntityScan("br.com.j_fborges.*")  
@ComponentScan(basePackages = "br.com.j_fborges")
@SpringBootApplication
public class SpringProjectApplication implements CommandLineRunner {
	
	private static final Logger log = LoggerFactory.getLogger(SpringProjectApplication.class);
	
	@Autowired
	private IConsumerRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringProjectApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		log.info("StartApplication...");
		Consumer cliente = createConsumer();
		repository.save(cliente);
	}
	
	private Consumer createConsumer() {
		return Consumer.builder()
				.city("SP")
				.idNumber(12312312310L)
				.email("Teste@teste.com")
				.address("End")
				.state("SP")
				.name("Teste Spring Boot")
				.addressNumber(102030)
				.tel(10203040L)
				.build();
	}
}
