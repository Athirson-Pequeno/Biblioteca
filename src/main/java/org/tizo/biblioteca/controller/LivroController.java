package org.tizo.biblioteca.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tizo.biblioteca.model.Livro;
import org.tizo.biblioteca.service.LivroService;

import java.util.List;

@RestController
@RequestMapping(value = "/livros")
@RequiredArgsConstructor
public class LivroController {

    private final LivroService livroService;

    @GetMapping
    public ResponseEntity<List<Livro>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(livroService.getLivros());
    }

    @PostMapping
    public ResponseEntity<Livro> createLivro(@Valid @RequestBody Livro livro) {
        return ResponseEntity.status(HttpStatus.CREATED).body(livroService.createLivro(livro));
    }
}
