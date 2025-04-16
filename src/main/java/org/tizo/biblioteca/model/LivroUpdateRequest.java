package org.tizo.biblioteca.model;

import jakarta.validation.constraints.NotBlank;

public record LivroUpdateRequest(@NotBlank String titulo,@NotBlank String autor) {
}
