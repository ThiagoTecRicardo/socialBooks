package br.com.livros.socialbooks.resources;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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
	public void save(@RequestBody Livro livro) {

		livrosRepository.save(livro);

	}
	
	@RequestMapping(value = "/{id}" , method = RequestMethod.GET)
	public ResponseEntity<Livro> buscar(@PathVariable Long id) {
		
		Optional<Livro> livro = livrosRepository.findById(id);
		
		return ResponseEntity.ok(livro.get());
	}

}
