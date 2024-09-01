package br.com.fiap.Checkpoint4.service;

import br.com.fiap.Checkpoint4.model.Tarefa;
import br.com.fiap.Checkpoint4.model.Usuario;
import br.com.fiap.Checkpoint4.repository.TarefaRepository;
import br.com.fiap.Checkpoint4.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class TarefaService {
    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Tarefa atualizarTarefa(Long idTarefa, Tarefa novaTarefa) {
        Usuario usuarioAutenticado = getUsuarioAutenticado();

        Tarefa tarefa = tarefaRepository.findById(idTarefa)
                .orElseThrow(() -> new IllegalArgumentException("Tarefa não encontrada"));

        if (!tarefa.getUsuario().getId().equals(usuarioAutenticado.getId())) {
            throw new SecurityException("Você não tem permissão para alterar esta tarefa");
        }

        tarefa.setDescricao(novaTarefa.getDescricao());
        tarefa.setDataPrevista(novaTarefa.getDataPrevista());
        return tarefaRepository.save(tarefa);
    }

    private Usuario getUsuarioAutenticado() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        return null;
    }
}
