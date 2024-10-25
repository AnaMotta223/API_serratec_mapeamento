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

import br.org.serratec.mapeamento.domain.Produto;
import br.org.serratec.mapeamento.repository.ProdutoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	//spring gerencia a instancia do ProdutoRepository (injestao de dependencia -> repository no controller)
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping
	public List<Produto> listar(){
		//findAll vem do jpaRepository, que puxa comandos do banco
		//funciona como um select
		return produtoRepository.findAll();
	}
	
	//responseentity define a classe como de resposta p/ auxiliar a retornar respostas http
	@GetMapping("/{id}")
	public ResponseEntity<Produto> pesquisar(@PathVariable Long id) {
		//optional é uma "caixa" q pode conter o produto do id especificado ou não - retorna nulo
		Optional<Produto> produtoOptional = produtoRepository.findById(id);
		if (produtoOptional.isPresent()) {
			return ResponseEntity.ok(produtoOptional.get());
		}
		//se n encontrar o produto define o status como não encontrado e define o corpo da requisição
		return ResponseEntity.notFound().build();
	}
	
	//valid valida o body da requisicao
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Produto inserir(@Valid @RequestBody Produto produto) {
		produto = produtoRepository.save(produto);
		return produto;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Produto> atualizar(@PathVariable Long id, @Valid @RequestBody Produto produto){
		if (!produtoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		//seta o id pra poder ser usado no save
		//se o id nao tem registrado, é feito update, se não é feito create 
		produto.setId(id);
		produto = produtoRepository.save(produto);
		return ResponseEntity.ok(produto);
		
		
		//Optional<Produto> produtoOpt = produtoRepoisitory.findById(id);
		//if (produtoOpt.isEmpty()) {
		//return ResponseEntity.notFound().build();
		//}
//		Produto produtoDB = produtoOpt.get();
//		produtoDB.setDataCadastro(produto.getDataCadastro());
//		produtoDB.setDescricao(produto.getDescricao());
//		produtoDB.setValor(produto.getValor());
//		
//		produto = produtoRepository.save(produtoDB);
//		return ResponseEntity.ok(produto);
		
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		if (!produtoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		produtoRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
