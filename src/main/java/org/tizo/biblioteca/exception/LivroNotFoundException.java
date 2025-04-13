package org.tizo.biblioteca.exception;

public class LivroNotFoundException extends RuntimeException{
    public LivroNotFoundException(Long id) {
        super(String.format("Livro %d não encontrado", id));
    }
    public LivroNotFoundException(String message) {
        super(message);
    }
}
