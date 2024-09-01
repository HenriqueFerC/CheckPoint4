package br.com.fiap.Checkpoint4.dto.autenticacao;

import jakarta.validation.constraints.NotBlank;

public record DadosLoginDto(@NotBlank
                            String login,
                            @NotBlank
                            String senha) {
}
