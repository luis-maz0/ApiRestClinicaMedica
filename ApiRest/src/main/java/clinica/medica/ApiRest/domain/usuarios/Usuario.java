package clinica.medica.ApiRest.domain.usuarios;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

@Entity(name = "usuario")
@Table(name = "usuarios")
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String contrasenia;

    public Usuario() {
    }

    public Usuario(Long id, String login, String contrasenia) {
        this.id = id;
        this.login = login;
        this.contrasenia = contrasenia;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getContrasenia() {
        return contrasenia;
    }
}
