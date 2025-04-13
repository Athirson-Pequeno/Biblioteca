package org.tizo.biblioteca.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tizo.biblioteca.model.Livro;
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
}
