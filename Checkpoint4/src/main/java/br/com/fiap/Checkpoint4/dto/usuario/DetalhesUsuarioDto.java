package br.com.fiap.Checkpoint4.dto.usuario;

import br.com.fiap.Checkpoint4.model.Usuario;

public record DetalhesUsuarioDto(String nome, String login) {
    public DetalhesUsuarioDto(Usuario usuario){
        this(usuario.getNome(), usuario.getLogin());
    }
}
