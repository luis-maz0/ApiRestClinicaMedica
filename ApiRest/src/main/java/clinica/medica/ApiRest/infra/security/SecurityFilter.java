package clinica.medica.ApiRest.infra.security;

import clinica.medica.ApiRest.domain.usuarios.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("Filtro llamado...");
        var token = request.getHeader("Authorization");
        if(token != null){
            token = token.replace("Bearer","");
            System.out.println("Token:" + token);
            System.out.println("TokenService: "+ tokenService.getSubject(token));
            var nombreUsuario = tokenService.getSubject(token);
            if(nombreUsuario != null){
                var usuario = usuarioRepository.findByLogin(nombreUsuario);
                //Forzar autenticacion
                var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request,response);
    }
}
