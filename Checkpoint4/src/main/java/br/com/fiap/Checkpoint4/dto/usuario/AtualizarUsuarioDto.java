package br.com.fiap.Checkpoint4.dto.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AtualizarUsuarioDto(
        @NotBlank(message = "Nome não pode estar em branco!")
        String nome,
        @Email(message = "Coloque um endereço de e-mail válido!")
        String login,
        @NotBlank(message = "Senha não pode estar em branco!")
        String senha
) {
}
