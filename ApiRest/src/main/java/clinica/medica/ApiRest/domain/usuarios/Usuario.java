package clinica.medica.ApiRest.domain.usuarios;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity(name = "usuario")
@Table(name = "usuarios")
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String clave;

    public Usuario() {
    }

    public Usuario(Long id, String login, String contrasenia) {
        this.id = id;
        this.login = login;
        this.clave = contrasenia;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getClave() {
        return clave;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return clave;
    }

    @Override
    public String getUsername() {
        return login;
    }
}
