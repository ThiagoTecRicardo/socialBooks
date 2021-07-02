package br.com.livros.socialbooks.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.livros.socialbooks.domain.Autor;
import br.com.livros.socialbooks.services.AutoresServices;

@RestController
@RequestMapping("/autores")
public class AutoresResources {
	
	@Autowired
	private AutoresServices autoresServices;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Autor>> listar(){
		
		List<Autor> autores = autoresServices.listar();
		
		return ResponseEntity.status(HttpStatus.OK).body(autores);
	}

}
