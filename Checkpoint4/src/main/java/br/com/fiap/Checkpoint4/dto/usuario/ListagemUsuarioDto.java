package br.com.fiap.Checkpoint4.dto.usuario;

import br.com.fiap.Checkpoint4.model.Usuario;

public record ListagemUsuarioDto(String nome, String login) {
    public ListagemUsuarioDto(Usuario usuario){
        this(usuario.getNome(), usuario.getLogin());
    }
}
