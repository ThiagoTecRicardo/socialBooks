package br.com.livros.socialbooks.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.livros.socialbooks.domain.Livro;
import br.com.livros.socialbooks.repository.LivrosRepository;


@RestController
@RequestMapping("/livros")
public class LivrosResources {

	@Autowired
	private LivrosRepository livrosRepository;

	@RequestMapping(method = RequestMethod.GET)
	public List<Livro> listar() {

		return livrosRepository.findAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@RequestBody Livro livro) {
		
		livro = livrosRepository.save(livro);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(livro.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}" , method = RequestMethod.GET)
	public ResponseEntity<Livro> buscar(@PathVariable Long id) {
		
		Optional<Livro> livro = livrosRepository.findById(id);
		
		if(livro.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(livro.get());
	}
	
	@RequestMapping(value = "/{id}" , method = RequestMethod.DELETE)
	public void deletar(@PathVariable Long id) {
		
		livrosRepository.deleteById(id);	
		
	}
	
	@RequestMapping(value = "/{id}" , method = RequestMethod.PUT)
	public void atualizar(@RequestBody Livro livro, @PathVariable Long id ) {
		
		livro.setId(id);
		
		livrosRepository.save(livro);
		
	}

}
