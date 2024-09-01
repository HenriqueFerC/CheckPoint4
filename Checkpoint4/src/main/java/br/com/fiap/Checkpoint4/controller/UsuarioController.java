package br.com.fiap.Checkpoint4.controller;

import br.com.fiap.Checkpoint4.dto.tarefa.CadastrarTarefaDto;
import br.com.fiap.Checkpoint4.dto.tarefa.DetalhesTarefaDto;
import br.com.fiap.Checkpoint4.dto.usuario.CadastrarUsuarioDto;
import br.com.fiap.Checkpoint4.dto.usuario.DetalhesUsuarioDto;
import br.com.fiap.Checkpoint4.model.Tarefa;
import br.com.fiap.Checkpoint4.model.Usuario;
import br.com.fiap.Checkpoint4.repository.TarefaRepository;
import br.com.fiap.Checkpoint4.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("auth")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("register")
    @Transactional
    public ResponseEntity<DetalhesUsuarioDto> registerUser(@RequestBody @Valid CadastrarUsuarioDto usuarioDto, UriComponentsBuilder uriBuilder){
        var usuario = new Usuario(usuarioDto.nome(), usuarioDto.login(), passwordEncoder.encode(usuarioDto.senha()));
        usuarioRepository.save(usuario);
        var url = uriBuilder.path("user/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(url).body(new DetalhesUsuarioDto(usuario));
    }


    @PostMapping("{id}/tasks")
    @Transactional
    public ResponseEntity<DetalhesTarefaDto> cadastrarTarefa(@PathVariable("id") Long id, @RequestBody @Valid CadastrarTarefaDto tarefaDto, UriComponentsBuilder uriBuilder){
        var usuario = usuarioRepository.getReferenceById(id);
        var tarefa = new Tarefa(tarefaDto, usuario);
        usuario.adicionarTarefa(tarefa);
        tarefaRepository.save(tarefa);
        var url = uriBuilder.path("tasks/{id}").buildAndExpand(tarefa.getId()).toUri();
        return ResponseEntity.created(url).body(new DetalhesTarefaDto(tarefa));
    }

}
