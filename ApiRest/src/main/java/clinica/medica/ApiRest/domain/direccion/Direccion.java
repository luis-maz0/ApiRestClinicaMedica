package clinica.medica.ApiRest.domain.direccion;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Embeddable
public class Direccion {
    @NotBlank
    private String calle;
    @NotBlank
    private String ciudad;

    public Direccion(){}
    public Direccion(DatosDireccion direccion) {
        this.calle = direccion.calle();
        this.ciudad = direccion.ciudad();
    }

    public @NotBlank String getCalle() {
        return calle;
    }

    public @NotBlank String getCiudad() {
        return ciudad;
    }

    public Direccion actualizarDireccion(DatosDireccion direccion) {
        if(direccion.ciudad() != null){
            this.ciudad = direccion.ciudad();
        }
        if( direccion.calle()!= null){
            this.calle = direccion.calle();
        }
        return this;
    }
}
