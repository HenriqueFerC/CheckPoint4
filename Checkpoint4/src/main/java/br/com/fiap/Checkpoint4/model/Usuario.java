package br.com.fiap.Checkpoint4.model;

import br.com.fiap.Checkpoint4.dto.usuario.CadastrarUsuarioDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter@Setter@NoArgsConstructor

@Entity
@Table(name = "TB_USUARIO")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue
    @Column(name = "ID_USUARIO")
    private Long id;

    @Column(name = "NOME_USUARIO", nullable = false, length = 50)
    private String nome;

    @Column(name = "EMAIL_USUARIO", length = 100, nullable = false, unique = true)
    private String login;

    @Column(name = "SENHA_USUARIO", nullable = false)
    private String senha;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Tarefa> tarefas;

    public Usuario(String nome, String login, String senha) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }

    public void adicionarTarefa(Tarefa tarefa){
        tarefas.add(tarefa);
    }

    public void removerTarefa(Tarefa tarefa){
        tarefas.remove(tarefa);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }
    @Override
    public String getPassword() {
        return senha;
    }
    @Override
    public String getUsername() {
        return login;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}
