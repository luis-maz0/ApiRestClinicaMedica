package clinica.medica.ApiRest.direccion;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@Embeddable
public class Direccion {
    private String calle;
    private String ciudad;

    public Direccion(){}

    public Direccion(DatosDireccion direccion) {
        this.calle = direccion.calle();
        this.ciudad = direccion.ciudad();
    }
}
