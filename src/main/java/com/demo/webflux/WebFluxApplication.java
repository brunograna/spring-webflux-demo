package com.demo.webflux;

import com.demo.webflux.domain.Product;
import com.demo.webflux.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;

import java.util.UUID;

@SpringBootApplication
public class WebFluxApplication implements CommandLineRunner {

	private final ProductRepository productRepository;

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
						.map(name -> new Product(UUID.randomUUID().toString(), name))
				)
				.flatMap(productRepository::save)
				.subscribe(System.out::println);
	}
}
