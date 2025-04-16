package org.tizo.biblioteca.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tizo.biblioteca.exception.LivroNotFoundException;
import org.tizo.biblioteca.model.Livro;
import org.tizo.biblioteca.model.LivroUpdateRequest;
import org.tizo.biblioteca.repository.LivroRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository livroRepository;

    public List<Livro> getLivros() {
        return livroRepository.findAll();
    }

    @Transactional
    public Livro createLivro(Livro livro) {
        return livroRepository.save(livro);
    }

    @Transactional
    public Livro updateLivro(LivroUpdateRequest livro, Long id) {
        Livro livroDB = livroRepository.findById(id).orElseThrow(() -> new LivroNotFoundException(id));
        livroDB.setTitulo(livro.titulo());
        livroDB.setAutor(livro.autor());
        return livroRepository.save(livroDB);
    }
}
