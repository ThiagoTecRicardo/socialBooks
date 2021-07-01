package br.com.livros.socialbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.livros.socialbooks.domain.Livro;

public interface LivrosRepository extends JpaRepository<Livro, Long>{

}
