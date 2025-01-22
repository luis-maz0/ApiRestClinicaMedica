package clinica.medica.ApiRest.domain.paciente;

import clinica.medica.ApiRest.domain.direccion.DatosDireccion;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;


public record DatosActualizacionPaciente(
        Long id,
        String nombre,
        @Email String email,
        String telefono,
        String documentoIdentidad,
        @Valid DatosDireccion direccion
) {
}