package br.com.livros.socialbooks.services;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import br.com.livros.socialbooks.domain.Comentario;
import br.com.livros.socialbooks.domain.Livro;
import br.com.livros.socialbooks.repository.ComentarioRepository;
import br.com.livros.socialbooks.repository.LivrosRepository;
import br.com.livros.socialbooks.services.exceptions.LivroNaoEncontradoException;

@Service
public class LivrosService {

	@Autowired
	private LivrosRepository livrosRepository;

	@Autowired
	private ComentarioRepository comentarioRepository;

	public List<Livro> listar() {

		return livrosRepository.findAll();

	}

	public Livro salvar(Livro livro) {
		livro.setId(null);
		return livrosRepository.save(livro);
	}

	public void deletar(Long id) {
		try {
			livrosRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new LivroNaoEncontradoException("O livro não pôde ser encontrado.");
		}
	}

	public void atualizar(Livro livro) {

		verificarExistencia(livro.getId());

		if (verificarExistencia(livro.getId()) == null) {

			throw new LivroNaoEncontradoException("O livro não pôde ser encontrado.");

		}

		livrosRepository.save(livro);
	}

	private Livro verificarExistencia(Long id) {

		return livrosRepository.getById(id);
	}

	public Comentario salvarComenterio(Long livroId, Comentario comentario) {

		Livro livro = livrosRepository.getById(livroId);

		comentario.setLivro(livro);
		comentario.setData(new Date());
		return comentarioRepository.save(comentario);

	}

	public List<Comentario> listarComentarios(Long livroId) {
		Livro livro = livrosRepository.getById(livroId);

		return livro.getComentarios();

	}

}
