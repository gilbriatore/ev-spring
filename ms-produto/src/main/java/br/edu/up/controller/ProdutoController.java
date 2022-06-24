package br.edu.up.controller;

import br.edu.up.model.Produto;
import br.edu.up.repository.ProdutoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProdutoController {

    private final ProdutoRepository repository;

    public ProdutoController(ProdutoRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/produtos")
    List<Produto> listar(){
        return repository.findAll();
    }

    @PostMapping("/produtos")
    Produto incluir(@RequestBody Produto novoProduto){
        return repository.save(novoProduto);
    }

    @GetMapping("/produtos/{id}")
    Optional<Produto> buscarPorId(@PathVariable Long id){
        return repository.findById(id);
    }

    @PutMapping("/produtos/{id}")
    Optional<Produto> atualizar(@RequestBody Produto novoProduto,
                                @PathVariable Long id) {
        return repository.findById(id).map(produto -> {
            produto.setNome(novoProduto.getNome());
            produto.setPreco(novoProduto.getPreco());
            return repository.save(produto);
        });
    }

    @DeleteMapping("/produtos/{id}")
    void excluir(@PathVariable Long id){
        repository.deleteById(id);
    }

}
