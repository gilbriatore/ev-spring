package br.edu.up.util;

import br.edu.up.model.Cliente;
import br.edu.up.repository.ClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Banco {

    private static final Logger log =
            LoggerFactory.getLogger(Banco.class);


    @Bean
    CommandLineRunner carregar(ClienteRepository repository) {
        return (args) -> {

            // Inclui alguns clientes
            repository.save(new Cliente("Carlos", "Silva"));
            repository.save(new Cliente("Pedro", "Siqueira"));

            // Lista todos os clientes
            log.info("Clientes listados com findAll();");
            log.info("--------------------------------");
            for (Cliente cliente : repository.findAll()) {
                log.info(cliente.toString());
            }
            log.info("");

            // Busca um cliente pelo ID
            log.info("Cliente buscado com findById(1L);");
            log.info("--------------------------------");
            Cliente clientePorId = repository.findById(1L);
            log.info(clientePorId.toString());
            log.info("");

            // Busca um cliente pelo ID
            log.info("Cliente buscado com findByNome('Pedro');");
            log.info("--------------------------------");
            repository.findByNome("Pedro").forEach(cliente -> log.info(cliente.toString()));
            log.info(clientePorId.toString());
            log.info("");
        };
    }
}
