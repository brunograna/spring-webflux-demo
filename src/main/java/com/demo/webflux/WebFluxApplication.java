package com.demo.webflux;

import com.demo.webflux.domain.Product;
import com.demo.webflux.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;

import java.util.UUID;

@SpringBootApplication
public class WebFluxApplication implements CommandLineRunner {

	private final ProductRepository productRepository;
	private static final Logger logger = LoggerFactory.getLogger(WebFluxApplication.class);

	public WebFluxApplication(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(WebFluxApplication.class, args);
	}

	@Override
	public void run(String... args) {
		this.productRepository.deleteAll()
				.thenMany(
						Flux.just("Computer", "Cellphone", "Notebook", "Charger", "Monitor", "TV")
						.map(name -> new Product(UUID.randomUUID().toString(), name, 1))
				)
				.flatMap(productRepository::save)
				.subscribe(product -> logger.info("Saving on database: {}", product));
	}
}
