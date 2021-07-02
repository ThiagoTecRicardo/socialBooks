package br.com.livros.socialbooks.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.livros.socialbooks.domain.Autor;
import br.com.livros.socialbooks.repository.AutoresRepository;
import br.com.livros.socialbooks.services.AutoresServices;
import br.com.livros.socialbooks.services.exceptions.AutorNaoEncontradoException;

@RestController
@RequestMapping("/autores")
public class AutoresResources {
	
	@Autowired
	private AutoresServices autoresServices;
	
	@Autowired
	private AutoresRepository autoresRepository;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Autor>> listar(){
		
		List<Autor> autores = autoresServices.listar();
		
		return ResponseEntity.status(HttpStatus.OK).body(autores);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@Valid @RequestBody Autor autor) {
		
		autor = autoresServices.salvar(autor);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(autor.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Autor> buscar(@PathVariable Long id){
		
		Optional<Autor> autor = autoresRepository.findById(id);
		
		if(autor.isEmpty()) {
			throw new AutorNaoEncontradoException("mensagem");
		}
		
		return ResponseEntity.ok(autor.get());
		
	}
	

}
