package br.com.livros.socialbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.livros.socialbooks.domain.Autor;

public interface AutoresRepository extends JpaRepository<Autor, Long>{

}
