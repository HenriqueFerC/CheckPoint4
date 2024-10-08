package br.com.fiap.Checkpoint4.security;

import br.com.fiap.Checkpoint4.repository.UsuarioRepository;
import br.com.fiap.Checkpoint4.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{
        String tokenJwt = request.getHeader("Authorization");
        if (tokenJwt != null){
            tokenJwt = tokenJwt.replace("Bearer ", "");

            String login = tokenService.getSubject(tokenJwt);

            UserDetails usuario = usuarioRepository.findByLogin(login);

            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

}
