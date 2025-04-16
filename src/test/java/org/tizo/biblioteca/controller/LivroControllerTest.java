package org.tizo.biblioteca.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.tizo.biblioteca.exception.LivroNotFoundException;
import org.tizo.biblioteca.model.Livro;
import org.tizo.biblioteca.model.LivroUpdateRequest;
import org.tizo.biblioteca.service.LivroService;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LivroController.class)
public class LivroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LivroService livroService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void atualizarLivrosComDadosValidos() throws Exception {
        Long id = 1L;
        LivroUpdateRequest livroUpdate = new LivroUpdateRequest("titulo u","autor u");
        Livro livroResposta = new Livro(1L,"titulo u","autor u",2021,"978-85-7232-224-5");

        when(livroService.updateLivro(any(LivroUpdateRequest.class), eq(id))).thenReturn(livroResposta);

        mockMvc.perform(put("/livros/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(livroUpdate)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("titulo u"))
                .andExpect(jsonPath("$.autor").value("autor u"))
                .andExpect(jsonPath("$.anoPublicacao").value(2021))
                .andExpect(jsonPath("$.isbn").value("978-85-7232-224-5"));

        verify(livroService).updateLivro(any(LivroUpdateRequest.class), eq(id));

    }

    @Test
    public void atualizarLivrosComIdNaoExistente() throws Exception {
        Long id = 999L;
        LivroUpdateRequest livroUpdateRequest = new LivroUpdateRequest("titulo u","autor u");

        when(livroService.updateLivro(any(LivroUpdateRequest.class),eq(id))).thenThrow(new LivroNotFoundException(id));

        mockMvc.perform(put("/livros/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(livroUpdateRequest)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Livro 999 não encontrado"));
    }

    @Test
    public void atualizarLivrosComCamposInvalidos() throws Exception {
        Long id = 1L;
        LivroUpdateRequest livroUpdate = new LivroUpdateRequest("","");

        mockMvc.perform(put("/livros/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(livroUpdate)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors").isArray())
                .andExpect(jsonPath("$.errors[0]").value(containsString("titulo")));
    }

}
