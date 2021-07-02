package br.com.livros.socialbooks.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.livros.socialbooks.domain.Autor;
import br.com.livros.socialbooks.repository.AutoresRepository;

@Service
public class AutoresServices {
	
	@Autowired
	private AutoresRepository autoresRepository;
	
	public List<Autor> listar(){
		
		return autoresRepository.findAll();
	}

}
