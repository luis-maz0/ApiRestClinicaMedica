package clinica.medica.ApiRest.medico;

import clinica.medica.ApiRest.direccion.Direccion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Entity()
@Table(name = "medicos")

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private String documento;
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "calle", column = @Column(name = "calle")),
            @AttributeOverride(name = "ciudad", column = @Column(name = "ciudad"))
    })
    private Direccion direccion;

    public Medico(DatosRegistroMedico dataRegistroMedico) {
        this.nombre = dataRegistroMedico.nombre();
        this.email = dataRegistroMedico.email();
        this.documento = dataRegistroMedico.documento();
        this.especialidad = dataRegistroMedico.especialidad();
        this.direccion = new Direccion( dataRegistroMedico.direccion());
        this.telefono = dataRegistroMedico.telefono();
    }
}
