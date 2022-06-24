package br.edu.up.util;

import br.edu.up.model.Produto;
import br.edu.up.repository.ProdutoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Banco {

    private static final Logger log = LoggerFactory.getLogger(Banco.class);

    @Bean
    CommandLineRunner carregar(ProdutoRepository repository){
        return arg -> {
            // Inclui alguns produtos
            repository.save(new Produto("Arroz", 10.0));
            repository.save(new Produto("Carne", 25.0));

            // Lista todos os produtos
            log.info("Produtos listados com findAll();");
            log.info("--------------------------------");
            for (Produto produto : repository.findAll()) {
                log.info(produto.toString());
            }
            log.info("");

            // Busca um produto pelo ID
            log.info("Produto buscado com findById(1L);");
            log.info("--------------------------------");
            Produto produtoPorId = repository.findById(1L);
            log.info(produtoPorId.toString());
            log.info("");

            // Busca um produto pelo Nome
            log.info("Produto encontrado com findByNome('Arroz');");
            log.info("--------------------------------");
            repository.findByNome("Arroz").forEach(produto -> log.info(produto.toString()));
            log.info("");
        };
    }
}
