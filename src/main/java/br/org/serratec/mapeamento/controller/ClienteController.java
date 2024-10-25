package br.org.serratec.mapeamento.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.mapeamento.domain.Cliente;
import br.org.serratec.mapeamento.repository.UsuarioRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private UsuarioRepository clienteRepository;
	
	@GetMapping
	public List<Cliente> listar(){
		return clienteRepository.findAll();
	}
	
	@GetMapping("/{id_cliente}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long id_cliente){
		Optional<Cliente> clienteOpt = clienteRepository.findById(id_cliente);
		if(clienteOpt.isPresent()) {
			return ResponseEntity.ok(clienteOpt.get());
		}
		return ResponseEntity.notFound().build();
	}	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente inserir(@Valid @RequestBody Cliente cliente) {
		clienteRepository.save(cliente);
		return cliente;
	}
	
	@PutMapping("/{id_cliente}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long id_cliente, @Valid @RequestBody Cliente cliente){
		if(clienteRepository.existsById(id_cliente)) {
			cliente.setId_cliente(id_cliente);
			cliente = clienteRepository.save(cliente);
			return ResponseEntity.ok(cliente);
		}
		
		return ResponseEntity.notFound().build();	
	}
	
	@DeleteMapping("/{id_cliente}")
	public ResponseEntity<Void> deletar(@PathVariable Long id_cliente){
		if(!clienteRepository.existsById(id_cliente)) {
			return ResponseEntity.notFound().build();
		}
		
		clienteRepository.deleteById(id_cliente);
		return ResponseEntity.noContent().build();
	}
	
}
