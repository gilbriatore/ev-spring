package br.edu.up.controller;

import br.edu.up.model.Cliente;
import br.edu.up.repository.ClienteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ClienteController {

    private final ClienteRepository repository;

    public ClienteController(ClienteRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/clientes")
    Iterable<Cliente> listar(){
      return repository.findAll();
    }

    @PostMapping("/clientes")
    Cliente incluir(@RequestBody Cliente novoCliente){
        return  repository.save(novoCliente);
    }

    @GetMapping("/clientes/{id}")
    Optional<Cliente> buscarPorId(@PathVariable Long id){
        return repository.findById(id);
    }

    @PutMapping("/clientes/{id}")
    Optional<Cliente> atualizar(@RequestBody Cliente novoCliente, @PathVariable Long id){
        return repository.findById(id).map(cliente -> {
           cliente.setNome(novoCliente.getNome());
           cliente.setSobrenome(novoCliente.getSobrenome());
           return repository.save(cliente);
        });
    }

    @DeleteMapping("/clientes/{id}")
    void excluir(@PathVariable Long id){
        repository.deleteById(id);
    }

}
