package org.tizo.biblioteca.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Livro  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O título não pode ser nulo")
    @NotBlank(message = "O título não pode estar vazio")
    @Size(min = 2, max = 100, message = "O titulo deve ter entre 2 e 100 caracteres")
    private String titulo;

    @NotNull(message = "O nome do autor não pode ser nulo")
    @NotBlank(message = "O nome do autor não pode estar vazio")
    @Size(max = 50, message = "O nome do autor deve ter no máximo 50 caracteres")
    private String autor;

    @NotNull(message = "O ano de publicação não pode ser nulo")
    @Min(value = 1000, message = "O ano mínimo é 1000")
    @Max(value = 9999, message = "O ano máximo é 9999")
    private Integer anoPublicacao;

    @Pattern(regexp = "^(?:ISBN(?:-1[03])?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$|97[89][0-9]{10}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)(?:97[89][- ]?)?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$", message = "Formato inválido")
    private String isbn;
}
