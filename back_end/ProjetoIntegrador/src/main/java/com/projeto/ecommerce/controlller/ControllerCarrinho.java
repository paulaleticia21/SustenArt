package com.projeto.ecommerce.controlller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.ecommerce.model.Carrinho;
import com.projeto.ecommerce.repository.RepositoryCarrinho;

@RestController
@RequestMapping("/carrinho")
public class ControllerCarrinho {
	
	@Autowired
	private RepositoryCarrinho repository;
	
	@PostMapping
	public Carrinho criar(@RequestBody Carrinho objetinho) {
		repository.save(objetinho);
		return objetinho;
	}
	
	@GetMapping 
	public ResponseEntity <List <Carrinho>> GetAll() {
		return ResponseEntity.ok (repository.findAll());
	}
	
	@GetMapping ("/getById/{id}")
	public ResponseEntity <Carrinho> GetById (@PathVariable Long id){	
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/getByTipo/{quantidade}")
	public ResponseEntity<List<Carrinho>> getByQuantidade ( @PathVariable int quantidade){
		return ResponseEntity.ok(repository.findAllByQuantidade(quantidade));
	}
	
	@PutMapping  ("/put/{id}")
	public Carrinho atualizar (@PathVariable Long id, @RequestBody Carrinho objetinho) {
		objetinho.setId_carrinho(id);
		repository.save(objetinho);
		return objetinho;
	}
	
	@DeleteMapping ("/delete/{id}")
	public String remover (@PathVariable Long id) {
		try {
			repository.deleteById(id);
		return "Deletado com sucesso !";
		} catch (Exception e) {
			return "Erro: " + e.getLocalizedMessage();			
		}
	
	}
}
