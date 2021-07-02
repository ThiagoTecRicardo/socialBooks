package br.com.livros.socialbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.livros.socialbooks.domain.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long>{

}
